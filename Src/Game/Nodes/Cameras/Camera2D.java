package Game.Nodes.Cameras;

import org.joml.*;

import Game.Core.Node2D;
import Rendering.CameraRender2D;
import Rendering.RenderingServer;

public class Camera2D extends Node2D{
    private CameraRender2D camera = null;
    
    
    public Camera2D(Vector3f _position,float _width,float _height){
        super("");
        camera = RenderingServer.getSingleton().createCamera2D(_position, _width, _height);
    }

    public void current(){RenderingServer.getSingleton().makeCamera2DCurrent(camera);}
    
}
