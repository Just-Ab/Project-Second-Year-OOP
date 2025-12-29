package CodeNameNeutronStar.Interaction;

import CodeNameNeutronStar.Buildings.BuildingResource;

public class BuildSelector {

    private BuildingResource selected;

    public void select(BuildingResource building) {
        selected = building;
    }

    public void cancel() {
        selected = null;
    }

    public boolean hasSelection() {
        return selected != null;
    }

    public BuildingResource getSelected() {
        return selected;
    }
}
