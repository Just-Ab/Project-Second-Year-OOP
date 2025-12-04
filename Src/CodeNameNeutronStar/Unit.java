package CodeNameNeutronStar;

import org.joml.Vector3f;

import Game.Core.Node2D;
import Game.Visuals.Nodes.AnimatedSprite2D;

public class Unit extends Node2D{
    protected AnimatedSprite2D animatedSprite=new AnimatedSprite2D();
    protected MapCell currentCell = null;
    protected MapCell destinationCell = null;


    public void setTexture(String _path){
        animatedSprite.setTexture(_path);
    }

    public void moveTo(MapCell _destinationCell){
        destinationCell = _destinationCell;
    }

    public AnimatedSprite2D getAnimatedSprite2D(){
        return animatedSprite;
    }
    
    public void _update(float _delta){
    }


}
