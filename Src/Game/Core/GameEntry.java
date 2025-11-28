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
                    GameServer.getSingleton()._physicsUpdate(Time.getDeltaTime());
                    GameServer.getSingleton()._nodesUpdate(Time.getDeltaTime());
                } 
                GameServer.getSingleton()._renderingUpdate();
            }

    }
}
