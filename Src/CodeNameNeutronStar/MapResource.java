package CodeNameNeutronStar;

import org.joml.Vector2f;

import Game.Core.Resource;

public class MapResource extends Resource{
    
    private MapCellResource[][] mapCellsResources;

    public MapResource(int _width,int _height){
        mapCellsResources = new MapCellResource[_width][_height];
        for (int y = 0; y < _height; y++) {
            for (int x = 0; x < _width; x++) {
                mapCellsResources[x][y]=new MapCellResource(x, y);
            }
        }
    }

    public MapCellResource getCell(int _x,int _y){
        return mapCellsResources[_x][_y];
    }

    public boolean isCellWalkable(int _x,int _y){
        return mapCellsResources[_x][_y].isWalkable();
    }

}
