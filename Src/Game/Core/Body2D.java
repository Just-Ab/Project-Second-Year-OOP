package Game.Core;

import Physics.Body;

public abstract class Body2D extends Node2D{
    
    
    public Body2D(String _name){
        super(_name);
    }
    
    public abstract Body getBodyResource();
    public abstract void setBodyResource(Body body);

}
