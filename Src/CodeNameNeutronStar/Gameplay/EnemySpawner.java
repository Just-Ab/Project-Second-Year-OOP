package CodeNameNeutronStar.Gameplay;

import java.util.List;
import java.util.Random;

import org.joml.Vector2i;

import CodeNameNeutronStar.Buildings.Building2D;
import CodeNameNeutronStar.Buildings.BuildingRules;
import CodeNameNeutronStar.Buildings.BuildingRuntimeServer;
import CodeNameNeutronStar.Units.UnitResource;
import CodeNameNeutronStar.Units.UnitSystem;
import CodeNameNeutronStar.World.WorldSystem;

public class EnemySpawner {
    

    private final BuildingRuntimeServer runtime;
    private final List<UnitResource> enemyResourceList;
    private float spawnTimer = 0.0f;
    private float spawnTime = 20.0f;
    private Random random = new Random();

    public EnemySpawner(BuildingRuntimeServer _runtime,List<UnitResource> _enemyResourceList){
        runtime = _runtime;
        enemyResourceList = _enemyResourceList;
    }

    public void update(float _delta){

        spawnTimer+=_delta;
        if (spawnTimer >= spawnTime) {
            spawnEnemy(enemyResourceList.get(random.nextInt(enemyResourceList.size())));
            spawnTimer = 0.0f;
        }
    }


    public void spawnEnemy(UnitResource resource){
        for (Building2D  enemySpawnerBuilding: runtime.getBuildingsOfName(BuildingRules.ENEMY_SPAWNER_NAME)) {
            Vector2i spawnerPosition = enemySpawnerBuilding.getPositionNormalized();
            Vector2i spawnPosition = WorldSystem.getSingleton().getWalkableAdjacentCell(spawnerPosition.x, spawnerPosition.y);
            if (spawnPosition==null) continue;
            UnitSystem.getSingleton().spawnUnit(resource, GameplayRules.ENEMY_AI_TEAM_0, spawnPosition.x, spawnPosition.y);
        }
    }

}
