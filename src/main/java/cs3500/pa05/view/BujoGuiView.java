package cs3500.pa05.view;

import cs3500.pa05.controller.Controller;
import cs3500.pa05.model.BujoReader;
import cs3500.pa05.model.Direction;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

/**
 * Represents a Bujo GUI view
 */
public class BujoGuiView implements GuiView {
  private FXMLLoader loader;

  /**
   * Instantiates a BujoGuiView
   *
   * @param controller to handle state change
   */
  public BujoGuiView(Controller controller) {
    this.loader = new FXMLLoader();
    this.loader.setController(controller);
    BujoReader reader = new BujoReader();
    try {
      String layout = reader.read(new Scanner(new File("src/main/resources/layout.md")));
      if (layout.contains(Direction.HORIZONTAL.toString().toLowerCase())) {
        this.loader.setLocation(getClass().getClassLoader().getResource("weekView.fxml"));
      } else {
        this.loader.setLocation(getClass().getClassLoader().getResource("weekVertical.fxml"));
      }
    } catch (FileNotFoundException e) {
      throw new RuntimeException("Cannot find file.");
    }
  }

  /**
   * loads the scene
   *
   * @return the scene
   */
  @Override
  public Scene load() {
    try {
      return this.loader.load();
    } catch (IOException e) {
      throw new IllegalStateException("Unable to load layout.");
    }
  }
}