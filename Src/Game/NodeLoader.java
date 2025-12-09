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


    public void _ready(){        
    }

    public void _update(float _delta){
        // System.out.println(rigid.getBodyResource().getPosition());
    }


    protected void _enterTree(){
    }
}
