package Rendering;

public class RenderMaterial {
    private ShaderProgram shaderProgram=null;
    private TextureResource textureResource=null;

    RenderMaterial(ShaderProgram _ShaderProgram){
        shaderProgram = _ShaderProgram;
    }

    RenderMaterial(ShaderProgram _ShaderProgram,TextureResource _TextureResource){
        shaderProgram = _ShaderProgram;textureResource = _TextureResource;
    }

    public void setShaderProgram(ShaderProgram _ShaderProgram) { shaderProgram=_ShaderProgram; }
    public ShaderProgram getShaderProgram() { return shaderProgram; }

    public void setTextureResource(TextureResource _TextureResource) { textureResource = _TextureResource; }
    public TextureResource getTextureResource() { return textureResource; }
    
}
