package CodeNameNeutronStar.Buildings;

import CodeNameNeutronStar.Economy.EconomySystem;

public class PopulationEffect implements BuildingEffect {

    private final int amount;

    public PopulationEffect(int amount) {
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
