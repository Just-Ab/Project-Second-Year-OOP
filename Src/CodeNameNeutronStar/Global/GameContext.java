package CodeNameNeutronStar.Global;


import java.util.List;

import org.joml.*;


import CodeNameNeutronStar.Buildings.BuildingRules;
import CodeNameNeutronStar.Buildings.Effects.CommandEffect;
import CodeNameNeutronStar.Buildings.Effects.FearFactorEffect;
import CodeNameNeutronStar.Buildings.Effects.FoodEffect;
import CodeNameNeutronStar.Buildings.Effects.GoldEffect;
import CodeNameNeutronStar.Buildings.Effects.MaterialEffect;
import CodeNameNeutronStar.Buildings.Effects.PopularityEffect;
import CodeNameNeutronStar.Buildings.Effects.PopulationEffect;
import CodeNameNeutronStar.Economy.EconomyRules;
import CodeNameNeutronStar.Interaction.InteractionController;
import CodeNameNeutronStar.Interaction.InteractionSystem;
import CodeNameNeutronStar.World.World2D;
import CodeNameNeutronStar.World.WorldResource;
import CodeNameNeutronStar.World.WorldRules;
import CodeNameNeutronStar.World.TerrainCellResource.TerrainType;
import Game.Core.Node;
import Game.Visuals.Resources.TilesetResource;

public class GameContext extends Node {

    private final SystemsRegistery systems = SystemsRegistery.getSingleton();
    private final ServersRegistery servers = ServersRegistery.getSingleton();
    private final InteractionSystem interactionSystem = InteractionSystem.getSingleton();
    private final InteractionController interactionController = new InteractionController(this);

    private World2D world2D = null;

    public GameContext(int _width, int _height, TilesetResource _tileset, WorldRules _worldRules) {
        initSystems(_width,_height,_tileset,_worldRules);
        registerBuildings();
    }

    public ServersRegistery getServers(){return servers;}


    public SystemsRegistery getSystems(){return systems;}


    private void initSystems(int _width,int _height,TilesetResource _tileset, WorldRules _WorldRules){
        WorldResource worldResource = servers.getWorldServer().createWorld(_width, _height, _tileset);
        systems.getWorldSystem().setWorld(worldResource, _WorldRules);
        
        systems.getWorldSystem().fillType(TerrainType.OFFROAD);

        systems.getWorldSystem().build();

        systems.getEconomySystem().getResource().addGold(EconomyRules.START_GOLD);
        systems.getEconomySystem().getResource().addFood(EconomyRules.START_FOOD);
        systems.getEconomySystem().getResource().addMaterial(EconomyRules.START_MATERIAL);
        systems.getEconomySystem().getResource().addPopulation(EconomyRules.START_POPULATION);
        systems.getBuildingSystem().setRootNode(this);

    }
  

