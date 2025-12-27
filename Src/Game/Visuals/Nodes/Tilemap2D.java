package Game.Visuals.Nodes;


import org.joml.Vector3f;

import Game.Core.Node2D;
import Game.Visuals.Resources.TilesetResource;
import Rendering.RenderInstance;
import Rendering.RenderingServer;

public class Tilemap2D extends Node2D {
    private class Tile{public int index=0;RenderInstance instance=null;}
    private TilesetResource tileset=null;
    private Tile[][] tiles=null;

    private int horizontalTilesCount;
    private int verticalTilesCount;


    private boolean isReady = false;

    public Tilemap2D(int _horizontalTiles, int _verticalTiles) {
        tiles = new Tile[_horizontalTiles][_verticalTiles];
        horizontalTilesCount = _horizontalTiles;
        verticalTilesCount = _verticalTiles;

        for (int y = 0; y < _verticalTiles; y++)
            for (int x = 0; x < _horizontalTiles; x++){
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
        if(_x>=tiles.length ||_x< 0 || _y>=tiles[0].length||_y<0){return;}
        tiles[_x][_y].index = _index;

        if (!isReady) { return ;}

        buildCell(_x, _y);
    }

    @Override
    public void setLocalPosition(Vector3f _position){
        super.setLocalPosition(_position);
       if (tileset != null && isInTree) {
            rebuildAll();
        }    
    }

    @Override
    public void setLocalScale(Vector3f _scale) {
        super.setLocalScale(_scale);
        if (tileset != null && isInTree) {
            rebuildAll();
        }
    }

    protected void rebuildAll() {
        int width = horizontalTilesCount;
        int height = verticalTilesCount;

        for (int x = 0; x < width; x++)
            for (int y = 0; y < height; y++){
                buildCell(x, y);
            }
    }


    protected void buildCell(int _x, int _y) {
        int index = tiles[_x][_y].index;
        if (index < 0 || tileset == null) return;

        RenderInstance instance = tiles[_x][_y].instance;

        if (instance == null) {
            tiles[_x][_y].instance = RenderingServer.getSingleton().createSprite();
            instance = tiles[_x][_y].instance;
        }

        instance.setPosition(new Vector3f(
            getGlobalPosition().x + _x * getGlobalScale().x,
            getGlobalPosition().y - _y * getGlobalScale().y,
            getGlobalPosition().z
        ));

        instance.setScale(new Vector3f(
            getGlobalScale().x,
            getGlobalScale().y,
            1.0f
        ));

        instance.setTextureResource(
            tileset.getTextureResource().getTexture()
        );
        instance.setUV(
            tileset.getTileUV(index)
        );
    }


    public int getWidth(){return horizontalTilesCount;}
    public int getHeight(){return verticalTilesCount;}

    @Override
    public void _onGlobalPositionChanged() {
        if (isReady && tileset != null) {
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

    @Override
    protected void _exitTree() {
        super._exitTree();

        if (tiles == null) return;

        for (int x = 0; x < tiles.length; x++) {
            for (int y = 0; y < tiles[0].length; y++) {
                Tile tile = tiles[x][y];
                if (tile.instance != null) {
                    RenderingServer.getSingleton().remove(tile.instance);
                    tile.instance = null;
                }
                tile.index = -1;
            }
        }
    }


}
