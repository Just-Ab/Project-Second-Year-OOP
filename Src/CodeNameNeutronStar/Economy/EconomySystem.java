package CodeNameNeutronStar.Economy;

public final class EconomySystem {

    private static EconomySystem system;

    public static EconomySystem getSingleton() {
        if (system == null) {
            system = new EconomySystem();
        }
        return system;
    }

    private final EconomyResource resource;

    private float goldDelta;
    private float materialDelta;
    private float foodDelta;
    private float populationDelta;

    private EconomySystem() {
        resource = new EconomyResource();
    }

    public void update(float deltaTime) {
        if (deltaTime <= 0f) return;

        resource.addGold(goldDelta * deltaTime);
        resource.addMaterial(materialDelta * deltaTime);
        resource.addFood(foodDelta * deltaTime);
        resource.addPopulation(populationDelta * deltaTime);
    }


    public EconomyResource getResource() {
        return resource;
    }

    public void addGoldDelta(float amountPerSecond) {
        goldDelta += amountPerSecond;
    }

    public void removeGoldDelta(float amountPerSecond) {
        goldDelta -= amountPerSecond;
    }

    public void addMaterialDelta(float amountPerSecond) {
        materialDelta += amountPerSecond;
    }

    public void removeMaterialDelta(float amountPerSecond) {
        materialDelta -= amountPerSecond;
    }

    public void addFoodDelta(float amountPerSecond) {
        foodDelta += amountPerSecond;
    }

    public void removeFoodDelta(float amountPerSecond) {
        foodDelta -= amountPerSecond;
    }

    public void addPopulationDelta(float amountPerSecond) {
        populationDelta += amountPerSecond;
    }

    public void removePopulationDelta(float amountPerSecond) {
        populationDelta -= amountPerSecond;
    }

    public boolean canAfford(float goldCost , float materialCost ) {
        return (goldCost <= resource.getGold() && materialCost <= resource.getMaterial());
    }

    public boolean taxResources(float goldCost , float materialCost ) {
        if (!canAfford(goldCost, materialCost)){return false;}
        resource.removeGold(goldCost); resource.removeMaterial(materialCost);
        return true;
    }

}
