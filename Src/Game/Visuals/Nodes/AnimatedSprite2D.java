package Game.Visuals.Nodes;

import org.joml.Vector4f;

public class AnimatedSprite2D extends Sprite2D{
    
    public AnimatedSprite2D(){}

    protected int frameRows=1;
    protected int frameColumns=1;
    protected float uvRowUnit=1;
    protected float uvColumnUnit=1;
    protected int currentFrame=0;
    protected float nextFrameTime=0.2f;
    protected float accumulatedFrameTime=0.0f;
    protected int startingFrame=0;
    protected int endingFrame=0;
    protected boolean isPlaying=false;  

    public void setFrameRows(int _count) { frameRows=_count;uvRowUnit=1.0f/frameRows; }
    public void setFrameColumns(int _count) { frameColumns=_count;uvColumnUnit=1.0f/frameColumns; }
    public void setStartingFrame(int _count) { startingFrame=_count; }
    public void setEndingFrame(int _count) { endingFrame=_count; }
    public void setNextFrameTime(float _time) { nextFrameTime = _time; }

    public void play(){isPlaying=true;}
    public void stop(){isPlaying=false;}

    @Override
    protected void updateEngine(float _delta){
        super.updateEngine(_delta);
        uv.set(
            uvColumnUnit * (currentFrame % frameColumns),
            uvRowUnit * (currentFrame / frameColumns),
            uvColumnUnit,
            uvRowUnit);
        if(isPlaying){
            accumulatedFrameTime+=_delta;
            if(accumulatedFrameTime>=nextFrameTime){
                currentFrame++;
                if(currentFrame>=frameRows*frameColumns || currentFrame>endingFrame){
                    currentFrame=startingFrame;
                }
                accumulatedFrameTime-=nextFrameTime;
            }
        }
    }

    @Override
    protected void _enterTree(){
        super._enterTree();
        currentFrame = startingFrame;
    }

}
