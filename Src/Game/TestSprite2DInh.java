package Game;

import org.joml.*;
import Game.Core.*;
import Rendering.LineInstance;
import Rendering.RenderingServer;



public class TestSprite2DInh extends Sprite2D {
    public TestSprite2DInh(String _name){
        super(_name);
    }

    RigidBody2D rigidbody = new RigidBody2D("name", 10.0f);
    Collision2D rigCollision2d = new Collision2D();

    StaticBody2D staticbody = new StaticBody2D("name");
    Collision2D statCollision2d = new Collision2D();

    Raycast2D raycast = new Raycast2D("name");

    public void _ready(){       
        setVisibility(false);
        addChild(rigidbody);
        rigidbody.setAcceleration(new Vector3f(0.0f,-9.8f,0.0f));
        rigidbody.addChild(rigCollision2d);
        addChild(staticbody);
        staticbody.setLocalPosition(new Vector3f(0.4f,-4.0f,0.0f));
        staticbody.addChild(statCollision2d);
        addChild(raycast);
        raycast.setLocalPosition(new Vector3f(-1.5000001f,-2.0f,0.0f));
        raycast.setLength(2.0f);
    }

    public void _update(float _delta){

        if(raycast.isColliding()){
            System.out.println(raycast.getCollider()+" "+raycast.getHitPosition());
        }
    }
}
