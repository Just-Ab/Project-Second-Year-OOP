package CodeNameNeutronStar.Buildings.Effects;

import CodeNameNeutronStar.Buildings.BuildingEffect;
import CodeNameNeutronStar.Stats.StatsSystem;

public class PopularityEffect implements BuildingEffect {

    private final float amount;

    public PopularityEffect(float amount) {
        this.amount = amount;
    }

    @Override
    public void apply() {
        StatsSystem.getSingleton().getResource().addPopularity(amount);
    }

    @Override
    public void remove() {
        StatsSystem.getSingleton().getResource().removePopularity(amount);
    }
}
