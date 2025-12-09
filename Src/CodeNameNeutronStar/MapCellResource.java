package CodeNameNeutronStar;

import org.joml.Vector2f;

import Game.Core.Resource;

public class MapCellResource extends Resource {
    public final int x;
    public final int y;

    public boolean walkable = true;

    public MapCellResource(int _x, int _y) {
        this.x = _x;
        this.y = _y;
    }

    public boolean isWalkable(){return walkable;}
}
