package Rendering;
import java.util.ArrayList;
import java.util.List;


import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import org.joml.*;
import org.lwjgl.opengl.GL;

public class RenderingServer {
    
    private static RenderingServer server;

    private Window window;

    private List<TextureResource> textureResources=new ArrayList<>();
    private List<ShaderProgram> shaderPrograms=new ArrayList<>();
    private List<CameraRender2D> cameras=new ArrayList<>();
    private CameraRender2D currentCamera2D;

    private QuadMeshResource quadMeshResource;
    private List<ColorRectInstance> colorRectInstances=new ArrayList<>();

    private List<SpriteRenderInstance> spriteInstances=new ArrayList<>();

    private List<LineResource> lineResources=new ArrayList<>();
    private List<LineInstance> lineInstances=new ArrayList<>();


    public static RenderingServer getSingleton(){
        if(server==null){
            server = new RenderingServer();
        }
        return server;
    }

    public Window createWindow(String _name,int _width,int _height){
        window = new Window(_name, _width, _height);
        glfwMakeContextCurrent(window.getWindow());
        GL.createCapabilities();
        glViewport(0, 0, _width, _height);
        initializeNativeResources();
        return window;
    }

    public CameraRender2D createCamera2D(Vector3f _position,float _width,float _height){
        cameras.addLast(new CameraRender2D(_position, _width, _height));
        return cameras.getLast();
    }

    public void makeCamera2DCurrent(CameraRender2D _camera){
        currentCamera2D = _camera;
    }
    public void makeDefaultCamera2DCurrent(){
        currentCamera2D = cameras.getFirst();
    }
    private void initializeNativeResources(){
        server.quadMeshResource = new QuadMeshResource();
        server.cameras.addLast(new CameraRender2D(new Vector3f(0.0f,0.0f,0.0f),5.0f,5.0f));
        server.currentCamera2D = cameras.getFirst();
    }

    public ColorRectInstance createColorRect(){
        colorRectInstances.addLast(new ColorRectInstance(quadMeshResource,createShaderProgram("Assets/Shaders/ColorRect.vert","Assets/Shaders/ColorRect.frag")));
        return colorRectInstances.getLast();
    }

    public ColorRectInstance createColorRect(ShaderProgram shaderProgram){
        colorRectInstances.addLast(new ColorRectInstance(quadMeshResource,shaderProgram));
        return colorRectInstances.getLast();
    }

    public SpriteRenderInstance createSprite(){
        spriteInstances.addLast(new SpriteRenderInstance(quadMeshResource,createShaderProgram("Assets/Shaders/Sprite.vert","Assets/Shaders/Sprite.frag")));
        return spriteInstances.getLast();
    }

    public SpriteRenderInstance createSprite(ShaderProgram shaderProgram){
        spriteInstances.addLast(new SpriteRenderInstance(quadMeshResource,shaderProgram));
        return spriteInstances.getLast();
    }


    public LineInstance createLine(float[] _vertices){
        lineResources.addLast(new LineResource());
        lineInstances.addLast(new LineInstance(lineResources.getLast(),createShaderProgram("Assets/Shaders/Line.vert","Assets/Shaders/Line.frag")));
        lineInstances.getLast().setPoints(_vertices);
        return lineInstances.getLast();
    }

    public LineInstance createLine(float[] _vertices,ShaderProgram shaderProgram){
        lineResources.addLast(new LineResource());
        lineInstances.addLast(new LineInstance(lineResources.getLast(),shaderProgram));
        lineInstances.getLast().setPoints(_vertices);
        return lineInstances.getLast();
    }

    public TextureResource creaTextureResource(String path){
        for (TextureResource texture : textureResources) {
            if(texture.getPath().equals(path)){
                return texture;
            }
        }
        textureResources.addLast(new TextureResource(path));
        return textureResources.getLast();
    }

    public ShaderProgram createShaderProgram(String vertexPath,String fragmentPath){
        for (ShaderProgram program : shaderPrograms) {
            if(program.vertexPath.equals(vertexPath) && program.fragmentPath.equals(fragmentPath)){
                return program;
            }
        }
        shaderPrograms.addLast(new ShaderProgram(vertexPath,fragmentPath));
        return shaderPrograms.getLast();
    }

