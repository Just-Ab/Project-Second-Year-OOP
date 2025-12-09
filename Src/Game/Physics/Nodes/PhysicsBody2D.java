package Game.Physics.Nodes;

import Physics.Body;
import Physics.PhysicsBody;
import Physics.PhysicsServer;

public abstract class PhysicsBody2D extends Body2D{
    
    protected PhysicsBody physicsbody=null;

    PhysicsBody2D(){}

    public abstract Body getBodyResource();
    public abstract void setBodyResource(Body body);

    @Override
    public void _exitTree(){
        super._exitTree();
        PhysicsServer.getSingleton().remove(physicsbody);
    }

}
