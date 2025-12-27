package UserIO;

import static org.lwjgl.glfw.GLFW.*;

import Rendering.CameraRender2D;
import Rendering.RenderingServer;
import Rendering.Window;
import org.joml.Vector2f;

public class Input {

    public static Window window;

    public static void initInput(Window _window){
        window = _window;
    }

    private static final boolean[] prevKeyState = new boolean[GLFW_KEY_LAST + 1];

    public static boolean isKeyPressed(int scancode){
        return glfwGetKey(window.getWindow(), scancode) == GLFW_PRESS;
    }

    public static boolean isKeyReleased(int scancode){
        return glfwGetKey(window.getWindow(), scancode) == GLFW_RELEASE;
    }

    public static boolean isKeyJustPressed(int scancode){
        boolean isPressed = isKeyPressed(scancode);
        boolean wasPressed = prevKeyState[scancode];

        prevKeyState[scancode] = isPressed;

        return isPressed && !wasPressed;
    }

    public static boolean isKeyJustReleased(int scancode){
        boolean isPressed = isKeyPressed(scancode);
        boolean wasPressed = prevKeyState[scancode];

        prevKeyState[scancode] = isPressed;

        return !isPressed && wasPressed;
    }

    public static int getAxis(int negativeKey, int positiveKey){
        if (isKeyPressed(negativeKey)) return -1;
        if (isKeyPressed(positiveKey)) return 1;
        return 0;
    }

    private static final boolean[] prevMouseState = new boolean[8];

    public static boolean isMouseButtonPressed(int button){
        return glfwGetMouseButton(window.getWindow(), button) == GLFW_PRESS;
    }

    public static boolean isMouseButtonReleased(int button){
        return glfwGetMouseButton(window.getWindow(), button) == GLFW_RELEASE;
    }

    public static boolean isMouseButtonJustPressed(int button){
        boolean isPressed = isMouseButtonPressed(button);
        boolean wasPressed = prevMouseState[button];

        prevMouseState[button] = isPressed;

        return isPressed && !wasPressed;
    }

    public static boolean isMouseButtonJustReleased(int button){
        boolean isPressed = isMouseButtonPressed(button);
        boolean wasPressed = prevMouseState[button];

        prevMouseState[button] = isPressed;

        return !isPressed && wasPressed;
    }

    public static Vector2f getMousePixelPosition() {
        double[] xpos = new double[1];
        double[] ypos = new double[1];

        glfwGetCursorPos(window.getWindow(), xpos, ypos);

        return new Vector2f((float)xpos[0], (float)ypos[0]);
    }

    public static Vector2f getMouseNormalizedPosition() {
        Vector2f pixelPosition = getMousePixelPosition();
        return new Vector2f(
                pixelPosition.x / window.getWidth(),
                pixelPosition.y / window.getHeight()
        );
    }

    public static Vector2f getMouseGlobalPosition() {
        Vector2f mouseNormal = getMouseNormalizedPosition();
        CameraRender2D camera = RenderingServer.getSingleton().getCurrentCameraRender2D();

        Vector2f cameraScale = camera.getZoom();
        Vector2f cameraPosition = new Vector2f(camera.getPosition());

        Vector2f mouseCentered = new Vector2f(mouseNormal.x, -mouseNormal.y).add(-0.5f, 0.5f);

        Vector2f mouseCameraRelative = new Vector2f(mouseCentered).mul(cameraScale);
        return new Vector2f(mouseCameraRelative).add(cameraPosition);
    }
}
