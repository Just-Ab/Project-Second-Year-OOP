package FlappyBird;


import static org.lwjgl.glfw.GLFW.GLFW_KEY_DOWN;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_LEFT;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_RIGHT;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_SPACE;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_UP;

import org.joml.Math;
import org.joml.Vector3f;

import Game.Physics.Nodes.Collision2D;
import Game.Physics.Nodes.RigidBody2D;
import Game.Visuals.Nodes.AnimatedSprite2D;
import Game.Visuals.Nodes.Sprite2D;
import UserIO.Input;

public class Player extends RigidBody2D{

    AnimatedSprite2D sprite = new AnimatedSprite2D();
    Collision2D collision = new Collision2D();
    boolean dead=false;
    public Player(){
        super(1.0f);
    }

    
    @Override
    public void _ready(){
        setGroup("Player");
        collision.setWidth(0.25f);
        collision.setHeight(0.25f);
        sprite.setTexture("Assets/Textures/FBSH.png");
        sprite.setFrameColumns(3);
        sprite.play();
        // setAcceleration(new Vector3f(0.0f,-9.8f,0.0f));

        addChild(sprite);
        addChild(collision);
    }

    @Override
    public void _update(float _delta){
        super._update(_delta);
        if(!dead){
            // if(Input.isKeyJustPressed(GLFW_KEY_SPACE)){
            //     setVelocity(new Vector3f(0.0f,3.0f,0.0f));
            // }
            // if(getVelocity().y>0){
            //     setLocalRotationRad(Math.lerp(getGlobalRotation(),Math.PI_OVER_4_f,2.0f*_delta));
            // }
            // else{
            //     setLocalRotationRad(Math.lerp(getGlobalRotation(),-Math.PI_OVER_4_f,2.0f*_delta));

            // }
        setVelocity(new Vector3f(Input.getAxis(GLFW_KEY_LEFT, GLFW_KEY_RIGHT),Input.getAxis(GLFW_KEY_DOWN, GLFW_KEY_UP),0.0f));
        }
    }


    public void hit(){
        // dead=true;
        // sprite.stop();
        // setAcceleration(new Vector3f(0.0f,0.0f,0.0f));
        // setVelocity(new Vector3f(0.0f,0.0f,0.0f));
        System.out.println("hit");
    }


}