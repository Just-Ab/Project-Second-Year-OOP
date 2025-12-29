package CodeNameNeutronStar.Buildings.Effects;

import CodeNameNeutronStar.Buildings.BuildingEffect;
import CodeNameNeutronStar.Economy.EconomySystem;
import CodeNameNeutronStar.Stats.StatsResource;
import CodeNameNeutronStar.Stats.StatsSystem;

public class CommandEffect implements BuildingEffect {

    private final float amount;

    public CommandEffect(float amount) {
        this.amount = amount;
    }

    @Override
    public void apply() {
        StatsSystem.getSingleton().getResource().addCommand(amount);
    }

    @Override
    public void remove() {
        StatsSystem.getSingleton().getResource().addCommand(amount);
    }
}
