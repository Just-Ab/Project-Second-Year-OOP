package CodeNameNeutronStar;

import Game.Core.Resource;

public class MapResource extends Resource{
    
    private MapCellResource[][] mapCellsResources;
    private int width=0,height=0;
    public MapResource(int _width,int _height){
        mapCellsResources = new MapCellResource[_width][_height];
        width = _width;height = _height;
        for (int y = 0; y < _height; y++){
            for (int x = 0; x < _width; x++) {
                mapCellsResources[x][y]=new MapCellResource(x, y);
            }
        }
    }

    public MapCellResource getCell(int _x,int _y){
        if(_x<0||_y<0||_x>=width||_y>=height){
            return null;
        }
        return mapCellsResources[_x][_y];
    }

    public int getCellUVIndex(int _x,int _y){
        if(_x<0||_y<0||_x>=width||_y>=height){
            return -1;
        }
        return mapCellsResources[_x][_y].getUVIndex();
    }

    public void setCellUVIndex(int _x,int _y,int _index){
        if(_x<0||_y<0||_x>=width||_y>=height){
            return;
        }
        mapCellsResources[_x][_y].setUVIndex(_index);
    }

    public boolean isCellWalkable(int _x,int _y){
        if(_x<0||_y<0||_x>=width||_y>=height){
            return false;
        }
        return mapCellsResources[_x][_y].isWalkable();
    }

}
