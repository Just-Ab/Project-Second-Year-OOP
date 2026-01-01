package CodeNameNeutronStar.Global;

import java.util.List;

import CodeNameNeutronStar.World.TerrainCellResource.TerrainType;
import CodeNameNeutronStar.World.WorldRules;
import CodeNameNeutronStar.World.WorldServer;
import Game.Core.Node;
import Game.Visuals.Resources.TilesetResource;

public class Game {
    
    private GameContext gameContext;
    private final Node root;

    public Game(Node _node){
        root = _node;
    }

    public void init(){

        TilesetResource tileset = new TilesetResource(
            "Assets/Textures/MultiSpreadSheet1X1.png",
            8,
            8
        );

        WorldRules worldRules = WorldServer.getSingleton().createRules();
        worldRules.setIndices(TerrainType.OFFROAD, List.of(8*7,8*7+1,8*7+2,8*7+3,8*7+4,8*7+5,8*7+6,8*7+7));
        
        gameContext = new GameContext(50, 50, tileset, worldRules);
        
        root.addChild(gameContext);
    }


    public void kill(){
        if (gameContext!=null)
        gameContext.queueFree();
    }

}
