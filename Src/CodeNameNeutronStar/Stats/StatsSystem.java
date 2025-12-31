package CodeNameNeutronStar.Stats;

public final class StatsSystem {

    private static StatsSystem system;

    public static StatsSystem getSingleton() {
        if (system == null) {
            system = new StatsSystem();
        }
        return system;
    }

    private final StatsResource resource;

    private StatsSystem() {
        resource = new StatsResource();
    }

    public void update(float deltaTime) {
        if (deltaTime <= 0f) return;

        int factorsAccumulation = (int) (resource.getFoodFactor() + resource.getPrideFactor() - resource.getFearFactor());
        float factorsRatio = (float )factorsAccumulation / (StatsRules.FACTORS_MAX_ACCUMULATION);
        float factorsRate = 2.0f * factorsRatio;

        resource.addPopularity(factorsRate * deltaTime);
    } 


    public StatsResource getResource() {
        return resource;
    }
}
