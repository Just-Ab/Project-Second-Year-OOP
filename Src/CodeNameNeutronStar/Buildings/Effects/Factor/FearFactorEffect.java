package CodeNameNeutronStar.Buildings.Effects.Factor;

import CodeNameNeutronStar.Buildings.BuildingEffect;
import CodeNameNeutronStar.Stats.StatsSystem;

public class FearFactorEffect implements BuildingEffect {

    private final float amount;

    public FearFactorEffect(float amount) {
        this.amount = amount;
    }

    @Override
    public void apply() {
        StatsSystem.getSingleton().getResource().addFearFactor(amount);
    }

    @Override
    public void remove() {
        StatsSystem.getSingleton().getResource().removeFearFactor(amount);
    }
}
