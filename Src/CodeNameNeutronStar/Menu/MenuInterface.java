package CodeNameNeutronStar.Menu;

import org.joml.Vector3f;

import CodeNameNeutronStar.Global.Game;
import Game.NodeLoader;
import Game.Core.Node;
import Game.Core.Node2D;
import Game.UI.Button2D;
import Game.UI.UIPanel;

public class MenuInterface extends UIPanel{
    
    private final NodeLoader loader;

    public MenuInterface(NodeLoader _loader){
        Button2D btn = new StartButton(this);
        addChild(btn);
        loader =_loader;
    }

    
    public void start(){
        loader.newGame();
        queueFree();
    }

}
