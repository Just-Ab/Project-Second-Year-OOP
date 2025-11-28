package Rendering;

import org.joml.*;

public class RenderInstance {
    private Vector3f position = new Vector3f(0.0f);
    private Vector3f scale = new Vector3f(1.0f);
    private Vector3f rotation = new Vector3f(0.0f);
    private boolean visible = true;
    private QuadMeshResource resource=null;
    private RenderMaterial renderMaterial = null;
    private Vector4f uv = new Vector4f(0.0f, 0.0f, 1.0f, 1.0f);
    private Vector3f color = new Vector3f(1.0f);

    public RenderInstance(QuadMeshResource _resource, RenderMaterial _renderMaterial) {
        resource = _resource;
        renderMaterial = _renderMaterial;
    }

    public void setShaderProgram(ShaderProgram _shaderProgram) { renderMaterial.setShaderProgram(_shaderProgram);; }
    public ShaderProgram getShaderProgram() { return renderMaterial.getShaderProgram(); }

    public void setTextureResource(TextureResource _textureResource) { renderMaterial.setTextureResource(_textureResource); }
    public TextureResource getTextureResource() { return renderMaterial.getTextureResource(); }

    public void setPosition(Vector3f _position) { position = _position; }
    public Vector3f getPosition() { return position; }

    public void setScale(Vector3f _scale) { scale = _scale; }
    public Vector3f getScale() { return scale; }

    public void setRotation(Vector3f _degree) { rotation = _degree; }
    public Vector3f getRotation() { return rotation; }

    public void setVisibility(boolean _visiblity) { visible = _visiblity; }
    public boolean isVisible() { return visible; }

    public void setUV(Vector4f _uv) { uv = _uv; }
    public Vector4f getUV() { return uv; }

    public void setColor(Vector3f _color) { color = _color; }
    public Vector3f getColor() { return color; }

    public QuadMeshResource getResource() { return resource; }
}
