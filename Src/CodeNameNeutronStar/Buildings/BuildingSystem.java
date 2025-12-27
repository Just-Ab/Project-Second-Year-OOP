package CodeNameNeutronStar.Buildings;

import java.util.ArrayList;
import java.util.List;

import org.joml.Vector3f;

import CodeNameNeutronStar.Economy.EconomySystem;
import CodeNameNeutronStar.World.WorldSystem;
import Game.Core.Node;

public class BuildingSystem {

    private static BuildingSystem instance;

    private Node buildingsRootNode = null;
    private final BuildingRuntimeServer runtime;
    private final BuildingsProductionSystem production;
    private final List<Building2D> constructionQueue = new ArrayList<>();
    private final List<Building2D> destructionQueue = new ArrayList<>();

    private BuildingSystem() {
        runtime = new BuildingRuntimeServer();
        production = new BuildingsProductionSystem();
    }

    public static BuildingSystem getSingleton() {
        if (instance == null) {
            instance = new BuildingSystem();
        }
        return instance;
    }

    public void damageBuilding(Building2D building, int damage) {
        building.applyDamage(damage);

        if (building.getHealth() <= 0) {
            destroyBuilding(building);
        }
    }

    public void repairBuilding(Building2D building, int amount) {
        building.applyHeal(amount);
    }

    public Building2D buildBuilding(BuildingResource resource, int gridX, int gridY) {

        if (buildingsRootNode == null) return null;
        
        WorldSystem world = WorldSystem.getSingleton();
        EconomySystem economy = EconomySystem.getSingleton();

        if (!world.canPlace(gridX, gridY, resource.getWidth(), resource.getHeight())) return null;
        if (!economy.taxResources(resource.getGoldCost(),resource.getMaterialCost())) return null;

        world.place(gridX, gridY, resource.getWidth(), resource.getHeight());

        Building2D building = new Building2D(resource);

        building.setLocalScale(
            new Vector3f(resource.getWidth(), resource.getHeight(), 1.0f)
        );

        building.setLocalPosition(
            new Vector3f(
                gridX + (resource.getWidth()  - 1) * 0.5f,
               -gridY - (resource.getHeight() - 1) * 0.5f,
                0.0f
            )
        );

        buildingsRootNode.addChild(building);

        runtime.register(building);
        constructionQueue.add(building);
        return building;
    }

    public void destroyBuilding(Building2D building) {

        BuildingResource resource = building.getResource();

        int gridX = (int) building.getLocalPosition().x;
        int gridY = (int) -building.getLocalPosition().y;

        WorldSystem.getSingleton().removePlacement(
            gridX,
            gridY,
            resource.getWidth(),
            resource.getHeight()
        );

        runtime.unregister(building);
        destructionQueue.add(building);
        building.queueFree();
    }


    public void update(float delta) {

        for (int i = constructionQueue.size() - 1; i >= 0; i--) {
            Building2D building = constructionQueue.get(i);
            BuildingResource resource = building.getResource();

            building.addBuildProgress(delta);

            if (building.getBuildProgress() >= resource.getBuildTime()) {
                building.setOperational(true);
                resource.getEffect().apply();
                constructionQueue.remove(i);
                production.register(building);
            }
        }

        for (int i = destructionQueue.size() - 1; i >= 0; i--) {
            Building2D building = destructionQueue.get(i);
            BuildingResource resource = building.getResource();

            building.setOperational(false);
            resource.getEffect().remove();
            destructionQueue.remove(i);
            production.unRegister(building);
        }
    }

    public BuildingRuntimeServer getRuntimeServer(){
        return runtime;
    }

    public void setRootNode(Node newRoot) {
        buildingsRootNode = newRoot;
    }
}
