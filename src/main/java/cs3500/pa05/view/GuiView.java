package cs3500.pa05.view;

import javafx.scene.Scene;

/**
 * Represents a GUI view
 */
public interface GuiView {

  /**
   * Loads a scene from a GUI layout.
   *
   * @return the layout
   * @throws IllegalStateException if the scene can't be loaded
   */
  Scene load() throws IllegalStateException;

}
