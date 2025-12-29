package CodeNameNeutronStar.Interaction;

import CodeNameNeutronStar.Interaction.InteractionState.InteractionMode;
import Game.Core.Node;

public class InteractionController {

    private final BuildSelector buildController;
    private final BuildModeController buildMode;

    private InteractionMode activeMode = InteractionMode.ROAM;

    public InteractionController(Node uiRoot) {
        buildController = new BuildSelector();
        buildMode = new BuildModeController(uiRoot, buildController);
    }

    public void update() {
        InteractionMode mode = InteractionSystem.getSingleton().getState().getMode();

        if (mode != activeMode) {
            if (activeMode == InteractionMode.BUILD)
                buildMode.exit();

            if (mode == InteractionMode.BUILD)
                buildMode.enter();

            activeMode = mode;
        }

        if (activeMode == InteractionMode.BUILD)
            buildMode.update();
    }
}

