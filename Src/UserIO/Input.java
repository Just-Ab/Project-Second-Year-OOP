package UserIO;

import static org.lwjgl.glfw.GLFW.*;

import Rendering.Window;
import org.joml.*;

public class Input {
    public static Window window;
    
    public static void initInput(Window _window){window=_window;}

    public static boolean isKeyPressed(int scancode){
        if(glfwGetKey(window.getWindow(), scancode)==GLFW_PRESS){
            return true;
        }
        return false;
    }

    static int prevKey=GLFW_KEY_UNKNOWN;
    public static boolean isKeyJustPressed(int scancode){
        boolean isPressed = (glfwGetKey(window.getWindow(),scancode)==GLFW_PRESS);
        if (isPressed && scancode!=prevKey){
            prevKey = scancode;
            return true;
        }
        if(!isPressed && scancode==prevKey){
            prevKey=GLFW_KEY_UNKNOWN;
        }
        return false;
    }

       public static boolean isKeyReleased(int scancode){
        if(glfwGetKey(window.getWindow(), scancode)==GLFW_RELEASE){
            return true;
        }
        return false;
    } 

    public static int getAxis(int scancodeA,int scancodeB){
        if(glfwGetKey(window.getWindow(), scancodeA)==GLFW_PRESS){
            return -1;
        }
        if(glfwGetKey(window.getWindow(), scancodeB)==GLFW_PRESS){
            return 1;
        }
        return 0;
    }

    static float prevMousePoX=0.0f,prevMousePoY=0.0f;
    public static Vector2f InputgetMouseOffset(float mouseX,float mouseY,float sensitivity){
        float offsetX = mouseX - prevMousePoX;
        float offsetY = prevMousePoY - mouseY;

        prevMousePoX = mouseX;
        prevMousePoY = mouseY;

        return new Vector2f(offsetX * sensitivity,offsetY * sensitivity);
    }
}
