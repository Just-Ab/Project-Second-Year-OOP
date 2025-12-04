package Game;


import org.joml.Vector3f;

import CodeNameNeutronStar.Map;
import Game.Cameras.Nodes.Camera2D;
import Game.Core.Node;
import Game.Visuals.Nodes.AnimatedSprite2D;


public class NodeLoader extends Node{
    
    public NodeLoader(){
        super();
    }

    AnimatedSprite2D unit = new AnimatedSprite2D();
    Map map = new Map(10, 10);


    public void _ready(){
        Camera2D camera = new Camera2D(new Vector3f(0.0f), 10, 10);
        camera.current();
        unit.setFrameColumns(8);
        unit.setFrameRows(9);
        unit.setTexture("Assets/Textures/Anim.png");
        unit.setStartingFrame(48);
        unit.setEndeingFrame(48+3);
        addChild(unit);

        unit.setLocalPosition(new Vector3f(map.getCell(3, 3).position,0.0f));


    }
    float time=0;

    public void _update(float _delta){
        time+=_delta;
        if(time>=1){
            time-=time;
            System.out.println("Fps: "+1.0f/_delta);
        }
        Vector3f destination = new Vector3f(map.getCell(1, 0).position,0.0f);
        Vector3f direction = destination.sub(unit.getGlobalPosition()).normalize();
        float speed = 5.0f;
        unit.setLocalPosition(unit.getGlobalPosition().add(new Vector3f(direction).mul(speed*_delta)));
        System.out.println(direction);
    }


    protected void _enterTree(){
    }
}
