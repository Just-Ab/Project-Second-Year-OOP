package Game.Core;

import org.joml.*;
import org.joml.Math;

public class Node2D extends Node{
    protected Vector3f position = new Vector3f(0.0f);
    protected Vector3f scale = new Vector3f(1.0f);
    protected float rotation = 0.0f;
    protected boolean visiblity=true;

    private Vector3f globalPosition=new Vector3f(),prevGlobalPosition= new Vector3f();

    public Node2D(){}

    

    public boolean isVisible(){
        return visiblity;
    }

    public void setVisibility(boolean _visiblity){
        visiblity=_visiblity;
    }



    public Vector3f getGlobalPosition(){
        return new Vector3f(globalPosition);
    }


    public Vector3f getLocalPosition(){
        return new Vector3f(position);
    }

    public void setLocalPosition(Vector3f _position){
        position.set(_position);
    }

    protected void setLocalPositionInternal(Vector3f _position){
        position.set(_position);
    }
    
    @Override
    protected void updateEngine(float _delta) {

        prevGlobalPosition.set(globalPosition);

        Node parentNode = getParent();
        if (parentNode instanceof Node2D parent2D) {
            globalPosition
                .set(parent2D.globalPosition)
                .add(position);
        } else {
            globalPosition.set(position);
        }

        if (!globalPosition.equals(prevGlobalPosition)) {
            _onGlobalPositionChanged();
        }
    }


    public float getGlobalRotation(){
        Node parentNode = getParent();
        if (parentNode instanceof Node2D parent2D){
            return parent2D.getGlobalRotation() + rotation;
        }
        return rotation;
    }

    public float getLocalRotation(){
        return rotation;
    }

    public void setLocalRotationRad(float _rotation){
        rotation = _rotation;
    }
    
    public void setLocalRotationDegrees(float _rotation){
        rotation = (_rotation*(float)Math.PI)/180;
    }

    public void _onGlobalPositionChanged(){

    }

    public Vector3f getGlobalScale(){
        Node parentNode = getParent();
        if (parentNode instanceof Node2D parent2D){
            return new Vector3f(parent2D.getGlobalScale()).mul(scale);
        }
        return new Vector3f(scale);
    }

    public Vector3f getLocalScale(){
        return new Vector3f(scale);
    }

    public void setLocalScale(Vector3f _scale){
        scale.set(_scale);
    }
}
