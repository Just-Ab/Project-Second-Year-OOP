package Rendering;

import static org.lwjgl.opengl.GL20.glVertexAttribPointer;
import static org.lwjgl.opengl.GL30.*;

public class VAO {
    

    private int id;

    public VAO(){
        id = glGenVertexArrays();
    }

    public void linkAttribute(VBO vbo,int index, int size, int type, boolean normalized, int stride, int offset){
        vbo.bind();
        glVertexAttribPointer(index,size,type,normalized,stride,offset);
        glEnableVertexAttribArray(index);
        vbo.unbind();
    }

    public void bind(){
        glBindVertexArray(id);
    }

    public void unbind(){
        glBindVertexArray(0);
    }

}
