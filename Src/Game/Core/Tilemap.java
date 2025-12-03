// package Game.Core;


// import java.util.ArrayList;
// import java.util.List;

// import org.joml.Vector2f;
// import org.joml.Vector2i;
// import org.joml.Vector3f;
// import org.joml.Vector4f;

// import Rendering.RenderInstance;
// import Rendering.RenderingServer;

// public class Tilemap extends Node2D{

//     protected record Tile(Vector2f uv,Boolean dirty,RenderInstance instance) {}
//     protected List<Tile> tiles=new ArrayList<>();
//     protected TextureResource2D texture=null;
//     protected int width=0,height=0;


//     protected boolean visiblity=true;
//     protected boolean dirty=true;


//     public Tilemap(String _name,int _width,int _height){
//         super(_name);
//         width = _width;
//         height = _height;
//     }

//     public void setTexture(String _path){
//         if(texture==null){
//             texture = new TextureResource2D(_path);
//         }
//         else{
//             texture.loadTexture(_path);
//         }
//         if(isInTree&&(tiles!=null)){
//             for (Tile tile : tiles) {
//                 tile.instance.setTextureResource(texture.getRenderingResource());
//             }
//         }
//     }

//     public Tile setCellAtlas(){
//         if (x<0||x>width||y<0||y>height) {return null;}
//         return tiles.get(x+width*y);
//     }

//     public Tile getCell(int x,int y){
//         if (x<0||x>width||y<0||y>height) {return null;}
//         return tiles.get(x+width*y);
//     }



//     public void setUV(Vector2i _position,Vector4f _uv){
//         if(instances!=null){
//             instances.get(0)
//         }
//     }
    
//     public Vector4f getUV() {
//         if(instance!=null){
//             return instance.getUV();
//         }
//         else{
//             return uv;
//         }        
//     }

//     public void setVisibility(boolean _visiblity){
//         if(instance==null){
//             visiblity=_visiblity;
//         }
//         else{
//             instance.setVisibility(_visiblity);
//         }
//     }
    
//     public boolean isVisible(){
//         if(instance==null){
//             return visiblity;
//         }
//         else{
//             return instance.isVisible();
//         }    
//     }

//     @Override
//     public void setLocalPosition(Vector3f _position){super.setLocalPosition(_position);dirty=true;}

//     @Override
//     public void setLocalScale(Vector3f _scale){super.setLocalScale(_scale);dirty=true;}

//     @Override
//     public void setLocalRotationDegrees(float _degrees){super.setLocalRotationDegrees(_degrees);dirty=true;}

//     @Override
//     public void setLocalRotationRad(float _rad){super.setLocalRotationRad(_rad);dirty=true;}

//     @Override
//     public void _update(float _delta){
//         if(instance!=null && dirty){
//             instance.setPosition(getGlobalPosition());
//             instance.setRotation(getGlobalRotation());
//             instance.setScale(getGlobalScale());
//             instance.setUV(uv);
//             dirty=false;
//         }
//     }

//     @Override
//     protected void _enterTree(){
//         super._enterTree();
//         instance=RenderingServer.getSingleton().createSprite();
//         instance.setVisibility(visiblity);
//         instance.setPosition(getGlobalPosition());
//         instance.setRotation(getGlobalRotation());
//         instance.setScale(getGlobalScale());
//         instance.setUV(uv);
//         if (texture!=null){
//             instance.setTextureResource(texture.getRenderingResource());
//         }
//     }
// }

