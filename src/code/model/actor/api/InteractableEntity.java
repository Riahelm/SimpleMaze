package code.model.actor.api;

import code.model.actor.impl.EntityType;

public interface InteractableEntity extends Entity {

    void onInteract(EntityType type);
}
