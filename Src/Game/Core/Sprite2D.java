package Game.Core;


import Rendering.RenderingServer;
import Rendering.SpriteRenderInstance;

public class Sprite2D extends Node2D{
    Texture2D texture=null;
    SpriteRenderInstance instance=null;

    boolean visiblity=true;



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
            instance.setTexture(texture.getRenderingResource());
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
            instance.setTexture(texture.getRenderingResource());
        }
    }
}

