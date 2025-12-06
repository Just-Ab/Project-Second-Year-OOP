package FlappyBird;

import org.joml.Vector3f;

import Game.Core.Node2D;

public class Obstacle extends Node2D{
    protected Pipe top=new Pipe(),bottom=new Pipe();

    public void _ready(){
        addChild(top);
        addChild(bottom);
        bottom.setLocalRotationDegrees(180);
        top.setLocalPosition(new Vector3f(0.0f,3.0f,0.0f));
        bottom.setLocalPosition(new Vector3f(0.0f,-3.0f,0.0f));

    }

    public void set(){
        
    }

}
