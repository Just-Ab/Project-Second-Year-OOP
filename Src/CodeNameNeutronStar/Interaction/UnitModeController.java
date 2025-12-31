package CodeNameNeutronStar.Interaction;

import static org.lwjgl.glfw.GLFW.GLFW_KEY_Q;
import static org.lwjgl.glfw.GLFW.GLFW_MOUSE_BUTTON_LEFT;
import static org.lwjgl.glfw.GLFW.GLFW_MOUSE_BUTTON_RIGHT;

import java.lang.Thread.State;
import java.util.List;

import org.joml.Vector2f;
import org.joml.Vector2i;

import CodeNameNeutronStar.Buildings.Building2D;
import CodeNameNeutronStar.Buildings.BuildingRules;
import CodeNameNeutronStar.Gameplay.GameplayRules;
import CodeNameNeutronStar.Global.SystemsRegistery;
import CodeNameNeutronStar.Interaction.BuildModeController.BuildMode;
import CodeNameNeutronStar.UI.BuildModeControllerPannel;
import CodeNameNeutronStar.UI.BuildingSelectorPanel;
import CodeNameNeutronStar.UI.UnitModeControllerPannel;
import CodeNameNeutronStar.UI.UnitSelectorPanel;
import CodeNameNeutronStar.Units.Unit2D;
import CodeNameNeutronStar.Units.UnitResource;
import CodeNameNeutronStar.Units.UnitRunTimeServer;
import CodeNameNeutronStar.Units.Unit2D.StateUnit;
import Game.Core.Node;
import UserIO.Input;

public class UnitModeController {

    private final Node uiRoot;
    private final UnitSelector unitSelector;
    private final UnitOrderSelector unitOrderSelector;
    private final SystemsRegistery systemsRegistery = SystemsRegistery.getSingleton();

    public enum UnitMode{
        NONE,ORDER,RECRUIT
    }
    private UnitMode currentUnitMode = UnitMode.NONE;

    private UnitSelectorPanel selectorPanel;
    private UnitModeControllerPannel displayPanel;

    public UnitModeController(Node uiRoot, UnitSelector _unitSelector , UnitOrderSelector _unitOrderSelector) {
        this.uiRoot = uiRoot;
        unitSelector = _unitSelector;
        unitOrderSelector = _unitOrderSelector;
    }

    public void enter() {
        instanceDisplay();
        instanceUnitList();
        unitSelector.cancel();
        unitOrderSelector.cancel();

    }

    public void exit() {
        freeDisplay();
        freeUnitList();
        currentUnitMode = UnitMode.NONE;
        unitSelector.cancel();
        unitOrderSelector.cancel();
    }

    private void instanceDisplay(){
        if(displayPanel==null){
            displayPanel = new UnitModeControllerPannel();
            uiRoot.addChild(displayPanel);
        }
    }

    private void freeDisplay(){
        if (displayPanel != null) {
            displayPanel.queueFree();
            displayPanel = null;
        }
    }  

    private void instanceUnitList(){
        if(selectorPanel==null){
            selectorPanel = new UnitSelectorPanel(unitSelector);
            uiRoot.addChild(selectorPanel);
        }
    }

    private void freeUnitList(){
        if (selectorPanel != null) {
            selectorPanel.queueFree();
            selectorPanel = null;
        }
        unitSelector.cancel();
    }  


    public void update() {
        if (Input.isKeyJustPressed(InteractionRules.UNITS_MODE_KEY)){
            if (currentUnitMode==UnitMode.NONE) currentUnitMode = UnitMode.RECRUIT;
            else if (currentUnitMode==UnitMode.RECRUIT) currentUnitMode = UnitMode.ORDER;
            else currentUnitMode = UnitMode.RECRUIT;
        }
        if (currentUnitMode == UnitMode.ORDER) {
            displayPanel.setMode(UnitMode.ORDER);
            freeUnitList();
            if(Input.isMouseButtonJustPressed(GLFW_MOUSE_BUTTON_LEFT)) {
                List<Unit2D> selectedUnits = systemsRegistery.getUnitSystem().getRuntimeServer().getUnitsAt(getMouseTile());
                Unit2D selectedUnit=null;
                for (Unit2D unit : selectedUnits) {
                    if (unit.getState() != StateUnit.DEAD) selectedUnit = unit;
                }
                if (selectedUnit != null)
                    if (selectedUnit.getState() != StateUnit.DEAD) unitOrderSelector.select(selectedUnit);

            }
            if(Input.isMouseButtonJustPressed(GLFW_MOUSE_BUTTON_RIGHT)&&unitOrderSelector.getSelected()!=null) {
                unitOrderSelector.getSelected().clearTarget();
                Vector2i mousePositionTile = getMouseTile();
                Unit2D target = systemsRegistery.getUnitSystem().getRuntimeServer().getUnitAt(mousePositionTile);
                Building2D building = systemsRegistery.getBuildingSystem().getRuntimeServer().getBuildingAt(mousePositionTile);

                if (target != null)
                    if(target.getTeam()!=unitOrderSelector.getSelected().getTeam()) {
                        systemsRegistery.getUnitSystem().orderAttack(unitOrderSelector.getSelected(), target);
                        return;
                    }
                if (building != null) 
                    if(building.getTeam()!=unitOrderSelector.getSelected().getTeam()) {
                        systemsRegistery.getUnitSystem().orderAttack(unitOrderSelector.getSelected(), building);
                        return;
                    }
                
                systemsRegistery.getUnitSystem().orderMovement(unitOrderSelector.getSelected(), mousePositionTile.x, mousePositionTile.y);
            }

        }
        if (currentUnitMode == UnitMode.RECRUIT) {
            displayPanel.setMode(UnitMode.RECRUIT);
            instanceUnitList();

            UnitResource resource = unitSelector.getSelected();
            if (resource==null) return;

            Building2D recruitCenter = systemsRegistery.getBuildingSystem().getRuntimeServer()
                                        .getBuildingOfName(BuildingRules.RECRUIT_CENTER_NAME);
            if (recruitCenter==null) return;
            if (!recruitCenter.isOperational()) return;
            
            Vector2i recruitCenterPosition = recruitCenter.getPositionNormalized();
            Vector2i spawnPoint = systemsRegistery.getWorldSystem().getWalkableAdjacentCell(recruitCenterPosition.x,recruitCenterPosition.y);

            systemsRegistery.getUnitSystem().spawnUnit(unitSelector.getSelected(),GameplayRules.PLAYER_TEAM, spawnPoint.x, spawnPoint.y);
            unitSelector.cancel();
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
