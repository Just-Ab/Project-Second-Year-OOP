package Game.Visuals.Resources;

import Game.Core.Resource;
import org.joml.Vector4f;

public class TilesetResource extends Resource {

    private TextureResource texture;
    
    private int horizontalRegions;
    private int verticalRegions;

    public TilesetResource(String texturePath, int _horizontalRegions, int _verticalRegions) {
        this.texture = new TextureResource(texturePath);

        this.horizontalRegions  = _horizontalRegions;
        this.verticalRegions = _verticalRegions;

    }

    public TextureResource getTextureResource() {
        return texture;
    }

    public int getHorizontalTilesCount() { return horizontalRegions; }

    public int getVerticalTilesCount() { return verticalRegions; }

    public Vector4f getTileUV(int tileIndex) {
        int tileX = tileIndex % horizontalRegions;
        int tileY = tileIndex / horizontalRegions;

        float tileOriginX = (float)tileX  / horizontalRegions;
        float tileOriginY = (float)tileY / verticalRegions;

        float tileOffsetX = 1.0f  / horizontalRegions;
        float tileOffsetY = 1.0f / verticalRegions;

        return new Vector4f(tileOriginX, tileOriginY, tileOffsetX, tileOffsetY);
    }

    public int getTileCount() {
        return horizontalRegions * verticalRegions;
    }

    public String getTexturePath() {
        return texture.getTexturePath();
    }
}
