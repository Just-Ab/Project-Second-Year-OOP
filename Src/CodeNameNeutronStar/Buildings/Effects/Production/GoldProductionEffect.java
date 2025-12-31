package CodeNameNeutronStar.Buildings.Effects.Production;

import CodeNameNeutronStar.Buildings.BuildingEffect;
import CodeNameNeutronStar.Economy.EconomySystem;

public class GoldProductionEffect implements BuildingEffect{
    
    private final float amount;

    public GoldProductionEffect(float amount) {
        this.amount = amount;
    }

    public void apply(){
        EconomySystem.getSingleton().addGoldDelta(amount);
    }

    public void remove(){
        EconomySystem.getSingleton().removeGoldDelta(amount);

    }
}


