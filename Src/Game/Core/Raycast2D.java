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
    float length=0,angle=0;

    public Raycast2D(String _name){
        super(_name);
    }


    public void setLength(float _length){
        length = _length;
        setLine();
    }

    public void setAngleRad(float _angle){
        angle = _angle;
        setLine();
    }
    public void setAngleDeg(float _angle){
        angle = _angle*(float)Math.PI/180;;
        setLine();
    }

    private void setLine(){
                lineResource.setPoints(                
                    new float[]{
                    getGlobalPosition().x,getGlobalPosition().y,0.0f,
                    getGlobalPosition().x+length*Math.cos(angle),getGlobalPosition().y+length*Math.sin(angle),0.0f
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

    public void _update(float _delta){
        if(raycastResource!=null){
            raycastResource.setAngleRad(angle);
            raycastResource.setLength(length);
            raycastResource.setPosition(getGlobalPosition());
        }
    }

    public void _enterTree(){
        if(raycastResource==null){
            raycastResource = PhysicsServer.getSingleton().createRaycast(getGlobalPosition(), length, angle);
        }
        if(lineResource==null){
            lineResource = RenderingServer.getSingleton().createLine(
                new float[]{
                    getGlobalPosition().x,getGlobalPosition().y,0.0f,
                    getGlobalPosition().x+length*Math.cos(angle),getGlobalPosition().y+length*Math.sin(angle),0.0f
                }
            );
            lineResource.setColor(new Vector3f(0.1f,0.3f,0.4f));
            lineResource.setThickness(10);
        }
    }


}
