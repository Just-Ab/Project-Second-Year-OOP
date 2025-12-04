package Game.Visuals.Nodes;

import java.util.ArrayList;
import java.util.List;

import org.joml.Vector3f;

import Game.Core.Node2D;
import Game.Visuals.Resources.TilesetResource;
import Rendering.RenderInstance;
import Rendering.RenderingServer;

public class Tilemap2D extends Node2D {
    private class Tile{public int index=0;RenderInstance instance=null;}
    private TilesetResource tileset;
    private Tile[][] tiles;

    private int horizontalTilesCount;
    private int verticalTilesCount;


    private boolean isReady = false;

    public Tilemap2D(int _horizontalTiles, int _verticalTiles) {
        tiles = new Tile[_horizontalTiles][_verticalTiles];
        horizontalTilesCount = _horizontalTiles;
        verticalTilesCount = _verticalTiles;

        for (int x = 0; x < _verticalTiles; x++)
            for (int y = 0; y < _horizontalTiles; y++){
                tiles[x][y] = new Tile();
                tiles[x][y].index = -1;
            }
    }

    public void setTileset(String _texturePath,int _verticalRegions,int _horizontalRegions) {
        tileset = new TilesetResource(_texturePath, _horizontalRegions, _verticalRegions);
        if (isReady) {
            rebuildAll();
        }
    }

    public void setTileset(TilesetResource _tileSet) {
        tileset = _tileSet;
        if (isReady) {
            rebuildAll();
        }
    }

    public void setCell(int _x, int _y, int _index) {
        tiles[_x][_y].index = _index;

        if (!isReady) { return ;}

        buildCell(_x, _y);
    }

    @Override
    public void setLocalPosition(Vector3f _position){
        super.setLocalPosition(_position);
       if (tileset != null) {
            rebuildAll();
        }    
    }

    @Override
    protected void _enterTree() {
        super._enterTree();
        isReady=true;
        if (tileset != null) {
            rebuildAll();
        }
    }

    private void rebuildAll() {
        int width = horizontalTilesCount;
        int height = verticalTilesCount;

        for (int x = 0; x < width; x++)
            for (int y = 0; y < height; y++){
                buildCell(x, y);
            }
    }


    private void buildCell(int _x, int _y) {
        int index = tiles[_x][_y].index;

        if (index < 0 || tileset == null) return;
        

        RenderInstance instance = tiles[_x][_y].instance;

        if(instance==null){
            tiles[_x][_y].instance = RenderingServer.getSingleton().createSprite();
            instance = tiles[_x][_y].instance;
        }

        instance.setPosition(new Vector3f(getGlobalPosition().x+_x,getGlobalPosition().y-_y,getGlobalPosition().z));
        instance.setTextureResource(tileset.getTextureResource().getTexture());
        instance.setUV(tileset.getTileUV(index));
    }
}
