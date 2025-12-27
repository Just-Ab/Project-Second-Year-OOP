package CodeNameNeutronStar.Global;

import CodeNameNeutronStar.Buildings.BuildingResource;
import Game.UI.Button2D;
import Game.UI.Label2D;
import Game.Visuals.Nodes.Sprite2D;

public class BuildingPanelButton extends Button2D{
    
    BuildingResource resource=null;
    BuildingPanel panel=null;

    public BuildingPanelButton(BuildingResource _Resource,BuildingPanel _panel){
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
