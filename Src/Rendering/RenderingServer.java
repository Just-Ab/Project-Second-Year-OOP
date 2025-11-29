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

    private List<RenderMaterial> renderMaterials = new ArrayList<>();
    private List<RenderBatch> renderBatches = new ArrayList<>();
    private List<RenderInstance> renderInstances=new ArrayList<>();
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

    public RenderInstance createColorRect(){
        ShaderProgram newShaderProgram = createShaderProgram("Assets/Shaders/ColorRect.vert","Assets/Shaders/ColorRect.frag");
        RenderInstance newRenderInstance = createRenderInstance();
        setInstanceShader(newRenderInstance, newShaderProgram);
        return newRenderInstance;
    }

    public RenderInstance createColorRect(ShaderProgram shaderProgram){
        RenderInstance newRenderInstance = createRenderInstance();
        setInstanceShader(newRenderInstance, shaderProgram);
        return newRenderInstance;
    }

    public RenderInstance createSprite(){
        ShaderProgram newShaderProgram = createShaderProgram("Assets/Shaders/Sprite.vert","Assets/Shaders/Sprite.frag");
        RenderInstance newRenderInstance = createRenderInstance();
        setInstanceShader(newRenderInstance, newShaderProgram);
        return newRenderInstance;
    }

    public RenderInstance createSprite(ShaderProgram shaderProgram){
        RenderInstance newRenderInstance = createRenderInstance();
        setInstanceShader(newRenderInstance, shaderProgram);
        return newRenderInstance;
    }

    public RenderMaterial createRenderMaterial(ShaderProgram _ShaderProgram){
        for (RenderMaterial renderMaterial : renderMaterials) {
            if(renderMaterial.getShaderProgram()==_ShaderProgram && renderMaterial.getTextureResource()==null){
                return renderMaterial;
            }
        }
        renderMaterials.addLast(new RenderMaterial(_ShaderProgram));
        return renderMaterials.getLast();
    }

    public RenderMaterial createRenderMaterial(ShaderProgram _ShaderProgram,TextureResource _TextureResource){
        for (RenderMaterial renderMaterial : renderMaterials) {
            if(renderMaterial.getShaderProgram()==_ShaderProgram && renderMaterial.getTextureResource()==_TextureResource){
                return renderMaterial;
            }
        }
        renderMaterials.addLast(new RenderMaterial(_ShaderProgram,_TextureResource));
        return renderMaterials.getLast();
    }

    public RenderInstance createRenderInstance(){
        renderInstances.addLast(new RenderInstance(quadMeshResource, null));
        return renderInstances.getLast();
    }

    public RenderBatch createRenderBatch(RenderMaterial _RenderMaterial){
        renderBatches.addLast(new RenderBatch(_RenderMaterial));
        return renderBatches.getLast();
    }

    public void setInstanceShader(RenderInstance _RenderInstance,ShaderProgram _ShaderProgram){
        TextureResource instanceTexture = _RenderInstance.getTextureResource();
        for (RenderMaterial renderMaterial : renderMaterials) {
            if( renderMaterial.getTextureResource() != instanceTexture ||
                renderMaterial.getShaderProgram() != _ShaderProgram){
                continue;
            }
            setInstanceMaterial(_RenderInstance,renderMaterial);
            return;
        }
        RenderMaterial newMaterial = createRenderMaterial(_ShaderProgram,instanceTexture);
        setInstanceMaterial(_RenderInstance, newMaterial);
    }

    public void setInstanceTexture(RenderInstance _RenderInstance,TextureResource _textureResource){
        ShaderProgram instanceShader = _RenderInstance.getShaderProgram();
        for (RenderMaterial renderMaterial : renderMaterials) {
            if( renderMaterial.getTextureResource() !=_textureResource ||
                renderMaterial.getShaderProgram() != instanceShader){
                continue;
            }
            setInstanceMaterial(_RenderInstance,renderMaterial);
            return;
        }
        RenderMaterial newMaterial = createRenderMaterial(instanceShader,_textureResource);
        setInstanceMaterial(_RenderInstance, newMaterial);
    }

    public void setInstanceMaterial(RenderInstance _RenderInstance,RenderMaterial _RenderMaterial){
        if(_RenderInstance.hasRenderBatch()) { 
            _RenderInstance.getRenderBatch().removeInstance(_RenderInstance);
            _RenderInstance.setRenderBatch(null);
        }

        for (RenderBatch renderBatch : renderBatches) {
            RenderMaterial renderMaterial = renderBatch.getRenderMaterial();
            if( renderMaterial.getShaderProgram()!=_RenderMaterial.getShaderProgram()||
                renderMaterial.getTextureResource()!=_RenderMaterial.getTextureResource()){
                    continue;
            }
            _RenderInstance.setRenderBatch(renderBatch);
            renderBatch.addInstance(_RenderInstance);
            return;
        }
        RenderBatch newRenderBatch = createRenderBatch(_RenderMaterial);
        _RenderInstance.setRenderBatch(newRenderBatch);
        newRenderBatch.addInstance(_RenderInstance);
        return;
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
        
        if (object instanceof RenderInstance){
            renderInstances.remove(object);
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
        for (RenderBatch renderBatch : renderBatches) {
            RenderMaterial renderMaterial = renderBatch.getRenderMaterial();

            TextureResource textureResource = renderMaterial.getTextureResource();
            ShaderProgram shaderProgram = renderMaterial.getShaderProgram();

            shaderProgram.bind();
            if(textureResource!=null){
                textureResource.bind();
            }

            shaderProgram.setMat4("projection", currentCamera2D.getProjection());
            shaderProgram.setMat4("view", currentCamera2D.getView());


            for (RenderInstance renderInstance : renderBatch.getInstances()) {
                if(!renderInstance.isVisible()){continue;}
                Matrix4f model = new Matrix4f().translate(renderInstance.getPosition());
                model.rotate(renderInstance.getRotation(),new Vector3f(0.0f,0.0f,1.0f));
                model.scale(renderInstance.getScale());
                shaderProgram.setMat4("model", model);
                shaderProgram.setVec3("color", renderInstance.getColor());
                shaderProgram.setVec4("uv", renderInstance.getUV());
                glDrawElements(GL_TRIANGLES, quadMeshResource.getIndices().length,GL_UNSIGNED_INT,0);
            }
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