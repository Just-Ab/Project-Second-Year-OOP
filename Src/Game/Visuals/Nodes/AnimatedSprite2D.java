package Game.Visuals.Nodes;

import java.util.ArrayList;
import java.util.List;

import org.joml.Vector4f;

import Game.Visuals.Resources.AnimationResource;

public class AnimatedSprite2D extends Sprite2D{
    
    public AnimatedSprite2D(){}


    protected List<AnimationResource> animations = new ArrayList<>();
    protected AnimationResource activeAnimation = null;
    protected int frameRows=1;
    protected int frameColumns=1;
    protected float uvRowUnit=1;
    protected float uvColumnUnit=1;
    protected int currentFrame=0;
    protected float nextFrameTime=0.2f;
    protected float accumulatedFrameTime=0.0f;
    protected boolean isPlaying=false;  
    protected boolean isOneShot=false;

    public void setFrameRows(int _count) { frameRows=_count;uvRowUnit=1.0f/frameRows; }
    public void setFrameColumns(int _count) { frameColumns=_count;uvColumnUnit=1.0f/frameColumns; }
    public void setNextFrameTime(float _time) { nextFrameTime = _time; }

    public void play(){isPlaying=true;}
    public void stop(){isPlaying=false;}
    public void reset(){
        if(activeAnimation!=null){
            currentFrame = activeAnimation.getStartingFrame();
            _animationStarted();
        }
    }
    public void oneShot(){isOneShot=true;}
    public void loop(){isOneShot=false;}

    
    public void createAnimation(String _name,int _start,int _end){
        for (AnimationResource animation : animations) {
            if(animation.getName().equals(_name)){
                animation.setStartingFrame(_start);
                animation.setEndingFrame(_end);
                return;
            }
        }
        animations.addLast(new AnimationResource(_name, _start, _end));
    }

    public void activateAnimation(String _name){
        for (AnimationResource animation : animations) {
            if(animation.getName().equals(_name)){
                activeAnimation = animation;
                reset();
                return;
            }
        }
        System.out.println("Animation does not exist!");
    }


    public void _animationEnded(){
    }

    public void _animationStarted(){
    }


    @Override
    protected void updateEngine(float _delta){
        super.updateEngine(_delta);
        if(isPlaying && activeAnimation!=null){
            accumulatedFrameTime+=_delta;
            if(accumulatedFrameTime>=nextFrameTime){
                currentFrame++;
                if(currentFrame>=activeAnimation.getEndingFrame()){
                    _animationEnded();
                    if(isOneShot){
                        stop();
                    }
                    else{
                        reset();
                    }
                }
                else if(currentFrame<activeAnimation.getStartingFrame()){
                    reset();
                }
                accumulatedFrameTime-=nextFrameTime;
            }
        }
        uv.set(
            uvColumnUnit * (currentFrame % frameColumns),
            uvRowUnit * (currentFrame / frameColumns),
            uvColumnUnit,
            uvRowUnit);
    }

    @Override
    protected void _enterTree(){
        super._enterTree();
        reset();
    }

}
