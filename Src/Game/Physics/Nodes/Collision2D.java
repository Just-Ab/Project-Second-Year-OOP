package Game.Physics.Nodes;

import org.joml.Vector3f;

import Game.Core.Node2D;
import Game.Visuals.Nodes.ColorRect2D;
import Physics.PhysicsServer;
import Physics.RectCollider;

public class Collision2D extends Node2D{
    protected RectCollider collider = null;
    protected ColorRect2D debug = new ColorRect2D();
    protected Vector3f color = new Vector3f(0.2f,0.2f,0.2f);

    protected float width=1,height=1;

    public Collision2D(){
        super();
    }



    public float getWidth(){
        return width;
    }
    public void setWidth(float _width){
        width=_width;
        debug.setLocalScale(new Vector3f(_width,debug.getLocalScale().y,debug.getLocalScale().z));
        
    }

    public float getHeight(){
        return height;
    }

    public void setHeight(float _height){
        height=_height;
        debug.setLocalScale(new Vector3f(debug.getLocalScale().x,_height,debug.getLocalScale().z)); 
    }

    public void setVisibility(boolean _visiblity){
        super.setVisibility(_visiblity);
        debug.setVisibility(_visiblity);
    }

    @Override
    public void updateEngine(float _delta){
        if(collider!=null){
            collider.setWidth(width*getGlobalScale().x);
            collider.setHeight(height*getGlobalScale().y);
        }
        debug.setVisibility(isVisible());

    }

    @Override
    protected void _enterTree(){
        super._enterTree();
        collider = PhysicsServer.getSingleton().createRectCollider(null, width, height);
        setVisibility(false);
        addChild(debug);
        if(!(parent instanceof Body2D bodyParent)){return;}
        collider.setBody(bodyParent.getBodyResource());
    }
    

}
