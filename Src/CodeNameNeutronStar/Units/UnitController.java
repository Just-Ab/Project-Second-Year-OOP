package CodeNameNeutronStar.Units;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import CodeNameNeutronStar.Buildings.Building2D;
import CodeNameNeutronStar.Buildings.BuildingRuntimeServer;
import CodeNameNeutronStar.Buildings.BuildingSystem;
import CodeNameNeutronStar.Units.Unit2D.StateUnit;

public class UnitController {

    private final UnitRunTimeServer unitRuntime;
    private final BuildingRuntimeServer buildingRuntime;

    public UnitController( UnitRunTimeServer _unitRunTimeServer
    ) {
        unitRuntime = _unitRunTimeServer;
        buildingRuntime = BuildingSystem.getSingleton().getRuntimeServer();
    }

    public void update(float _delta) {

        for (Unit2D unit : unitRuntime.getAll()) {

            if (unit.getState() != StateUnit.IDLE)
                continue;

            Building2D buildingTarget = findEnemyBuilding(unit);
            if (buildingTarget != null) {
                unit.setTarget(buildingTarget);
            }

            Unit2D unitTarget = findEnemyUnit(unit);
            if (unitTarget != null) {
                unit.setTarget(unitTarget);
                continue;
            }
        }
    }

    private Unit2D findEnemyUnit(Unit2D unit) {

        List<Unit2D> candidates = new ArrayList<>();

        for (Unit2D target : unitRuntime.getAll()) {

            if (target == unit)
                continue;

            if (target.getState() == StateUnit.DEAD)
                continue;

            if (target.getTeam() == unit.getTeam())
                continue;

            if (!unit.canDetectUnit(target))
                continue;

            candidates.add(target);
        }

        if (candidates.isEmpty())
            return null;

        return candidates.get(
            new Random().nextInt(candidates.size())
        );
    }


    private Building2D findEnemyBuilding(Unit2D unit) {

        List<Building2D> candidates = new ArrayList<>();

        for (Building2D building : buildingRuntime.getAll()) {

            if (!building.isOperational())
                continue;

            if (building.getTeam() == unit.getTeam())
                continue;

            if (!unit.canDetectBuilding(building))
                continue;

            candidates.add(building);
        }

        if (candidates.isEmpty())
            return null;

        return candidates.get(
            new Random().nextInt(candidates.size())
        );
    }

}
