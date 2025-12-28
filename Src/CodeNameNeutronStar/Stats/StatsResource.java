package CodeNameNeutronStar.Stats;

public class StatsResource {

    private float popularity;

    public StatsResource() {
        popularity = StatsRules.START_POPULARITY;
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

}
