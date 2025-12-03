package Game;


import org.joml.Vector3f;

import Game.Core.ColorRect2D;
import Game.Core.Node;
import Game.Core.Sprite2D;

public class NodeLoader extends Node{
    
    public NodeLoader(){
        super("NodeLoader");
    }
    public void _ready(){
        Sprite2D sprite = new Sprite2D("name");
        sprite.setTexture("Assets/Textures/cat.png");
        addChild(sprite);
        sprite.setVisibility(true);
        sprite.setLocalPosition(new Vector3f(1.0f));
        sprite.setLocalRotationDegrees(90);

        ColorRect2D colorRect2D = new ColorRect2D("name");
        colorRect2D.setColor(new Vector3f(1.0f,1.0f,0.0f));
        addChild(colorRect2D);
        colorRect2D.setVisibility(true);
    }
    

    protected void _enterTree(){

    }
}
