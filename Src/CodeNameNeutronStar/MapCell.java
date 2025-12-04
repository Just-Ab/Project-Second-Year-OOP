package CodeNameNeutronStar;

import org.joml.Vector2f;

import Game.Core.Resource;



public class MapCell extends Resource{
    public Vector2f position = new Vector2f(0.0f);
    public short packedBits = 0;
}
