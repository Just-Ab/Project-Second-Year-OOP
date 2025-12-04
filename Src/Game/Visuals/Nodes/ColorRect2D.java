package Game.Visuals.Nodes;


import Rendering.RenderingServer;
import Rendering.RenderInstance;

import org.joml.*;

import Game.Core.Node2D;

public class ColorRect2D extends Node2D{
    Vector3f color=new Vector3f(1.0f,0.0f,0.0f);
    boolean visiblity = true;
    RenderInstance instance=null;


    
    public ColorRect2D(){
    }


    public void setColor(Vector3f _color){
        color.set(_color);
        if(isInTree&&(instance!=null)){
            instance.setColor(color);
        }
    }

    public void setVisibility(boolean _visiblity){
        if(instance!=null){
            instance.setVisibility(_visiblity);
        }
        else{
            visiblity=_visiblity;
        }
    }
    public boolean isVisible(){
        if(instance!=null){
            return instance.isVisible();
        }
        else{
            return visiblity;
        }
    }

    @Override
    public void _update(float _delta){
        if(instance!=null){
            instance.setPosition(position);
            instance.setScale(scale);
            instance.setRotation(rotation);
            instance.setVisibility(visiblity);
            instance.setColor(color);        
        }
    }

    @Override
    protected void _enterTree(){
        instance=RenderingServer.getSingleton().createColorRect();
        instance.setPosition(position);
        instance.setScale(scale);
        instance.setRotation(rotation);
        instance.setVisibility(visiblity);
        instance.setColor(color);
        
    }
}

