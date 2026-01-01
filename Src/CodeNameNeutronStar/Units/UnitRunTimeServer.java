package CodeNameNeutronStar.Units;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.joml.Vector2i;

public class UnitRunTimeServer {

    private final List<Unit2D> units = new ArrayList<>();

    public void register(Unit2D unit) {
        units.add(unit);
    }

    public void unregister(Unit2D unit) {
        units.remove(unit);
    }

    public Unit2D getUnitAt(Vector2i _position ) {
        for (Unit2D unit2d : getAll()) {
            if(unit2d.getPositionNormalized().equals(_position)) return unit2d;
        }
        return null;
    }

    public List<Unit2D> getUnitsAt(Vector2i _position ) {
        List<Unit2D> units = new ArrayList<>(); 
        for (Unit2D unit2d : getAll()) {
            if(unit2d.getPositionNormalized().equals(_position)) units.addLast(unit2d);;
        }
        return units;
    }

    public List<Unit2D> getAll() {
        return Collections.unmodifiableList(units);
    }

    public void clean(){
        for (Unit2D unit2d : units) {
            if (unit2d.readyToQueueFree == true) {
                UnitSystem.getSingleton().destroyUnit(unit2d);
            }
        }
    }

}
