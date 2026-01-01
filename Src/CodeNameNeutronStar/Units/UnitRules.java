package CodeNameNeutronStar.Units;

import Game.Visuals.Resources.AnimationResource;

public final class UnitRules {

    private UnitRules() {}

    public static final String UNITS1X1_TILESET_PATH = "Assets/Textures/UnitsSpreadSheet1X1.png";
    public static final int UNITS1X1_TILESET_H = 10;
    public static final int UNITS1X1_TILESET_V = 20;

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
              new AnimationResource(MOVE_ANIMATION_NAME,10*13,10*13+2);
              
    public static final AnimationResource RIFLEMAN_IDLE     = 
              new AnimationResource(IDLE_ANIMATION_NAME,10*14,10*14+3);
              
    public static final AnimationResource RIFLEMAN_ATTACK   = 
              new AnimationResource(ATTACK_ANIMATION_NAME,10*15,10*15+2);

    public static final AnimationResource RIFLEMAN_DIE      = 
              new AnimationResource(DIE_ANIMATION_NAME,10*16,10*16+3);


    public static final String KNIFE_DUDE_NAME = "KNIFE DUDE";

    public static final float KNIFE_DUDE_PRICE = 50.0f;

    public static final float KNIFE_DUDE_MAX_HEALTH = 70.0f;
    public static final float KNIFE_DUDE_ATTACK_RANGE = 1.2f;
    public static final float KNIFE_DUDE_DETECTION_RANGE = 4.0f;
    public static final float KNIFE_DUDE_DAMAGE = 18.0f;
    public static final float KNIFE_DUDE_SPEED = 3.2f;
    public static final float KNIFE_DUDE_COOLDOWN = 0.5f;

    public static final AnimationResource KNIFE_DUDE_MOVE =
            new AnimationResource(MOVE_ANIMATION_NAME, 10 * 9, 10 * 9 + 2);

    public static final AnimationResource KNIFE_DUDE_IDLE =
            new AnimationResource(IDLE_ANIMATION_NAME, 10 * 10, 10 * 10 + 3);

    public static final AnimationResource KNIFE_DUDE_ATTACK =
            new AnimationResource(ATTACK_ANIMATION_NAME, 10 * 11, 10 * 11 + 2);

    public static final AnimationResource KNIFE_DUDE_DIE =
            new AnimationResource(DIE_ANIMATION_NAME, 10 * 12, 10 * 12 + 3);

    public static final String ROVER_DUDE_NAME = "ROVER DUDE";

    public static final float ROVER_DUDE_PRICE = 120.0f;

    public static final float ROVER_DUDE_MAX_HEALTH = 140.0f;
    public static final float ROVER_DUDE_ATTACK_RANGE = 4.5f;
    public static final float ROVER_DUDE_DETECTION_RANGE = 6.5f;
    public static final float ROVER_DUDE_DAMAGE = 28.0f;
    public static final float ROVER_DUDE_SPEED = 2.1f;
    public static final float ROVER_DUDE_COOLDOWN = 1.1f;

    public static final AnimationResource ROVER_DUDE_MOVE =
            new AnimationResource(MOVE_ANIMATION_NAME, 10 * 6, 10 * 6 );

    public static final AnimationResource ROVER_DUDE_IDLE =
            new AnimationResource(IDLE_ANIMATION_NAME, 10 * 5, 10 * 5 + 1);

    public static final AnimationResource ROVER_DUDE_ATTACK =
            new AnimationResource(ATTACK_ANIMATION_NAME, 10 * 7, 10 * 7 + 1);

    public static final AnimationResource ROVER_DUDE_DIE =
            new AnimationResource(DIE_ANIMATION_NAME, 10 * 8, 10 * 8 + 3);


    public static final String DALEK_NAME = "DALEK";

    public static final float DALEK_PRICE = 0.0f;
    public static final float DALEK_MAX_HEALTH = 100.0f;
    public static final float DALEK_ATTACK_RANGE = 2.0f;
    public static final float DALEK_DETECTION_RANGE = 4.0f;
    public static final float DALEK_DAMAGE = 22.0f;
    public static final float DALEK_SPEED = 2.5f;
    public static final float DALEK_COOLDOWN = 0.7f;
    
    public static final AnimationResource DALEK_MOVE     = 
              new AnimationResource(MOVE_ANIMATION_NAME,10*17,10*17);
              
    public static final AnimationResource DALEK_IDLE     = 
              new AnimationResource(IDLE_ANIMATION_NAME,10*17,10*17+7);
              
    public static final AnimationResource DALEK_ATTACK   = 
              new AnimationResource(ATTACK_ANIMATION_NAME,10*18,10*18+1);

    public static final AnimationResource DALEK_DIE      = 
              new AnimationResource(DIE_ANIMATION_NAME,10*19,10*19+4);

  }
