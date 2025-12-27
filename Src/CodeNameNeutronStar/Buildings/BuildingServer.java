package CodeNameNeutronStar.Buildings;

import java.util.ArrayList;
import java.util.List;

import org.joml.Vector2i;

public class BuildingServer {

    private static BuildingServer instance;
    private final List<BuildingResource> definitions = new ArrayList<>();

    private BuildingServer() {}

    public static BuildingServer getSingleton() {
        if (instance == null) {
            instance = new BuildingServer();
        }
        return instance;
    }

    public BuildingResource register(
        String name,
        float goldCost,
        float mateiralCost,
        float buildTime,
        float maxHealth,
        int width,
        int height,
        Product product,
        BuildingEffect effect,
        String texturePath,
        int atlasH,
        int atlasV,
        Vector2i buildUV,
        Vector2i doneUV
    ) {
        if (effect == null) { effect = NoEffect.INSTANCE; }

        BuildingResource resource = new BuildingResource(
            name,
            goldCost,
            mateiralCost,
            buildTime,
            maxHealth,
            width,
            height,
            product,
            effect,
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
