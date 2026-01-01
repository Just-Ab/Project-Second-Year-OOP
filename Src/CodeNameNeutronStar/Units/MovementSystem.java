package CodeNameNeutronStar.Units;

import java.util.List;

import org.joml.Vector2f;
import org.joml.Vector2i;

import CodeNameNeutronStar.Buildings.Building2D;
import CodeNameNeutronStar.Global.SystemsRegistery;
import CodeNameNeutronStar.Units.Unit2D.StateUnit;

public class MovementSystem {

    private final UnitRunTimeServer runtime;
    private final PathFinder pathFinder = new PathFinder();
    public MovementSystem(UnitRunTimeServer _runtime){
        runtime = _runtime;
    }

    public void update(float _delta) {

        List<Unit2D> units = runtime.getAll();

        for (Unit2D unit : units) {
            
            if (unit.getState() != StateUnit.MOVING) continue;

            Vector2i destination = unit.getDestination();

            if (destination == null) continue;

            if (!SystemsRegistery.getSingleton().getWorldSystem().isWalkable(destination.x, destination.y) ) {
                unit.setDestination(null);
                continue;
            } 

            if (unit.reachedDestination()) {
                unit.setDestination(null);
                continue;
            }
            Path path = pathFinder.findPath(unit);
            


            if (path==null) {
                unit.setDestination(null);
                unit.setTarget((Unit2D)null);
                unit.setTarget((Building2D)null);
                continue;
            }
            

            if (unit.getPositionNormalized().equals(path.getCurrentPoint())) path.advance();

            Vector2i pathDestination = path.getCurrentPoint();
            if (pathDestination == null) continue;
            float velocityX = (float) pathDestination.x - (float) unit.getPositionNormalized().x;
            float velocityY = -((float) pathDestination.y - (float) unit.getPositionNormalized().y);
            Vector2f direction = new Vector2f(velocityX,velocityY).normalize();

            unit.move(direction.mul(unit.getResource().getSpeed()).mul(_delta));

        }

        
    }

}
