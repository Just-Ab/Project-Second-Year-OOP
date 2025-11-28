package Rendering;

import static org.lwjgl.opengl.GL15.*;


public class EBO {
    
    private int[] indices;

    private int id;

    
    public EBO(){
        id = glGenBuffers();
    }
    public EBO(int[] _indices){
        id = glGenBuffers();
        this.indices = _indices;
    }

    public void setIndices(int[] _indices){
        this.indices = _indices;
    }

    public void loadGpu(int mode){
        if(indices.length<=0){
            return;
        }
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, id);
        glBufferData(GL_ELEMENT_ARRAY_BUFFER,indices,mode);
    }

    public void bind(){
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, id);
    }

    public void unbind(){
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
    }

}
