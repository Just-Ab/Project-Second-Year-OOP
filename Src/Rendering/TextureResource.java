package Rendering;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL12.GL_CLAMP_TO_EDGE;
import static org.lwjgl.opengl.GL12.GL_TEXTURE_WRAP_R;
import static org.lwjgl.opengl.GL13.GL_TEXTURE0;
import static org.lwjgl.opengl.GL13.glActiveTexture;
import static org.lwjgl.opengl.GL30.*;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.stb.STBImage;




public class TextureResource {

    private String path;
    private int textureUnitNormal=0;
    private int id=0;

    public TextureResource(){
    }

    public TextureResource(String path){
        try{setTexture(path);}
        catch(Exception e){System.err.println("Texture Loading Failed!");}
    }

    public void setTexture(String path){
        
        IntBuffer width = BufferUtils.createIntBuffer(1);
        IntBuffer height = BufferUtils.createIntBuffer(1);
        IntBuffer colorDepth = BufferUtils.createIntBuffer(1);
        ByteBuffer data;
        this.path = path;
        STBImage.stbi_set_flip_vertically_on_load(true);
        glPixelStorei(GL_UNPACK_ALIGNMENT, 1);
        data=STBImage.stbi_load(path, width, height, colorDepth, 0);
        if (data==null){
            throw new RuntimeException("Image doesn't exist or corrup.");
        }
        id = glGenTextures();
        glBindTexture(GL_TEXTURE_2D, id);

        int format,internalFormat=0;

        switch (colorDepth.get(0)) {
            case 1:
                format = GL_R;
                internalFormat = GL_R8;
                break;
            case 2:
                format = GL_RG;
                internalFormat = GL_RG8;
                break;
            case 3:
                format = GL_RGB;
                internalFormat = GL_RGB8;
                break;
            case 4:
                format = GL_RGBA;
                internalFormat = GL_RGBA8;
                break;
            default:
                format = GL_RGB;
                internalFormat = GL_RGB8;
                break;
        }

        glTexImage2D(GL_TEXTURE_2D, 0, internalFormat, width.get(0), height.get(0), 0, format, GL_UNSIGNED_BYTE, data);
        glGenerateMipmap(GL_TEXTURE_2D);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST_MIPMAP_NEAREST);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_CLAMP_TO_EDGE);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_R, GL_CLAMP_TO_EDGE);



        STBImage.stbi_image_free(data);
    }

    int getId(){
        return id;
    }

    public String getPath(){return path;}

    public int getTextureUnitNormal(){
        return textureUnitNormal;
    }

    void bind(){
        glActiveTexture(GL_TEXTURE0);
        textureUnitNormal=0;
        glBindTexture(GL_TEXTURE_2D, id);;
    }

    void bind(int unit){
        glActiveTexture(GL_TEXTURE0+unit);
        textureUnitNormal = unit;
        glBindTexture(GL_TEXTURE_2D, id);;
    }

    void unbind(){
        glActiveTexture(GL_TEXTURE0+textureUnitNormal);
        glBindTexture(GL_TEXTURE_2D, 0);;
    }

}



