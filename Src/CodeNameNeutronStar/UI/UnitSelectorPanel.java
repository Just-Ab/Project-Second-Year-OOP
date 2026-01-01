package CodeNameNeutronStar.UI;

import org.joml.Vector3f;

import CodeNameNeutronStar.Buildings.BuildingRules;
import CodeNameNeutronStar.Interaction.UnitSelector;
import CodeNameNeutronStar.Units.UnitResource;
import CodeNameNeutronStar.Units.UnitRules;
import CodeNameNeutronStar.Units.UnitServer;
import Game.UI.UIPanel;

public class UnitSelectorPanel extends UIPanel {

    private final UnitSelector unitSlector;

    public UnitSelectorPanel(UnitSelector controller) {
        this.unitSlector = controller;
        int index = 0;
        for (UnitResource resource :
            UnitServer.getSingleton().getAll()) {
            
            if (
                resource.getName() == UnitRules.DALEK_NAME
            ) continue;

            UnitSelectorPanelButton button = new UnitSelectorPanelButton(resource, this);

            addChild(button);

            button.setLocalPosition(new Vector3f(-0.4f,0.2f + 0.05f*index++ , 0));
            button.setLocalScale(new Vector3f(0.05f));

        }
    }

    public void select(UnitResource resource) {
        unitSlector.select(resource);
    }
}
