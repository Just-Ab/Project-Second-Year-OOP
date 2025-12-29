package CodeNameNeutronStar.Interaction;

import static org.lwjgl.glfw.GLFW.GLFW_KEY_B;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_C;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_E;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_ESCAPE;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_R;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_SPACE;
import static org.lwjgl.glfw.GLFW.GLFW_MOUSE_BUTTON_LEFT;

public class InteractionRules {

    public static final int BUILD_MODE_KEY = GLFW_KEY_B;
    public static final int UNITS_MODE_KEY = GLFW_KEY_E;
    public static final int ROAM_MODE_KEY = GLFW_KEY_R;

    public static final int CANCEL_KEY = GLFW_KEY_C;
    public static final int CONFIRM_KEY = GLFW_KEY_SPACE;

    public static final int FIRE_KEY = GLFW_MOUSE_BUTTON_LEFT;


    public static final int MAIN_MENU_KEY = GLFW_KEY_ESCAPE;


    private InteractionRules() {}

}
