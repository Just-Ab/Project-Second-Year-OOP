package Physics;

import java.util.ArrayList;
import java.util.List;

import org.joml.*;
import org.joml.Math;

import Physics.CollisionEvent.CollisionPair;

public class PhysicsServer {
    
    private static PhysicsServer server;

    private List<AreaBody> areaBodies = new ArrayList<>();
    private List<RigidBody> rigidbodies = new ArrayList<>();
    private List<StaticBody> staticBodies = new ArrayList<>();
    private List<RectCollider> rectColliders = new ArrayList<>();
    private List<Raycast> raycasts = new ArrayList<>();
    private List<CollisionEvent> collisionEvents = new ArrayList<>();

    public static PhysicsServer getSingleton(){
        if (server == null){
            server = new PhysicsServer();
        }
        return server;
    }

    public RigidBody createRigidBody(float mass){
        rigidbodies.addLast(new RigidBody(mass));
        return rigidbodies.getLast();
    }

    public StaticBody createStaticBody(){
        staticBodies.addLast(new StaticBody());
        return staticBodies.getLast();
    }

    public RectCollider createRectCollider(Body body, float width, float height){
        rectColliders.addLast(new RectCollider(body, width, height));
        if(body!=null){
            body.addCollider(rectColliders.getLast());
        }
        return rectColliders.getLast();
    }

    public Raycast createRaycast(Vector3f _position,float _length,float _rad){
        raycasts.addLast(new Raycast(_position,_length,_rad));
        return raycasts.getLast();
    }

    public AreaBody creatAreaBody(Vector3f _position){
        areaBodies.addLast(new AreaBody(_position));
        return areaBodies.getLast();
    }

    public void update(float _delta){
        for (RigidBody RigidBody : rigidbodies) {
            Vector3f oldVelocity = RigidBody.getVelocity();
            if (RigidBody.getIsOnGround()){
                if(oldVelocity.x>0){oldVelocity.x = Math.max(oldVelocity.x-0.04f, 0.0f);}
                else if(oldVelocity.x<0){oldVelocity.x = Math.min(oldVelocity.x+0.04f, 0.0f);}
            }
            Vector3f newVelocity = new Vector3f(oldVelocity).fma(_delta, RigidBody.getAcceleration());
            Vector3f newPosition = new Vector3f(RigidBody.getPosition()).fma(_delta, newVelocity);

            RigidBody.setVelocity(newVelocity);
            RigidBody.setPosition(newPosition);
            RigidBody.setIsOnGround(false);

        }

        collisionEvents.clear();

        List<RigidBody> rigidBodiesList = new ArrayList<>(rigidbodies);
        for (int i = 0; i < rigidBodiesList.size() - 1; i++) {
            for (int j = i + 1; j < rigidBodiesList.size(); j++) {
                RigidBody RigidBodyA = rigidBodiesList.get(i);
                RigidBody RigidBodyB = rigidBodiesList.get(j);
                if(RigidBodyA.getColliders().isEmpty()){break;}
                if(RigidBodyB.getColliders().isEmpty()){continue;}
                if (checkPhysicsbodiesCollision(RigidBodyA, RigidBodyB)){
                    registerRigidBodyRigid(RigidBodyA, RigidBodyB);
                }
            }
        }

        for (RigidBody RigidBody : rigidbodies) {
            for (StaticBody StaticBody : staticBodies) {
                if(RigidBody.getColliders().isEmpty()){break;}
                if(StaticBody.getColliders().isEmpty()){continue;}
                if (checkPhysicsbodiesCollision(RigidBody, StaticBody)) {
                    registerRigidBodyStatic(RigidBody, StaticBody);
                }
            }
        }

        for (CollisionEvent collisionEvent : collisionEvents) {
            appyCollision(collisionEvent);
        }

        for (Raycast raycast : raycasts) {
            raycast.setRaycastInfo(updateRaycast(raycast));
        }

        for (AreaBody areaBody : areaBodies) {
            areaBody.clearBodies();
            for (PhysicsBody physicsBody : getAllBodies()) {
                if(areaBody.getColliders().isEmpty()){break;}
                if(physicsBody.getColliders().isEmpty()){continue;}
                if(checkAreabodiesCollision(areaBody,physicsBody)){
                    areaBody.addBody(physicsBody);
                }
            }
        }
    }



    private boolean checkPhysicsbodiesCollision(PhysicsBody physicsBodyA, PhysicsBody physicsBodyB){
        for (RectCollider colliderA:physicsBodyA.getColliders()) {
            for (RectCollider colliderB:physicsBodyB.getColliders()) {
                if(checkAABBCollision(colliderA, colliderB)){return true;}
            }
        }
        return false;
    }

