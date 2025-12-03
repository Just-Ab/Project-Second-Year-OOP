package Game.Nodes.Physics;

import org.joml.Vector3f;

import Game.Core.Node2D;
import Game.Nodes.Visuals.ColorRect2D;
import Physics.PhysicsServer;
import Physics.RectCollider;

public class Collision2D extends Node2D{
    protected ColorRect2D colorrect = new ColorRect2D("Bounds");
    protected Vector3f color = new Vector3f(0.2f,0.2f,0.2f);
    protected RectCollider rectCollider = null;

    protected float width=1,height=1;

    public Collision2D(){
        super("Collision2D");
    }



    public float getWidth(){
        if (rectCollider==null){
            return width;
        }
        else{
            return rectCollider.getWidth();
        }    
    }
    public void setWidth(float _width){
        if (rectCollider==null){
            width=_width;
        }
        else{
            rectCollider.setWidth(_width);
        }
    }

    public float getHeight(){
        if (rectCollider==null){
            return height;
        }
        else{
            return rectCollider.getHeight();
        }    
    }
    public void setHeight(float _height){
        if (rectCollider==null){
            height=_height;
        }
        else{
            rectCollider.setHeight(_height);
        }    
    }

    public void setVisibility(boolean _visiblity){colorrect.setVisibility(_visiblity);}

    @Override
    public void _update(float _delta){
    }

    @Override
    protected void _enterTree(){
        super._enterTree();
        if(rectCollider==null){
            rectCollider = PhysicsServer.getSingleton().createRectCollider(null, width, height);
        }
        colorrect.setVisibility(false);
        addChild(colorrect);
        if(!(parent instanceof Body2D bodyParent)){return;}
        rectCollider.setBody(bodyParent.getBodyResource());
    }
    

}
