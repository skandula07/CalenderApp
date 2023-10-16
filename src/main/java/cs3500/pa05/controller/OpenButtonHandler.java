package cs3500.pa05.controller;

import java.io.File;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * A class for an OpenButtonHandler
 */
public class OpenButtonHandler implements EventHandler {
  private Stage stage;
  private Controller controller;

  /**
   * Constructor for OpenButtonHandler
   *
   * @param stage to display information
   * @param controller to handle state change
   */
  public OpenButtonHandler(Stage stage, Controller controller) {
    this.stage = stage;
    this.controller = controller;
  }


  /**
   * handles the pressing of an open button
   *
   * @param e the event which occurred
   */
  @Override
  public void handle(Event e) {
    FileChooser fileChooser = new FileChooser();
    fileChooser.setInitialDirectory(new File("src/main/resources/bujoFiles"));
    fileChooser.setTitle("Open Resource File");
    fileChooser.getExtensionFilters().addAll(
        new FileChooser.ExtensionFilter("Bujo Files", "*.bujo"));
    File selectedFile = fileChooser.showOpenDialog(stage);
    controller.loadFromExisting(selectedFile.getName());
    controller.setCurrentFileName(selectedFile.getName());
    stage.show();
  }
}
