package CodeNameNeutronStar.World;

import java.util.HashMap;
import java.util.Map;

import org.joml.Vector2i;

import CodeNameNeutronStar.Buildings.Building2D;
import CodeNameNeutronStar.World.TerrainCellResource.TerrainType;

public class WorldSystem {

    private static WorldSystem system;
    private WorldResource worldResource;
    private WorldRules worldRules;
    public record CellPos(int x, int y) {}
    private Map<CellPos,Building2D> buildingByCell = new HashMap<>();

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

    public boolean hasWorld() {
        return worldResource != null && worldRules != null;
    }


    public WorldResource getWorld(){
        return worldResource;
    }

    public void setCellType(int x, int y, TerrainType type){
        if (!hasWorld()) return;
        worldResource.getTerrainResource().setCellType(x, y, type);
    }


    public void fillType(TerrainType type){
        if (!hasWorld()) return;

        int width = worldResource.getWidth();
        int height = worldResource.getHeight();

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                worldResource.getTerrainResource().setCellType(x, y, type);
            }
        }
    }


    public void build(){
        if (!hasWorld()) return;

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
        if (!worldResource.getTerrainResource().getTerrainGrid().inBounds(x, y)) return false;
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

    public void registerBuilding(Building2D building, int x, int y, int width, int height) {
        for (int py = y; py < y + height; py++) {
            for (int px = x; px < x + width; px++) {

                buildingByCell.put(new CellPos(px, py), building);

            }
        }
    }

    public void unregisterBuilding(Building2D building) {

        CellPos[] keys = buildingByCell.keySet().toArray(new CellPos[0]);

        for (CellPos cell : keys) {
            if (buildingByCell.get(cell) == building) {
                buildingByCell.remove(cell);
            }
        }
    }

    public Building2D getBuildingAt(int x,int y) {
        return buildingByCell.get(new CellPos(x,y));
    }

    public Map<CellPos,Building2D> getBuildingMap() {
        return buildingByCell;
    }

    public boolean isInside(int x, int y){
        return !(x<0||x>=getWorld().getWidth()||y<0||y>=getWorld().getHeight());
    }

    public void removePlacement(int x, int y, int width, int height){
        if (worldResource == null) return;
        worldResource.getPlacementsResource().freeCell(x, y, width, height);
    }
}
