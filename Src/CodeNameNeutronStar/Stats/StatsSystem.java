package CodeNameNeutronStar.Stats;

public final class StatsSystem {

    private static StatsSystem instance;

    public static StatsSystem getSingleton() {
        if (instance == null) {
            instance = new StatsSystem();
        }
        return instance;
    }

    private final StatsResource resource;

    private float popularityDelta;

    private StatsSystem() {
        resource = new StatsResource();
    }

    public void update(float deltaTime) {
        if (deltaTime <= 0f) return;

        resource.addPopularity(popularityDelta * deltaTime);
    }


    public StatsResource getResource() {
        return resource;
    }

    public void addPopularityDelta(float amountPerSecond) {
        popularityDelta += amountPerSecond;
    }

    public void removePopularityDelta(float amountPerSecond) {
        popularityDelta -= amountPerSecond;
    }

}
