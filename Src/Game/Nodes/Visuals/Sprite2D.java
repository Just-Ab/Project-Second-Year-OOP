package Game.Nodes.Visuals;


import org.joml.Vector3f;
import org.joml.Vector4f;

import Game.Core.Node2D;
import Game.Core.TextureResource2D;
import Rendering.RenderInstance;
import Rendering.RenderingServer;

public class Sprite2D extends Node2D{
    protected TextureResource2D texture=null;
    protected RenderInstance instance=null;

    protected boolean visiblity=true;
    protected boolean dirty=true;
    protected Vector4f uv=new Vector4f(0.0f,0.0f,1.0f,1.0f);


    public Sprite2D(String _name){
        super(_name);
    }

    public void setTexture(String _path){
        if(texture==null){
            texture = new TextureResource2D(_path);
        }
        else{
            texture.loadTexture(_path);
        }
        if(isInTree&&(instance!=null)){
            instance.setTextureResource(texture.getRenderingResource());
        }
        dirty=true;
    }

    @Override
    public void setLocalPosition(Vector3f _position){super.setLocalPosition(_position);dirty=true;}

    @Override
    public void setLocalScale(Vector3f _scale){super.setLocalScale(_scale);dirty=true;}

    @Override
    public void setLocalRotationDegrees(float _degrees){super.setLocalRotationDegrees(_degrees);dirty=true;}

    @Override
    public void setLocalRotationRad(float _rad){super.setLocalRotationRad(_rad);dirty=true;}

    public void setUV(Vector4f _uv){
        if(instance!=null){
            instance.setUV(_uv);
        }
        else{
            uv.set(_uv);
        }
        dirty=true;
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

    @Override
    public void _update(float _delta){
        if(instance!=null && dirty){
            instance.setPosition(getGlobalPosition());
            instance.setRotation(getGlobalRotation());
            instance.setScale(getGlobalScale());
            instance.setUV(uv);
            dirty=false;
        }
    }

    @Override
    protected void _enterTree(){
        super._enterTree();
        instance=RenderingServer.getSingleton().createSprite();
        instance.setVisibility(visiblity);
        instance.setPosition(getGlobalPosition());
        instance.setRotation(getGlobalRotation());
        instance.setScale(getGlobalScale());
        instance.setUV(uv);
        if (texture!=null){
            instance.setTextureResource(texture.getRenderingResource());
        }
    }
}

