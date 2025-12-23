package Game;

import static org.lwjgl.glfw.GLFW.*;

import java.util.List;

import org.joml.Vector3f;

import CodeNameNeutronStar.*;
import CodeNameNeutronStar.TerrainCellResource.TerrainType;
import Game.Cameras.Nodes.Camera2D;
import Game.Core.Node;
import Game.Visuals.Resources.TilesetResource;
import UserIO.Input;

public class NodeLoader extends Node {

    private Camera2D camera;
    private World2D world2D;

    private boolean zoomed = false;
    private boolean dirtyZoom = true;

    @Override
    protected void _enterTree() {
        camera = new Camera2D(new Vector3f(), 5, 5);
        addChild(camera);
        camera.current();

        TilesetResource tileset = new TilesetResource(
                "Assets/Textures/tileset.png",
                12,
                12
        );

        WorldResource worldResource = WorldServer.getSingleton().createWorld(10, 10,tileset);
        
        WorldRules worldRules = WorldServer.getSingleton().createRules();
        worldRules.setIndices(TerrainType.OFFROAD, List.of(1,2,3,4));
        worldRules.setIndices(TerrainType.ROAD, List.of(12*5+5,12*5+6));

        WorldSystem.getSingleton().setWorld(worldResource,worldRules);
        WorldSystem.getSingleton().fillType(TerrainType.OFFROAD);
        for (int y = 0; y < 2; y++) {
            for (int x = 0; x < 2; x++) {
            WorldSystem.getSingleton().setCellType(x, y, TerrainType.ROAD);
            }
        }
        WorldSystem.getSingleton().build();

        world2D = new World2D(worldResource);
        addChild(world2D);
        System.out.println(WorldSystem.getSingleton().isWalkable(1, 1));
        WorldSystem.getSingleton().place(0, 0, 2, 2);
        System.out.println(WorldSystem.getSingleton().isWalkable(1, 1));
        WorldSystem.getSingleton().removePlacement(0, 0, 2, 2);
        System.out.println(WorldSystem.getSingleton().isWalkable(1, 1));
    }

    @Override
    public void _update(float delta) {

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

        camera.setLocalPosition(
                camera.getLocalPosition().add(
                        Input.getAxis(GLFW_KEY_A, GLFW_KEY_D) * delta * 4,
                        Input.getAxis(GLFW_KEY_S, GLFW_KEY_W) * delta * 4,
                        0.0f
                )
        );
    }
}
