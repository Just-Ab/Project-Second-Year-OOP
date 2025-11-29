package Rendering;

import org.joml.*;

public class LineInstance {
    private Vector3f position = new Vector3f(0.0f);
    private float thickness = 0.0f;
    private Vector3f color = new Vector3f(1.0f);
    private float[] points = {};
    private boolean visiblity = true,dirty=true;
    private LineResource resource;
    private ShaderProgram shaderProgram;

    LineInstance(LineResource _resource,ShaderProgram _shaderProgram){
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

    public float getThickness(){
        return thickness;
    }

    public void setThickness(float _thickness){
        thickness = _thickness;
    }


    public void setVisibility(boolean _visiblity){
        visiblity = _visiblity;
    }

    public void setColor(Vector3f _color){
        color = _color;
    }

    public Vector3f getColor(){
        return color;
    }

    public void setPoints(float[] _points){points=_points;dirty=true;}
    public float[] getPoints(){return points;}

    public boolean isDirty(){
        return dirty;
    }
    public void setIsDirty(boolean _state){
        dirty=_state;
    }
    public LineResource getResource(){
        return resource;
    }

    public boolean isVisible(){return visiblity;}


}