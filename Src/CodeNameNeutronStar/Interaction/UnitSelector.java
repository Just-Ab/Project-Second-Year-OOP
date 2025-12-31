package CodeNameNeutronStar.Interaction;

import CodeNameNeutronStar.Units.UnitResource;

public class UnitSelector {

    private UnitResource selected;

    public void select(UnitResource unit) {
        selected = unit;
    }

    public void cancel() {
        selected = null;
    }

    public boolean hasSelection() {
        return selected != null;
    }

    public UnitResource getSelected() {
        return selected;
    }
}
