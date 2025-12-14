package CodeNameNeutronStar;

import Game.Core.Resource;

public class UnitResource extends Resource{


    private float health = 100.0f;
    private float speed = 1.0f;
    private int team=0;


    public UnitResource(){
    }

    public UnitResource(float _health,float _speed,int _team){
        health = _health;speed = _speed;team = _team;
    }

    public float getHealth(){
        return health;
    }

    public void setHealth(float _health){
        health = _health;
    }

    public void accumulateHealth(float _amount){
        health+=_amount;
    }

    public float getSpeed(){
        return speed;
    }

    public void setSpeed(float _speed){
        speed = _speed;
    }

    public void accumulateSpeed(float _amount){
        speed+=_amount;
    }

    public int getTeam(){
        return team;
    }

    public void setTeam(int _team){
        team = _team;
    }

}