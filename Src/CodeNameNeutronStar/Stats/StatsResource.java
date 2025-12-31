package CodeNameNeutronStar.Stats;

public class StatsResource {

    private float popularity;

    private int command;

    private float fearFactor;
    private float foodFactor;
    private float prideFactor;
    private int objective;

    public StatsResource() {
        popularity = StatsRules.POPULARITY_START;

        command = StatsRules.COMMAND_START;

        fearFactor = StatsRules.FEAR_FACTOR_START;
        foodFactor = StatsRules.FOOD_FACTOR_START;
        prideFactor = StatsRules.PRIDE_FACTOR_START;

    }

    
    public void addPopularity(float amount) {

        popularity = Math.min(popularity+amount,StatsRules.POPULARITY_MAX);
    }

    public void removePopularity(float amount) {
        popularity = Math.max(popularity-amount,StatsRules.POPULARITY_MIN);
    }

    public float getPopularity() {
        return popularity;
    }


    public void addFearFactor(float amount) {

        fearFactor = Math.min(fearFactor+amount,StatsRules.FEAR_FACTOR_MAX);
    }

    public void addFoodFactor(float amount) {

        foodFactor = Math.min(foodFactor+amount,StatsRules.FOOD_FACTOR_MAX);
    }

    public void addPrideFactor(float amount) {

        prideFactor = Math.min(prideFactor+amount,StatsRules.PRIDE_FACTOR_MAX);
    }


    public void removeFearFactor(float amount) {
        fearFactor = Math.max(fearFactor-amount,StatsRules.FEAR_FACTOR_MIN);
    }

    public void removeFoodFactor(float amount) {
        foodFactor = Math.max(foodFactor-amount,StatsRules.FOOD_FACTOR_MIN);
    }

    public void removePrideFactor(float amount) {
        prideFactor = Math.max(prideFactor-amount,StatsRules.PRIDE_FACTOR_MIN);
    }


    public float getFearFactor() {
        return fearFactor;
    }

    public float getFoodFactor() {
        return foodFactor;
    }
    
    public float getPrideFactor() {
        return prideFactor;
    }


    public void addCommand(int amount) {

        command = Math.min(command+amount,StatsRules.COMMAND_MAX);
    }

    public void addObjective(int amount) {

        objective +=amount;
    }

    public void removeCommand(int amount) {
        command = Math.max(command-amount,StatsRules.COMMAND_MIN);
    }

    public void removeObjective(int amount) {
        objective -= amount;
    }

    public int getCommand() {
        return command;
    }

    public int getObjective() {
        return objective;
    }

}
