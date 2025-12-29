package CodeNameNeutronStar.Buildings.Effects;

import CodeNameNeutronStar.Buildings.BuildingEffect;
import CodeNameNeutronStar.Economy.EconomySystem;

public class PopulationEffect implements BuildingEffect {

    private final float amount;

    public PopulationEffect(float amount) {
        this.amount = amount;
    }

    @Override
    public void apply() {
        EconomySystem.getSingleton().getResource().addPopulation(amount);
    }

    @Override
    public void remove() {
        EconomySystem.getSingleton().getResource().removePopulation(amount);
    }
}
