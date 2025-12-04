package Game.Cameras.Nodes;

import org.joml.*;

import Game.Core.Node2D;
import Rendering.CameraRender2D;
import Rendering.RenderingServer;

public class Camera2D extends Node2D{
    private CameraRender2D camera = null;
    
    private boolean dirty = true;
    
    public Camera2D(Vector3f _position,float _width,float _height){
        camera = RenderingServer.getSingleton().createCamera2D(_position, _width, _height);
    }

    @Override
    public void setLocalPosition(Vector3f _Vector3f){
        super.setLocalPosition(_Vector3f);
        dirty = true;
    }

    public void current(){RenderingServer.getSingleton().makeCamera2DCurrent(camera);}
    
    @Override
    public void _update(float _delta){
        if(dirty){
            dirty = false;
            camera.setPosition(getGlobalPosition());
        }
    }
}
