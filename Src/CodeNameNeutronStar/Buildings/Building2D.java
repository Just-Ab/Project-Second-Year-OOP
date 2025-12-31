package CodeNameNeutronStar.Buildings;

import org.joml.Vector2i;

import Game.Visuals.Nodes.Sprite2D;

public class Building2D extends Sprite2D {

    private final BuildingResource resource;
    private float buildProgress;
    private boolean operational;
    private float health;

    private int team=-1;

    public Building2D(BuildingResource resource, int _team) {
        this.resource = resource;
        this.health = resource.getMaxHealth();
        team = _team;

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

    public Vector2i getPositionNormalized() {
        return new Vector2i(
                (int) Math.floor(getGlobalPosition().x + 0.5f),
                -(int) Math.floor(getGlobalPosition().y + 0.5f)
        );
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

    public void applyDamage(float amount) {
        health -= amount;
    }

    public void applyHeal(float amount) {
        health = Math.min(health + amount, resource.getMaxHealth());
    }

    public float getHealth() {
        return health;
    }

    public int getTeam() {
        return team;
    }

    public void setTeam(int _team) {
        team = _team;
    }

    public BuildingResource getResource() {
        return resource;
    }
}
