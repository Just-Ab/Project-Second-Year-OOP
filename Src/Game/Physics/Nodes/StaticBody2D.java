package Game.Physics.Nodes;

import org.joml.Vector3f;

import Physics.Body;
import Physics.PhysicsServer;
import Physics.StaticBody;

public class StaticBody2D extends PhysicsBody2D{
    
    StaticBody bodyResource=null;

    public StaticBody2D(String name){}

    public void setLocalPosition(Vector3f pos){
        super.setLocalPosition(pos);
        if(bodyResource!=null){
            bodyResource.setPosition(pos);
        }
    }

    public void setBodyResource(Body _body){
        if(_body instanceof StaticBody _staticBody){
            bodyResource = _staticBody;
        }
    }

    @Override
    public Body getBodyResource(){
        return bodyResource;
    }

    @Override
    protected void _enterTree(){
        super._enterTree();
        if (bodyResource==null){
            setBodyResource(PhysicsServer.getSingleton().createStaticBody());
            bodyResource.setOwner(this);
            bodyResource.setPosition(getGlobalPosition());
        }
    }

}
