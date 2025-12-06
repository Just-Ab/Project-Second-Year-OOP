package FlappyBird;

import org.joml.Vector3f;

import Game.Visuals.Nodes.Sprite2D;

public class Pipe extends Bound{
    
    protected Sprite2D sprite = new Sprite2D();

    public void _ready(){
        super._ready();
        sprite.setTexture("Assets/Textures/Pipe.png");
        addChild(sprite);
        setWidth(0.2f);
        setLocalScale(new Vector3f(4.0f,4.0f,0.0f));
    }


}
