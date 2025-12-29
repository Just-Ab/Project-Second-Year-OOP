package Game.UI;

import org.joml.Vector3f;

import Game.Cameras.Nodes.Camera2D;
import Game.Core.GameServer;
import Game.Core.Node2D;

public class UIPanel extends Node2D {




    @Override
    protected void updateEngine(float _delta) {
        Camera2D currentCamera= GameServer.getSingleton().getCurrentCamera2D();
        if(currentCamera!=null){
            setLocalPosition(new Vector3f(currentCamera.getGlobalPosition().x,currentCamera.getGlobalPosition().y,1.0f));
            setLocalScale(new Vector3f(currentCamera.getZoom().x,currentCamera.getZoom().y,1.0f));
        }
        super.updateEngine(_delta);
    }
}
