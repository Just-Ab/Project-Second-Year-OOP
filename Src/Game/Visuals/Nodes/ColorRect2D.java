package Game.Visuals.Nodes;


import Rendering.RenderingServer;
import Rendering.RenderInstance;

import org.joml.*;

import Game.Core.Node2D;

public class ColorRect2D extends Node2D{
    protected Vector3f color=new Vector3f(1.0f,1.0f,1.0f);
    protected boolean visiblity = true;
    protected RenderInstance instance=null;

    
    public ColorRect2D(){
    }


    public void setColor(Vector3f _color){
        color.set(_color);
        if(instance!=null){
            instance.setColor(_color);
        }
    }

    @Override
    public void setVisibility(boolean _visiblity){
        super.setVisibility(_visiblity);
        if(instance!=null){
            instance.setVisibility(_visiblity);
        }
    }

    @Override
    protected void updateEngine(float _delta){
        super.updateEngine(_delta);
        instance.setPosition(getGlobalPosition());
        instance.setRotation(getGlobalRotation());
        instance.setScale(getGlobalScale());
    }

    @Override
    protected void _enterTree(){
        instance=RenderingServer.getSingleton().createColorRect();
        instance.setPosition(getGlobalPosition());
        instance.setScale(getGlobalScale());
        instance.setRotation(getGlobalRotation());
        instance.setVisibility(visiblity);
        instance.setColor(color);
    }
    
    @Override
    protected void _exitTree(){
        super._exitTree();
        RenderingServer.getSingleton().remove(instance);
    }

}

