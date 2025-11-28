package Rendering;


import static org.lwjgl.opengl.GL11.GL_FLOAT;
import static org.lwjgl.opengl.GL15.GL_STATIC_DRAW;


public class ColorRectResource {
    VAO vao=new VAO();VBO vbo= new VBO();EBO ebo=new EBO();

    private static final float[] vertices = {
        -0.5f, -0.5f, 0.0f,
        -0.5f,  0.5f, 0.0f,
         0.5f, -0.5f, 0.0f,
         0.5f,  0.5f, 0.0f
    };

    private static final int[] indices = {
        0, 1, 2,
        1, 2, 3
    };

    public ColorRectResource(){
        vao.bind();
        vbo.bind();
        ebo.bind();
        vbo.setVertices(vertices);
        vbo.loadGpu(GL_STATIC_DRAW);
        ebo.setIndices(indices);
        ebo.loadGpu(GL_STATIC_DRAW);
        vao.linkAttribute(vbo, 0, 3, GL_FLOAT, false, 3*Float.BYTES, 0);
        vao.unbind();
        vbo.unbind();
    }


    public float[] getVertices(){
        return vertices;
    }


    public int[] getIndices(){
        return indices;
    }

    public void bind(){
        vao.bind();
    }

    public void unbind(){
        vao.unbind();
    }

}
