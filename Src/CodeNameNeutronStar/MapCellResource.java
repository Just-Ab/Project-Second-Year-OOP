package CodeNameNeutronStar;

import Game.Core.Resource;

public class MapCellResource extends Resource {
    public final int x;
    public final int y;
    public int uVIndex;

    public boolean walkable = true;

    public MapCellResource(int _x, int _y) {
        this.x = _x;
        this.y = _y;
    }


    public int getUVIndex(){
        return uVIndex;
    }

    public void setUVIndex(int _index){
        uVIndex = _index; 
    }

    public boolean isWalkable(){return walkable;}
}
