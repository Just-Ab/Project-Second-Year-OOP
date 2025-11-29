package Rendering;

import org.joml.*;

public class RenderInstance {
    private Vector3f position = new Vector3f(0.0f);
    private Vector3f scale = new Vector3f(1.0f);
    private float rotation = 0;
    private boolean visible = true;
    private QuadMeshResource resource=null;
    private RenderBatch renderBatch = null;
    private Vector4f uv = new Vector4f(0.0f, 0.0f, 1.0f, 1.0f);
    private Vector3f color = new Vector3f(1.0f);

    public RenderInstance(QuadMeshResource _resource, RenderBatch _RenderBatch) {
        resource = _resource;
        renderBatch = _RenderBatch;
    }

    public void setShaderProgram(ShaderProgram _shaderProgram) {
        RenderingServer.getSingleton().setInstanceShader(this, _shaderProgram);
    }
    public ShaderProgram getShaderProgram() {
        if(!hasRenderBatch()){return null;}
        return renderBatch.getRenderMaterial().getShaderProgram(); 
    }

    public void setTextureResource(TextureResource _textureResource) {
        RenderingServer.getSingleton().setInstanceTexture(this, _textureResource);
    }

    public TextureResource getTextureResource() {
        if(!hasRenderBatch()){return null;}
        return renderBatch.getRenderMaterial().getTextureResource(); 
    }

    public void setPosition(Vector3f _position) { position.set(_position); }
    public Vector3f getPosition() { return position; }

    public void setScale(Vector3f _scale) { scale.set(_scale); }
    public Vector3f getScale() { return scale; }

    public void setRotation(float _degree) { rotation = _degree; }
    public float getRotation() { return rotation; }

    public void setVisibility(boolean _visiblity) { visible = _visiblity; }
    public boolean isVisible() { return visible; }

    public void setUV(Vector4f _uv) { uv = _uv; }
    public Vector4f getUV() { return uv; }

    public void setColor(Vector3f _color) { color = _color; }
    public Vector3f getColor() { return color; }

    public QuadMeshResource getResource() { return resource; }

    public void setRenderBatch(RenderBatch _RenderBatch) { renderBatch=_RenderBatch; }
    public RenderBatch getRenderBatch() { return renderBatch; }
    public boolean hasRenderBatch() { if(renderBatch!=null){return true;}return false;}


}
