package Game.Core;

import Game.Cameras.Nodes.Camera2D;
import Physics.PhysicsServer;
import Rendering.RenderingServer;
import Rendering.Window;
import UserIO.Input;


public class GameServer {
    private static SceneTree sceneTree = null;

    private static GameServer server = null;
    private static Window window=null;
    private static boolean gameShouldClose=false;
    private Camera2D currentCamera2d=null;

    public static GameServer getSingleton(){
        if (server==null){
            server = new GameServer();
            window = RenderingServer.getSingleton().createWindow("Game Engine Debug!!!", 900, 900);
            sceneTree = new SceneTree(new Node("_ROOT"));
            Input.initInput(window);
        }
        return server;
    }

    public void addChild(Node _node){
        sceneTree.getRoot().addChild(_node);
    }

    public Node getRoot(){
        return sceneTree.getRoot();
    }

    public void setRoot(Node _node){
        sceneTree.setRoot(_node);
    }

    public void nodesUpdate(float _delta){
        sceneTree.update(_delta);
    }

    public void physicsUpdate(float _delta){
            PhysicsServer.getSingleton().update(_delta);
    }

    public void renderingUpdate(){
            RenderingServer.getSingleton().beginFrame();
            RenderingServer.getSingleton().drawFrame();
            RenderingServer.getSingleton().endFrame();
    }

    public void close(){
        gameShouldClose=true;
        window.closeWindow();
    }


    public Camera2D getCurrentCamera2D(){
        return currentCamera2d;
    }
    public void setCurrentCamera2D(Camera2D _camera2d){
        currentCamera2d=_camera2d;
    }
    public boolean getGameShouldClose(){
        return gameShouldClose;
    }
    public boolean getGameWindowShouldClose(){
        return window.windowShouldClose();
    }

    public Window getGameWindow(){
        return window;
    }
}
