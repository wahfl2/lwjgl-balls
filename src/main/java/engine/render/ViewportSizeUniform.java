package engine.render;

import engine.util.Vec2;
import org.joml.Vector2f;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;

import java.nio.FloatBuffer;

import static org.lwjgl.opengl.GL20.*;

public class ViewportSizeUniform {

    private int programId;
    private int uniform;

    public ViewportSizeUniform(int programId) {
        this.programId = programId;

        int uniformLocation = glGetUniformLocation(programId, "viewportSize");
        if (uniformLocation < 0) {
            throw new RuntimeException("Could not find uniform viewportSize in shader program [" +
                    programId + "]");
        }
        uniform = uniformLocation;
    }

    public void setUniform(int width, int height) {
        try (MemoryStack stack = MemoryStack.stackPush()) {
            Integer location = uniform;
            if (location == null) {
                throw new RuntimeException("Could not find uniform viewportSize");
            }
            glUniform2i(location.intValue(), width, height);
        }
    }
}
