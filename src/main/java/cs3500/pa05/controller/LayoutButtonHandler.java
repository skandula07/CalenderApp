package cs3500.pa05.controller;

import cs3500.pa05.model.BujoReader;
import cs3500.pa05.model.Direction;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.stage.Stage;

/**
 * to handle switching bujo view layout
 */
public class LayoutButtonHandler implements EventHandler {
  private final Stage stage;
  private final BujoController controller;

  /**
   * Creates a LayoutButtonHandler object
   *
   * @param stage to display information
   * @param controller to handle state changes
   */
  public LayoutButtonHandler(Stage stage, BujoController controller) {
    this.stage = stage;
    this.controller = controller;
  }

  /**
   * shows a choice box and sets the theme to the selection
   *
   * @param event the event which occurred
   */
  @Override
  public void handle(Event event) {
    try {
      BujoReader reader = new BujoReader();
      String layout = reader.read(new Scanner(new File("src/main/resources/layout.md")));
      FileWriter fw = new FileWriter("src/main/resources/layout.md");
      if (layout.equalsIgnoreCase(Direction.HORIZONTAL.toString())) {
        fw.write(Direction.VERTICAL.toString().toLowerCase());
      } else {
        fw.write(Direction.HORIZONTAL.toString().toLowerCase());
      }
      fw.close();
      controller.start(stage);
      controller.loadFromExisting(controller.getCurrentFileName());
    } catch (IOException e) {
      throw new RuntimeException("couldn't write file");
    }
  }
}
