package Rendering;

import org.joml.*;

public class CameraRender2D {

    private final Vector3f UP_DIR = new Vector3f(0.0f,1.0f,0.0f),FOR_DIR = new Vector3f(0.0f,0.0f,-2.0f);
    private Matrix4f projection = new Matrix4f(),view = new Matrix4f();
    private Vector3f position = new Vector3f(0.0f);
    public CameraRender2D(Vector3f _position,float width,float height){
        position = _position;
        projection.ortho(-(width/2), width/2, -(height/2), height/2, -1.0f, 1.0f);
    }

    public Vector3f getPosition(){return new Vector3f(position);}
    public Matrix4f getProjection(){return new Matrix4f(projection);}
    public Matrix4f getView(){return new Matrix4f(view);}

    public void update(){
        view.lookAt(position,new Vector3f(position).add(FOR_DIR),UP_DIR);
    }
}
