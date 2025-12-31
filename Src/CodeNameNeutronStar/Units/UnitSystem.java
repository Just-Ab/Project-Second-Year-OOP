package CodeNameNeutronStar.Units;

import java.security.Principal;
import java.util.*;

import org.joml.Vector2i;
import org.joml.Vector3f;

import CodeNameNeutronStar.Buildings.Building2D;
import CodeNameNeutronStar.Units.Unit2D.StateUnit;
import CodeNameNeutronStar.World.WorldSystem;
import Game.Core.Node;

public class UnitSystem {

    private static UnitSystem system;

    private Node unitsRootNode = null;

    private final UnitRunTimeServer runtime = new UnitRunTimeServer();
    private final MovementSystem movementSystem = new MovementSystem(runtime);
    private final CombatSystem combatSystem = new CombatSystem(runtime);
    private final UnitAnimationSystem unitAnimationSystem = new UnitAnimationSystem(runtime);
    private final UnitController unitController = new UnitController(runtime);


    private final List<Unit2D> birthQueue = new ArrayList<>();
    private final List<Unit2D> deathQueue = new ArrayList<>();

    private final Map<Unit2D, Path> activePaths = new HashMap<>();

    private UnitSystem() {
    }

    public static UnitSystem getSingleton() {
        if (system == null) {
            system = new UnitSystem();
        }
        return system;
    }

    public Unit2D spawnUnit(UnitResource resource,int _team, int _x,int _y) {

        if (unitsRootNode == null) return null;

        Unit2D unit = new Unit2D(resource,_team);

        unit.setGlobalPosition(new Vector3f(
            _x,
           -_y,
            0.5f
        ));

        birthQueue.add(unit);
        return unit;
    }

    public void destroyUnit(Unit2D unit) {
        activePaths.remove(unit);
        deathQueue.add(unit);
    }

    public void damageUnit(Unit2D _unit ,float amount){
        _unit.applyDamage(amount);
    }


    public void orderMovement(Unit2D _unit,int x, int y){
        _unit.setDestination(new Vector2i(x,y));
    }

    public void orderAttack(Unit2D _unitA,Unit2D _unitB){
        _unitA.setTarget(_unitB);
    }

    public void orderAttack(Unit2D _unitA,Building2D _Building2d){
        _unitA.setTarget(_Building2d);
    }

    public void update(float delta) {

        for (Unit2D unit : runtime.getAll()) {
            if (unit.getHealth()<=0.0f) {
                if (unit.getState() != StateUnit.DEAD) {
                    unit.setState(StateUnit.DEAD);
                }
                continue;
            }

            StateUnit nextState;

            if (unit.hasTarget()) {
                if (unit.isTargetInRange()) {
                    nextState = StateUnit.ATTACKING;
                } else {
                    Vector2i destination = unit.getTargetDestination();
                    unit.setDestination(WorldSystem.getSingleton().getWalkableAdjacentCell(destination.x, destination.y) );
                    nextState = StateUnit.MOVING;
                }
            }
            else if (unit.hasDestination()) {
                nextState = StateUnit.MOVING;
            }
            else {
                nextState = StateUnit.IDLE;
            }

            if (unit.getState() != nextState) {
                unit.setState(nextState);
            }
        }

        combatSystem.update(delta);
        movementSystem.update(delta);
        unitController.update(delta);
        unitAnimationSystem.update();

        resolveQueues();
    }



    private void resolveQueues(){
        for (Unit2D unit : birthQueue) {
            if(unitsRootNode==null) break;
            unitsRootNode.addChild(unit);
            runtime.register(unit);
        }
        if(!birthQueue.isEmpty()){
            birthQueue.clear();
        }
        for (Unit2D unit : deathQueue) {
            if(unitsRootNode==null) break;
            unit.queueFree();
            runtime.unregister(unit);
        }
        if(!deathQueue.isEmpty()){
            deathQueue.clear();
        }
    }

    public void setRootNode(Node root) {
        this.unitsRootNode = root;
    }

    public UnitRunTimeServer getRuntimeServer() {
        return runtime;
    }
}
