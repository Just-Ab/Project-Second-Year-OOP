package CodeNameNeutronStar.Buildings;

import CodeNameNeutronStar.Economy.EconomySystem;

public class GoldEffect implements BuildingEffect{
    
    private final float amount;

    public GoldEffect(float amount) {
        this.amount = amount;
    }

    public void apply(){
        EconomySystem.getSingleton().addGoldDelta(amount);
    }

    public void remove(){
        EconomySystem.getSingleton().removeGoldDelta(amount);

    }
}


