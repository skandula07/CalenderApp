package cs3500.pa05.controller;

import cs3500.pa05.model.Week;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * a class for a NameClickHandler
 */
public class NameClickHandler implements EventHandler {
  private Label name;
  private Stage stage;
  private Controller controller;

  /**
   * Creates a NameClickHandler object
   *
   * @param name the label with the name
   * @param c  the Controller to handle state change
   */
  public NameClickHandler(Label name, Controller c) {
    this.name = name;
    this.stage = new Stage();
    this.controller = c;
  }

  /**
   * displays a text field to enter the new name, sets the name to the text
   *
   * @param e the event which occurred
   */
  @Override
  public void handle(Event e) {
    if (stage.isShowing()) {
      stage.hide();
    } else {
      TextField tf = new TextField();
      Scene s = new Scene(new VBox(new Label("What would you like name your week?"), tf));
      stage.setScene(s);
      stage.show();
      tf.setOnAction(event -> {
        stage.close();
        name.setText(tf.getText());
        Week w = controller.getWeek();
        w.setName(tf.getText());
        controller.setWeek(w);
      });
    }
  }
}
