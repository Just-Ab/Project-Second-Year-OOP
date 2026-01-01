package CodeNameNeutronStar.Gameplay;

import java.util.ArrayList;
import java.util.List;

import org.joml.Vector2i;

import CodeNameNeutronStar.Buildings.Building2D;
import CodeNameNeutronStar.Buildings.BuildingRules;
import CodeNameNeutronStar.Buildings.BuildingSystem;
import CodeNameNeutronStar.Units.Unit2D;
import CodeNameNeutronStar.Units.UnitSystem;

public class EnemyBrain {

    private final List<Squad> squads = new ArrayList<>();

    private float squadCreateTimer = 0.0f;
    private final float squadCreateInterval = 120.0f;

    public EnemyBrain() {
    }

    public void update(float delta) {

        squadCreateTimer += delta;

        if (squadCreateTimer >= squadCreateInterval) {
            squadCreateTimer = 0.0f;
            createNewSquad();
        }

        Vector2i attackLine = getAttackLine();
        if (attackLine == null) return;

        for (Squad squad : squads) {
            squad.update(delta, attackLine);
        }
    }


    private void createNewSquad() {

        Squad squad = new Squad(2.5f);

        for (Unit2D unit :
                UnitSystem.getSingleton()
                          .getRuntimeServer()
                          .getAll()) {

            if (unit.getTeam() != GameplayRules.ENEMY_AI_TEAM_0)
                continue;

            if (unit.hasDestination())
                continue;

            squad.add(unit);
        }

        if (!squad.isEmpty()) {
            squads.add(squad);
        }
    }

    private Vector2i getAttackLine() {

        Building2D comandCenter =
            BuildingSystem.getSingleton()
                          .getRuntimeServer()
                          .getBuildingOfName(
                            BuildingRules.COMMAND_CENTER_NAME
                        );

        if (comandCenter == null) return null;

        Vector2i comandCenterPos = comandCenter.getPositionNormalized();

        return new Vector2i(comandCenterPos.x - 2, comandCenterPos.y);
    }
}
