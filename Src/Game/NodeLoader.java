package Game;

import static org.lwjgl.glfw.GLFW.*;

import org.joml.Vector3f;

import CodeNameNeutronStar.Global.Game;
import CodeNameNeutronStar.Menu.MenuInterface;
import CodeNameNeutronStar.Units.Unit2D;
import Game.Cameras.Nodes.Camera2D;
import Game.Core.GameServer;
import Game.Core.Node;
import UserIO.Input;



public class NodeLoader extends Node {

    private Camera2D camera;

    private boolean zoomed = false;
    private boolean dirtyZoom = true;
    private boolean startNewGame = false;
Unit2D unit1,unit2;
    @Override
    protected void _enterTree() {
        camera = new Camera2D(new Vector3f(), 1, 1);
        addChild(camera);
        camera.current();

        game = new Game(this);
        game.init();
        game.kill();

        addChild(new MenuInterface(this));

  
        camera.setGlobalPosition(new Vector3f(13,-13,0.0f));
    }
    Game game;

    public void newGame(){
        startNewGame = true;

    }

    @Override
    public void _update(float delta) {
        if (!zoomed && dirtyZoom) {
            camera.setZoom(10.0f, 10.0f);
            dirtyZoom = false;
        } else if (zoomed && dirtyZoom) {
            camera.setZoom(20.0f, 20.0f);
            dirtyZoom = false;
        }

        if (Input.isKeyJustPressed(GLFW_KEY_Z)) {
            zoomed = !zoomed;
            dirtyZoom = true;
        }

        if (startNewGame==true){
                    game = new Game(this);
                    game.init();
                    startNewGame = false;
        }

        if(Input.isKeyJustReleased(GLFW_KEY_ESCAPE)){
            GameServer.getSingleton().close();
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
