package Game;


import static org.lwjgl.glfw.GLFW.GLFW_KEY_SPACE;

import org.joml.Vector3f;

import Game.Cameras.Nodes.Camera2D;
import Game.Core.Node;
import Game.Physics.Nodes.AreaBody2D;
import Game.Physics.Nodes.Collision2D;
import Game.Physics.Nodes.Raycast2D;
import Game.Physics.Nodes.RigidBody2D;
import Game.Visuals.Nodes.AnimatedSprite2D;
import Game.Visuals.Nodes.ColorRect2D;
import Game.Visuals.Nodes.Sprite2D;
import Game.Visuals.Nodes.Tilemap2D;
import UserIO.Input;


public class NodeLoader extends Node{
    
    public NodeLoader(){
        super();
    }


    AnimatedSprite2D anim = new AnimatedSprite2D();
    AreaBody2D rigid = new AreaBody2D();
    Raycast2D ray = new Raycast2D();
    Collision2D collision = new Collision2D();

    public void _ready(){  
        anim.setTexture("Assets/Textures/anim.png");
        anim.setFrameColumns(8);
        anim.setFrameRows(9);
        anim.createAnimation("walk", 0, 7);
        anim.activateAnimation("walk");
        anim.loop();
        anim.play();
        addChild(rigid);
        rigid.addChild(anim);
        rigid.addChild(ray);
        ray.setLength(4.0f);
        rigid.addChild(collision);
        collision.setVisibility(true);
        
    }

    public void _update(float _delta){
        if(Input.isKeyJustPressed(GLFW_KEY_SPACE)){
            rigid.queueFree();
        }
    }


    protected void _enterTree(){
    }
}
