package CodeNameNeutronStar.Global;


import CodeNameNeutronStar.Buildings.BuildingServer;
import CodeNameNeutronStar.Units.UnitServer;
import CodeNameNeutronStar.World.WorldServer;

public class ServersRegistery {
    
    private static ServersRegistery registery = null;


    public static ServersRegistery getSingleton(){
        if(registery == null){
            registery = new ServersRegistery();
        }
        return registery;
    }
    

    public WorldServer getWorldServer(){
        return WorldServer.getSingleton();
    }
    
    public BuildingServer getBuildingServer(){
        return BuildingServer.getSingleton();
    }

    public UnitServer getUnitServer(){
        return UnitServer.getSingleton();
    }

    public void clean(){
        getWorldServer().clear();
        getBuildingServer().clean();
        getUnitServer().clean();
        registery = null;
    }

}
