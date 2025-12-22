package CodeNameNeutronStar;

import Game.Visuals.Nodes.Tilemap2D;

public class Terrain2D extends Tilemap2D{
    
    TerrainResource terrainResource=null;

    public Terrain2D(TerrainResource _TerrainResource){
        super(_TerrainResource.getWidth(), _TerrainResource.getHeight());
        terrainResource = _TerrainResource;
    }

    private void build() {
        setTileset(terrainResource.getTileset());

        TerrainGridResource grid = terrainResource.getTerrainGrid();

        for (int y = 0; y < grid.getHeight(); y++) {
            for (int x = 0; x < grid.getWidth(); x++) {
                setCell(x, y, grid.getCell(x, y).getUVIndex());
            }
        }
        rebuildAll();
    }

    @Override
    protected void _enterTree() {
        super._enterTree();
        build();
    }

}
