package CodeNameNeutronStar;

import Game.Visuals.Nodes.Tilemap2D;

public class Terrain2D extends Tilemap2D {

    private final TerrainResource terrainResource;

    public Terrain2D(TerrainResource terrainResource){
        super(terrainResource.getWidth(), terrainResource.getHeight());
        this.terrainResource = terrainResource;
    }

    private void build(){
        setTileset(terrainResource.getTileset());

        TerrainGridResource terrainGrid = terrainResource.getTerrainGrid();

        for (int y = 0; y < terrainGrid.getHeight(); y++) {
            for (int x = 0; x < terrainGrid.getWidth(); x++) {
                setCell(x, y, terrainGrid.getCellUVIndex(x, y));
            }
        }

        rebuildAll();
    }

    @Override
    protected void _enterTree(){
        super._enterTree();
        build();
    }
}
