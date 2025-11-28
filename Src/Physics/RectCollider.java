package Physics;

import org.joml.*;

public class RectCollider {
    private Vector3f dimension=new Vector3f(0.0f),offset=new Vector3f(0.0f) ,rotation=new Vector3f(0.0f);
    private float width,halfWidth,height,halfHeight;

    private Body body;

    
    public RectCollider(Body _body,float _width,float _height){
       body=_body; 
       setWidth(_width);setHeight(_height);
    }

    public Body getRigidBody(){
        return body;
    }
    

    public Vector3f getWorldPosition(){return body.getPosition().add(offset);}
    public Vector3f getDimension(){return dimension;}
    public Vector3f getOffset(){return offset;}
    public Vector3f getRotation(){return rotation;}
    public float getWidth(){return width;}
    public float getHalfWidth(){return halfWidth;}
    public float getHeight(){return height;}
    public float getHalfHeight(){return halfHeight;}

    
    
    public void setDimension(Vector3f _dimension){
        dimension.set(_dimension);
        width = _dimension.x;
        height = _dimension.y;
        halfWidth = width * 0.5f;
        halfHeight = height * 0.5f;
    }

    public void setWidth(float _width){
        width = _width;
        halfWidth = _width * 0.5f;
        dimension.x = _width;
    }

    public void setHeight(float _height){
        height = _height;
        halfHeight = _height * 0.5f;
        dimension.y = _height;
    }

    public void setHalfWidth(float _halfWidth){
        halfWidth = _halfWidth;
        width = _halfWidth * 2f;
        dimension.x = width;
    }

    public void setHalfHeight(float _halfHeight){
        halfHeight = _halfHeight;
        height = _halfHeight * 2f;
        dimension.y = height;
    }

    public void setOffset(Vector3f _offset){
        offset.set(_offset);
    }

    public void setRotation(Vector3f _rotation){
        rotation.set(_rotation);
    }

    public void setBody(Body _body){
        if(body==_body){return;}
        if(body!=null){
            body.removeCollider(this);
        }
        body = _body;
        if(_body!=null){
            body.addCollider(this);
        }
    }



}

