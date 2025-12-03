package Game.Core;

import Physics.AreaBody;
import Physics.Body;
import Physics.PhysicsServer;

public class AreaBody2D extends Body2D{

    protected AreaBody areaResource=null;

    public AreaBody2D(String name){
        super(name);
    }


    public void _bodyEntered(Body2D _body){ 
    }

    public void _bodyExited(Body2D _body){
    }

    public boolean isColliding(){
        if(areaResource!=null){
            return areaResource.isColliding();
        }
        return false;
    }

    public Body getBodyResource(){
        return areaResource;
    }
    public void setBodyResource(Body _body){
        if(_body instanceof AreaBody _areaBody){
            areaResource = _areaBody;
        }
    }

    private void resolveCollisions(){
        for (Body body : areaResource.getCurrentBodies()){
            if(areaResource.bodyEntered(body)){_bodyEntered(body.getOwner());}
        }
        for (Body body : areaResource.getLastBodies()){
            if(areaResource.bodyExited(body)){_bodyExited(body.getOwner());}
        }
    }

    @Override
    public void _update(float _delta){
        if(areaResource!=null){
            resolveCollisions();
        }
    }

    @Override
    protected void _enterTree(){
        super._enterTree();

        if(areaResource==null){
            setBodyResource(PhysicsServer.getSingleton().creatAreaBody(position));
            areaResource.setPosition(getGlobalPosition());
            areaResource.setOwner(this);
        }
    }

}
