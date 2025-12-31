package CodeNameNeutronStar.Buildings.Effects.Factor;

import CodeNameNeutronStar.Buildings.BuildingEffect;
import CodeNameNeutronStar.Stats.StatsSystem;

public class PrideFactorEffect implements BuildingEffect {

    private final float amount;

    public PrideFactorEffect(float amount) {
        this.amount = amount;
    }

    @Override
    public void apply() {
        StatsSystem.getSingleton().getResource().addPrideFactor(amount);
    }

    @Override
    public void remove() {
        StatsSystem.getSingleton().getResource().removePrideFactor(amount);
    }
}
