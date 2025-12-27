package CodeNameNeutronStar.Global;

import CodeNameNeutronStar.Buildings.BuildingResource;
import CodeNameNeutronStar.Buildings.BuildingSystem;
import CodeNameNeutronStar.World.WorldSystem;

public class InteractionSystem {

    public enum InteractionMode {
        ROAMING,
        BUILDING_SELECTION,
        BUILDING_PLACEMENT,
        UNITS_SELECTION,
        UNITS_COMMAND
    }

    private InteractionMode currentMode = InteractionMode.ROAMING;

    private BuildingResource selectedBuilding;

    private BuildingSystem buildingSystem;
    private WorldSystem worldSystem;

    public void initialize(BuildingSystem buildingSystem, WorldSystem worldSystem) {
        this.buildingSystem = buildingSystem;
        this.worldSystem = worldSystem;
    }

    public InteractionMode getMode() {
        return currentMode;
    }

    public boolean isPlacingBuilding() {
        return currentMode == InteractionMode.BUILDING_PLACEMENT;
    }

    public void enterRoamingMode() {
        selectedBuilding = null;
        currentMode = InteractionMode.ROAMING;
    }

    public void enterBuildingSelectionMode() {
        currentMode = InteractionMode.BUILDING_SELECTION;
    }

    public void selectBuildingForPlacement(BuildingResource building) {
        selectedBuilding = building;
        currentMode = InteractionMode.BUILDING_PLACEMENT;
    }

    public void onWorldClick(int tileX, int tileY) {
        if (currentMode != InteractionMode.BUILDING_PLACEMENT) return;
        if (selectedBuilding == null) return;
        if (!worldSystem.isInside(tileX, tileY)) return;

        buildingSystem.buildBuilding(selectedBuilding, tileX, tileY);
        enterRoamingMode();
    }

    public void cancelCurrentAction() {
        enterRoamingMode();
    }
}
