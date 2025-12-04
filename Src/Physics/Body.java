package Physics;

import java.util.ArrayList;
import java.util.List;

import org.joml.Vector3f;

import Game.Physics.Nodes.Body2D;

public class Body {

    protected List<RectCollider> collisions = new ArrayList<>();
    protected Vector3f position = new Vector3f();
    protected Vector3f rotation=new Vector3f(0.0f);

    protected Body2D owner=null;

    public Vector3f getPosition(){return new Vector3f(position);}
    public void setPosition(Vector3f pos){position.set(pos);}

    public void addCollider(RectCollider rect){collisions.addLast(rect);}
    public List<RectCollider> getColliders(){return collisions;}
    public void removeCollider(RectCollider rect){collisions.remove(rect);}
    public Vector3f getRotation(){return new Vector3f(rotation);}
    public void setRotation(Vector3f rot){ this.rotation.set(rot); }
    public Body2D getOwner(){return owner;}
    public void setOwner(Body2D _owner){owner=_owner;}
}
