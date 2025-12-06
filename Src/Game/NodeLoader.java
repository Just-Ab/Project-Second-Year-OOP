package Game;


import org.joml.Vector3f;

import Game.Cameras.Nodes.Camera2D;
import Game.Core.Node;
import Game.Physics.Nodes.RigidBody2D;
import Game.Visuals.Nodes.AnimatedSprite2D;
import Game.Visuals.Nodes.ColorRect2D;


public class NodeLoader extends Node{
    
    public NodeLoader(){
        super();
    }

    AnimatedSprite2D unit = new AnimatedSprite2D();

        Camera2D camera = new Camera2D(new Vector3f(0.0f), 10, 10);
        RigidBody2D rigid = new RigidBody2D(1.0f);

    public void _ready(){
        camera.current();
        addChild(camera);
        unit.setFrameColumns(8);
        unit.setFrameRows(9);
        unit.setTexture("Assets/Textures/Anim.png");
        addChild(unit);

        unit.createAnimation("walk", 48, 51);
        unit.createAnimation("run", 40, 47);
        unit.createAnimation("death", 8, 8+7);

        unit.activateAnimation("death");
        unit.loop();

        unit.play();

        unit.setLocalPosition(new Vector3f(0.0f,0.0f,0.0f));

        ColorRect2D clo = new ColorRect2D();
        clo.setColor(new Vector3f(1.0f,0.0f,1.0f));
        clo.setLocalPosition(new Vector3f(1.0f,0.0f,0.0f));
        rigid.setAcceleration(new Vector3f(0.0f,-9.8f,0.0f));
        addChild(rigid);
        rigid.addChild(clo);
    }
    float time=0;

    public void _update(float _delta){
        // System.out.println(rigid.getBodyResource().getPosition());
    }


    protected void _enterTree(){
    }
}
