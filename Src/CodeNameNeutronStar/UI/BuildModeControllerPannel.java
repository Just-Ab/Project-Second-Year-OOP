package CodeNameNeutronStar.UI;

import org.joml.Vector3f;


import CodeNameNeutronStar.Interaction.BuildModeController;
import CodeNameNeutronStar.Interaction.BuildModeController.BuildMode;
import Game.UI.Label2D;
import Game.UI.UIPanel;

public class BuildModeControllerPannel extends UIPanel{
    

    private Label2D label = new Label2D();

    private String modeText = "Placeholder bla bla, you are not supposed to see this!";

    public BuildModeControllerPannel() {
        label.setLocalScale(new Vector3f(0.05f,0.05f,1.0f));
        label.setLocalPosition(new Vector3f(-0.4f,-0.3f , 0));

        addChild(label);
        applyText();
    }

    private void applyText(){
        label.setText(modeText);
    }

    public void setMode(BuildModeController.BuildMode _mode){
        if(_mode == null){return;}
        else if(_mode == BuildMode.BUILD){
            modeText = "Building Mode"; 
        }
        else if(_mode == BuildMode.DESTRUCT){
            modeText = "Destruct Mode"; 
        }
        applyText();
    }

}
