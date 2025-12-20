package CodeNameNeutronStar;

import Game.Core.Resource;

public class TerrainCellResource extends Resource {


    public enum TerrainType {
        VOID(false, Integer.MAX_VALUE),
        ROAD(true, 1),
        OFFROAD(true, 2),
        SHALLOW_WATER(true, 3),
        BLOCKED(false, Integer.MAX_VALUE);

        public final boolean walkable;
        public final int movementCost;

        TerrainType(boolean walkable, int movementCost) {
            this.walkable = walkable;
            this.movementCost = movementCost;
        }
    }


    private int uvIndex=-1;
    private TerrainType type = TerrainType.OFFROAD;

    public TerrainCellResource() {
    }

    public TerrainCellResource(TerrainType _type) {
        type = _type;
    }

    public TerrainCellResource(TerrainType _type,int _uv) {
        type = _type;
        uvIndex =_uv;
    }

    public TerrainType getType(){
        return type;
    }

    public void setType(TerrainType _Type){
        type = _Type;
    }

    public int getUVIndex(){
        return uvIndex;
    }
    public void setUVIndex(int _index){
        uvIndex = _index; 
    }

    public int getMovementCost(){
        return type.movementCost;
    }
    
    public boolean getWalkability(){
        return type.walkable;
    }

}