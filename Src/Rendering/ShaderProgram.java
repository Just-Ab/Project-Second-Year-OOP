package Rendering;

import static org.lwjgl.opengl.GL11.GL_FALSE;
import static org.lwjgl.opengl.GL20.*;

import java.nio.file.Files;
import java.nio.file.Path;
import java.io.*;

import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.joml.Vector4f;

public class ShaderProgram {
    String vertexSource,fragmentSource;
    String vertexPath,fragmentPath;
    int vertexId,fragmentId,programId;

    public ShaderProgram(String vertexPath,String fragmentPath){
        this.vertexPath = vertexPath;this.fragmentPath = fragmentPath;
        String vertexSrc=new String();
        String fragmentSrc=new String();
        try {
            vertexSrc = Files.readString(Path.of(vertexPath));
            fragmentSrc = Files.readString(Path.of(fragmentPath));
        } catch (IOException e) {
            throw new RuntimeException("IncorrectPath: " + e.getMessage());
        }

        vertexSource = vertexSrc;fragmentSource=fragmentSrc;
        vertexId = glCreateShader(GL_VERTEX_SHADER);
        fragmentId = glCreateShader(GL_FRAGMENT_SHADER);

        glShaderSource(vertexId, vertexSource);
        glShaderSource(fragmentId, fragmentSrc);

        int success=0;

        glCompileShader(vertexId);
        String vertexlog = glGetShaderInfoLog(vertexId);
        success = glGetShaderi(vertexId,GL_COMPILE_STATUS);
        if (success == GL_FALSE) {
            throw new RuntimeException("Vertex shader compilation failed:\n" + vertexlog);
        }


        glCompileShader(fragmentId);
        String fragmentlog = glGetShaderInfoLog(fragmentId);
        success = glGetShaderi(fragmentId,GL_COMPILE_STATUS);
        if (success == GL_FALSE) {
            throw new RuntimeException("Vertex shader compilation failed:\n" + fragmentlog);
        }

        programId = glCreateProgram();

        glAttachShader(programId, fragmentId);
        glAttachShader(programId, vertexId);

        glLinkProgram(programId);

        String programlog = glGetProgramInfoLog(programId);
        success = glGetProgrami(programId, GL_LINK_STATUS);

        if(success == GL_FALSE){
            throw new RuntimeException("Shader program linking failed:\n" + programlog);
        }
    
        glDeleteShader(vertexId);
        glDeleteShader(fragmentId);
    }


    public void setVec3(String uniformName,Vector3f vec3){
        int uniformCode = glGetUniformLocation(programId,uniformName);
        if (uniformCode>=0){
            glUniform3f(uniformCode,vec3.x, vec3.y, vec3.z);
        }
    }

    public void setVec4(String uniformName,Vector4f vec4){
        int uniformCode = glGetUniformLocation(programId,uniformName);
        if (uniformCode>=0){
            glUniform4f(uniformCode,vec4.x, vec4.y, vec4.z,vec4.w);
        }
    }

    public void setMat4(String uniformName,Matrix4f mat4){
        int uniformCode = glGetUniformLocation(programId,uniformName);
        if (uniformCode>=0){
            float[] matriceArray = new float[16];
            mat4.get(matriceArray);
            glUniformMatrix4fv(uniformCode, false,matriceArray);;
        }
    }

    public void setInt(String uniformName,int value){
        int uniformCode = glGetUniformLocation(programId,uniformName);
        if (uniformCode>=0){
            glUniform1i(uniformCode, value);
        }
    }

    public void setFloat(String uniformName,float value){
        int uniformCode = glGetUniformLocation(programId,uniformName);
        if (uniformCode>=0){
            glUniform1f(uniformCode, value);
        }
    }

    public void bind(){
        glUseProgram(programId);
    }

    public void unbind(){
        glUseProgram(0);
    }
}
