package CodeNameNeutronStar.Buildings;

import java.util.ArrayList;
import java.util.List;

import org.joml.Vector2i;

public class BuildingRuntimeServer {

    private final List<Building2D> buildings = new ArrayList<>();

    public void register(Building2D building) {
        buildings.add(building);
    }

    public void unregister(Building2D building) {
        buildings.remove(building);
    }

    public List<Building2D> getBuildingsOfName(String _name){
        List<Building2D> buildings = new ArrayList<>();
        for (Building2D building2d : getAll()) {
            if (building2d.getResource().getName().equals(_name)) buildings.addLast(building2d);
        }
        return buildings;
    }

    public Building2D getBuildingOfName(String _name){
        for (Building2D building2d : getAll()) {
            if (building2d.getResource().getName().equals(_name)) return building2d;
        }
        return null;
    }

    public Building2D getBuildingAt(Vector2i position){
        for (Building2D building2d : getAll()) {
            if (building2d.getPositionNormalized().equals(position)) return building2d;
        }
        return null;
    }

    public List<Building2D> getAll() {
        return buildings;
    }
}
