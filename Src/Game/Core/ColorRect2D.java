package Game.Core;


import Rendering.RenderingServer;
import Rendering.RenderInstance;

import org.joml.*;

public class ColorRect2D extends Node2D{
    Vector3f color=new Vector3f(1.0f,0.0f,0.0f);
    boolean visiblity = true;
    RenderInstance instance=null;


    
    public ColorRect2D(String _name){
        super(_name);
    }
    
    public void _update(float _delta){
        if(instance!=null){
            instance.setPosition(getGlobalPosition());
        }
    }

    public void setColor(Vector3f _color){
        color.set(_color);
        if(isInTree&&(instance!=null)){
            instance.setColor(color);
        }
    }

    public void setLocalPosition(Vector3f _position){
        position.set(_position);
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



    protected void _enterTree(){
        instance=RenderingServer.getSingleton().createColorRect();
        instance.setVisibility(visiblity);
        instance.setColor(color);
        
    }
}

