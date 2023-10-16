package cs3500.pa05.controller;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Popup;
import javafx.stage.Stage;

/**
 * A class for a FileInputHandler
 */
public class FileInputHandler implements EventHandler {

  private TextField tf;
  private String toWrite;
  private Stage stage;
  private Controller controller;

  /**
   * Creates a FileInputHandler object
   *
   * @param tf the text field to get user input from
   * @param toWrite the string to write
   * @param stage the stage to display information
   * @param controller to handle state change
   */
  public FileInputHandler(TextField tf, String toWrite, Stage stage, Controller controller) {
    this.tf = tf;
    this.toWrite = toWrite;
    this.stage = stage;
    this.controller = controller;
  }

  /**
   * Invoked when a specific event of the type for which this handler is
   * registered happens.
   *
   * @param event the event which occurred
   */
  @Override
  public void handle(Event event) {
    File file = new File("src/main/resources/bujoFiles/" + tf.getText() + ".bujo");
    if (file.length() != 0) {
      Popup pop = new Popup();
      Label problem = new Label("that file name is already taken, try again!");
      pop.getContent().add(problem);
      pop.show(stage);
      tf.clear();
    } else {
      try {
        controller.setCurrentFileName(tf.getText() + ".bujo");
        FileWriter fw = new FileWriter(file);
        fw.write(this.toWrite);
        fw.close();
        stage.close();
      } catch (IOException exc) {
        throw new RuntimeException("couldn't write to file");
      }
    }
  }
}
