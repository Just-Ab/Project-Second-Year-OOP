package CodeNameNeutronStar.UI;

import org.joml.Vector3f;

import CodeNameNeutronStar.Buildings.BuildingResource;
import CodeNameNeutronStar.Units.UnitResource;
import Game.UI.Button2D;
import Game.UI.Label2D;

public class UnitSelectorPanelButton extends Button2D{
    
    UnitResource resource=null;
    UnitSelectorPanel panel=null;

    public UnitSelectorPanelButton(UnitResource _Resource,UnitSelectorPanel _panel){
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
