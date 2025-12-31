package CodeNameNeutronStar.Interaction;

import static org.lwjgl.glfw.GLFW.GLFW_KEY_Q;
import static org.lwjgl.glfw.GLFW.GLFW_MOUSE_BUTTON_LEFT;

import org.joml.Vector2f;
import org.joml.Vector2i;

import CodeNameNeutronStar.Buildings.Building2D;
import CodeNameNeutronStar.Gameplay.GameplayRules;
import CodeNameNeutronStar.Global.SystemsRegistery;
import CodeNameNeutronStar.UI.BuildModeControllerPannel;
import CodeNameNeutronStar.UI.BuildingSelectorPanel;
import Game.Core.Node;
import UserIO.Input;

public class BuildModeController {

    private final Node uiRoot;
    private final BuildSelector buildSelector;
    private final SystemsRegistery systemsRegistery = SystemsRegistery.getSingleton();

    public enum BuildMode{
        NONE,BUILD,DESTRUCT
    }
    private BuildMode currentBuildMode = BuildMode.NONE;

    private BuildingSelectorPanel selectorPanel;
    private BuildModeControllerPannel displayPanel;

    public BuildModeController(Node uiRoot, BuildSelector controller) {
        this.uiRoot = uiRoot;
        this.buildSelector = controller;
    }

    public void enter() {
        instanceDisplay();
        buildSelector.cancel();
    }

    public void exit() {
        freeDisplay();
        freeBuildingList();
        currentBuildMode = BuildMode.NONE;
        buildSelector.cancel();
    }

    private void instanceDisplay(){
        if(displayPanel==null){
            displayPanel = new BuildModeControllerPannel();
            uiRoot.addChild(displayPanel);
        }
    }

    private void freeDisplay(){
        if (displayPanel != null) {
            displayPanel.queueFree();
            displayPanel = null;
        }
    }  

    private void instanceBuildingList(){
        if(selectorPanel==null){
            selectorPanel = new BuildingSelectorPanel(buildSelector);
            uiRoot.addChild(selectorPanel);
        }
    }

    private void freeBuildingList(){
        if (selectorPanel != null) {
            selectorPanel.queueFree();
            selectorPanel = null;
        }
        buildSelector.cancel();
    }  

    public void update() {
        if(Input.isKeyJustPressed(InteractionRules.BUILD_MODE_KEY)){
            if (currentBuildMode==BuildMode.NONE) currentBuildMode = BuildMode.BUILD;
            else if (currentBuildMode==BuildMode.BUILD) currentBuildMode = BuildMode.DESTRUCT;
            else currentBuildMode = BuildMode.BUILD;
        }
        if(currentBuildMode == BuildMode.BUILD){
            displayPanel.setMode(BuildMode.BUILD);
            instanceBuildingList();
            if (buildSelector.hasSelection() && Input.isMouseButtonJustPressed(GLFW_MOUSE_BUTTON_LEFT)) {
                Vector2i tile = getMouseTile();
                systemsRegistery.getBuildingSystem().buildBuilding(
                    buildSelector.getSelected(),
                    GameplayRules.PLAYER_TEAM,
                    tile.x,
                    tile.y
                );
                buildSelector.cancel();
            }
            if (Input.isKeyJustPressed(GLFW_KEY_Q)) {
                freeBuildingList();
                buildSelector.cancel();
            }
        }
        else{
            displayPanel.setMode(BuildMode.DESTRUCT);
            freeBuildingList();
            if (Input.isMouseButtonJustPressed(GLFW_MOUSE_BUTTON_LEFT)) {
                Vector2i tile = getMouseTile();
                Building2D building = systemsRegistery.getWorldSystem().getBuildingAt(tile.x,tile.y);
                if(building!=null) systemsRegistery.getBuildingSystem().destroyBuilding(building);    
            }
        }
    }

    private Vector2i getMouseTile() {
        Vector2f mouse = Input.getMouseGlobalPosition();
        return new Vector2i(
            (int)Math.floor(mouse.x + 0.5f),
            (int)Math.floor(-mouse.y + 0.5f)
        );
    }
}
