package Physics;

import org.joml.*;

public class CollisionEvent {
    public static enum Types{RIGIDSTATIC,RIGIDRIGID};
    private final Types collisionType;
    public record CollisionPair(PhysicsBody bodyA, PhysicsBody bodyB) {}
    private final CollisionPair colliders;
    private final Vector3f collisionNormal;
    private final float collisionPenetration;


    public CollisionEvent(PhysicsBody bodyA,PhysicsBody bodyB,Types type,Vector3f _collisionNormal,float _collisionPenetration){
        colliders = new CollisionPair(bodyA, bodyB);
        collisionType = type;
        collisionNormal = _collisionNormal;
        collisionPenetration = _collisionPenetration;
    }
    public CollisionPair getColliders(){
        return colliders;
    }
    public Types getCollisionType(){
        return collisionType;
    }

    public Vector3f getCollisionNormal(){
        return collisionNormal;
    }

    public float getCollisionPenetration(){
        return collisionPenetration;
    }
}
