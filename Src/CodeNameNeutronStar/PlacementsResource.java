package CodeNameNeutronStar;

import Game.Core.Resource;

public class PlacementsResource extends Resource {

    private final PlacementsGridResource placementsGrid;

    public PlacementsResource(PlacementsGridResource placementsGrid){
        this.placementsGrid = placementsGrid;
    }

    boolean isBlocked(int x, int y){
        return placementsGrid.isBlocked(x, y);
    }

    void blockCell(int x, int y){
        placementsGrid.blockCell(x, y);
    }

    void blockCell(int x, int y, int width, int height){
        placementsGrid.blockCell(x, y, width, height);
    }

    void freeCell(int x, int y){
        placementsGrid.freeCell(x, y);
    }

    void freeCell(int x, int y, int width, int height){
        placementsGrid.freeCell(x, y, width, height);
    }

    int getWidth(){
        return placementsGrid.getWidth();
    }

    int getHeight(){
        return placementsGrid.getHeight();
    }

    PlacementsGridResource getPlacementsGrid(){
        return placementsGrid;
    }
}
