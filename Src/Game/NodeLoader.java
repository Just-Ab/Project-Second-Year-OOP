package Game;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_A;

import org.joml.Math;
import org.joml.Vector3f;

import Game.Core.Node;
import Game.Core.Time;
import Game.Nodes.Visuals.Sprite2D;
import UserIO.Input;

public class NodeLoader extends Node{
    
    public NodeLoader(){
        super("NodeLoader");
    }
            Sprite2D sprite3 = new Sprite2D("name");
    public void _ready(){
            Sprite2D sprite = new Sprite2D("name");
            sprite.setTexture("Assets/Textures/cat.png");
            sprite.setLocalPosition(new Vector3f(0.0f,0.0f,0.0f));
            sprite.setLocalRotationDegrees(90);
            addChild(sprite);
            Sprite2D sprite2 = new Sprite2D("name");
            sprite2.setTexture("Assets/Textures/cat.png");
            sprite2.setLocalPosition(new Vector3f(2.0f,0.0f,0.0f));
            sprite2.setLocalRotationDegrees(90);
            addChild(sprite2);
            sprite3 = new Sprite2D("name");
            sprite3.setTexture("Assets/Textures/socrat.jpg");
            sprite3.setLocalPosition(new Vector3f(-1.0f,0.0f,0.0f));
            sprite3.setLocalRotationDegrees(90);
            addChild(sprite3);
    }
    
    public void _update(float _delta){

        sprite3.setLocalRotationRad(Math.sin(Time.getTime()));
        if(Input.isKeyPressed(GLFW_KEY_A)){
        sprite3.setLocalPosition(new Vector3f(0.0f));

        }
        else{
                    sprite3.setLocalPosition(new Vector3f(Time.getTime()*0.3f,0.0f,0.0f));
        }

    }


    protected void _enterTree(){
    }
}
