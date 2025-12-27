package Game.UI;

import org.joml.Vector2f;
import org.joml.Vector3f;

import Game.Core.Node2D;
import UserIO.Input;

import static org.lwjgl.glfw.GLFW.*;

public class Button2D extends Node2D {

    private boolean hovered = false;
    private boolean pressed = false;

    public Button2D() {}


    @Override
    public void _update(float delta) {
        Vector2f mouseWorldPosition = Input.getMouseGlobalPosition();

        Vector3f centerPosition = getGlobalPosition();
        Vector3f globalScale = getGlobalScale();

        float leftBound = centerPosition.x - globalScale.x * 0.5f;
        float rightBound = centerPosition.x + globalScale.x * 0.5f;
        float topBound = centerPosition.y + globalScale.y * 0.5f;
        float bottomBound = centerPosition.y - globalScale.y * 0.5f;

        boolean isInside =
            mouseWorldPosition.x >= leftBound &&
            mouseWorldPosition.x <= rightBound &&
            mouseWorldPosition.y >= bottomBound &&
            mouseWorldPosition.y <= topBound;

        if (isInside && !hovered) {
            hovered = true;
            _onMouseEntered();
        }

        if (!isInside && hovered) {
            hovered = false;
            _onMouseExited();
        }

        if (hovered &&!pressed&& Input.isMouseButtonJustPressed(GLFW_MOUSE_BUTTON_LEFT)) {
            pressed = true;
            _onPressed();
        }

        if (pressed && Input.isMouseButtonJustReleased(GLFW_MOUSE_BUTTON_LEFT)) {
            pressed = false;
            _onReleased();
        }
    }

    protected void _onMouseEntered() {
    }

    protected void _onMouseExited() {
    }

    protected void _onPressed() {
        System.out.println("Fsdg");
    }

    protected void _onReleased() {
    }
}
