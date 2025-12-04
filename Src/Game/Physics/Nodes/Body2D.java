package Game.Physics.Nodes;

import Game.Core.Node2D;
import Physics.Body;

public abstract class Body2D extends Node2D{
    
    
    public Body2D(){}
    
    public abstract Body getBodyResource();
    public abstract void setBodyResource(Body body);

}
