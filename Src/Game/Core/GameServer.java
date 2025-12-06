package Game.Core;

import Physics.PhysicsServer;
import Rendering.RenderingServer;
import Rendering.Window;
import UserIO.Input;


public class GameServer {
    private Node root = new Node("");

    private static GameServer server = null;
    private static Window window=null;
    private static boolean gameShouldClose=false;

    public static GameServer getSingleton(){
        if (server==null){
            server = new GameServer();
            window = RenderingServer.getSingleton().createWindow("Game Engine Debug!!!", 900, 900);
            Input.initInput(window);
        }
        return server;
    }

    public void addChild(Node _node){
        root.addChild(_node);
    }

    public Node getRoot(){
        return root;
    }

    public void setRoot(Node _node){
        root = _node;
    }

    public void nodesUpdate(float _delta){
        root.update(_delta);
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
