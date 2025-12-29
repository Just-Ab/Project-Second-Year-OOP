package CodeNameNeutronStar.UI;

import org.joml.Vector3f;

import CodeNameNeutronStar.Buildings.BuildingResource;
import CodeNameNeutronStar.Buildings.BuildingServer;
import CodeNameNeutronStar.Interaction.BuildSelector;
import Game.UI.UIPanel;

public class BuildingSelectorPanel extends UIPanel {

    private final BuildSelector buildController;

    public BuildingSelectorPanel(BuildSelector controller) {
        this.buildController = controller;
        int index = 0;
        for (BuildingResource resource :
             BuildingServer.getSingleton().getAll()) {

            BuildingSelectorPanelButton button = new BuildingSelectorPanelButton(resource, this);

            addChild(button);

            button.setLocalPosition(new Vector3f(-0.4f,0.2f + 0.05f*index++ , 0));
            button.setLocalScale(new Vector3f(0.05f));

        }
    }

    public void select(BuildingResource resource) {
        buildController.select(resource);
    }
}