    public void remove(Object object){
        
        if (object instanceof ColorRectInstance){
            colorRectInstances.remove(object);
        }
        else if (object instanceof SpriteRenderInstance){
            spriteInstances.remove(object);
        }
        else if (object instanceof LineInstance){
            lineInstances.remove(object);
        }
        else if (object instanceof TextureResource){
            textureResources.remove(object);
        }
        else if (object instanceof ShaderProgram){
            shaderPrograms.remove(object);
        }
        else if (object instanceof CameraRender2D){
            cameras.remove(object);
        }
        else{
            System.err.println("Type Unrecognizeable!: "+object.getClass());
        }

    }


    public void beginFrame(){
        glClearColor(0.2f, 0.4f, 0.4f, 1.0f);
        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
        glfwSwapInterval(1);
        glClear(GL_COLOR_BUFFER_BIT);
        glfwPollEvents();
    }

     public void drawFrame(){
        currentCamera2D.update();
        quadMeshResource.bind();
        for (ColorRectInstance colorRectInstance : colorRectInstances) {
            if (!colorRectInstance.isVisible()) {
                continue;
            }
            colorRectInstance.getShaderProgram().bind();

            Matrix4f model = new Matrix4f();

            model.translate(colorRectInstance.getPosition());
            
            model.rotate(colorRectInstance.getRotation().x, 1.0f,0.0f,0.0f);
            model.rotate(colorRectInstance.getRotation().y, 0.0f,1.0f,0.0f);
            model.rotate(colorRectInstance.getRotation().z, 0.0f,0.0f,1.0f);

            model.scale(colorRectInstance.getScale());
            


            colorRectInstance.getShaderProgram().setMat4("model", model);
            colorRectInstance.getShaderProgram().setMat4("projection", currentCamera2D.getProjection());
            colorRectInstance.getShaderProgram().setMat4("view", currentCamera2D.getView());

            colorRectInstance.getShaderProgram().setVec3("color", colorRectInstance.getColor());
            glDrawElements(GL_TRIANGLES,colorRectInstance.getResource().getIndices().length,GL_UNSIGNED_INT,0);
            colorRectInstance.getShaderProgram().unbind();
        
        }
        for (SpriteRenderInstance spriteInstance : spriteInstances) {
            if (!spriteInstance.isVisible()) {
                continue;
            }
            spriteInstance.getShaderProgram().bind();

            Matrix4f model = new Matrix4f();

            model.translate(spriteInstance.getPosition());
            
            model.rotate(spriteInstance.getRotation().x, 1.0f,0.0f,0.0f);
            model.rotate(spriteInstance.getRotation().y, 0.0f,1.0f,0.0f);
            model.rotate(spriteInstance.getRotation().z, 0.0f,0.0f,1.0f);

            model.scale(spriteInstance.getScale());
            
            spriteInstance.getShaderProgram().setVec4("uv", spriteInstance.getUV());
            spriteInstance.getShaderProgram().setMat4("model", model);
            spriteInstance.getShaderProgram().setMat4("projection", currentCamera2D.getProjection());
            spriteInstance.getShaderProgram().setMat4("view", currentCamera2D.getView());

            spriteInstance.getTextureResource().bind();
            spriteInstance.getShaderProgram().setInt("textureUnit", spriteInstance.getTextureResource().getTextureUnitNormal());
            glDrawElements(GL_TRIANGLES,spriteInstance.getResource().getIndices().length,GL_UNSIGNED_INT,0);
            spriteInstance.getShaderProgram().unbind();
            spriteInstance.getTextureResource().unbind();        
        }

        for (LineInstance lineInstance : lineInstances) {
            if(!lineInstance.isVisible()){
                continue;
            }
            ShaderProgram shader = lineInstance.getShaderProgram();
            LineResource lineResource = lineInstance.getResource();

            shader.bind();
            lineResource.bind();

            if(lineInstance.isDirty()){
                lineResource.setVertices(lineInstance.getPoints());
                lineInstance.setIsDirty(false);
            }
            Matrix4f model = new Matrix4f();

            shader.setVec3("color", lineInstance.getColor());
            model.translate(lineInstance.getPosition());
            shader.setMat4("view", currentCamera2D.getView());
            shader.setMat4("projection", currentCamera2D.getProjection());
            shader.setMat4("model", model);

            glLineWidth(lineInstance.getThickness());
            glDrawArrays(GL_LINE_STRIP,0, lineInstance.getPoints().length/3);
            lineResource.unbind();
            shader.unbind();
        }
    }   

    public void endFrame(){
        glfwSwapBuffers(window.getWindow());
    }

}