package Game.Visuals.Nodes;


import org.joml.Vector4f;

import Game.Core.Node2D;
import Game.Visuals.Resources.TextureResource;
import Rendering.RenderInstance;
import Rendering.RenderingServer;

public class Sprite2D extends Node2D{
    protected TextureResource texture=null;
    protected boolean dirtyTexture=true;
    protected Vector4f uv=new Vector4f(0.0f,0.0f,1.0f,1.0f);

    protected RenderInstance instance=null;


    public Sprite2D(){}

    public void setTexture(String _path){
        if(texture==null){
            texture = new TextureResource(_path);
        }
        else{
            texture.loadTexture(_path);
        }
        if(instance!=null){
            instance.setTextureResource(texture.getTexture());
        }
    }

    

    public Vector4f getUV() {
        return uv;
    }

    public void setUV(Vector4f _uv){
        uv.set(_uv);
        if(instance!=null){
            instance.setUV(_uv);
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
        super._enterTree();
        instance=RenderingServer.getSingleton().createSprite();
        instance.setPosition(getGlobalPosition());
        instance.setRotation(getGlobalRotation());
        instance.setScale(getGlobalScale());
        instance.setUV(uv);
        instance.setVisibility(visiblity);
        if (texture!=null){
            instance.setTextureResource(texture.getTexture());
        }
    }

    @Override
    protected void _exitTree(){
        super._exitTree();
        RenderingServer.getSingleton().remove(instance);
    }

}

