package Rendering;

import static org.lwjgl.opengl.GL20.glVertexAttribPointer;
import static org.lwjgl.opengl.GL33.*;

public class VAO {
    

    private int id;

    public VAO(){
        id = glGenVertexArrays();
    }

    // public void linkAttribute(VBO vbo,int index, int size, int type, boolean normalized, int stride, int offset){
    //     vbo.bind();
    //     glVertexAttribPointer(index,size,type,normalized,stride,offset);
    //     glEnableVertexAttribArray(index);
    //     vbo.unbind();
    // }

    public void linkAttribute(VBO _vbo,int _index, int _size, int _type, boolean _normalized, int _stride, int _offset,int _divisor){
        _vbo.bind();
        glVertexAttribPointer(_index,_size,_type,_normalized,_stride,_offset);
        glEnableVertexAttribArray(_index);
        glVertexAttribDivisor(_index, _divisor);
        _vbo.unbind();
    }

    public void bind(){
        glBindVertexArray(id);
    }

    public void unbind(){
        glBindVertexArray(0);
    }

}
