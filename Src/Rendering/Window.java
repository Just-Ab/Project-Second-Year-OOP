package Rendering;

import static org.lwjgl.glfw.GLFW.*;

public class Window {
    


    private int width,height;
    private String name;
    private long windowId;

    public Window(String _name,int _width,int _height){
        name = _name;width = _width;height=_height;
        boolean result = glfwInit();
        if (result==false){
            throw new IllegalStateException("GLFW failed!\n");
        }

        glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR,3);
        glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR,3);
        glfwWindowHint(GLFW_OPENGL_PROFILE,GLFW_OPENGL_CORE_PROFILE);

        windowId = glfwCreateWindow(width, height, name, 0, 0);
        if (windowId==0){
            throw new IllegalStateException("Window failed\n");
        }
    }

    public long getWindow(){
        return windowId;
    }

    public boolean windowShouldClose(){
        return glfwWindowShouldClose(windowId);
    }

    public void closeWindow(){
        glfwSetWindowShouldClose(windowId, true);
    }
}
