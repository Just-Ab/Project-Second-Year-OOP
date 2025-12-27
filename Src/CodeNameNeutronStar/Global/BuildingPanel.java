package CodeNameNeutronStar.Global;

import org.joml.Vector3f;

import CodeNameNeutronStar.Buildings.BuildingResource;
import Game.UI.UIPanel;

public class BuildingPanel extends UIPanel{
    
    GameContext context=null;

    public BuildingPanel(GameContext _context){
        context=_context;
        int index=0;
        for (BuildingResource resource : context.getServers().getBuildingServer().getAll()) {
            BuildingPanelButton button = new BuildingPanelButton(resource, this);
            addChild(button);
            button.setLocalPosition(new Vector3f(0.0f,index++,0.0f));
        }
    }

    public void select(BuildingResource _Resource){
        if(context==null){return;}
        context.getBuildController().select(_Resource);
    }

}
