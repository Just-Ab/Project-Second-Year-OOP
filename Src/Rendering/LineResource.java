package Rendering;


import static org.lwjgl.opengl.GL11.GL_FLOAT;
import static org.lwjgl.opengl.GL15.GL_STATIC_DRAW;


public class LineResource {
    VAO vao=new VAO();VBO vbo= new VBO();

    private float[] vertices = {};

    public LineResource(){

    }

    public void setVertices(float[] _vertices){
        vertices = _vertices;
        vao.bind();
        vbo.bind();
        vbo.setData(_vertices);
        vbo.loadGpu(GL_STATIC_DRAW);
        vao.linkAttribute(vbo, 0, 3, GL_FLOAT, false, 3*Float.BYTES, 0,0);
        vao.unbind();
        vbo.unbind();    
    }

    public float[] getVertices(){
        return vertices;
    }

    public void bind(){
        vao.bind();
    }

    public void unbind(){
        vao.unbind();
    }

}
