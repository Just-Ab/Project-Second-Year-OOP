package CodeNameNeutronStar;

import Game.Core.Resource;

public class WorldResource extends Resource{
    
    private PlacementsResource placementsResource=null;
    private TerrainResource terrainResource=null;
    private int width,height;


    public WorldResource(PlacementsResource _placementsResource,TerrainResource _terrainResource,int _width,int _height){
        placementsResource = _placementsResource;
        terrainResource = _terrainResource;
        width = _width;height = _height;
    }


    public PlacementsResource getPlacementsResource(){  return placementsResource; }
    public TerrainResource getTerrainResource(){ return terrainResource; }
    public int getWidth(){ return width; }
    public int getHeight(){ return height; }
}   
