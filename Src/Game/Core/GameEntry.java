package Game.Core;

import Game.NodeLoader;
import Rendering.Window;
public class GameEntry {
    public static void main(String args[]){
        GameServer.getSingleton().setRoot(new NodeLoader());
        Window window=null;
        while (
            !(GameServer.getSingleton().getGameWindowShouldClose()
            ||
            GameServer.getSingleton().getGameShouldClose())) 
            {
                if ((window = GameServer.getSingleton().getGameWindow())!=null) {
                    Time.update(window);
                }
                if(!Time.getIsPaused()){
                    GameServer.getSingleton().physicsUpdate(Time.getDeltaTime());
                    GameServer.getSingleton().nodesUpdate(Time.getDeltaTime());
                } 
                GameServer.getSingleton().renderingUpdate();
            }

    }
}
