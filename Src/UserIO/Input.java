package UserIO;

import static org.lwjgl.glfw.GLFW.*;

import java.nio.DoubleBuffer;

import Rendering.CameraRender2D;
import Rendering.RenderingServer;
import Rendering.Window;
import org.joml.*;
import org.lwjgl.BufferUtils;

public class Input {
    public static Window window;
    
    public static void initInput(Window _window){window=_window;}

    public static boolean isKeyPressed(int scancode){
        if(glfwGetKey(window.getWindow(), scancode)==GLFW_PRESS){
            return true;
        }
        return false;
    }

    public static boolean isKeyReleased(int scancode){
        if(glfwGetKey(window.getWindow(), scancode)==GLFW_RELEASE){
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

    public static boolean isKeyJustReleased(int scancode){
        boolean isPressed = (glfwGetKey(window.getWindow(),scancode)==GLFW_RELEASE);
        if (isPressed && scancode!=prevKey){
            prevKey = scancode;
            return true;
        }
        if(!isPressed && scancode==prevKey){
            prevKey=GLFW_KEY_UNKNOWN;
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

    public static boolean isMouseButtonPressed(int scancode){
        if(glfwGetMouseButton(window.getWindow(), scancode)==GLFW_PRESS){
            return true;
        }
        return false;
    }

    public static boolean isMouseButtonReleased(int scancode){
        if(glfwGetMouseButton(window.getWindow(), scancode)==GLFW_RELEASE){
            return true;
        }
        return false;
    }

    static int prevMouseButton=GLFW_KEY_UNKNOWN;
    public static boolean isMouseButtonJustPressed(int scancode){
        boolean isPressed = (glfwGetMouseButton(window.getWindow(),scancode)==GLFW_PRESS);
        if (isPressed && scancode!=prevMouseButton){
            prevMouseButton = scancode;
            return true;
        }
        if(!isPressed && scancode==prevMouseButton){
            prevMouseButton=GLFW_KEY_UNKNOWN;
        }
        return false;
    }
    public static boolean isMouseButtonJustReleased(int scancode){
        boolean isPressed = (glfwGetMouseButton(window.getWindow(),scancode)==GLFW_RELEASE);
        if (isPressed && scancode!=prevMouseButton){
            prevMouseButton = scancode;
            return true;
        }
        if(!isPressed && scancode==prevMouseButton){
            prevMouseButton=GLFW_KEY_UNKNOWN;
        }
        return false;
    }


   public static Vector2f getMousePixelPosition() {
        double[] xpos = new double[1];
        double[] ypos = new double[1];

        glfwGetCursorPos(window.getWindow(), xpos, ypos);

        return new Vector2f((float)xpos[0],(float)ypos[0]);
    }

   public static Vector2f getMouseNormalizedPosition() {
        Vector2f pixelPosition = getMousePixelPosition();
        Vector2f mouseNormalizedPosition = new Vector2f(
            pixelPosition.x/window.getWidth(),
            pixelPosition.y/window.getHeight()
        );

        return mouseNormalizedPosition;
    }

   public static Vector2f getMouseGlobalPosition() {
        Vector2f mouseNormal = getMouseNormalizedPosition();
        CameraRender2D camera = RenderingServer.getSingleton().getCurrentCameraRender2D();

        Vector2f cameraScale = camera.getZoom();
        Vector2f cameraPosition = new Vector2f(camera.getPosition().x,camera.getPosition().y);

        Vector2f mouseCentered = new Vector2f(mouseNormal.x,-mouseNormal.y).add(-0.5f,0.5f);

        Vector2f mouseCameraRelative = new Vector2f(mouseCentered).mul(cameraScale);
        Vector2f mouseGlobalPosition = new Vector2f(mouseCameraRelative).add(cameraPosition);

        return mouseGlobalPosition;
    }

}
