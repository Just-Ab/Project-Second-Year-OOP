package CodeNameNeutronStar.Interaction;

import CodeNameNeutronStar.Units.Unit2D;

public class UnitOrderSelector {

    private Unit2D selected;

    public void select(Unit2D unit) {
        selected = unit;
    }

    public void cancel() {
        selected = null;
    }

    public boolean hasSelection() {
        return selected != null;
    }

    public Unit2D getSelected() {
        return selected;
    }
}
