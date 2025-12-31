package CodeNameNeutronStar.Units;


import CodeNameNeutronStar.Units.Unit2D.StateUnit;

public class CombatSystem {

    private final UnitRunTimeServer runtime;

    public CombatSystem(UnitRunTimeServer _runtime){
        runtime = _runtime;
    }

    public void update(float _delta){

        for (Unit2D unit : runtime.getAll()) {
            
            if (unit.getState() != StateUnit.ATTACKING) continue;
            unit.addAttackTimer(_delta);
            if (unit.canAttack()){
                unit.performAttack();
                unit.resetAttackTimer();
            }
        }
        

    }

}

