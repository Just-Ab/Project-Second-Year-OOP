package CodeNameNeutronStar.Units;

import CodeNameNeutronStar.Units.Unit2D.StateUnit;

public class UnitController {

    private final UnitRunTimeServer runtime;

    public UnitController(UnitRunTimeServer _runtime){
        runtime = _runtime;
    }

    public void update(float _delta){

        for (Unit2D unit : runtime.getAll()) {

            if (unit.getState() != StateUnit.IDLE) continue;

            Unit2D chosenUnit = validateTarget(unit);

            if (chosenUnit == null) continue;

            unit.setTarget(chosenUnit);
        }
        
        

    }

    private Unit2D validateTarget(Unit2D _unit){
        for (Unit2D target : runtime.getAll()) {
            
            if (_unit.canDetectUnit(target) && target != _unit && target.getState() != StateUnit.DEAD && target.getTeam() != _unit.getTeam()) {

                return target;
            }
            
        }    
        return null;
    }


}

