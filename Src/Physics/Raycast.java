package Physics;

import org.joml.*;
import org.joml.Math;

public class Raycast {

    public static record RaycastInfo(boolean isColliding,PhysicsBody collider,float collisionDistance,Vector3f hitPosition) {}
    
    private RaycastInfo raycastInfo= new RaycastInfo(false, null, 0.0f,new Vector3f() );
    private Vector3f position=new Vector3f();
    private Vector3f normal=new Vector3f();
    private float angleRad;
    private float length;

    public Raycast(Vector3f _position,float _length,float _rad){
        length =_length;
        setAngleRad(_rad);
        setPosition(_position);
    }

    public Vector3f getPosition(){return new Vector3f(position);}
    public float getLength(){return length;}
    public Vector3f getNormal(){return new Vector3f(normal);}
    public float getAngleRad(){return angleRad;}

    public PhysicsBody getCollider(){return raycastInfo.collider();}
    public boolean isColliding(){return raycastInfo.isColliding();}
    public float getCollisionDistance(){return raycastInfo.collisionDistance();}
    public Vector3f getHitPosition(){return raycastInfo.hitPosition();}



    public void setLength(float _length){length=_length;}
    public void setAngleRad(float _rad){
        normal.x=Math.cos(_rad);
        normal.y=Math.sin(_rad);
        angleRad = _rad;
    }
    public void setPosition(Vector3f _position){position=_position;}
    public void setRaycastInfo(RaycastInfo _raycastInfo){raycastInfo = _raycastInfo;}


}
