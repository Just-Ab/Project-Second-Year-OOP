package CodeNameNeutronStar.Buildings.Effects.Factor;

import CodeNameNeutronStar.Buildings.BuildingEffect;
import CodeNameNeutronStar.Stats.StatsSystem;

public class CommandFactorEffect implements BuildingEffect {

    private final int amount;

    public CommandFactorEffect(int amount) {
        this.amount = amount;
    }

    @Override
    public void apply() {
        StatsSystem.getSingleton().getResource().addCommand(amount);
    }

    @Override
    public void remove() {
        StatsSystem.getSingleton().getResource().removeCommand(amount);
    }
}
