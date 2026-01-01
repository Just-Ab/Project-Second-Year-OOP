package CodeNameNeutronStar.Units;

import java.util.ArrayList;
import java.util.List;

import Game.Visuals.Resources.AnimationResource;

public class UnitServer {

    private static UnitServer server;
    private final List<UnitResource> definitions = new ArrayList<>();

    private UnitServer() {}

    public static UnitServer getSingleton() {
        if (server == null) {
            server = new UnitServer();
        }
        return server;
    }

    public UnitResource register(
        String name,
        float price,
        float maxHealth,
        float attackRange,
        float detectionRange,
        float damage,
        float speed,
        float cooldown,
        String texturePath,
        int atlasH,
        int atlasV,
        AnimationResource idleAnimation,
        AnimationResource attackAnimation,
        AnimationResource movementAnimation,
        AnimationResource deathAnimation
    ) {
        UnitResource resource = new UnitResource(
            name,
            price,
            maxHealth,
            attackRange,
            detectionRange,
            damage,
            speed,
            cooldown,
            texturePath,
            atlasH,
            atlasV,
            idleAnimation,
            attackAnimation,
            movementAnimation,
            deathAnimation
        );

        definitions.add(resource);
        return resource;
    }

    public UnitResource getUnitResource(String name) {
        for (UnitResource resource : definitions) {
            if (resource.getName().equals(name)) {
                return resource;
            }
        }
        return null;
    }

    public List<UnitResource> getAll() {
        return definitions;
    }

    public void clean(){
        server = null;
    }
}
