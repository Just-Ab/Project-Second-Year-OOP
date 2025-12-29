package CodeNameNeutronStar.Global;


import CodeNameNeutronStar.Buildings.BuildingSystem;
import CodeNameNeutronStar.Economy.EconomySystem;
import CodeNameNeutronStar.World.WorldSystem;

public class SystemsRegistery {
    
    private static SystemsRegistery registery = null;


    public static SystemsRegistery getSingleton(){
        if(registery == null){
            registery = new SystemsRegistery();
        }
        return registery;
    }
    

    public WorldSystem getWorldSystem(){
        return WorldSystem.getSingleton();
    }
    
    public BuildingSystem getBuildingSystem(){
        return BuildingSystem.getSingleton();
    }

    public EconomySystem getEconomySystem(){
        return EconomySystem.getSingleton();
    }
}
