package Game;


import static org.lwjgl.glfw.GLFW.GLFW_KEY_A;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_D;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_S;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_SPACE;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_W;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_Z;

import java.util.Random;

import org.joml.Vector3f;
import org.joml.Vector4f;

import CodeNameNeutronStar.Terrain2D;
import CodeNameNeutronStar.TerrainGridResource;
import CodeNameNeutronStar.TerrainResource;
import CodeNameNeutronStar.TerrainCellResource.TerrainType;
import Game.Cameras.Nodes.Camera2D;
import Game.Core.Node;

import Game.Visuals.Nodes.AnimatedSprite2D;
import Game.Visuals.Nodes.Sprite2D;
import Game.Visuals.Resources.TilesetResource;
import Rendering.RenderInstance;
import Rendering.RenderingServer;
import UserIO.Input;


public class NodeLoader extends Node{
    
    public NodeLoader(){
        super();
    }

    Terrain2D map = null;
    Camera2D camera = new Camera2D(new Vector3f(), 5, 5);

    Sprite2D sprite = new Sprite2D();

    public void _ready(){  
        sprite.setTexture("Assets/Textures/Anim.png");
        sprite.setUV(new Vector4f(0.0f,2.0f*(1.0f/9),1.0f/8,1.0f/9));
        addChild(camera);
        camera.current();
        TilesetResource tilsetres = new TilesetResource("Assets/Textures/Anim.png", 8, 9);
        TerrainGridResource terrainGrid = new TerrainGridResource(2, 2);
        TerrainResource terrainResource = new TerrainResource(tilsetres,terrainGrid);
        terrainGrid.setCellUVIndex(0,0 , 0);
        terrainGrid.setCellUVIndex(0,1 , 0);
        terrainGrid.setCellUVIndex(1,0 , 2);
        terrainGrid.setCellUVIndex(1,1 , 1);
        terrainGrid.setCellType(0, 0, TerrainType.ROAD);
        terrainGrid.setCellType(0, 1, TerrainType.ROAD);
        terrainGrid.setCellType(1, 0, TerrainType.BLOCKED);
        terrainGrid.setCellType(1, 1, TerrainType.ROAD);

        map = new Terrain2D(terrainResource);
        // addChild(map);
        addChild(sprite);
        System.out.println(terrainGrid.getCellWalkability(1, 0));
        System.out.println(terrainGrid.getCellWalkability(1, 1));

    }

    boolean zoom = false;
    boolean dirtyZoom = true;

    
    public void _update(float _delta){
        if(Input.isKeyJustPressed( GLFW_KEY_SPACE)){
            map.queueFree();
        }
        if(!zoom&&dirtyZoom){
            camera.setZoom(1.0f, 1.0f);
            dirtyZoom=false;
        }
        else if(zoom&&dirtyZoom){
            camera.setZoom(0.5f, 0.5f);
            dirtyZoom=false;
        }
        if(Input.isKeyJustPressed(GLFW_KEY_Z)){
            dirtyZoom = true;
            zoom = !zoom;
        }
        camera.setLocalPosition(camera.getLocalPosition().add(Input.getAxis(GLFW_KEY_A, GLFW_KEY_D)*_delta*2,Input.getAxis(GLFW_KEY_S, GLFW_KEY_W)*_delta*2,0.0f));
    }


    protected void _enterTree(){
    }
}
