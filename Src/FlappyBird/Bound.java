package FlappyBird;

import Game.Physics.Nodes.AreaBody2D;
import Game.Physics.Nodes.Body2D;
import Game.Physics.Nodes.Collision2D;

public class Bound extends AreaBody2D{

    Collision2D collision = new Collision2D();

    public void _ready(){
        super._ready();
        addChild(collision);
    }

    public void setWidth(float _width){
        collision.setWidth(_width);
    }

    public void setHeight(float _height){
        collision.setHeight(_height);
    }

    public void _bodyEntered(Body2D _Body){
        System.out.println(_Body.getGroup());
        if(_Body.isInGroup("Player")){
            if(_Body instanceof Player player){
                player = (Player)_Body;
                player.hit();
            }
        }
    }

}

