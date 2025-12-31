package CodeNameNeutronStar.Buildings.Effects.Production;

import CodeNameNeutronStar.Buildings.BuildingEffect;
import CodeNameNeutronStar.Economy.EconomySystem;

public class FoodProductionEffect implements BuildingEffect{
    
    private final float amount;

    public FoodProductionEffect(float amount) {
        this.amount = amount;
    }

    public void apply(){
        EconomySystem.getSingleton().addFoodDelta(amount);
    }

    public void remove(){
        EconomySystem.getSingleton().removeFoodDelta(amount);

    }
}


