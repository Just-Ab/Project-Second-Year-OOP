package Game.Core;

import org.joml.Vector2f;
import org.joml.Vector4f;

import Rendering.RenderingServer;

public class AnimatedSprite2D extends Sprite2D{
    public AnimatedSprite2D(String _name){
        super(_name);
    }

    protected int frameRows=1;
    protected int frameColumns=1;
    protected float uvRowUnit=1;
    protected float uvColumnUnit=1;
    protected int currentFrame=0;
    protected float nextFrameTime=0.2f;
    protected float accumulatedFrameTime=0.0f;
    protected int startingFrame=0;
    protected int endingFrame=0;


    public void setFrameRows(int _count) { frameRows=_count;uvRowUnit=1.0f/frameRows; }
    public void setFrameColumns(int _count) { frameColumns=_count;uvColumnUnit=1.0f/frameColumns; }
    public void setStartingFrame(int _count) { startingFrame=_count; }
    public void setEndeingFrame(int _count) { endingFrame=_count; }
    public void setNextFrameTime(float _time) { nextFrameTime = _time; }

    public void _ready(){
        currentFrame = startingFrame;
    }

    public void _update(float _delta){
        super._update(_delta);
        setUV(new Vector4f(uvColumnUnit*(currentFrame%frameColumns),uvRowUnit*(currentFrame/frameColumns),uvColumnUnit,uvRowUnit));
        accumulatedFrameTime+=_delta;
        if(accumulatedFrameTime>=nextFrameTime){
            currentFrame++;
            if(currentFrame>=frameRows*frameColumns || currentFrame>endingFrame){
                currentFrame=startingFrame;
            }
            accumulatedFrameTime-=nextFrameTime;
        }
        System.out.println(currentFrame);
    }


    protected void _enterTree(){
        super._enterTree();
    }

}
