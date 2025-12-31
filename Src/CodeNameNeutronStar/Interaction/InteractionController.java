package CodeNameNeutronStar.Interaction;

import CodeNameNeutronStar.Interaction.InteractionState.InteractionMode;
import Game.Core.Node;

public class InteractionController {

    private final BuildSelector buildSelector;
    private final BuildModeController buildMode;


    private final UnitSelector unitSelector;
    private final UnitOrderSelector unitOrderSelector;
    private final UnitModeController unitMode;

    private InteractionMode activeMode = InteractionMode.ROAM;

    public InteractionController(Node uiRoot) {
        buildSelector = new BuildSelector();
        buildMode = new BuildModeController(uiRoot, buildSelector);

        unitSelector = new UnitSelector();
        unitOrderSelector = new UnitOrderSelector();
        unitMode = new UnitModeController(uiRoot, unitSelector, unitOrderSelector);

    }

    public void update() {
        InteractionMode mode = InteractionSystem.getSingleton().getState().getMode();

        if (mode != activeMode) {
            if (activeMode == InteractionMode.BUILD)
                buildMode.exit();
            if (activeMode == InteractionMode.UNIT)
                unitMode.exit();
            if (mode == InteractionMode.BUILD)
                buildMode.enter();
            if (mode == InteractionMode.UNIT)
                unitMode.enter();
            activeMode = mode;
        }

        if (activeMode == InteractionMode.BUILD)
            buildMode.update();
            unitMode.update();

    }
}

