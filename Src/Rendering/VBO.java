package Rendering;

import static org.lwjgl.opengl.GL15.*;


public class VBO {
    
    private float[] vertices;

    private int id;

    public VBO(){
        id = glGenBuffers();
    }
    public VBO(float[] _vertices){
        id = glGenBuffers();
        this.vertices = _vertices;
    }

    public void setVertices(float[] _vertices){
        this.vertices = _vertices;
    }

    public void loadGpu(int mode){
        if(vertices.length<=0){
            return;
        }
        glBindBuffer(GL_ARRAY_BUFFER, id);
        glBufferData(GL_ARRAY_BUFFER,vertices,mode);
    }

    public void bind(){
        glBindBuffer(GL_ARRAY_BUFFER, id);
    }

    public void unbind(){
        glBindBuffer(GL_ARRAY_BUFFER, 0);
    }

}
