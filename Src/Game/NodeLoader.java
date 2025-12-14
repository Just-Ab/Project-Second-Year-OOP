package Game;


import static org.lwjgl.glfw.GLFW.GLFW_KEY_A;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_D;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_S;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_SPACE;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_W;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_Z;

import java.util.Random;

import org.joml.Vector3f;

import CodeNameNeutronStar.Map2D;
import Game.Cameras.Nodes.Camera2D;
import Game.Core.Node;

import Game.Visuals.Nodes.AnimatedSprite2D;
import Rendering.RenderInstance;
import Rendering.RenderingServer;
import UserIO.Input;


public class NodeLoader extends Node{
    
    public NodeLoader(){
        super();
    }

    Map2D map = new Map2D(10, 10);
    Camera2D camera = new Camera2D(new Vector3f(), 20, 20);
    AnimatedSprite2D sprite = new AnimatedSprite2D();

    public void _ready(){  
        addChild(camera);
        camera.current();
        addChild(map);
        camera.setLocalPosition(new Vector3f(5.0f,1.0f,0.0f));
        Random rand = new Random();
        map.setTileset("Assets/Textures/tileset.png", 12, 12);
        for (int y = 0; y < 10; y++) {
            for (int x = 0; x < 10; x++) {
                map.setCellUVIndex(x,y , 12*5+5+rand.nextInt(2));
            }
        }
        RenderInstance render = RenderingServer.getSingleton().createColorRect();
        render.setColor(new Vector3f(1.0f,1.0f,1.0f));
        
        addChild(sprite);
        sprite.setLocalPosition(new Vector3f(0.0f,0.0f,0.5f));
        sprite.setTexture("Assets/Textures/Anim.png");
        sprite.setFrameColumns(8);
        sprite.setFrameRows(9);
        sprite.createAnimation("Walk",8*6 ,8*6+3 );
        sprite.activateAnimation("Walk");
        sprite.setLocalPosition(new Vector3f(3.0f,2.0f,0.0f));
        sprite.play();
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
        System.out.println(Input.getMouseGlobalPosition());
        camera.setLocalPosition(camera.getLocalPosition().add(Input.getAxis(GLFW_KEY_A, GLFW_KEY_D)*_delta*2,Input.getAxis(GLFW_KEY_S, GLFW_KEY_W)*_delta*2,0.0f));
    }


    protected void _enterTree(){
    }
}
