package CodeNameNeutronStar.World;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import CodeNameNeutronStar.World.TerrainCellResource.TerrainType;

public class WorldRules {

    private final Map<TerrainType, List<Integer>> rules;
    private final Random random = new Random();

    public WorldRules(){
        rules = new EnumMap<>(TerrainType.class);

        rules.put(TerrainType.VOID, List.of());
        rules.put(TerrainType.ROAD, List.of());
        rules.put(TerrainType.OFFROAD, List.of());
        rules.put(TerrainType.SHALLOW_WATER, List.of());
        rules.put(TerrainType.WATER, List.of());
        rules.put(TerrainType.BLOCKED, List.of());
    }

    public List<Integer> getIndices(TerrainType type){
        return rules.get(type);
    }

    public void setIndices(TerrainType type,List<Integer> integers){
        if(!hasRule(type)){return;}
        rules.put(type, integers);
    }

    public int getRandomIndex(TerrainType type){
        List<Integer> indices = rules.get(type);
        if (indices == null || indices.isEmpty()) return -1;
        return indices.get(random.nextInt(indices.size()));
    }

    public boolean hasRule(TerrainType type){
        return rules.containsKey(type);
    }
}
