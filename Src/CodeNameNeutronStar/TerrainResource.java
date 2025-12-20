package CodeNameNeutronStar;

import Game.Core.Resource;
import Game.Visuals.Resources.TilesetResource;

public class TerrainResource extends Resource{
    private TilesetResource tileset = null;
    private TerrainGridResource terrainGrid = null;
    
    public TerrainResource(TilesetResource _tileset,TerrainGridResource _terrainGrid){
        tileset = _tileset;terrainGrid=_terrainGrid;
    } 

    public TilesetResource getTileset(){
        return tileset;
    }

    public TerrainGridResource getTerrainGrid(){
        return terrainGrid;
    }

    public int getWidth(){
        return terrainGrid.getWidth();
    }

    public int getHeight(){
        return terrainGrid.getHeight();
    }

}

