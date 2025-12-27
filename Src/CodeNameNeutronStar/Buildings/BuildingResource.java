package CodeNameNeutronStar.Buildings;

import org.joml.Vector2i;

public class BuildingResource {

    private final String name;
    private final float goldCost;
    private final float materialCost;
    private final float buildTime;
    private final float maxHealth;
    private final int width;
    private final int height;
    private final Product product;
    private final BuildingEffect effect;

    private final String texturePath;
    private final int atlasH;
    private final int atlasV;
    private final Vector2i buildUV;
    private final Vector2i doneUV;

    BuildingResource(
        String name,
        float goldCost,
        float materialCost,
        float buildTime,
        float maxHealth,
        int width,
        int height,
        Product product,
        BuildingEffect effect,
        String texturePath,
        int atlasH,
        int atlasV,
        Vector2i buildUV,
        Vector2i doneUV
    ) {
        this.name = name;
        this.goldCost = goldCost;
        this.materialCost = materialCost;
        this.buildTime = buildTime;
        this.maxHealth = maxHealth;
        this.width = width;
        this.height = height;
        this.product = product;
        this.effect = effect;
        this.texturePath = texturePath;
        this.atlasH = atlasH;
        this.atlasV = atlasV;
        this.buildUV = buildUV;
        this.doneUV = doneUV;
    }

    public String getTexturePath() { return texturePath; }
    public int getAtlasH() { return atlasH; }
    public int getAtlasV() { return atlasV; }
    public Vector2i getBuildUV() { return buildUV; }
    public Vector2i getDoneUV() { return doneUV; }

    public String getName() { return name; }
    public float getGoldCost() { return goldCost; }
    public float getMaterialCost() { return materialCost; }
    public int getWidth() { return width; }
    public int getHeight() { return height; }
    public float getBuildTime() { return buildTime; }
    public float getMaxHealth() { return maxHealth; }
    public Product getProduct() { return product; }
    public BuildingEffect getEffect() { return effect; }
}
