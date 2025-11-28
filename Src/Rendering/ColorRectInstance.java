package Rendering;

import org.joml.*;

public class ColorRectInstance {
    private Vector3f position = new Vector3f(0.0f);
    private Vector3f scale = new Vector3f(1.0f);
    private Vector3f color = new Vector3f(1.0f);
    private Vector3f angle = new Vector3f(0.0f);
    private boolean visiblity = true;
    private ColorRectResource resource;
    private ShaderProgram shaderProgram;

    ColorRectInstance(ColorRectResource _resource,ShaderProgram _shaderProgram){
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

    public void setVisibility(boolean _visiblity){
        visiblity = _visiblity;
    }

    public Vector3f getRotation(){
        return angle;
    }

    public void setColor(Vector3f _color){
        color = _color;
    }

    public Vector3f getColor(){
        return color;
    }

    public ColorRectResource getResource(){
        return resource;
    }

    public boolean isVisible(){return visiblity;}


}