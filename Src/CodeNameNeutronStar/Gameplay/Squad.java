package CodeNameNeutronStar.Gameplay;

import java.util.*;

import org.joml.Vector2i;

import CodeNameNeutronStar.Units.Unit2D;
import CodeNameNeutronStar.Units.UnitSystem;
import CodeNameNeutronStar.World.WorldSystem;

class Squad {

    private final List<Unit2D> units = new ArrayList<>();
    private final Map<Unit2D, Integer> yOffsets = new HashMap<>();

    private float orderTimer = 0.0f;
    private final float orderInterval;

    private final Random random = new Random();

    public Squad(float interval) {
        this.orderInterval = interval;
    }

    public void add(Unit2D unit) {
        units.add(unit);

        yOffsets.put(unit, random.nextInt(-4, 5));
    }

    public boolean isEmpty() {
        return units.isEmpty();
    }

    public void update(float delta, Vector2i attackLine) {

        orderTimer += delta;
        if (orderTimer < orderInterval) return;
        orderTimer = 0.0f;

        WorldSystem world = WorldSystem.getSingleton();

        for (Unit2D unit : units) {

            if (!unit.isAlive()) continue;

            int offset = yOffsets.getOrDefault(unit, 0);

            Vector2i target =
                world.getWalkableAdjacentCell(
                    attackLine.x,
                    attackLine.y + offset
                );

            if (target == null) continue;

            UnitSystem.getSingleton()
                      .orderMovement(unit, target.x, target.y);
        }
    }
}
