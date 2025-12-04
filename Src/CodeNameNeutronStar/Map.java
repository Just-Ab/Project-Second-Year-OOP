package CodeNameNeutronStar;

import org.joml.Vector2f;

import Game.Core.*;
import Game.Visuals.Nodes.*;

public class Map extends Node{
    
    protected int horizontalCellsCount=1,verticalCellsCount=1;
    protected Tilemap2D tilemap=null;
    protected MapCell[][] cells; 

    public Map(int _width,int _height){
        horizontalCellsCount = _width;
        verticalCellsCount = _height;
        tilemap = new Tilemap2D(_width, _height);
        cells = new MapCell[_width][_height];
        for (int y = 0; y < _height; y++) {
            for (int x = 0; x < _width; x++) {
                cells[x][y] = new MapCell();
                cells[x][y].position.set(new Vector2f(x,-y));
            }
        }
    }


    public MapCell getCell(int x,int y){
        if(x<0||x>=horizontalCellsCount||y<0||y>=horizontalCellsCount){return null;}
        return cells[x][y];
    }


    // public void setTileTextureIndex(int _x,int _y,char _index){
    //     short extendedIndex = (short)(_index<<8);
    //     cells[_x][_y].packedBits = (short)(cells[_x][_y].packedBits | extendedIndex);
    // }



    protected void _enterTree(){
        
    }


}
