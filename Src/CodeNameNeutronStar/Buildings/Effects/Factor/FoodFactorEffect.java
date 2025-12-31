package CodeNameNeutronStar.Buildings.Effects.Factor;

import CodeNameNeutronStar.Buildings.BuildingEffect;
import CodeNameNeutronStar.Stats.StatsSystem;

public class FoodFactorEffect implements BuildingEffect {

    private final float amount;

    public FoodFactorEffect(float amount) {
        this.amount = amount;
    }

    @Override
    public void apply() {
        StatsSystem.getSingleton().getResource().addFoodFactor(amount);
    }

    @Override
    public void remove() {
        StatsSystem.getSingleton().getResource().removeFoodFactor(amount);
    }
}
