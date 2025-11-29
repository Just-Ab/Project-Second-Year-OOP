package Game;

import org.joml.Vector3f;

import Game.Core.Camera2D;
import Game.Core.Node;

public class NodeLoader extends Node{
    
    public NodeLoader(){
        super("NodeLoader");
    }

    public void _ready(){
        addChild(new TestSprite2DInh("hello"));
        Camera2D camera = new Camera2D(new Vector3f(), 10, 10);
        addChild(camera);
        camera.current();
    }

    public void _update(float _delta){

    }  
}
