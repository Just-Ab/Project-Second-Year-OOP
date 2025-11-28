package Game.Core;

import org.joml.*;

public class Node2D extends Node{
    Vector3f position=new Vector3f();


    public Node2D(String _name){
        super(_name);
    }
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
    
}
