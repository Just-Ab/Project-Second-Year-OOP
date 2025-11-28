package Game.Core;

import Physics.Body;
import Physics.PhysicsBody;

public abstract class PhysicsBody2D extends Body2D{
    
    protected PhysicsBody physicsbody=null;

    PhysicsBody2D(String _name){
        super(_name);
    }

    public abstract Body getBodyResource();
    public abstract void setBodyResource(Body body);

}
