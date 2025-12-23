package CodeNameNeutronStar;

import CodeNameNeutronStar.TerrainCellResource.TerrainType;
import Game.Core.Resource;

public class TerrainGridResource extends Resource {

    private final TerrainCellResource[][] terrainCells;
    private final int width;
    private final int height;

    public TerrainGridResource(int width, int height){
        this.width = width;
        this.height = height;
        this.terrainCells = new TerrainCellResource[width][height];

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                terrainCells[x][y] = new TerrainCellResource();
            }
        }
    }

    public boolean inBounds(int x, int y) {
        return x >= 0 && y >= 0 && x < width && y < height;
    }

    private TerrainCellResource getInternalCell(int x, int y){
        if (!inBounds(x, y)) {
            return null;
        }
        return terrainCells[x][y];
    }

    public TerrainCellResource getCell(int x, int y){
        return getInternalCell(x, y);
    }

    public TerrainType getCellType(int x, int y){
        TerrainCellResource terrainCell = getInternalCell(x, y);
        if (terrainCell == null) {
            return TerrainType.VOID;
        }
        return terrainCell.getType();
    }

    public void setCellType(int x, int y, TerrainType terrainType){
        TerrainCellResource terrainCell = getInternalCell(x, y);
        if (terrainCell == null) {
            return;
        }
        terrainCell.setType(terrainType);
    }

    public int getCellUVIndex(int x, int y){
        TerrainCellResource terrainCell = getInternalCell(x, y);
        if (terrainCell == null) {
            return -1;
        }
        return terrainCell.getUVIndex();
    }

    public void setCellUVIndex(int x, int y, int uvIndex){
        TerrainCellResource terrainCell = getInternalCell(x, y);
        if (terrainCell == null) {
            return;
        }
        terrainCell.setUVIndex(uvIndex);
    }

    public void fillUV(int uvIndex){
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                terrainCells[x][y].setUVIndex(uvIndex);
            }
        }
    }


    public int getCellMovementCost(int x, int y){
        TerrainCellResource terrainCell = getInternalCell(x, y);
        if (terrainCell == null) {
            return -1;
        }
        return terrainCell.getMovementCost();
    }

    public boolean isCellWalkable(int x, int y){
        TerrainCellResource terrainCell = getInternalCell(x, y);
        if (terrainCell == null) {
            return false;
        }
        return terrainCell.isWalkable();
    }

    public int getWidth(){
        return width;
    }

    public int getHeight(){
        return height;
    }
}
