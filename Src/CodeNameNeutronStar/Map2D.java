package CodeNameNeutronStar;

import java.util.ArrayList;
import java.util.List;

import org.joml.Vector2i;
import org.joml.Vector3f;

import Game.Core.Node2D;
import Game.Visuals.Nodes.Tilemap2D;
import Game.Visuals.Resources.TilesetResource;

public class Map2D extends Node2D{
    private MapResource mapResource = null;
    private TilesetResource tilesetResource = null;
    private List<Vector2i> dirtyCells = new ArrayList<>();
    private Tilemap2D tileMap = null;
    private int width=0, height=0;


    public Map2D(int _x,int _y){
        super();
        width = _x;height = _y;        
    }


    public boolean isWalkable(int _x,int _y){
        if(mapResource!=null){
            return mapResource.isCellWalkable(_x, _y);
        }
        return false;
    }

    
    public void setCellUVIndex(int _x,int _y,int _index){
        if(mapResource!=null){
            mapResource.setCellUVIndex(_x, _y,_index);
            dirtyCells.addLast(new Vector2i(_x,_y));
        }
    }

    public Vector3f getCellWorldPosition(int _x,int _y){
        if(mapResource!=null){
            return new Vector3f(-(float)_x+getGlobalPosition().x,-(float)_y+getGlobalPosition().y,0.0f);
        }
        return new Vector3f(0.0f);    
    }

    public void setTileset(String _path,int _col,int _row){
        tilesetResource = new TilesetResource(_path, _col, _row);
        if(tileMap!=null){
            tileMap.setTileset(tilesetResource);
        }
    }

    @Override
    protected void updateEngine(float _delta){
        super.updateEngine(_delta);
        int x=0,y=0;
        for (Vector2i cellPosition : dirtyCells) {
            x = cellPosition.x;y = cellPosition.y;
            tileMap.setCell(cellPosition.x, cellPosition.y, mapResource.getCellUVIndex(x,y));
        }
        if(!dirtyCells.isEmpty()){
            dirtyCells.clear();
        }
        
    }


    @Override
    protected void _enterTree(){
        if(mapResource==null){mapResource = new MapResource(width, height);}
        if(tileMap==null){
            tileMap = new Tilemap2D(width, height);
            tileMap.setLocalPosition(getLocalPosition());
        }
        if(tilesetResource!=null){
            tileMap.setTileset(tilesetResource);
        }
        addChild(tileMap);
    }

    @Override
    protected void _exitTree(){
        mapResource = null;
    }
}
