package Physics;

import java.util.ArrayList;
import java.util.List;

import org.joml.Vector3f;

public class AreaBody extends Body{

    private List<Body> currentFrameBodies = new ArrayList<>();
    private List<Body> lastFrameBodies = new ArrayList<>();


    public AreaBody(Vector3f _position){
        position = _position;
    }


    public void clearBodies(){
        lastFrameBodies.clear();
        lastFrameBodies.addAll(currentFrameBodies);
        currentFrameBodies.clear();
    }
    public void addBody(Body physicsBody){currentFrameBodies.addLast(physicsBody);}
    public List<Body> getCurrentBodies(){return currentFrameBodies;}
    public List<Body> getLastBodies(){return lastFrameBodies;}

    public boolean isColliding(){return !currentFrameBodies.isEmpty();}
    public boolean isColliding(PhysicsBody physicsBody){return currentFrameBodies.contains(physicsBody);}
    
    public boolean bodyEntered(Body physicsBody)
    {
        return (
            !lastFrameBodies.contains(physicsBody) 
            &&
            currentFrameBodies.contains(physicsBody)
        );
    }
    public boolean bodyExited(Body physicsBody)
    {
        return (
            lastFrameBodies.contains(physicsBody) 
            &&
            !currentFrameBodies.contains(physicsBody)
        );
    }
}
