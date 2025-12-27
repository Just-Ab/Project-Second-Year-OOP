package CodeNameNeutronStar.Global;

import static org.lwjgl.glfw.GLFW.GLFW_KEY_E;
import static org.lwjgl.glfw.GLFW.GLFW_MOUSE_BUTTON_LEFT;

import org.joml.*;
import org.joml.Math;

import CodeNameNeutronStar.Buildings.BuildingRules;
import CodeNameNeutronStar.Buildings.FoodEffect;
import CodeNameNeutronStar.Buildings.GoldEffect;
import CodeNameNeutronStar.Buildings.MaterialEffect;
import CodeNameNeutronStar.Buildings.PopulationEffect;
import CodeNameNeutronStar.Buildings.Product;
import CodeNameNeutronStar.Economy.EconomyRules;
import CodeNameNeutronStar.World.World2D;
import CodeNameNeutronStar.World.WorldResource;
import CodeNameNeutronStar.World.WorldRules;
import CodeNameNeutronStar.World.TerrainCellResource.TerrainType;
import Game.Core.Node;
import Game.Visuals.Resources.TilesetResource;
import UserIO.Input;

public class GameContext extends Node {

    private final SystemsRegistery systems = SystemsRegistery.getSingleton();
    private final ServersRegistery servers = ServersRegistery.getSingleton();
    private final BuildController buildController = new BuildController();
    private World2D world2D = null;
    private BuildingPanel buildingPanel = null;

    public GameContext(int _width, int _height, TilesetResource _tileset, WorldRules worldRules) {

        WorldResource worldResource = servers.getWorldServer().createWorld(_width, _height, _tileset);
        systems.getWorldSystem().setWorld(worldResource, worldRules);
        
        systems.getWorldSystem().fillType(TerrainType.OFFROAD);

        systems.getWorldSystem().build();

        systems.getEconomySystem().getResource().addGold(EconomyRules.START_GOLD);
        systems.getEconomySystem().getResource().addFood(EconomyRules.START_FOOD);
        systems.getEconomySystem().getResource().addMaterial(EconomyRules.START_MATERIAL);
        systems.getEconomySystem().getResource().addPopulation(EconomyRules.START_POPULATION);


        servers.getBuildingServer().register(
            BuildingRules.HOUSE_NAME,
            EconomyRules.HOUSE_GOLD_COST,
            EconomyRules.HOUSE_MATERIAL_COST,
            BuildingRules.HOUSE_BUILD_TIME,
            BuildingRules.HOUSE_HEALTH,
            BuildingRules.HOUSE_WIDTH,
            BuildingRules.HOUSE_HEIGHT,
            Product.NONE,
            new PopulationEffect(8),
            BuildingRules.BUILDING_TILESET_PATH,
            BuildingRules.BUILDING_TILESET_H,
            BuildingRules.BUILDING_TILESET_V,
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
            Product.GOLD,
            new GoldEffect(20),
            BuildingRules.BUILDING_TILESET_PATH,
            BuildingRules.BUILDING_TILESET_H,
            BuildingRules.BUILDING_TILESET_V,
            new Vector2i(BuildingRules.GOLD_MINE_BUILDING_ATLASX,BuildingRules.GOLD_MINE_BUILDING_ATLASY),
            new Vector2i(BuildingRules.GOLD_MINE_DONE_ATLASX,BuildingRules.GOLD_MINE_DONE_ATLASY)
        );

        servers.getBuildingServer().register(
            BuildingRules.MATERIAL_MINE_NAME,
            EconomyRules.MATERIAL_MINE_GOLD_COST,
            EconomyRules.MATERIAL_MINE_MATERIAL_COST,
            BuildingRules.MATERIAL_MINE_BUILD_TIME,
            BuildingRules.MATERIAL_MINE_HEALTH,
            BuildingRules.MATERIAL_MINE_WIDTH,
            BuildingRules.MATERIAL_MINE_HEIGHT,
            Product.MATERIAL,
            new MaterialEffect(20),
            BuildingRules.BUILDING_TILESET_PATH,
            BuildingRules.BUILDING_TILESET_H,
            BuildingRules.BUILDING_TILESET_V,
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
            Product.FOOD,
            new FoodEffect(10),
            BuildingRules.BUILDING_TILESET_PATH,
            BuildingRules.BUILDING_TILESET_H,
            BuildingRules.BUILDING_TILESET_V,
            new Vector2i(BuildingRules.FARM_BUILDING_ATLASX,BuildingRules.FARM_BUILDING_ATLASY),
            new Vector2i(BuildingRules.FARM_DONE_ATLASX,BuildingRules.FARM_DONE_ATLASY)
        );


        systems.getBuildingSystem().setRootNode(this);
    }

    public ServersRegistery getServers(){return servers;}

    public SystemsRegistery getSystems(){return systems;}

    public BuildController getBuildController(){return buildController;}


    @Override
    public void _update(float _delta){
        systems.getBuildingSystem().update(_delta);
        systems.getEconomySystem().update(_delta);

        if(Input.isKeyJustReleased(GLFW_KEY_E) && buildingPanel==null){
            buildingPanel = new BuildingPanel(this);
            addChild(buildingPanel);
        }
        if (buildController.hasSelection()) {
            if(buildingPanel!=null){
                buildingPanel.queueFree();
                buildingPanel=null;
            }
            if (Input.isMouseButtonJustPressed(GLFW_MOUSE_BUTTON_LEFT)) {
                Vector2f mouse = Input.getMouseGlobalPosition();

                int tileX = (int)Math.floor(mouse.x + 0.5f);
                int tileY = (int)Math.floor(-mouse.y + 0.5f);

                Vector2i tile = new Vector2i(tileX, tileY);


                buildController.tryBuild(tile);
                buildController.cancel();
                System.out.println("Resourcs left: "+systems.getEconomySystem().getResource().getMaterial()+" "+systems.getEconomySystem().getResource().getGold());
            }
        }

    }


    @Override
    protected void _enterTree(){
        world2D = new World2D(systems.getWorldSystem().getWorld());
        addChild(world2D);
    }

}
