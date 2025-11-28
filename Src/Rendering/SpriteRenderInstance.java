package Rendering;

import org.joml.*;

public class SpriteRenderInstance {
    private Vector3f position = new Vector3f(0.0f);
    private Vector3f scale = new Vector3f(1.0f);
    private Vector3f angle = new Vector3f(0.0f);
    private boolean visiblity = true;
    private TextureResource textureResource = new TextureResource();
    private QuadMeshResource resource;
    private Vector4f uv = new Vector4f(0.0f,0.0f,1.0f,1.0f);
    private ShaderProgram shaderProgram;

    SpriteRenderInstance(QuadMeshResource _resource,ShaderProgram _shaderProgram){
        resource = _resource;
        shaderProgram = _shaderProgram;
    }

    public void setShaderProgram(ShaderProgram _shaderProgram){
        shaderProgram = _shaderProgram;
    }

    public ShaderProgram getShaderProgram(){
        return shaderProgram;
    }

    public void setPosition(Vector3f _position){
        position = _position;
    }

    public Vector3f getPosition(){
        return position;
    }

    public void setScale(Vector3f _scale){
        scale = _scale;
    }

    public Vector3f getScale(){
        return scale;
    }

    public void setRotation(Vector3f _angle){
        angle = _angle;
    }

    public Vector3f getRotation(){
        return angle;
    }

    public void setUV(Vector4f _uv){
        uv = _uv;
    }

    public Vector4f getUV(){
        return uv;
    }

    public void setTexture(TextureResource _textureResource){
        textureResource = _textureResource;
    }
    

    public TextureResource getTextureResource(){
        return textureResource;
    }

    public QuadMeshResource getResource(){
        return resource;
    }

    public void setVisibility(boolean _visiblity){visiblity = _visiblity;}

    public boolean isVisible(){return visiblity;}


}