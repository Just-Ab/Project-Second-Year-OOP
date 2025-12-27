package CodeNameNeutronStar.Global;

import org.joml.Vector2i;

import CodeNameNeutronStar.Buildings.BuildingResource;
import Game.Core.Node;

public class BuildController {

    private BuildingResource selectedBuilding;

    public void select(BuildingResource building) {
        selectedBuilding = building;
    }

    public void cancel() {
        selectedBuilding = null;
    }

    public boolean hasSelection() {
        return selectedBuilding != null;
    }

    public void tryBuild(Vector2i tile) {
        if (selectedBuilding == null) return;

        SystemsRegistery
            .getSingleton()
            .getBuildingSystem()
            .buildBuilding(selectedBuilding, tile.x, tile.y);
    }
}
