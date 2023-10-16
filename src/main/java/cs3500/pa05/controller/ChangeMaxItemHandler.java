package cs3500.pa05.controller;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * handles any changes to the maximum number of tasks or events
 */
public class ChangeMaxItemHandler implements EventHandler {

  private TextField tf;
  private Label item;
  private Controller controller;
  private String itemText;
  private Stage stage;

  /**
   * Creates a ChangeMaxItemHandler object
   *
   * @param name the label with the max count
   * @param c  the main Controller
   */
  public ChangeMaxItemHandler(Label name, Controller c) {
    this.item = name;
    this.controller = c;
    this.itemText = this.item.getText();
    this.tf = new TextField();
    stage = new Stage();
  }

  /**
   * displays a text field to enter the new count, sets the count to the text
   *
   * @param e the event which occurred
   */
  @Override
  public void handle(Event e) {

    Label prompt = new Label();
    Scene s = new Scene(new VBox(prompt, tf));
    stage = new Stage();

    stage.setScene(s);
    if (stage.isShowing()) {
      stage.hide();
    } else {
      stage.show();
      if (itemText.contains("Event")) {
        prompt.setText("Enter a max # of Events");
        handleEvent();
      } else {
        prompt.setText("Enter a max # of Tasks");
        handleTask();
      }
    }
  }

  /**
   * handles data validation for max events
   */
  private void handleEvent() {
    tf.setOnAction(event -> {
      stage.close();
      try {
        controller.getWeek().setMaxDailyEvents(Integer.parseInt(tf.getText()));
        item.setText(itemText.substring(0, itemText.indexOf(":") + 2) + tf.getText());
        controller.setWeek(controller.getWeek());
      } catch (IllegalArgumentException exception) {
        Alert alert = new Alert(Alert.AlertType.WARNING,
            "Max daily events must be a non-negative integer.");
        alert.show();
      }
    });
  }

  /**
   * handles data validation for max tasks
   */
  private void handleTask() {
    tf.setOnAction(event -> {
      stage.close();
      try {
        controller.getWeek().setMaxDailyTasks(Integer.parseInt(tf.getText()));
        item.setText(itemText.substring(0, itemText.indexOf(":") + 2) + tf.getText());
        controller.setWeek(controller.getWeek());
      } catch (IllegalArgumentException exception) {
        Alert alert = new Alert(Alert.AlertType.WARNING,
            "Max daily events must be a positive integer.");
        alert.show();
      }
    });
  }
}
