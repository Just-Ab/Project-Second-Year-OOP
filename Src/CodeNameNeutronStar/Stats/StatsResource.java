package CodeNameNeutronStar.Stats;

public class StatsResource {

    private float popularity;
    private float fearFactor;
    private float command;

    public StatsResource() {
        popularity = StatsRules.START_POPULARITY;
        fearFactor = StatsRules.START_FEAR_FACTOR;
        command = StatsRules.START_COMMAND;
    }

    
    public void addPopularity(float amount) {

        popularity = Math.min(popularity+amount,StatsRules.MAX_POPULARITY);
    }

    public void removePopularity(float amount) {
        popularity = Math.max(popularity-amount,StatsRules.MIN_POPULARITY);
    }

    public float getPopularity() {
        return popularity;
    }

    public void addFearFactor(float amount) {

        fearFactor = Math.min(fearFactor+amount,StatsRules.MAX_POPULARITY);
    }

    public void removeFearFactor(float amount) {
        fearFactor = Math.max(fearFactor-amount,StatsRules.MIN_POPULARITY);
    }

    public float getFearFactor() {
        return fearFactor;
    }

    public void addCommand(float amount) {

        command = Math.min(command+amount,StatsRules.MAX_COMMAND);
    }

    public void removeCommand(float amount) {
        command = Math.max(command-amount,StatsRules.MIN_COMMAND);
    }

    public float getCommand() {
        return fearFactor;
    }

}
