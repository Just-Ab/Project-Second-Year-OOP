package Game.Physics.Nodes;

import org.joml.Vector3f;

import Physics.Body;
import Physics.PhysicsServer;
import Physics.RigidBody;

public class RigidBody2D extends PhysicsBody2D{

    protected RigidBody bodyResource = null;

    protected float mass=1.0f;

    protected Vector3f velocity=new Vector3f(),acceleration=new Vector3f();


    public RigidBody2D(float _mass){
        mass=_mass;
    }
    

    public float getMass(){return mass;}
    public void setMass(float _mass){mass=_mass;}
    
    public void setLocalPosition(Vector3f pos){
        super.setLocalPosition(pos);
        if(bodyResource!=null){
            bodyResource.setPosition(pos);
        }
    }

    public Vector3f getVelocity(){
        if(bodyResource==null){return velocity;}
        return bodyResource.getVelocity();
    }
    public void setVelocity(Vector3f vel){
        if(bodyResource==null){
            velocity.set(vel);
        }
        else{
            bodyResource.setVelocity(vel);
        }    
    }

    public Vector3f getAcceleration(){
        if(bodyResource==null){return acceleration;}
        return bodyResource.getAcceleration();
    }
    public void setAcceleration(Vector3f acc){
        if(bodyResource==null){
            acceleration.set(acc);
        }
        else{
            bodyResource.setAcceleration(acc);
        }    
    }

    @Override
    public RigidBody getBodyResource(){
        return bodyResource;
    }
    @Override
    public void setBodyResource(Body _body){
        if(_body instanceof RigidBody _RigidBody){
            _RigidBody.setOwner(this);
            bodyResource = _RigidBody;
        }
    }

    @Override
    public void _update(float _delta){
        if(bodyResource!=null){
            position.set(bodyResource.getPosition());
        }
    }
    
    @Override
    protected void _enterTree(){
        super._enterTree();
        if (bodyResource==null){
            setBodyResource(PhysicsServer.getSingleton().createRigidBody(mass));
            bodyResource.setOwner(this);
            bodyResource.setAcceleration(acceleration);
            bodyResource.setVelocity(velocity);
            bodyResource.setPosition(getGlobalPosition());
        }
    }


}
