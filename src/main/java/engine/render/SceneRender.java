package engine.render;

import engine.Window;
import org.joml.Vector2f;

import java.util.*;

import static org.lwjgl.opengl.GL30.*;

public class SceneRender {

    private ShaderProgram shaderProgram;

    private UniformsMap uniformsMap;


    public SceneRender() {
        List<ShaderProgram.ShaderModuleData> shaderModuleDataList = new ArrayList<>();
        shaderModuleDataList.add(new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER));
        shaderModuleDataList.add(new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER));
        shaderProgram = new ShaderProgram(shaderModuleDataList);
        createUniforms();
    }

    private void createUniforms() {
        uniformsMap = new UniformsMap(shaderProgram.getProgramId());

        uniformsMap.createUniform("viewportSize");
        uniformsMap.createUniform("transform");
    }

    public void cleanup() {
        shaderProgram.cleanup();
    }

    public void render(Window window, Scene scene) {
        shaderProgram.bind();

        if (window.isResized()) {
            windowResize(window.getWidth(), window.getHeight());
        }

        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

        scene.getEntityMap().values().forEach(entity -> {
                    Mesh mesh = entity.getMesh();
                    glBindVertexArray(mesh.getVaoId());

                    uniformsMap.setUniform("transform", entity.getPosition());
                    glDrawElements(GL_TRIANGLES, mesh.getNumVertices(), GL_UNSIGNED_INT, 0);
                }
        );

        glBindVertexArray(0);

        shaderProgram.unbind();
    }

    public void windowResize(int width, int height) {
        uniformsMap.setUniform("viewportSize", new Vector2f(width, height));
    }
}