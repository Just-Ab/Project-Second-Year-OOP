package CodeNameNeutronStar.Buildings.Effects;

import CodeNameNeutronStar.Buildings.BuildingEffect;

public final class NoEffect implements BuildingEffect {

    public static final NoEffect INSTANCE = new NoEffect();

    private NoEffect() {}

    @Override
    public void apply() {}

    @Override
    public void remove() {}
}
