package CodeNameNeutronStar.Buildings;

import Game.Visuals.Nodes.Sprite2D;

public class Building2D extends Sprite2D {

    private final BuildingResource resource;
    private float buildProgress;
    private boolean operational;
    private float health;

    public Building2D(BuildingResource resource) {
        this.resource = resource;
        this.health = resource.getMaxHealth();

        setTexture(
            resource.getTexturePath(),
            resource.getAtlasH(),
            resource.getAtlasV()
        );

        setUV(resource.getBuildUV());
    }

    public void addBuildProgress(float delta) {
        buildProgress += delta;
    }

    public float getBuildProgress() {
        return buildProgress;
    }

    public void setOperational(boolean value) {
        operational = value;
        if (operational) {
            setUV(resource.getDoneUV());
        }
        else{
            setUV(resource.getBuildUV());
        }
    }

    public boolean isOperational() {
        return operational;
    }

    public void applyDamage(float damage) {
        health -= damage;
    }

    public void applyHeal(float amount) {
        health = Math.min(health + amount, resource.getMaxHealth());
    }

    public float getHealth() {
        return health;
    }

    public BuildingResource getResource() {
        return resource;
    }
}
