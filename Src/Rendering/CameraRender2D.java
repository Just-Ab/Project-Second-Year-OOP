package Rendering;

import org.joml.*;

public class CameraRender2D {

    private Matrix4f projection = new Matrix4f(),view = new Matrix4f();
    private Vector3f position = new Vector3f(0.0f);
    private float width,height;
    public CameraRender2D(Vector3f _position,float _width,float _height){
        position = _position;
        width = _width;
        height=_height;
        projection.identity();
        projection.ortho(-(_width/2), _width/2, -(_height/2), _height/2, -1.0f, 1.0f);
    }

    public void setPosition(Vector3f _position){
        position.set(_position);
    }

    public void setZoom(float _x,float _y){
        projection.identity();
        projection.ortho(-(width/2)*_x, width/2*_x, -(height/2)*_y, height/2*_y, -1.0f, 1.0f);
    }
    
    public Vector3f getPosition(){return new Vector3f(position);}
    public Matrix4f getProjection(){return new Matrix4f(projection);}
    public Matrix4f getView(){return new Matrix4f(view);}


    public void update() {
        view.identity();
        view.translate(-position.x, -position.y, 0f);
    }
}
