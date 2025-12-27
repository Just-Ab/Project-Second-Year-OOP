package CodeNameNeutronStar.Economy;

public class EconomyResource {

    private float gold;
    private float material;
    private float food;
    private float population;

    public EconomyResource() {
        gold = EconomyRules.START_GOLD;
        material = EconomyRules.START_MATERIAL;
        food = EconomyRules.START_FOOD;
        population = EconomyRules.START_POPULATION;
    }

    public void addGold(float amount) {
        gold += amount;
    }

    public void removeGold(float amount) {
        gold -= amount;
    }

    public void addMaterial(float amount) {
        material += amount;
    }

    public void removeMaterial(float amount) {
        material -= amount;
    }

    public void addFood(float amount) {
        food += amount;
    }

    public void removeFood(float amount) {
        food -= amount;
    }

    public void addPopulation(float amount) {
        population += amount;
    }

    public void removePopulation(float amount) {
        population -= amount;
    }

    public float getGold() {
        return gold;
    }

    public float getMaterial() {
        return material;
    }


    public float getFood() {
        return food;
    }

    public float getPopulation() {
        return population;
    }
}
