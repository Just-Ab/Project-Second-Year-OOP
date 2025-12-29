package CodeNameNeutronStar.Interaction;

import UserIO.Input;

public class InteractionSystem {
    

    private final InteractionState state = new InteractionState();

    private static InteractionSystem system = null;


    public static InteractionSystem getSingleton() {
        if(system == null){
            system = new InteractionSystem();
        }
        return system;
    }

    public InteractionState getState() {
        return state;
    }

    public void update(){
        if (Input.isKeyPressed(InteractionRules.BUILD_MODE_KEY)) {
            state.setMode(InteractionState.InteractionMode.BUILD);
        }

        else if (Input.isKeyPressed(InteractionRules.UNITS_MODE_KEY)) {
            state.setMode(InteractionState.InteractionMode.UNIT);
        }

        else if (Input.isKeyPressed(InteractionRules.ROAM_MODE_KEY)) {
            state.setMode(InteractionState.InteractionMode.ROAM);
        }
        else if (Input.isKeyPressed(InteractionRules.CANCEL_KEY)) {
            state.resetMode();
        }
    }

}
