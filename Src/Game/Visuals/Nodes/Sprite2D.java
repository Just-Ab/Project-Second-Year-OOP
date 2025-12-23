package Game.Visuals.Nodes;

import org.joml.Vector2i;
import org.joml.Vector4f;

import Game.Core.Node2D;
import Game.Visuals.Resources.AtlasTextureResource;
import Rendering.RenderInstance;
import Rendering.RenderingServer;

public class Sprite2D extends Node2D {

    protected AtlasTextureResource atlasTexture;
    protected Vector2i uv = new Vector2i(0, 0);
    protected RenderInstance instance;

    public Sprite2D(){}

    public void setTexture(String path){
        setTexture(path, 1, 1);
    }

    public void setTexture(String path, int _h, int _v){
        if(atlasTexture == null){
            atlasTexture = new AtlasTextureResource(path, _h, _v);
        } else {
            atlasTexture.setTextureResource(path, _h, _v);
        }

        if(instance != null){
            instance.setTextureResource(
                atlasTexture.getTextureResource().getTexture()
            );
            applyUV();
        }
    }

    public Vector2i getUV(){
        return uv;
    }

    public void setUV(Vector2i _uv){
        if(atlasTexture == null) return;

        if(_uv.x < 0 || _uv.y < 0 ||
           _uv.x >= atlasTexture.getHorizontalRegionsCount() ||
           _uv.y >= atlasTexture.getVerticalRegionsCount())
            return;

        uv.set(_uv);
        applyUV();
    }

    private void applyUV(){
        if(instance == null || atlasTexture == null) return;

        float hr = atlasTexture.getHorizontalRatio();
        float vr = atlasTexture.getVerticalRatio();

        instance.setUV(new Vector4f(
            hr * uv.x,
            vr * uv.y,
            hr,
            vr
        ));
    }

    @Override
    public void setVisibility(boolean visibility){
        super.setVisibility(visibility);
        if(instance != null){
            instance.setVisibility(visibility);
        }
    }

    @Override
    protected void updateEngine(float delta){
        super.updateEngine(delta);
        if(instance == null) return;

        instance.setPosition(getGlobalPosition());
        instance.setRotation(getGlobalRotation());
        instance.setScale(getGlobalScale());
    }

    @Override
    protected void _enterTree(){
        super._enterTree();

        instance = RenderingServer.getSingleton().createSprite();
        instance.setPosition(getGlobalPosition());
        instance.setRotation(getGlobalRotation());
        instance.setScale(getGlobalScale());
        instance.setVisibility(visiblity);

        if(atlasTexture != null){
            instance.setTextureResource(
                atlasTexture.getTextureResource().getTexture()
            );
            applyUV();
        }
    }

    @Override
    protected void _exitTree(){
        super._exitTree();
        if(instance != null){
            RenderingServer.getSingleton().remove(instance);
            instance = null;
        }
    }
}
