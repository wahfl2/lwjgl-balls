package engine.input;

import engine.util.Vec2;

import static org.lwjgl.glfw.GLFW.*;

public class MouseInput {

    private Vec2 currentPos;
    private Vec2 previousPos;
    private boolean inWindow;
    private boolean leftButtonPressed;
    private boolean prevLeft;
    private boolean leftJustPressed;
    private boolean rightButtonPressed;
    private boolean prevRight;
    private boolean rightJustPressed;

    public MouseInput(long windowHandle) {
        previousPos = new Vec2(-1, -1);
        currentPos = Vec2.ZERO;
        leftButtonPressed = false;
        rightButtonPressed = false;
        inWindow = false;

        glfwSetCursorPosCallback(windowHandle, (handle, xPos, yPos) -> {
            currentPos.x = (float) xPos - 960f;
            currentPos.y = -((float) yPos - 560.5f);
        });

        glfwSetCursorEnterCallback(windowHandle, (handle, entered) -> inWindow = entered);

        glfwSetMouseButtonCallback(windowHandle, (handle, button, action, mode) -> {
            leftButtonPressed = button == GLFW_MOUSE_BUTTON_1 && action == GLFW_PRESS;
            rightButtonPressed = button == GLFW_MOUSE_BUTTON_2 && action == GLFW_PRESS;
        });
    }

    public void input() {
        leftJustPressed = leftButtonPressed && !prevLeft;
        rightJustPressed = rightButtonPressed && !prevRight;

        prevLeft = leftButtonPressed;
        prevRight = rightButtonPressed;

        previousPos.x = currentPos.x;
        previousPos.y = currentPos.y;
    }

    public boolean isLeftButtonPressed() {
        return leftButtonPressed;
    }

    public boolean isRightButtonPressed() {
        return rightButtonPressed;
    }

    public boolean isLeftJustPressed() {
        return leftJustPressed;
    }

    public boolean isRightJustPressed() {
        return rightJustPressed;
    }

    public Vec2 getCurrentPos() {
        return currentPos;
    }
}
