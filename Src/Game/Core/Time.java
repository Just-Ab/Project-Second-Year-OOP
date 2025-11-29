package Game.Core;

import static org.lwjgl.glfw.GLFW.GLFW_FOCUSED;
import static org.lwjgl.glfw.GLFW.GLFW_TRUE;
import static org.lwjgl.glfw.GLFW.glfwGetTime;
import static org.lwjgl.glfw.GLFW.glfwGetWindowAttrib;

import Rendering.Window;

public class Time {
    private static float gameTime=0,lastGameTime=0,gameDelta=0,lastRealTime=(float)glfwGetTime(),realDelta=0,currentTime=0;
    private static boolean isPaused=false;
    public static void update(Window window){
        currentTime = (float)glfwGetTime();
        realDelta = currentTime-lastRealTime;
        lastRealTime = currentTime;
        if (realDelta>0.05){realDelta=0;}
        
        boolean focused = glfwGetWindowAttrib(window.getWindow(), GLFW_FOCUSED) == GLFW_TRUE;
        isPaused = !focused;
        if (! isPaused){gameTime+=realDelta;}
        gameDelta=gameTime-lastGameTime;
        lastGameTime=gameTime;
    }

    public static float getDeltaTime(){return gameDelta;}
    public static float getRealDeltaTime(){return realDelta;}
    public static float getTime(){return gameTime;}
    public static boolean getIsPaused(){return isPaused;}

}
