package Game.Visuals.Resources;

import Game.Core.Resource;
import org.joml.Vector4f;

public class AtlasTextureResource extends Resource {

    private TextureResource texture;
    
    private int horizontalRegions;
    private int verticalRegions;

    public AtlasTextureResource(int _horizontalRegions, int _verticalRegions) {
        this.horizontalRegions  = _horizontalRegions;
        this.verticalRegions = _verticalRegions;

    }

    public AtlasTextureResource(String texturePath, int _horizontalRegions, int _verticalRegions) {
        this.texture = new TextureResource(texturePath);
        this.horizontalRegions  = _horizontalRegions;
        this.verticalRegions = _verticalRegions;

    }

    public void setTextureResource(String _path, int _horizontalRegions, int _verticalRegions) {
        if(texture==null){
            texture = new TextureResource();
        }
        texture.loadTexture(_path);
        this.horizontalRegions  = _horizontalRegions;
        this.verticalRegions = _verticalRegions;
    }

    public TextureResource getTextureResource() {
        return texture;
    }

    public int getHorizontalRegionsCount() { return horizontalRegions; }
    public float getHorizontalRatio() { return 1.0f/horizontalRegions; }

    public int getVerticalRegionsCount() { return verticalRegions; }
    public float getVerticalRatio() { return 1.0f/verticalRegions; }

    public Vector4f getRegionUV(int tileIndex) {
        int tileX = tileIndex % horizontalRegions;
        int tileY = tileIndex / horizontalRegions;

        float tileOriginX = (float)tileX  / horizontalRegions;
        float tileOriginY = (float)tileY / verticalRegions;

        float tileOffsetX = 1.0f  / horizontalRegions;
        float tileOffsetY = 1.0f / verticalRegions;

        return new Vector4f(tileOriginX, tileOriginY, tileOffsetX, tileOffsetY);
    }

    public int getResgionsCount() {
        return horizontalRegions * verticalRegions;
    }

    public String getTexturePath() {
        return texture.getTexturePath();
    }
}
