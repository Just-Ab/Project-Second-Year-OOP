package Game.Physics.Nodes;

import org.joml.Vector3f;

import Physics.Body;
import Physics.PhysicsServer;
import Physics.RigidBody;

public class RigidBody2D extends PhysicsBody2D{

    protected RigidBody bodyResource = null;
    
    protected float mass=1.0f;

    protected Vector3f velocity=new Vector3f(0.0f),acceleration=new Vector3f(0.0f);




    public RigidBody2D(float _mass){
        mass=_mass;
    }
    

    public float getMass(){return mass;}

    public void setMass(float _mass){
        mass=_mass;
        if(bodyResource!=null){
            bodyResource.setMass(_mass);
        }
    }
    

    public Vector3f getVelocity(){
        return velocity;
    }

    public void setVelocity(Vector3f vel){
        velocity.set(vel);
        if(bodyResource!=null){
            bodyResource.setVelocity(vel);
        }    
    }

    protected void setVelocityInternal(Vector3f vel){
        velocity.set(vel);
    }


    public Vector3f getAcceleration(){
        return acceleration;
    }

    public void setAcceleration(Vector3f acc){
        acceleration.set(acc);
        if(bodyResource!=null){
            bodyResource.setAcceleration(acc);
        }    
    }

    protected void setAccelerationInternal(Vector3f acc){
        acceleration.set(acc);
    }


    @Override
    public void updateEngine(float _delta){
        if(bodyResource!=null){
            setVelocityInternal(bodyResource.getVelocity());
            setAccelerationInternal(bodyResource.getAcceleration());
            setLocalPositionInternal(bodyResource.getPosition());
        }
    }
    
    @Override
    protected void _enterTree(){
        super._enterTree();
        if (bodyResource==null){
            setBodyResource(PhysicsServer.getSingleton().createRigidBody(mass));
            bodyResource.setOwner(this);
            bodyResource.setAcceleration(getAcceleration());
            bodyResource.setVelocity(getVelocity());
            bodyResource.setPosition(getGlobalPosition());
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
}


