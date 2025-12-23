package CodeNameNeutronStar;

import Game.Core.Resource;

public class TerrainCellResource extends Resource {

    public enum TerrainType {
        VOID(false, Integer.MAX_VALUE),
        ROAD(true, 1),
        OFFROAD(true, 2),
        SHALLOW_WATER(true, 3),
        WATER(false, Integer.MAX_VALUE),
        BLOCKED(false, Integer.MAX_VALUE);

        private final boolean walkable;
        private final int movementCost;

        TerrainType(boolean walkable, int movementCost){
            this.walkable = walkable;
            this.movementCost = movementCost;
        }

        public boolean isWalkable(){
            return walkable;
        }

        public int getMovementCost(){
            return movementCost;
        }
    }

    private TerrainType type;
    private int uvIndex;

    public TerrainCellResource(){
        this.type = TerrainType.OFFROAD;
        this.uvIndex = -1;
    }

    public TerrainCellResource(TerrainType type){
        this.type = type;
        this.uvIndex = -1;
    }

    public TerrainCellResource(TerrainType type, int uvIndex){
        this.type = type;
        this.uvIndex = uvIndex;
    }

    public TerrainType getType(){
        return type;
    }

    public void setType(TerrainType type){
        this.type = type;
    }

    public int getUVIndex(){
        return uvIndex;
    }

    public void setUVIndex(int uvIndex){
        this.uvIndex = uvIndex;
    }

    public boolean isWalkable(){
        return type.isWalkable();
    }

    public int getMovementCost(){
        return type.getMovementCost();
    }
}
