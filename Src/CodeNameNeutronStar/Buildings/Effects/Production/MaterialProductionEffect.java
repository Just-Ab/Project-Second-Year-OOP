package CodeNameNeutronStar.Buildings.Effects.Production;

import CodeNameNeutronStar.Buildings.BuildingEffect;
import CodeNameNeutronStar.Economy.EconomySystem;

public class MaterialProductionEffect implements BuildingEffect{
    
    private final float amount;

    public MaterialProductionEffect(float amount) {
        this.amount = amount;
    }

    public void apply(){
        EconomySystem.getSingleton().addMaterialDelta(amount);
    }

    public void remove(){
        EconomySystem.getSingleton().removeMaterialDelta(amount);

    }
}


