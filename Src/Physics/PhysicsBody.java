package Physics;


import java.util.ArrayList;
import java.util.List;

import org.joml.Vector3f;

public abstract class PhysicsBody extends Body{
    List<RectCollider> colliders = new ArrayList<>();

    PhysicsBody(){

    }
    PhysicsBody(Vector3f _position){
        position.set(_position);;
    }
    PhysicsBody(Vector3f _position,Vector3f _rotation){
        position.set(_position);rotation.set(_rotation);
    }



}
