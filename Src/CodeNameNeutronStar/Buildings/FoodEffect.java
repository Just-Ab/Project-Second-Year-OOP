package CodeNameNeutronStar.Buildings;

import CodeNameNeutronStar.Economy.EconomySystem;

public class FoodEffect implements BuildingEffect{
    
    private final float amount;

    public FoodEffect(float amount) {
        this.amount = amount;
    }

    public void apply(){
        EconomySystem.getSingleton().addFoodDelta(amount);
    }

    public void remove(){
        EconomySystem.getSingleton().removeFoodDelta(amount);

    }
}


