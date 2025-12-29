package CodeNameNeutronStar.Interaction;

public class InteractionState {
    
    public enum InteractionMode{
        ROAM,
        BUILD,
        UNIT
    };

    private InteractionMode activeMode = InteractionMode.ROAM;

    public void setMode(InteractionMode _mode){
        if(_mode == null) { return; }
        activeMode = _mode;
    }

    public void resetMode(){
        activeMode = InteractionMode.ROAM;
    }

    public InteractionMode getMode() {
        return activeMode;
    }

    public boolean isRoaming() { return activeMode == InteractionMode.ROAM; }
    public boolean isBuilding() { return activeMode == InteractionMode.BUILD; }
    public boolean isUnits() { return activeMode == InteractionMode.UNIT; }



}
