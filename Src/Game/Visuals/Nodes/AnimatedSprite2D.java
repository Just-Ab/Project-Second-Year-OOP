package Game.Visuals.Nodes;

import java.util.ArrayList;
import java.util.List;

import org.joml.Vector2i;

import Game.Visuals.Resources.AnimationResource;

public class AnimatedSprite2D extends Sprite2D {

    protected List<AnimationResource> animations = new ArrayList<>();
    protected AnimationResource activeAnimation = null;

    protected int currentFrame = 0;
    protected float nextFrameTime = 0.2f;
    protected float accumulatedFrameTime = 0.0f;

    protected boolean isPlaying = false;
    protected boolean isOneShot = false;

    public AnimatedSprite2D(){}

    public void setNextFrameTime(float time){
        nextFrameTime = time;
    }

    public void play(){
        isPlaying = true;
    }

    public void stop(){
        isPlaying = false;
    }

    public void oneShot(){
        isOneShot = true;
    }

    public void loop(){
        isOneShot = false;
    }

    public void reset(){
        if(activeAnimation == null) return;

        currentFrame = activeAnimation.getStartingFrame();
        accumulatedFrameTime = 0.0f;
        applyFrame();
        _animationStarted();
    }

    public void createAnimation(String name, int start, int end){
        for(AnimationResource animation : animations){
            if(animation.getName().equals(name)){
                animation.setStartingFrame(start);
                animation.setEndingFrame(end);
                return;
            }
        }
        animations.add(new AnimationResource(name, start, end));
    }

    public void activateAnimation(String name){
        for(AnimationResource animation : animations){
            if(animation.getName().equals(name)){
                activeAnimation = animation;
                reset();
                return;
            }
        }
        System.out.println("Animation \"" + name + "\" does not exist!");
    }

    protected void applyFrame(){
        if(atlasTexture == null) return;

        int columns = atlasTexture.getHorizontalRegionsCount();

        int x = currentFrame % columns;
        int y = currentFrame / columns;

        setUV(new Vector2i(x, y));
    }

    public void _animationStarted(){}
    public void _animationEnded(){}

    public AnimationResource getActiveAnimation(){
        return activeAnimation;
    }

    @Override
    protected void updateEngine(float delta){
        super.updateEngine(delta);

        if(!isPlaying || activeAnimation == null) return;

        accumulatedFrameTime += delta;

        if(accumulatedFrameTime >= nextFrameTime){
            currentFrame++;

            if(currentFrame > activeAnimation.getEndingFrame()){
                _animationEnded();

                if(isOneShot){
                    stop();
                    currentFrame = activeAnimation.getEndingFrame();
                } else {
                    reset();
                    return;
                }
            }

            applyFrame();
            accumulatedFrameTime -= nextFrameTime;
        }
    }

    @Override
    protected void _enterTree(){
        super._enterTree();
        reset();
    }

    @Override
    protected void _exitTree(){
        super._exitTree();
        animations.clear();
    }
}
