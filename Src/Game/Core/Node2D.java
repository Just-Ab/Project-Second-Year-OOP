package Game.Core;

import org.joml.*;
import org.joml.Math;

public class Node2D extends Node{
    protected Vector3f position = new Vector3f();
    protected Vector3f scale = new Vector3f(1.0f);
    protected float rotation = 0.0f;


    public Node2D(){}
    
    public Vector3f getLocalPosition(){
        return new Vector3f(position);
    }

    public Vector3f getGlobalPosition(){
        Node parentNode = getParent();
        if (parentNode instanceof Node2D parent2D){
            return new Vector3f(parent2D.getGlobalPosition()).add(position);
        }
        return new Vector3f(position);
    }

    public void setLocalPosition(Vector3f _position){
        position.set(_position);
    }
   
    public float getLocalRotation(){
        return rotation;
    }

    public float getGlobalRotation(){
        Node parentNode = getParent();
        if (parentNode instanceof Node2D parent2D){
            return parent2D.getGlobalRotation() + rotation;
        }
        return rotation;
    }

    public void setLocalRotationRad(float _rotation){
        rotation = _rotation;
    }
    
    public void setLocalRotationDegrees(float _rotation){
        rotation = (_rotation*(float)Math.PI)/180;
    }

    public Vector3f getLocalScale(){
        return new Vector3f(scale);
    }

    public Vector3f getGlobalScale(){
        Node parentNode = getParent();
        if (parentNode instanceof Node2D parent2D){
            return new Vector3f(parent2D.getGlobalScale()).mul(scale);
        }
        return new Vector3f(scale);
    }

    public void setLocalScale(Vector3f _scale){
        scale.set(_scale);
    }
    

}