    private boolean checkAreabodiesCollision(AreaBody areaBody, PhysicsBody physics){
        for (RectCollider colliderA:areaBody.getColliders()) {
            for (RectCollider colliderB:physics.getColliders()) {

                if(checkAABBCollision(colliderA, colliderB)){return true;}
            }
        }
        return false;
    }
    private boolean checkAABBCollision(RectCollider colliderA, RectCollider colliderB){
        Vector3f colliderAPosition = colliderA.getWorldPosition();
        Vector3f colliderBPosition = colliderB.getWorldPosition();

        float topA = colliderAPosition.y+colliderA.getHalfHeight();
        float bottomA = colliderAPosition.y-colliderA.getHalfHeight();
        float leftA = colliderAPosition.x-colliderA.getHalfWidth();
        float rightA = colliderAPosition.x+colliderA.getHalfWidth();

        float topB= colliderBPosition.y+colliderB.getHalfHeight();
        float bottomB = colliderBPosition.y-colliderB.getHalfHeight();
        float leftB = colliderBPosition.x-colliderB.getHalfWidth();
        float rightB = colliderBPosition.x+colliderB.getHalfWidth();

        if (topA >= bottomB && bottomA <= topB && leftA <= rightB && rightA >= leftB)
        {
            return true;
        }        
        return false;
    }

    public Raycast.RaycastInfo updateRaycast(Raycast raycast) {
        PhysicsBody closestBody = null;
        float closestDist = Float.MAX_VALUE;
        Vector3f hitPoint = null;

        for (PhysicsBody body : getAllBodies()) {
            float distance = checkAABBRaycast(raycast, body);
            if (distance > 0 && distance < closestDist) {
                closestDist = distance;
                closestBody = body;
                hitPoint = new Vector3f(raycast.getPosition()).fma(distance, raycast.getNormal());
            }
        }

        if (closestBody == null)
            return new Raycast.RaycastInfo(false, null, 0, null);

        return new Raycast.RaycastInfo(true, closestBody, closestDist, hitPoint);
    }

    private float checkAABBRaycast(Raycast raycast,PhysicsBody body){
        
        RectCollider collider = body.getColliders().getFirst(); 
        Vector3f colliderPosition = collider.getWorldPosition();
        float top = colliderPosition.y + collider.getHalfHeight();
        float bottom = colliderPosition.y - collider.getHalfHeight();
        float left = colliderPosition.x - collider.getHalfWidth();
        float right = colliderPosition.x + collider.getHalfWidth();

        float diffLeft = (left-raycast.getPosition().x)/raycast.getNormal().x;
        float diffRigh = (right-raycast.getPosition().x)/raycast.getNormal().x;
        float diffTop = (top-raycast.getPosition().y)/raycast.getNormal().y;
        float diffBottom = (bottom-raycast.getPosition().y)/raycast.getNormal().y;

        float diffMin = Math.max(Math.min(diffLeft, diffRigh),Math.min(diffTop,diffBottom));
        float diffMax = Math.min(Math.max(diffLeft, diffRigh),Math.max(diffTop,diffBottom));

        if (diffMax<0||diffMax<diffMin||diffMin<0||diffMin>raycast.getLength()){return -1.0f;}
        return diffMin;
    }

    private void registerRigidBodyStatic(RigidBody RigidBody, StaticBody StaticBody) {
        RectCollider colliderA = RigidBody.getColliders().getFirst();
        RectCollider colliderB = StaticBody.getColliders().getFirst();

        Vector3f positionA = colliderA.getWorldPosition();
        Vector3f positionB = colliderB.getWorldPosition();

        float differenceX = positionA.x - positionB.x;
        float halfX = (colliderA.getHalfWidth() + colliderB.getHalfWidth()) - Math.abs(differenceX);

        float differenceY = positionA.y - positionB.y;
        float halfY = (colliderA.getHalfHeight() + colliderB.getHalfHeight()) - Math.abs(differenceY);

        Vector3f normal;
        float penetration;

        if (halfX < halfY) {
            normal = (differenceX > 0)?new Vector3f(1, 0, 0):new Vector3f(-1, 0, 0);
            penetration = halfX;
        } else {
            normal = (differenceY > 0)?new Vector3f(0, 1, 0):new Vector3f(0, -1, 0);
            penetration = halfY;
        }

        collisionEvents.addLast(new CollisionEvent(
            RigidBody, StaticBody,
            CollisionEvent.Types.RIGIDSTATIC,
            normal,
            penetration
        ));
    }

