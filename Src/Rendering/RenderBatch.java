package Rendering;

import java.util.ArrayList;
import java.util.List;

public class RenderBatch {
    
    private RenderMaterial renderMaterial=null;
    private List<RenderInstance> renderInstances = new ArrayList<>();


    RenderBatch(RenderMaterial _renderMaterial){
        renderMaterial = _renderMaterial;
    }

    public void setRenderMaterial(RenderMaterial _renderMaterial) { renderMaterial=_renderMaterial; }
    public RenderMaterial getRenderMaterial() { return renderMaterial; }

    public void addInstance(RenderInstance _RenderInstance) { renderInstances.addLast(_RenderInstance);}
    public void removeInstance(RenderInstance _RenderInstance) { renderInstances.remove(_RenderInstance);}

    public List<RenderInstance> getInstances() { return renderInstances; }


}
