package CodeNameNeutronStar.Global;


import CodeNameNeutronStar.Buildings.BuildingServer;
import CodeNameNeutronStar.World.WorldServer;

public class ServersRegistery {
    
    private static ServersRegistery instance = null;


    public static ServersRegistery getSingleton(){
        if(instance == null){
            instance = new ServersRegistery();
        }
        return instance;
    }
    

    public WorldServer getWorldServer(){
        return WorldServer.getSingleton();
    }
    
    public BuildingServer getBuildingServer(){
        return BuildingServer.getSingleton();
    }

}
