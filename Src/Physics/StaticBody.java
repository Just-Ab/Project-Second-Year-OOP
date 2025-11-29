package Physics;

import org.joml.Vector3f;

public class StaticBody extends PhysicsBody{

    StaticBody(){

    }
    StaticBody(Vector3f _position){
        super(_position);
    }
    StaticBody(Vector3f _position,Vector3f _rotation){
        super(_position,_rotation);
    }

}
