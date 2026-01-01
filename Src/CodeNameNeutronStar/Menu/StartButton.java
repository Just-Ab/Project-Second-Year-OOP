package CodeNameNeutronStar.Menu;

import org.joml.Vector3f;

import Game.UI.Button2D;
import Game.UI.Label2D;

public class StartButton extends Button2D{
    
    private final MenuInterface menuInterface; 

    public StartButton(MenuInterface _menuInterface){
        menuInterface = _menuInterface;
        Label2D lavel = new Label2D();
        lavel.setText("Start!");
        setLocalScale(new Vector3f(0.04f,0.04f,1.0f));
        addChild(lavel);
    }

    @Override
    public void _onReleased(){
        menuInterface.start();

    }


}
