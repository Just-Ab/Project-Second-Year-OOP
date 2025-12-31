package Game;

import static org.lwjgl.glfw.GLFW.*;

import java.util.List;
import java.util.Random;

import org.joml.Vector2i;
import org.joml.Vector3f;

import CodeNameNeutronStar.World.WorldRules;
import CodeNameNeutronStar.World.WorldServer;
import CodeNameNeutronStar.World.WorldSystem;
import CodeNameNeutronStar.Buildings.Building2D;
import CodeNameNeutronStar.Buildings.BuildingResource;
import CodeNameNeutronStar.Economy.EconomySystem;
import CodeNameNeutronStar.Gameplay.GameplayRules;
import CodeNameNeutronStar.Global.GameContext;
import CodeNameNeutronStar.Stats.StatsSystem;
import CodeNameNeutronStar.Units.Unit2D;
import CodeNameNeutronStar.Units.UnitResource;
import CodeNameNeutronStar.World.TerrainCellResource.TerrainType;
import CodeNameNeutronStar.World.WorldSystem.CellPos;
import Game.Cameras.Nodes.Camera2D;
import Game.Core.Node;
import Game.Core.Node2D;
import Game.Visuals.Nodes.ColorRect2D;
import Game.Visuals.Nodes.Sprite2D;
import Game.Visuals.Resources.TilesetResource;
import Rendering.RenderInstance;
import Rendering.RenderingServer;
import UserIO.Input;



public class NodeLoader extends Node {

    GameContext gameContext;
    private Camera2D camera;

    private boolean zoomed = false;
    private boolean dirtyZoom = true;

Unit2D unit1,unit2;
    @Override
    protected void _enterTree() {
        camera = new Camera2D(new Vector3f(), 1, 1);
        addChild(camera);
        camera.current();

        TilesetResource tileset = new TilesetResource(
            "Assets/Textures/MultiSpreadSheet1X1.png",
            8,
            8
        );

        WorldRules worldRules = WorldServer.getSingleton().createRules();
        worldRules.setIndices(TerrainType.OFFROAD, List.of(8*7,8*7+1,8*7+2,8*7+3,8*7+4,8*7+5,8*7+6,8*7+7));
        
        gameContext = new GameContext(100, 100, tileset, worldRules);
        addChild(gameContext);
        UnitResource res = gameContext.getServers().getUnitServer().getAll().get(0);
        BuildingResource res2 = gameContext.getServers().getBuildingServer().getAll().get(0);

        unit1 = gameContext.getSystems().getUnitSystem().spawnUnit(res,GameplayRules.ENEMY_AI_TEAM_0, 8, 8);
        unit2 = gameContext.getSystems().getUnitSystem().spawnUnit(res,GameplayRules.PLAYER_TEAM, 0, 2);
        gameContext.getSystems().getBuildingSystem().buildBuilding(res2, 4, 4, 4);
    }

    Random rand = new Random();

    @Override
    public void _update(float delta) {
        if (!zoomed && dirtyZoom) {
            camera.setZoom(5.0f, 5.0f);
            dirtyZoom = false;
        } else if (zoomed && dirtyZoom) {
            camera.setZoom(10.0f, 10.0f);
            dirtyZoom = false;
        }

        if (Input.isKeyJustPressed(GLFW_KEY_Z)) {
            zoomed = !zoomed;
            dirtyZoom = true;
        }
        if (Input.isKeyJustPressed(GLFW_KEY_SPACE)) {
            System.out.println(
                "Population   : "+EconomySystem.getSingleton().getResource().getPopulation()+
                "\nPopularity   : "+StatsSystem.getSingleton().getResource().getPopularity()+
                "\nFood Factor  : "+StatsSystem.getSingleton().getResource().getFoodFactor()+
                "\nFear Factor  : "+StatsSystem.getSingleton().getResource().getFearFactor()+
                "\nPride Factor : "+StatsSystem.getSingleton().getResource().getPrideFactor()
            );
            // gameContext.getSystems().getUnitSystem().orderAttack(unit2, unit1);


        }

        if(Input.isKeyJustPressed(GLFW_KEY_P)) gameContext.getSystems().getUnitSystem().orderAttack(unit1, unit2);


        camera.setLocalPosition(
            camera.getLocalPosition().add(
                Input.getAxis(GLFW_KEY_A, GLFW_KEY_D) * delta * 4,
                Input.getAxis(GLFW_KEY_S, GLFW_KEY_W) * delta * 4,
                0.0f
            )
        );

    }
}
