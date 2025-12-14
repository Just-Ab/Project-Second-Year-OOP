package CodeNameNeutronStar;

import Game.Core.Node2D;
import Game.Visuals.Nodes.AnimatedSprite2D;

public abstract class Unit2D extends Node2D{
    
    private UnitResource unitResource = null;
    private AnimatedSprite2D animatedSprite = null;
    public Unit2D(){
        
    }

    @Override
    protected void updateEngine(float _delta){
        super.updateEngine(_delta);
    }


    @Override
    public void _enterTree(){
        if(animatedSprite==null){
            animatedSprite = new AnimatedSprite2D();
        }
        if(unitResource==null){
            unitResource = new UnitResource();
        }
    }

}
