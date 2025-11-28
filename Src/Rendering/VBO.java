package Rendering;

import static org.lwjgl.opengl.GL15.*;


public class VBO {
    
    private float[] data;

    private int id;

    public VBO(){
        id = glGenBuffers();
    }
    public VBO(float[] _vertices){
        id = glGenBuffers();
        data = _vertices;
    }

    public void setData(float[] _vertices){
        data = _vertices;
    }

    public void loadGpu(int mode){
        if(data.length<=0){
            return;
        }
        glBindBuffer(GL_ARRAY_BUFFER, id);
        glBufferData(GL_ARRAY_BUFFER,data,mode);
    }

    public void bind(){
        glBindBuffer(GL_ARRAY_BUFFER, id);
    }

    public void unbind(){
        glBindBuffer(GL_ARRAY_BUFFER, 0);
    }

}
