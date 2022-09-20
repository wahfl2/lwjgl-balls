package engine.render;

import java.util.*;

public class Scene {

    private Map<String, Entity> entityMap;

    public Scene() {
        entityMap = new HashMap<>();
    }

    public void addEntity(Entity entity) {
        entityMap.put(entity.getId(), entity);
    }

    public void cleanup() {
        for (Entity e : entityMap.values()) {
            e.getMesh().cleanup();
        }
    }

    public Map<String, Entity> getEntityMap() {
        return entityMap;
    }
}