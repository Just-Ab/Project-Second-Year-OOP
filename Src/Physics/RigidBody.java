package Physics;

import java.util.ArrayList;
import java.util.List;

import org.joml.*;

public class RigidBody extends PhysicsBody{
    private Vector3f velocity=new Vector3f(0.0f),acceleration=new Vector3f(0.0f);
    private float mass=0;
    private boolean isOnGround=false;
    List<RectCollider> colliders = new ArrayList<>();

    RigidBody(float _mass){
        mass = _mass;
    }
    RigidBody(Vector3f position, float _mass){
        super(position);
        mass = _mass;
    }
    RigidBody(Vector3f position,Vector3f rotation, float _mass){
        super(position,rotation);
        mass = _mass;
    }


    public Vector3f getVelocity(){return new Vector3f(velocity);}
    public Vector3f getAcceleration(){return new Vector3f(acceleration);}
    public float getMass(){return mass;}
    public boolean getIsOnGround(){return isOnGround;}

    public void setVelocity(Vector3f vel){ this.velocity.set(vel); }
    public void setAcceleration(Vector3f acc){ this.acceleration.set(acc); }
    public void setMass(float mass){ this.mass = mass; }
    public void setIsOnGround(boolean value){ this.isOnGround = value; }

}
