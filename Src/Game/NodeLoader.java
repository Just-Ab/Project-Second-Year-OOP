package Game;

import static org.lwjgl.glfw.GLFW.*;

import java.util.List;

import org.joml.Vector2f;
import org.joml.Vector3f;

import CodeNameNeutronStar.World.WorldRules;
import CodeNameNeutronStar.World.WorldServer;
import CodeNameNeutronStar.Economy.EconomySystem;
import CodeNameNeutronStar.Global.Game;
import CodeNameNeutronStar.World.TerrainCellResource.TerrainType;
import Game.Cameras.Nodes.Camera2D;
import Game.Core.Node;
import Game.UI.Button2D;
import Game.UI.Label2D;
import Game.Visuals.Nodes.Sprite2D;
import Game.Visuals.Resources.TilesetResource;
import UserIO.Input;



public class NodeLoader extends Node {

    Game game;
    private Camera2D camera;

    private boolean zoomed = false;
    private boolean dirtyZoom = true;


    @Override
    protected void _enterTree() {
        camera = new Camera2D(new Vector3f(), 1, 1);
        addChild(camera);
        camera.current();



        
        TilesetResource tileset = new TilesetResource(
            "Assets/Textures/tileset.png",
            12,
            12
        );

        WorldRules worldRules = WorldServer.getSingleton().createRules();
        worldRules.setIndices(TerrainType.OFFROAD, List.of(0));
        
        game = new Game(30, 30, tileset, worldRules);
        addChild(game);
        System.out.println("Before Building!");
        System.out.println(EconomySystem.getSingleton().getResource().getGold());
        System.out.println(EconomySystem.getSingleton().getResource().getMaterial());
        System.out.println(EconomySystem.getSingleton().getResource().getPopulation());

        // game.buildHouse(0, 0);
        // game.buildHouse(0, 4);
        // game.buildFarm(4, 4);
        // game.buildGoldMine(8, 8);
        // game.buildGoldMine(8, 4);
        // game.buildMaterialMine(4, 6);

        System.out.println("After Building!");
        System.out.println(EconomySystem.getSingleton().getResource().getGold());
        System.out.println(EconomySystem.getSingleton().getResource().getMaterial());
        Sprite2D sprite = new Sprite2D();
        sprite.setTexture("Assets/Textures/anim.png", 8, 9);
        Button2D button = new Button2D();
        camera.addChild(button);
        button.addChild(sprite);
    }

    @Override
    public void _update(float delta) {
        // Vector2f v = Input.getMouseGlobalPosition();
        // System.out.printf("(%.2f, %.2f)%n", v.x, v.y);
        if (!zoomed && dirtyZoom) {
            camera.setZoom(1.0f, 1.0f);
            dirtyZoom = false;
        } else if (zoomed && dirtyZoom) {
            camera.setZoom(5.0f, 5.0f);
            dirtyZoom = false;
        }

        if (Input.isKeyJustPressed(GLFW_KEY_Z)) {
            zoomed = !zoomed;
            dirtyZoom = true;
        }
        if (Input.isKeyJustPressed(GLFW_KEY_SPACE)) {
            System.out.println(EconomySystem.getSingleton().getResource().getPopulation());
            System.out.println(EconomySystem.getSingleton().getResource().getGold());
        }
        // if (Input.isKeyJustPressed(GLFW_KEY_F)){
        //     Building2D res = BuildingSystem.getSingleton().getRuntimeServer().getAll().get(0);
        //     BuildingSystem.getSingleton().destroyBuilding(res);
        // }
        camera.setLocalPosition(
                camera.getLocalPosition().add(
                        Input.getAxis(GLFW_KEY_A, GLFW_KEY_D) * delta * 4,
                        Input.getAxis(GLFW_KEY_S, GLFW_KEY_W) * delta * 4,
                        0.0f
                )
        );
    }
}
