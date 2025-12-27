package CodeNameNeutronStar.World;

import Game.Core.Node2D;

public class World2D extends Node2D{
    
    private final WorldResource worldResource;
    private Terrain2D terrain2D=null;

    public World2D(WorldResource _worldResource){
        worldResource = _worldResource;
    }

    @Override
    public void _enterTree(){
        terrain2D = new Terrain2D(worldResource.getTerrainResource());
        addChild(terrain2D);
    }

    @Override
    public void _exitTree(){
        terrain2D.queueFree();
    }

}
