package Game.Visuals.Resources;

import Game.Core.Resource;
import Rendering.*;

public class TextureResource extends Resource{

    private Texture textureResource=null;


    public TextureResource(String _path){
        textureResource = RenderingServer.getSingleton().creaTextureResource(_path);
    }

    public void loadTexture(String _path){
        if (!(textureResource==null)){
            try {
                textureResource.setTexture(_path);
            } catch (Exception e) {
                System.err.println("Texture Path Invalid!");
            }
        }
    }

    public String getTexturePath(){return textureResource.getPath();}

    public Texture getTexture(){return textureResource;}
}
