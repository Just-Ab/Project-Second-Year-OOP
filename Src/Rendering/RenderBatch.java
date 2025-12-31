package Rendering;

import static org.lwjgl.opengl.GL11.GL_FLOAT;
import static org.lwjgl.opengl.GL15.GL_DYNAMIC_DRAW;

import java.util.ArrayList;
import java.util.List;

public class RenderBatch {
    
    private RenderMaterial renderMaterial=null;
    private List<RenderInstance> renderInstances = new ArrayList<>();
    private int visibleInstances = 0;
    private QuadMeshResource resource = new QuadMeshResource();
    private VBO instanceVBO = new VBO();

    RenderBatch(RenderMaterial _renderMaterial){
        renderMaterial = _renderMaterial;
    }

    public void setRenderMaterial(RenderMaterial _renderMaterial) { renderMaterial=_renderMaterial; }
    public RenderMaterial getRenderMaterial() { return renderMaterial; }

    public void addInstance(RenderInstance _RenderInstance) {
        renderInstances.addLast(_RenderInstance);
    }
    public void removeInstance(RenderInstance _RenderInstance) {
        renderInstances.remove(_RenderInstance);
    }

    public List<RenderInstance> getInstances() { return renderInstances; }

    public void updateInstanceVBO(){
        float[] instanceData = new float[renderInstances.size() * 14];
        int index = 0;
        visibleInstances = 0;
        for (RenderInstance renderInstance : renderInstances) {
            if(!renderInstance.isVisible()) continue;
            instanceData[index++] = renderInstance.getPosition().x;
            instanceData[index++] = renderInstance.getPosition().y;
            instanceData[index++] = renderInstance.getPosition().z;
            instanceData[index++] = renderInstance.getScale().x;
            instanceData[index++] = renderInstance.getScale().y;
            instanceData[index++] = renderInstance.getScale().z;
            instanceData[index++] = renderInstance.getRotation();
            instanceData[index++] = renderInstance.getUV().x;
            instanceData[index++] = renderInstance.getUV().y;
            instanceData[index++] = renderInstance.getUV().z;
            instanceData[index++] = renderInstance.getUV().w;
            instanceData[index++] = renderInstance.getColor().x;
            instanceData[index++] = renderInstance.getColor().y;
            instanceData[index++] = renderInstance.getColor().z;
            visibleInstances++;
        }
        instanceVBO.setData(instanceData);

        
        instanceVBO.setData(instanceData);
        instanceVBO.loadGpu(GL_DYNAMIC_DRAW);
    }
    
    public void registerVBOtoVAO(){
        resource.bind();
        VAO _vao = resource.getVAO();
        instanceVBO.bind();

        int stride = 14 * Float.BYTES;

        _vao.linkAttribute(instanceVBO, 1, 3, GL_FLOAT, false, stride, 0, 1);
        _vao.linkAttribute(instanceVBO, 2, 3, GL_FLOAT, false, stride, 3 * Float.BYTES, 1);
        _vao.linkAttribute(instanceVBO, 3, 1, GL_FLOAT, false, stride, 6 * Float.BYTES, 1);
        _vao.linkAttribute(instanceVBO, 4, 4, GL_FLOAT, false, stride, 7 * Float.BYTES, 1);
        _vao.linkAttribute(instanceVBO, 5, 3, GL_FLOAT, false, stride, 11 * Float.BYTES, 1);

        _vao.unbind();
        instanceVBO.unbind();

    }
    
    public VBO getInstaceVBO(){return instanceVBO;}
    public int getRenderableInstancesCount(){ return visibleInstances;}
    public QuadMeshResource getResource(){return resource;}

}