    private void registerRigidBodyRigid(RigidBody RigidBodyA,RigidBody RigidBodyB){
        RectCollider colliderA=RigidBodyA.getColliders().getFirst();
        RectCollider colliderB=RigidBodyB.getColliders().getFirst();


        Vector3f centerA = new Vector3f(RigidBodyA.getPosition());
        Vector3f centerB = new Vector3f(RigidBodyB.getPosition());
        Vector3f middlePoint = new Vector3f(centerB).sub(centerA);

        Vector3f halfA = new Vector3f(
            colliderA.getHalfWidth(),
            colliderA.getHalfHeight(),
            0.0f
        );
        Vector3f halfB = new Vector3f(
            colliderB.getHalfWidth(),
            colliderB.getHalfHeight(),
            0.0f
        );

        Vector3f overlap = new Vector3f(halfA).add(halfB).sub(new Vector3f(middlePoint).absolute());

        float collisionPenetration;
        Vector3f collisionNormal;

        if (overlap.x < overlap.y) {
            collisionPenetration = overlap.x;
            collisionNormal = middlePoint.x > 0?new Vector3f(1, 0, 0):new Vector3f(-1, 0, 0);
        } else {
            collisionPenetration = overlap.y;
            collisionNormal = middlePoint.y > 0?new Vector3f(0, 1, 0):new Vector3f(0, -1, 0);
        }

        collisionEvents.addLast(new CollisionEvent(
            RigidBodyA, RigidBodyB,
            CollisionEvent.Types.RIGIDRIGID,
            collisionNormal,
            collisionPenetration
        ));
    }

    private void appyCollision(CollisionEvent collisionEvent){

        CollisionEvent.Types type = collisionEvent.getCollisionType();
        CollisionPair pair = collisionEvent.getColliders();

        PhysicsBody bodyA = pair.bodyA();
        PhysicsBody bodyB = pair.bodyB();

        switch (type) {
        case CollisionEvent.Types.RIGIDRIGID:

            RigidBody rigidA = (bodyA instanceof RigidBody) ? (RigidBody)bodyA : null;
            RigidBody rigidB = (bodyB instanceof RigidBody) ? (RigidBody)bodyB : null;

            if (rigidA == null || rigidB == null) {
                throw new RuntimeException("Null Collision Event");
            }

            Vector3f velocityA = rigidA.getVelocity();
            Vector3f velocityB = rigidB.getVelocity();

            float invMassA = 1.0f / rigidA.getMass();
            float invMassB = 1.0f / rigidB.getMass();

            Vector3f collisionNormal = new Vector3f(collisionEvent.getCollisionNormal());
            float collisionPenetration = collisionEvent.getCollisionPenetration();

            Vector3f relativeVelocity = new Vector3f(velocityB).sub(velocityA);
            float normalVelocity = relativeVelocity.dot(collisionNormal);

            if (normalVelocity > 0) return;

            float impulseScalar = -normalVelocity / (invMassA + invMassB);

            Vector3f impulse = new Vector3f(collisionNormal).mul(impulseScalar);

            rigidA.setVelocity(new Vector3f(velocityA).sub(new Vector3f(impulse).mul(invMassA)));
            rigidB.setVelocity(new Vector3f(velocityB).add(new Vector3f(impulse).mul(invMassB)));

            Vector3f correction = new Vector3f(collisionNormal).mul(collisionPenetration/(invMassA+invMassB));

            rigidA.setPosition(new Vector3f(rigidA.getPosition()).sub(new Vector3f(correction).mul(invMassA)));
            rigidB.setPosition(new Vector3f(rigidB.getPosition()).add(new Vector3f(correction).mul(invMassB)));

            break;

        case CollisionEvent.Types.RIGIDSTATIC:

            RigidBody rigid = (bodyA instanceof RigidBody) ? (RigidBody)bodyA : null;
            StaticBody stat = (bodyB instanceof StaticBody) ? (StaticBody)bodyB : null;

            if (rigid == null || stat == null) {
                throw new RuntimeException("Null Collision Event");
            }

            Vector3f normal = new Vector3f(collisionEvent.getCollisionNormal());
            float penetration = collisionEvent.getCollisionPenetration();

            Vector3f pos = new Vector3f(rigid.getPosition()).add(new Vector3f(normal).mul(penetration));
            rigid.setPosition(pos);

            Vector3f velocity = rigid.getVelocity();
            float velocityNormalAngle = velocity.dot(normal);
            if (velocityNormalAngle < 0) {
                velocity.sub(new Vector3f(normal).mul(velocityNormalAngle));
                rigid.setVelocity(velocity);
            }
            if (normal.y > 0){
                rigid.setIsOnGround(true);
            }
            break;
        default:
            break;
        }
    }

    private List<PhysicsBody> getAllBodies(){
        List<PhysicsBody> bodies = new ArrayList<>();
        for (RigidBody RigidBody : rigidbodies) {
            bodies.addLast(RigidBody);
        }
        for (StaticBody StaticBody : staticBodies) {
            bodies.addLast(StaticBody);
        }
        return bodies;
    }

}