    private void registerBuildings(){

        servers.getBuildingServer().register(
            BuildingRules.HOUSE_NAME,
            EconomyRules.HOUSE_GOLD_COST,
            EconomyRules.HOUSE_MATERIAL_COST,
            BuildingRules.HOUSE_BUILD_TIME,
            BuildingRules.HOUSE_HEALTH,
            BuildingRules.HOUSE_WIDTH,
            BuildingRules.HOUSE_HEIGHT,
            List.of(new PopulationEffect(EconomyRules.HOUSE_POPULATION_BONUS)),
            BuildingRules.BUILDING1X1_TILESET_PATH,
            BuildingRules.BUILDING1X1_TILESET_H,
            BuildingRules.BUILDING1X1_TILESET_V,
            new Vector2i(BuildingRules.HOUSE_BUILDING_ATLASX,BuildingRules.HOUSE_BUILDING_ATLASY),
            new Vector2i(BuildingRules.HOUSE_DONE_ATLASX,BuildingRules.HOUSE_DONE_ATLASY)
        );

        servers.getBuildingServer().register(
            BuildingRules.GOLD_MINE_NAME,
            EconomyRules.GOLD_MINE_GOLD_COST,
            EconomyRules.GOLD_MINE_MATERIAL_COST,
            BuildingRules.GOLD_MINE_BUILD_TIME,
            BuildingRules.GOLD_MINE_HEALTH,
            BuildingRules.GOLD_MINE_WIDTH,
            BuildingRules.GOLD_MINE_HEIGHT,
            List.of(new GoldEffect(EconomyRules.GOLD_MINE_GOLD_DELTA)),
            BuildingRules.BUILDING1X1_TILESET_PATH,
            BuildingRules.BUILDING1X1_TILESET_H,
            BuildingRules.BUILDING1X1_TILESET_V,
            new Vector2i(BuildingRules.GOLD_MINE_BUILDING_ATLASX,BuildingRules.GOLD_MINE_BUILDING_ATLASY),
            new Vector2i(BuildingRules.GOLD_MINE_DONE_ATLASX,BuildingRules.GOLD_MINE_DONE_ATLASY)
        );

        servers.getBuildingServer().register(
            BuildingRules.COMMAND_CENTER_NAME,
            EconomyRules.COMMAND_CENTER_GOLD_COST,
            EconomyRules.COMMAND_CENTER_MATERIAL_COST,
            BuildingRules.COMMAND_CENTER_BUILD_TIME,
            BuildingRules.COMMAND_CENTER_HEALTH,
            BuildingRules.COMMAND_CENTER_WIDTH,
            BuildingRules.COMMAND_CENTER_HEIGHT,
            List.of(new CommandEffect(EconomyRules.COMMAND_CENTER_COMMAND_BONUS)),
            BuildingRules.BUILDING2X2_TILESET_PATH,
            BuildingRules.BUILDING2X2_TILESET_H,
            BuildingRules.BUILDING2X2_TILESET_V,
            new Vector2i(BuildingRules.COMMAND_CENTER_BUILDING_ATLASX,BuildingRules.COMMAND_CENTER_BUILDING_ATLASY),
            new Vector2i(BuildingRules.COMMAND_CENTER_DONE_ATLASX,BuildingRules.COMMAND_CENTER_DONE_ATLASY)
        );

        servers.getBuildingServer().register(
            BuildingRules.MATERIAL_MINE_NAME,
            EconomyRules.MATERIAL_MINE_GOLD_COST,
            EconomyRules.MATERIAL_MINE_MATERIAL_COST,
            BuildingRules.MATERIAL_MINE_BUILD_TIME,
            BuildingRules.MATERIAL_MINE_HEALTH,
            BuildingRules.MATERIAL_MINE_WIDTH,
            BuildingRules.MATERIAL_MINE_HEIGHT,
            List.of(new MaterialEffect(EconomyRules.MATERIAL_MINE_MATERIAL_DELTA)),
            BuildingRules.BUILDING1X1_TILESET_PATH,
            BuildingRules.BUILDING1X1_TILESET_H,
            BuildingRules.BUILDING1X1_TILESET_V,
            new Vector2i(BuildingRules.MATERIAL_MINE_BUILDING_ATLASX,BuildingRules.MATERIAL_MINE_BUILDING_ATLASY),
            new Vector2i(BuildingRules.MATERIAL_MINE_DONE_ATLASX,BuildingRules.MATERIAL_MINE_DONE_ATLASY)
        );


        servers.getBuildingServer().register(
            BuildingRules.FARM_NAME,
            EconomyRules.FARM_GOLD_COST,
            EconomyRules.FARM_MATERIAL_COST,
            BuildingRules.FARM_BUILD_TIME,
            BuildingRules.FARM_HEALTH,
            BuildingRules.FARM_WIDTH,
            BuildingRules.FARM_HEIGHT,
            List.of(new FoodEffect(EconomyRules.FARM_FOOD_DELTA)),
            BuildingRules.BUILDING1X1_TILESET_PATH,
            BuildingRules.BUILDING1X1_TILESET_H,
            BuildingRules.BUILDING1X1_TILESET_V,
            new Vector2i(BuildingRules.FARM_BUILDING_ATLASX,BuildingRules.FARM_BUILDING_ATLASY),
            new Vector2i(BuildingRules.FARM_DONE_ATLASX,BuildingRules.FARM_DONE_ATLASY)
        );

        servers.getBuildingServer().register(
            BuildingRules.WALL_NAME,
            EconomyRules.WALL_GOLD_COST,
            EconomyRules.WALL_MATERIAL_COST,
            BuildingRules.WALL_BUILD_TIME,
            BuildingRules.WALL_HEALTH,
            BuildingRules.WALL_WIDTH,
            BuildingRules.WALL_HEIGHT,
            List.of(new FearFactorEffect(EconomyRules.WALL_FEAR_FACTOR_BONUS),new PopularityEffect(EconomyRules.WALL_POPULARITY_DELTA)),
            BuildingRules.BUILDING1X1_TILESET_PATH,
            BuildingRules.BUILDING1X1_TILESET_H,
            BuildingRules.BUILDING1X1_TILESET_V,
            new Vector2i(BuildingRules.WALL_BUILDING_ATLASX,BuildingRules.WALL_BUILDING_ATLASY),
            new Vector2i(BuildingRules.WALL_DONE_ATLASX,BuildingRules.WALL_DONE_ATLASY)
        );

        servers.getBuildingServer().register(
            BuildingRules.STATUE0_NAME,
            EconomyRules.STATUE0_GOLD_COST,
            EconomyRules.STATUE0_MATERIAL_COST,
            BuildingRules.STATUE0_BUILD_TIME,
            BuildingRules.STATUE0_HEALTH,
            BuildingRules.STATUE0_WIDTH,
            BuildingRules.STATUE0_HEIGHT,
            List.of(new FearFactorEffect(-15),new PopularityEffect(20)),
            BuildingRules.BUILDING2X2_TILESET_PATH,
            BuildingRules.BUILDING2X2_TILESET_H,
            BuildingRules.BUILDING2X2_TILESET_V,
            new Vector2i(BuildingRules.STATUE0_BUILDING_ATLASX,BuildingRules.STATUE0_BUILDING_ATLASY),
            new Vector2i(BuildingRules.STATUE0_DONE_ATLASX,BuildingRules.STATUE0_DONE_ATLASY)
        );
    }

    @Override
    public void _update(float _delta){
        systems.getBuildingSystem().update(_delta);
        systems.getEconomySystem().update(_delta);
        interactionSystem.update();
        interactionController.update();

    }


    @Override
    protected void _enterTree(){
        world2D = new World2D(systems.getWorldSystem().getWorld());
        addChild(world2D);
    }

}
