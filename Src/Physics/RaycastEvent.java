package Physics;

import org.joml.Vector3f;

public class RaycastEvent {
    
    public static record RaycastInfo(PhysicsBody collider,boolean isColliding,float collisionDistance,Vector3f hitPosition,float leftDifference,float rightDifference,float topDifference,float bottomDifferenc,float minimumDifference,float maximumDifference) {}

    private RaycastInfo raycastInfo;

    RaycastEvent(RaycastInfo info){
        raycastInfo = info;
    }

    public Vector3f getHitPosition(){return new Vector3f(raycastInfo.hitPosition);}
    public PhysicsBody getCollider(){return raycastInfo.collider;}
    public boolean isColliding(){return raycastInfo.isColliding;}
    public float getCollisionDistance(){return raycastInfo.collisionDistance;}
    public RaycastInfo getRaycastInfo(){return raycastInfo;}
}
