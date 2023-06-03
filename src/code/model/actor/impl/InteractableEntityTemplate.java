package code.model.actor.impl;

import code.viewModel.GameViewModel;
import code.model.actor.api.InteractableEntity;

public abstract class InteractableEntityTemplate extends EntityTemplate implements InteractableEntity {

    @Override
    public void onInteract(EntityType type) {
        gVMCC.sendMessage("Hey! Don't touch me!!");
    }
}
