package FlappyBird;

import org.joml.Vector3f;

import Game.Cameras.Nodes.Camera2D;
import Game.Core.Node;
import Game.Physics.Nodes.AreaBody2D;
import Game.Physics.Nodes.Collision2D;
import Game.Visuals.Nodes.ColorRect2D;

public class FlappyBirdEntry extends Node{
    
    Player bird = new Player();
    Camera2D camera = new Camera2D(new Vector3f(0.0f), 10, 10);
    Bound topBound=new Bound(),bottomBound=new Bound();
    Obstacle obstacle = new Obstacle();

    public void _ready(){
        addChild(topBound);
        topBound.setWidth(1.0f);
        topBound.setHeight(1.0f);
        topBound.setLocalPosition(new Vector3f(0.0f,3.0f,0.0f));

        addChild(bottomBound);
        bottomBound.setWidth(1);
        bottomBound.setHeight(1);
        bottomBound.setLocalPosition(new Vector3f(0.0f,-3.0f,0.0f));
        
        obstacle.setLocalPosition(new Vector3f(0.0f,0.0f,0.0f));

        addChild(camera);
        addChild(bird);
        addChild(obstacle);
    }
    float t=0;
    public void _update(float _delta){
        if (topBound.isColliding()) {
            // System.out.println("gdgshdh");
        }
    }

}
