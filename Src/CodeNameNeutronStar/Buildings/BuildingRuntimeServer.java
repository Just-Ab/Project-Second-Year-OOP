package CodeNameNeutronStar.Buildings;

import java.util.ArrayList;
import java.util.List;

public class BuildingRuntimeServer {

    private final List<Building2D> buildings = new ArrayList<>();

    public void register(Building2D building) {
        buildings.add(building);
    }

    public void unregister(Building2D building) {
        buildings.remove(building);
    }

    public List<Building2D> getAll() {
        return buildings;
    }
}
