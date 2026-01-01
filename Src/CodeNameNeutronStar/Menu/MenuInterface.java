package CodeNameNeutronStar.Menu;

import org.joml.Vector3f;

import CodeNameNeutronStar.Global.Game;
import Game.NodeLoader;
import Game.Core.Node;
import Game.Core.Node2D;
import Game.UI.Button2D;
import Game.UI.UIPanel;
import Game.Visuals.Nodes.Sprite2D;

public class MenuInterface extends UIPanel{
    
    private final NodeLoader loader;

    public MenuInterface(NodeLoader _loader){
        Sprite2D backgrond = new Sprite2D();
        backgrond.setTexture("Assets/Textures/Background.jpg");
        addChild(backgrond);
        Button2D btn = new StartButton(this);
        addChild(btn);
        btn.setLocalPosition(new Vector3f(-0.45f,0.0f,0.0f));
        System.out.println(btn.getLocalPosition());
        loader =_loader;
    }

    
    public void start(){
        loader.newGame();
        queueFree();
    }

}
