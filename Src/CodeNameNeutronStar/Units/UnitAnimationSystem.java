package CodeNameNeutronStar.Units;

import java.util.List;

public class UnitAnimationSystem {

    private final UnitRunTimeServer runtime;

    public UnitAnimationSystem(UnitRunTimeServer runtime) {
        this.runtime = runtime;
    }

    public void update() {
        List<Unit2D> units = runtime.getAll();

        for (int i = 0; i < units.size(); i++) {
            Unit2D unit = units.get(i);
            updateAnimationForUnit(unit);
        }
    }

    private void updateAnimationForUnit(Unit2D unit) {

        String animationName = resolveAnimationName(unit);

        if (animationName == null) return;

        if (!animationName.equals(getActiveAnimationName(unit))) {
            unit.activateAnimation(animationName);
            unit.loop();
            unit.play();
        }
    }

    private String resolveAnimationName(Unit2D unit) {

        if (!unit.isAlive()) {
            unit.oneShot();
            return UnitRules.DIE_ANIMATION_NAME;
        }

        return switch (unit.getState()) {
            case MOVING -> UnitRules.MOVE_ANIMATION_NAME;
            case ATTACKING, DESTROYING -> UnitRules.ATTACK_ANIMATION_NAME;
            case IDLE, DEFENDING, RETREATING -> UnitRules.IDLE_ANIMATION_NAME;
            default -> UnitRules.IDLE_ANIMATION_NAME;
        };
    }

    private String getActiveAnimationName(Unit2D unit) {
        return unit.getActiveAnimation() == null ? null : unit.getActiveAnimation().getName();
    }
}
