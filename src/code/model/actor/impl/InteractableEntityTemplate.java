package code.model.actor.impl;

import code.controller.GameChatController;
import code.model.actor.api.InteractableEntity;

public abstract class InteractableEntityTemplate implements InteractableEntity {

    @Override
    public void onInteract(EntityType type) {
        GameChatController.getInstance().sendMessage("Hey! Don't touch me!!");
    }
}
