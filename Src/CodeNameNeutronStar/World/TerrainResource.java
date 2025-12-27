package CodeNameNeutronStar.World;

import CodeNameNeutronStar.World.TerrainCellResource.TerrainType;
import Game.Core.Resource;
import Game.Visuals.Resources.TilesetResource;

public class TerrainResource extends Resource {

    private final TilesetResource tileset;
    private final TerrainGridResource terrainGrid;

    public TerrainResource(TilesetResource tileset, TerrainGridResource terrainGrid){
        this.tileset = tileset;
        this.terrainGrid = terrainGrid;
    }

    public boolean isCellWalkable(int x, int y){
        return terrainGrid.isCellWalkable(x, y);
    }

    public int getCellMovementCost(int x, int y){
        return terrainGrid.getCellMovementCost(x, y);
    }

    public TerrainType getCellType(int x, int y){
        return terrainGrid.getCellType(x, y);
    }

    public void setCellType(int x, int y, TerrainType terrainType){
        terrainGrid.setCellType(x, y, terrainType);
    }

    public int getCellUVIndex(int x, int y){
        return terrainGrid.getCellUVIndex(x, y);
    }

    public void setCellUVIndex(int x, int y, int uvIndex){
        terrainGrid.setCellUVIndex(x, y, uvIndex);
    }

    public TilesetResource getTileset(){
        return tileset;
    }

    public int getWidth(){
        return terrainGrid.getWidth();
    }

    public int getHeight(){
        return terrainGrid.getHeight();
    }

    public TerrainGridResource getTerrainGrid(){
        return terrainGrid;
    }
}
