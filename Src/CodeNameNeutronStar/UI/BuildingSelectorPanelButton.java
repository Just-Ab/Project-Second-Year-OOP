package CodeNameNeutronStar.UI;

import org.joml.Vector3f;

import CodeNameNeutronStar.Buildings.BuildingResource;
import Game.UI.Button2D;
import Game.UI.Label2D;

public class BuildingSelectorPanelButton extends Button2D{
    
    BuildingResource resource=null;
    BuildingSelectorPanel panel=null;

    public BuildingSelectorPanelButton(BuildingResource _Resource,BuildingSelectorPanel _panel){
        resource = _Resource;
        panel = _panel;
        Label2D label2d = new Label2D();
        label2d.setText(_Resource.getName());
        addChild(label2d);
    }

    protected void _onPressed(){
        if(panel!=null && resource != null){
            panel.select(resource);
        }
    }

}
