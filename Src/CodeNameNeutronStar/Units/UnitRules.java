package CodeNameNeutronStar.Units;

import java.security.PublicKey;

import Game.Visuals.Resources.AnimationResource;

public final class UnitRules {

    private UnitRules() {}

    public static final String UNITS1X1_TILESET_PATH = "Assets/Textures/UnitsSpreadSheet1X1.png";
    public static final int UNITS1X1_TILESET_H = 10;
    public static final int UNITS1X1_TILESET_V = 10;

    public static final int MAX_UNITS = 500;

    public static final String IDLE_ANIMATION_NAME = "IDLE";
    public static final String MOVE_ANIMATION_NAME = "MOVE";
    public static final String ATTACK_ANIMATION_NAME = "ATTACK";
    public static final String DIE_ANIMATION_NAME = "DIE";


    public static final String RIFLEMAN_NAME = "RIFLEMAN";

    public static final float RIFLEMAN_PRICE = 75.0f;
    public static final float RIFLEMAN_MAX_HEALTH = 100.0f;
    public static final float RIFLEMAN_ATTACK_RANGE = 2.0f;
    public static final float RIFLEMAN_DETECTION_RANGE = 4.0f;
    public static final float RIFLEMAN_DAMAGE = 22.0f;
    public static final float RIFLEMAN_SPEED = 2.5f;
    public static final float RIFLEMAN_COOLDOWN = 0.7f;
    
    public static final AnimationResource RIFLEMAN_MOVE     = 
              new AnimationResource(MOVE_ANIMATION_NAME,10*6,10*6+2);
              
    public static final AnimationResource RIFLEMAN_IDLE     = 
              new AnimationResource(IDLE_ANIMATION_NAME,10*7,10*7+3);
              
    public static final AnimationResource RIFLEMAN_ATTACK   = 
              new AnimationResource(ATTACK_ANIMATION_NAME,10*8,10*8+2);

    public static final AnimationResource RIFLEMAN_DIE      = 
              new AnimationResource(DIE_ANIMATION_NAME,10*9,10*9+3);


  }
