package CodeNameNeutronStar.World;

import Game.Visuals.Resources.TilesetResource;
import java.util.ArrayList;
import java.util.List;

public class WorldServer {

    private static WorldServer server;
    private final List<WorldResource> worlds = new ArrayList<>();
    private final List<WorldRules> rules = new ArrayList<>();

    public static WorldServer getSingleton(){
        if (server == null) {
            server = new WorldServer();
        }
        return server;
    }

    public WorldResource createWorld(int width,int height,TilesetResource tileset){

        TerrainGridResource terrainGrid = new TerrainGridResource(width, height);

        PlacementsGridResource placementsGrid = new PlacementsGridResource(width, height);

        TerrainResource terrain = new TerrainResource(tileset, terrainGrid);

        PlacementsResource placements = new PlacementsResource(placementsGrid);

        WorldResource world = new WorldResource(placements,terrain,width,height);

        worlds.addLast(world);
        return world;
    }

    public WorldRules createRules(){
        rules.addLast(new WorldRules());
        return rules.getLast();
    }

    public void clear(){
        server = null;
    }
}
