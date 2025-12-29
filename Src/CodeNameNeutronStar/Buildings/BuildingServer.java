package CodeNameNeutronStar.Buildings;

import java.util.ArrayList;
import java.util.List;

import org.joml.Vector2i;

import CodeNameNeutronStar.Buildings.Effects.NoEffect;

public class BuildingServer {

    private static BuildingServer server;
    private final List<BuildingResource> definitions = new ArrayList<>();

    private BuildingServer() {}

    public static BuildingServer getSingleton() {
        if (server == null) {
            server = new BuildingServer();
        }
        return server;
    }

    public BuildingResource register(
        String name,
        float goldCost,
        float mateiralCost,
        float buildTime,
        float maxHealth,
        int width,
        int height,
        List<BuildingEffect> effects,
        String texturePath,
        int atlasH,
        int atlasV,
        Vector2i buildUV,
        Vector2i doneUV
    ) {
        if (effects == null) { effects = List.of(NoEffect.INSTANCE); }

        BuildingResource resource = new BuildingResource(
            name,
            goldCost,
            mateiralCost,
            buildTime,
            maxHealth,
            width,
            height,
            effects,
            texturePath,
            atlasH,
            atlasV,
            buildUV,
            doneUV
        );

        definitions.add(resource);
        return resource;
    }

    public BuildingResource getBuildingResource(String _name){
        for (BuildingResource resource : definitions) {
            if(resource.getName().equals(_name)){
                return resource;
            }
        }
        return null;
    }


    public List<BuildingResource> getAll() {
        return definitions;
    }
}
