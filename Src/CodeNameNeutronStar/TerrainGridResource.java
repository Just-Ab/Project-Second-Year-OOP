package CodeNameNeutronStar;

import CodeNameNeutronStar.TerrainCellResource.TerrainType;
import Game.Core.Resource;

public class TerrainGridResource extends Resource{
    
    private TerrainCellResource[][] mapCellsResources;
    private int width=0,height=0;

    public TerrainGridResource(int _width,int _height){
        mapCellsResources = new TerrainCellResource[_width][_height];
        width = _width;height = _height;
        for (int y = 0; y < _height; y++){
            for (int x = 0; x < _width; x++) {
                mapCellsResources[x][y]=new TerrainCellResource();
            }
        }
    }

    public TerrainCellResource getCell(int _x,int _y){
        if(_x<0||_y<0||_x>=width||_y>=height){
            return null;
        }
        return mapCellsResources[_x][_y];
    }

    public TerrainType getCellType(int _x,int _y){
        if(_x<0||_y<0||_x>=width||_y>=height){
            return TerrainType.VOID;
        }
        return mapCellsResources[_x][_y].getType();
    }

    public void setCellType(int _x,int _y,TerrainType _type){
        if(_x<0||_y<0||_x>=width||_y>=height){
            return;
        }
        mapCellsResources[_x][_y].setType(_type);
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

    public int getCellMovementCost(int _x,int _y){
        if(_x<0||_y<0||_x>=width||_y>=height){
            return -1;
        }
        return mapCellsResources[_x][_y].getMovementCost();
    }

    public boolean getCellWalkability(int _x,int _y){
        if(_x<0||_y<0||_x>=width||_y>=height){
            return false;
        }
        return mapCellsResources[_x][_y].getWalkability();
    }

    public int getWidth(){return width;}
    public int getHeight(){return height;}
}
