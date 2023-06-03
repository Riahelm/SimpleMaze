package code.viewModel.observers;

import javax.swing.*;

public interface GameAreaObserver {
    <X> void useUpdatedState(X[][] updatedState);
}
