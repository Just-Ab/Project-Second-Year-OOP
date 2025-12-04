package Game.Physics.Nodes;

import Physics.Body;
import Physics.PhysicsBody;

public abstract class PhysicsBody2D extends Body2D{
    
    protected PhysicsBody physicsbody=null;

    PhysicsBody2D(){}

    public abstract Body getBodyResource();
    public abstract void setBodyResource(Body body);

}
