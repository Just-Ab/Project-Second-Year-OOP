package CodeNameNeutronStar;

import CodeNameNeutronStar.TerrainCellResource.TerrainType;

public class WorldSystem {

    private static WorldSystem system;
    private WorldResource worldResource;
    private WorldRules worldRules;

    private WorldSystem(){}

    public static WorldSystem getSingleton(){
        if (system == null) {
            system = new WorldSystem();
        }
        return system;
    }

    public void setWorld(WorldResource _worldResource,WorldRules _worldRules){
        worldResource = _worldResource;
        worldRules = _worldRules;
    }

    public WorldResource getWorld(){
        return worldResource;
    }

    public void setCellType(int x,int y,TerrainType type){
        worldResource.getTerrainResource().setCellType(x, y, type);
    }

    public void fillType(TerrainType type){
        int width = worldResource.getWidth();
        int height = worldResource.getHeight();
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                worldResource.getTerrainResource().setCellType(x, y, type);
            }        
        }
    }

    public void build(){
        int width = worldResource.getWidth();
        int height = worldResource.getHeight();

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                TerrainType type = worldResource.getTerrainResource().getCellType(x, y);
                paintTerrain(x, y, worldRules.getRandomIndex(type));
            }
        }
    }

    private void paintTerrain(int x, int y, int uvIndex){
        if (worldResource == null || uvIndex < 0) return;
        worldResource.getTerrainResource().setCellUVIndex(x, y, uvIndex);
    }

    public boolean isWalkable(int x, int y){
        if (worldResource == null) return false;

        return worldResource.getTerrainResource().isCellWalkable(x, y)
            && !worldResource.getPlacementsResource().isBlocked(x, y);
    }

    public int getMovementCost(int x, int y) {
        if (worldResource == null) return Integer.MAX_VALUE;
        return worldResource.getTerrainResource().getCellMovementCost(x, y);
    }

    public boolean canPlace(int x, int y, int width, int height){
        if (worldResource == null) return false;

        PlacementsResource placements = worldResource.getPlacementsResource();

        for (int py = y; py < y + height; py++) {
            for (int px = x; px < x + width; px++) {
                if (placements.isBlocked(px, py)) {
                    return false;
                }
            }
        }
        return true;
    }

    public void place(int x, int y, int width, int height){
        if (!canPlace(x, y, width, height)) return;
        worldResource.getPlacementsResource().blockCell(x, y, width, height);
    }

    public void removePlacement(int x, int y, int width, int height){
        if (worldResource == null) return;
        worldResource.getPlacementsResource().freeCell(x, y, width, height);
    }
}
