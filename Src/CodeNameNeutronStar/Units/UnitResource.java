package CodeNameNeutronStar.Units;

import Game.Visuals.Resources.AnimationResource;

public class UnitResource {

    private final String name;
    private final float price;
    private final float maxHealth;
    private final float attackRange;
    private final float detectionRange;
    private final float damage;
    private final float speed;
    private final float cooldown;

    private final String texturePath;
    private final int atlasH;
    private final int atlasV;

    private final AnimationResource idleAnimation;
    private final AnimationResource attackAnimation;
    private final AnimationResource movementAnimation;
    private final AnimationResource deathAnimation;

    UnitResource(
        String name,
        float price,
        float maxHealth,
        float attackRange,
        float detectionRange,
        float damage,
        float speed,
        float cooldown,
        String texturePath,
        int atlasH,
        int atlasV,
        AnimationResource idleAnimation,
        AnimationResource attackAnimation,
        AnimationResource movementAnimation,
        AnimationResource deathAnimation
    ) {
        this.name = name;
        this.price = price;
        this.maxHealth = maxHealth;
        this.attackRange = attackRange;
        this.detectionRange = detectionRange;
        this.damage = damage;
        this.speed = speed;
        this.cooldown = cooldown;
        this.texturePath = texturePath;
        this.atlasH = atlasH;
        this.atlasV = atlasV;
        this.idleAnimation = idleAnimation;
        this.attackAnimation = attackAnimation;
        this.movementAnimation = movementAnimation;
        this.deathAnimation = deathAnimation;
    }

    public String getName() { return name; }
    public float getPrice() { return price; }
    public float getMaxHealth() { return maxHealth; }
    public float getaAttackRange() { return attackRange; }
    public float getDetectionRange() { return detectionRange; }

    public float getDamage() { return damage; }
    public float getSpeed() { return speed; }
    public float getCooldown() { return cooldown; }

    public String getTexturePath() { return texturePath; }
    public int getAtlasH() { return atlasH; }
    public int getAtlasV() { return atlasV; }

    public AnimationResource getIdleAnimation() { return idleAnimation; }
    public AnimationResource getAttackAnimation() { return attackAnimation; }
    public AnimationResource getMovementAnimation() { return movementAnimation; }
    public AnimationResource getDeathAnimation() { return deathAnimation; }
}
