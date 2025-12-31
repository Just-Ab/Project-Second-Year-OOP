package CodeNameNeutronStar.Buildings.Effects.Factor;

import CodeNameNeutronStar.Buildings.BuildingEffect;
import CodeNameNeutronStar.Stats.StatsSystem;

public class ObjectiveFactorEffect implements BuildingEffect {

    private final int amount;

    public ObjectiveFactorEffect(int amount) {
        this.amount = amount;
    }

    @Override
    public void apply() {
        StatsSystem.getSingleton().getResource().addObjective(amount);
    }

    @Override
    public void remove() {
        StatsSystem.getSingleton().getResource().removeObjective(amount);
    }
}
