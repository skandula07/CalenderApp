package cs3500.pa05.view;

import cs3500.pa05.controller.SplashScreenController;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

/**
 * represents a splash screen GUI view
 */
public class SplashScreenGuiView implements GuiView {

  private FXMLLoader loader;


  /**
   * constructor for a SplashScreenGuiView
   *
   * @param controller to handle state change
   */
  public SplashScreenGuiView(SplashScreenController controller) {
    this.loader = new FXMLLoader();
    this.loader.setController(controller);
    this.loader.setLocation(getClass().getClassLoader().getResource("splash.fxml"));

  }

  /**
   * loads a scene from a GUI layout
   *
   * @return the loaded scene
   * @throws IllegalStateException if the scene can't be loaded
   */
  @Override
  public Scene load() throws IllegalStateException {
    try {
      return this.loader.load();
    } catch (IOException e) {
      throw new IllegalStateException("Unable to load layout.");
    }
  }
}
