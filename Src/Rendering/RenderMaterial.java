package Rendering;

public class RenderMaterial {
    private ShaderProgram shaderProgram=null;
    private Texture textureResource=null;

    RenderMaterial(ShaderProgram _ShaderProgram){
        shaderProgram = _ShaderProgram;
    }

    RenderMaterial(ShaderProgram _ShaderProgram,Texture _TextureResource){
        shaderProgram = _ShaderProgram;textureResource = _TextureResource;
    }

    public void setShaderProgram(ShaderProgram _ShaderProgram) { shaderProgram=_ShaderProgram; }
    public ShaderProgram getShaderProgram() { return shaderProgram; }

    public void setTextureResource(Texture _TextureResource) { textureResource = _TextureResource; }
    public Texture getTextureResource() { return textureResource; }
    
}
