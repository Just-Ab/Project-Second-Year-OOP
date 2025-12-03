package Game.Core;

import org.joml.*;
import org.joml.Math;

import Physics.PhysicsServer;
import Physics.Raycast;
import Rendering.LineInstance;
import Rendering.RenderingServer;

public class Raycast2D extends Node2D{

    Raycast raycastResource=null;
    LineInstance lineResource=null;
    float length=0;

    public Raycast2D(String _name){
        super(_name);
    }


    public void setLength(float _length){
        length = _length;
        setLine();
    }

    @Override
    public void setLocalRotationRad(float _rotation) {
        super.setLocalRotationRad(_rotation);
        setLine();
    }

    @Override
    public void setLocalRotationDegrees(float _rotation) {
        super.setLocalRotationDegrees(_rotation);
        setLine();
    }

    private void setLine(){
        Vector3f position = getGlobalPosition();
        float rotation = getGlobalRotation();

        lineResource.setPoints(                
            new float[]{
            position.x,position.y,0.0f,
            position.x+length*Math.cos(rotation),position.y+length*Math.sin(rotation),0.0f
        });
    }

    public Body2D getCollider(){
        if(raycastResource!=null){
            return raycastResource.getCollider().getOwner();
        }
        return null;
    }

    public boolean isColliding(){
        if(raycastResource!=null){
            return raycastResource.isColliding();
        }
        return false;
    }

    public Vector3f getHitPosition(){
        if(raycastResource!=null){
            return raycastResource.getHitPosition();
        }
        return null;
    }

    public float getCollisionDistance(){
        if(raycastResource!=null){
            return raycastResource.getCollisionDistance();
        }
        return -1;
    }

    @Override
    public void _update(float _delta){
        if(raycastResource!=null){
            raycastResource.setAngleRad(getGlobalRotation());
            raycastResource.setLength(length);
            raycastResource.setPosition(getGlobalPosition());
        }
    }

    @Override
    protected void _enterTree(){
        if(raycastResource==null){
            raycastResource = PhysicsServer.getSingleton().createRaycast(getGlobalPosition(), length, getGlobalRotation());
        }
        if(lineResource==null){
            lineResource = RenderingServer.getSingleton().createLine(
                new float[]{
                    getGlobalPosition().x,getGlobalPosition().y,0.0f,
                    getGlobalPosition().x+length*Math.cos(getGlobalRotation()),getGlobalPosition().y+length*Math.sin(getGlobalRotation()),0.0f
                }
            );
            lineResource.setColor(new Vector3f(0.1f,0.3f,0.4f));
            lineResource.setThickness(10);
        }
    }


}
