package Game;

import static org.lwjgl.glfw.GLFW.*;

import java.util.List;

import org.joml.Vector3f;

import CodeNameNeutronStar.World.WorldRules;
import CodeNameNeutronStar.World.WorldServer;
import CodeNameNeutronStar.World.WorldSystem;
import CodeNameNeutronStar.Economy.EconomySystem;
import CodeNameNeutronStar.Global.GameContext;
import CodeNameNeutronStar.World.TerrainCellResource.TerrainType;
import CodeNameNeutronStar.World.WorldSystem.CellPos;
import Game.Cameras.Nodes.Camera2D;
import Game.Core.Node;
import Game.Visuals.Resources.TilesetResource;
import UserIO.Input;



public class NodeLoader extends Node {

    GameContext gameContext;
    private Camera2D camera;

    private boolean zoomed = false;
    private boolean dirtyZoom = true;


    @Override
    protected void _enterTree() {
        camera = new Camera2D(new Vector3f(), 1, 1);
        addChild(camera);
        camera.current();



        
        TilesetResource tileset = new TilesetResource(
            "Assets/Textures/MultiSpreadSheet.png",
            8,
            8
        );

        WorldRules worldRules = WorldServer.getSingleton().createRules();
        worldRules.setIndices(TerrainType.OFFROAD, List.of(8*7,8*7+1,8*7+2,8*7+3,8*7+4,8*7+5,8*7+6,8*7+7));
        
        gameContext = new GameContext(30, 30, tileset, worldRules);
        addChild(gameContext);
    }

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
            System.out.println(EconomySystem.getSingleton().getResource().getPopulation());
            System.out.println(EconomySystem.getSingleton().getResource().getGold());
            System.out.println(WorldSystem.getSingleton().getBuildingAt(3,3));
        }
        camera.setLocalPosition(
                camera.getLocalPosition().add(
                        Input.getAxis(GLFW_KEY_A, GLFW_KEY_D) * delta * 4,
                        Input.getAxis(GLFW_KEY_S, GLFW_KEY_W) * delta * 4,
                        0.0f
                )
        );
    }
}
