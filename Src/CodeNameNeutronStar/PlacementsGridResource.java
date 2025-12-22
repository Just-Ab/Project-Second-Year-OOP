package CodeNameNeutronStar;

import Game.Core.Resource;

public class PlacementsGridResource extends Resource{
    
    private boolean[][] blockingGrid;
    private int width=0,height=0;

    public PlacementsGridResource(int _width,int _height){
        blockingGrid = new boolean[_width][_height];
        width = _width;height = _height;
        for (int y = 0; y < _height; y++){
            for (int x = 0; x < _width; x++) {
                blockingGrid[x][y]= false;
            }
        }
    }

    public boolean getCellBlocked(int _x,int _y){
        if(!inBounds(_x,_y)){
            return false;
        }
        return blockingGrid[_x][_y];
    }


    public void blockCell(int _x,int _y){
        if(!inBounds(_x,_y)){
            return;
        }
        blockingGrid[_x][_y] = true;
    }

    public void blockCell(int _x,int _y,int _w,int _h){
        int x,y;
        for (int yoffset = 0; yoffset < _h; yoffset++) {
            for (int xoffset = 0; xoffset < _w; xoffset++) {
                x=_x+xoffset;
                y=_y+yoffset;
                if(!inBounds(x,y)){
                    continue;
                }
                blockingGrid[x][y] = true;
            }
        }
    }
    
    public void freeCell(int _x,int _y){
        if(!inBounds(_x,_y)){
            return;
        }
        blockingGrid[_x][_y] = false;
    }

    public void freeCell(int _x,int _y,int _w,int _h){
        int x,y;
        for (int yoffset = 0; yoffset < _h; yoffset++) {
            for (int xoffset = 0; xoffset < _w; xoffset++) {
                x=_x+xoffset;
                y=_y+yoffset;
                if(!inBounds(x,y)){
                    continue;
                }
                blockingGrid[x][y] = false;
            }
        }
    }

    public int getWidth(){return width;}
    public int getHeight(){return height;}
    private boolean inBounds(int x, int y){
        return !(x < 0 || y < 0 || x >= width || y >= height);
    }

}
