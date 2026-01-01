package CodeNameNeutronStar.Units;

import Game.Visuals.Nodes.AnimatedSprite2D;
import org.joml.Vector2f;
import org.joml.Vector2i;
import CodeNameNeutronStar.Buildings.Building2D;
import CodeNameNeutronStar.Buildings.BuildingSystem;
import CodeNameNeutronStar.Gameplay.GameplayRules;
import CodeNameNeutronStar.Stats.StatsSystem;

public class Unit2D extends AnimatedSprite2D {

    public enum StateUnit {
        IDLE,
        MOVING,
        ATTACKING,
        DEFENDING,
        RETREATING,
        DEAD,
        DESTROYING
    }

    private final UnitResource resource;
    private StateUnit state;
    private float health;
    private float attackTimer = 0f;

    private Vector2i destination;

    private Unit2D unitTarget;
    private Building2D buildingTarget;

    private int team=-1;
    
    public boolean readyToQueueFree = false;

    public Unit2D(UnitResource resource, int _team) {
        this.resource = resource;
        this.health = resource.getMaxHealth();
        this.state = StateUnit.IDLE;
        this.team = _team;

        setTexture(resource.getTexturePath(), resource.getAtlasH(), resource.getAtlasV());

        createAnimation(
                resource.getIdleAnimation().getName(),
                resource.getIdleAnimation().getStartingFrame(),
                resource.getIdleAnimation().getEndingFrame()
        );
        createAnimation(
                resource.getMovementAnimation().getName(),
                resource.getMovementAnimation().getStartingFrame(),
                resource.getMovementAnimation().getEndingFrame()
        );
        createAnimation(
                resource.getAttackAnimation().getName(),
                resource.getAttackAnimation().getStartingFrame(),
                resource.getAttackAnimation().getEndingFrame()
        );
        createAnimation(
                resource.getDeathAnimation().getName(),
                resource.getDeathAnimation().getStartingFrame(),
                resource.getDeathAnimation().getEndingFrame()
        );
    }


    @Override
    public void _animationEnded(){
        if (activeAnimation.getName() == UnitRules.DIE_ANIMATION_NAME){
            readyToQueueFree = true;
        }
    }

    @Override
    protected void _enterTree() {
        super._enterTree();
    }

    public UnitResource getResource() {
        return resource;
    }

    public StateUnit getState() {
        return state;
    }

    public void setState(StateUnit state) {
        this.state = state;
    }

    public float getHealth() {
        return health;
    }

    public boolean isAlive() {
        return health>0;
    }

    public void applyDamage(float amount) {
        health -= amount;

    }

    public void applyHeal(float amount) {
        health = Math.min(health + amount, resource.getMaxHealth());
    }

    public void performAttack(){
        if (unitTarget!=null) {
            if (getTeam()==GameplayRules.PLAYER_TEAM) UnitSystem.getSingleton().damageUnit(unitTarget, resource.getDamage() - resource.getDamage() * StatsSystem.getSingleton().getResource().getFearFactor());
            else UnitSystem.getSingleton().damageUnit(unitTarget, resource.getDamage() );
            if (!unitTarget.isAlive()) unitTarget = null;
        }
        
        if (buildingTarget!=null) {
            if (getTeam()==GameplayRules.PLAYER_TEAM) BuildingSystem.getSingleton().damageBuilding(buildingTarget, resource.getDamage() - resource.getDamage() * StatsSystem.getSingleton().getResource().getFearFactor());
            else BuildingSystem.getSingleton().damageBuilding(buildingTarget, resource.getDamage() );            
            if (!buildingTarget.isOperational()) buildingTarget = null;
        }
    }

    public void addAttackTimer(float delta) {
        attackTimer += delta;
    }

    public boolean canAttack() {
        return attackTimer >= resource.getCooldown();
    }

    public void resetAttackTimer() {
        attackTimer = 0f;
    }

    public Vector2i getPositionNormalized() {
        return new Vector2i(
                (int) Math.floor(getGlobalPosition().x + 0.5f),
                (int) Math.floor(-getGlobalPosition().y + 0.5f)
        );
    }

    public void move(Vector2f velocity) {
        setGlobalPosition(getGlobalPosition().add(velocity.x, velocity.y, 0.0f));
    }

    public void setTeam(int _team) {team = _team;}

    public int getTeam() {return team;}

    public void setDestination(Vector2i dest) {
        if (dest == null) {
            destination = null;
        } else {
            if (destination == null) {
                destination = new Vector2i(dest);
            } else {
                destination.set(dest);
            }
        }
    }

    public Vector2i getDestination() {
        return destination;
    }

    public boolean hasDestination() {
        return destination != null;
    }

    public boolean reachedDestination() {
        return destination != null &&
               getPositionNormalized().equals(destination);
    }

    public Vector2i getTargetDestination(){
        if (unitTarget!=null) {
            return unitTarget.getPositionNormalized();
        }
        
        if (buildingTarget!=null) {
            return buildingTarget.getPositionNormalized();
        }
        return null;
    }

    public void clearTarget() {
        unitTarget = null;
        buildingTarget = null;
    }

    public void setTarget(Unit2D target) {
        unitTarget = target;
        buildingTarget = null;
    }

    public void setTarget(Building2D target) {
        buildingTarget = target;
        unitTarget = null;
    }

    public boolean hasUnitTarget() {
        return unitTarget != null;
    }

    public boolean hasBuildingTarget() {
        return buildingTarget != null;
    }

    public boolean hasTarget() {
        return hasUnitTarget() || hasBuildingTarget();
    }

    public Vector2i getTargetPosition() {
        if (hasUnitTarget())
            return unitTarget.getPositionNormalized();
        if (hasBuildingTarget())
            return buildingTarget.getPositionNormalized();
        return null;
    }

    public boolean canDetectUnit(Unit2D _unit) {
        Vector2i targetPos = _unit.getPositionNormalized();

        Vector2i delta = new Vector2i(getPositionNormalized()).sub(targetPos);

        return delta.length() <= resource.getDetectionRange();
    }

    public boolean canDetectBuilding(Building2D _building) {
        Vector2i targetPos = _building.getPositionNormalized();

        Vector2i delta = new Vector2i(getPositionNormalized()).sub(targetPos);

        return delta.length() <= resource.getDetectionRange();
    }

    public boolean isTargetInRange() {
        Vector2i targetPos = getTargetPosition();
        if (targetPos == null)
            return false;

        Vector2i delta = new Vector2i(getPositionNormalized()).sub(targetPos);
        return delta.length() <= resource.getaAttackRange();
    }

}
