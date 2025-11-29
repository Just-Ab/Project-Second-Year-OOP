package Game.Core;


import org.joml.Vector4f;

import Rendering.RenderInstance;
import Rendering.RenderingServer;

public class Sprite2D extends Node2D{
    Texture2D texture=null;
    RenderInstance instance=null;

    boolean visiblity=true;
    Vector4f uv=new Vector4f();


    public Sprite2D(String _name){
        super(_name);
    }

    public void setTexture(String _path){
        if(texture==null){
            texture = new Texture2D(_path);
        }
        else{
            texture.loadTexture(_path);
        }
        if(isInTree&&(instance!=null)){
            instance.setTextureResource(texture.getRenderingResource());
        }
    }

    public void setUV(Vector4f _uv){
        if(instance!=null){
            instance.setUV(_uv);
        }
        else{
            uv.set(_uv);
        }
    }
    public Vector4f getUV() {
        if(instance!=null){
            return instance.getUV();
        }
        else{
            return uv;
        }        
    }

    public void setVisibility(boolean _visiblity){
        if(instance==null){
            visiblity=_visiblity;
        }
        else{
            instance.setVisibility(_visiblity);
        }
    }
    public boolean isVisible(){
        if(instance==null){
            return visiblity;
        }
        else{
            return instance.isVisible();
        }    
    }

    public void _update(float _delta){
        if(instance!=null){
            instance.setPosition(getGlobalPosition());
        }
    }

    protected void _enterTree(){
        super._enterTree();
        instance=RenderingServer.getSingleton().createSprite();
        instance.setVisibility(visiblity);
        instance.setPosition(getGlobalPosition());
        if (texture!=null){
            instance.setTextureResource(texture.getRenderingResource());
        }
    }
}

