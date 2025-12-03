package Game.Core;

import Rendering.*;

public class TextureResource2D extends Resource{

    private String path="";
    private TextureResource textureResource=null;


    public TextureResource2D(String _path){
        textureResource = RenderingServer.getSingleton().creaTextureResource(_path);
    }

    public void loadTexture(String _path){
        if (!(textureResource==null)){
            try {
                textureResource.setTexture(_path);
                path=_path;
            } catch (Exception e) {
                System.err.println("Texture Path Invalid!");
            }
        }
    }

    public String getPath(){return path;}
    public TextureResource getRenderingResource(){return textureResource;}
}
