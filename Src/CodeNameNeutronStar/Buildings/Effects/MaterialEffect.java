package CodeNameNeutronStar.Buildings.Effects;

import CodeNameNeutronStar.Buildings.BuildingEffect;
import CodeNameNeutronStar.Economy.EconomySystem;

public class MaterialEffect implements BuildingEffect{
    
    private final float amount;

    public MaterialEffect(float amount) {
        this.amount = amount;
    }

    public void apply(){
        EconomySystem.getSingleton().addMaterialDelta(amount);
    }

    public void remove(){
        EconomySystem.getSingleton().removeMaterialDelta(amount);

    }
}


