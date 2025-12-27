package CodeNameNeutronStar.Buildings;

import java.util.ArrayList;
import java.util.List;

public class BuildingsProductionSystem {

    List<Building2D> registerdBuildings = new ArrayList<>();

    public void register(Building2D building2D){
        registerdBuildings.addLast(building2D);
    }

    public void unRegister(Building2D building2D){
        registerdBuildings.remove(building2D);
    }

    public void update(float delta) {
        for (Building2D building : registerdBuildings) {
            if(!(building.getResource().getProduct()==Product.NONE)){
                //bla bla economy bla bla
            }
        }
    }
}
