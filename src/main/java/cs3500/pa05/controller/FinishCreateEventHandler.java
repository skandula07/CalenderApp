package cs3500.pa05.controller;

import cs3500.pa05.model.Day;
import cs3500.pa05.model.Event;
import cs3500.pa05.model.Week;
import java.time.format.DateTimeParseException;
import java.util.Objects;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

/**
 * A class for a FinishCreateEventHandler
 */
public class FinishCreateEventHandler implements EventHandler {

  private ComboBox<String> dayOptions;
  private TextField nameInput;
  private TextField startTimeInput;
  private TextField durationInput;
  private TextArea descriptionInput;
  private Week week;
  private Stage stage;
  private Event event;
  private Day dayToAddTo;
  private Button button;
  private Controller controller;

  /**
   * Creates a FinishCreateEventHandler
   *
   * @param dayops the combo box to get user input from
   *
   * @param dur the text field with a specified duration text
   *
   * @param start the text field with a specified start time text
   *
   * @param name the text field with a specified name text
   *
   * @param des the text area with a specified description text
   *
   * @param stage the stage to display information
   *
   * @param controller the controller to handle state change
   */
  public FinishCreateEventHandler(ComboBox<String> dayops, TextField dur, TextField start,
                                  TextField name, TextArea des, Stage stage,
                                  Controller controller) {
    this.dayOptions = dayops;
    this.startTimeInput = start;
    this.durationInput = dur;
    this.nameInput = name;
    this.descriptionInput = des;
    this.week = controller.getWeek();
    this.stage = stage;
    this.controller = controller;
    button = new Button();
  }


  /**
   * handle finishing creating an event
   *
   * @param ev the event which occurred
   */
  @Override
  public void handle(javafx.event.Event ev) {
    if (Objects.isNull(dayOptions.getValue())) {
      Alert alert = new Alert(Alert.AlertType.WARNING,
          "Please enter a day for your event!");
      alert.show();
    }
    dayToAddTo = week.getDay(dayOptions.getValue());
    String name = nameInput.getText();
    try {
      event = new Event(name, this.descriptionInput.getText(),
          startTimeInput.getText(),
          Integer.parseInt(durationInput.getText()));
    } catch (DateTimeParseException e) {
      Alert alert = new Alert(Alert.AlertType.WARNING,
          "Please enter a time using the 24 hour clock format!");
      alert.show();
    } catch (IllegalStateException s) {
      Alert alert = new Alert(Alert.AlertType.WARNING, "Please enter an event name!");
      alert.show();
    } catch (IllegalArgumentException a) {
      Alert alert = new Alert(Alert.AlertType.WARNING,
          "Please enter a positive integer for the duration!");
      alert.show();
    }
    controller.removeButton(button, this.dayToAddTo.getName());
    this.button.setText(event.getName());
    button.setBackground(Background.fill(Paint.valueOf("#fac9b1")));
    controller.addItemView(dayToAddTo.getName(), this.button);
    button.setOnAction(new ShowEventMiniviewHandler(event, dayToAddTo.getName(),
        controller, week, button));
    stage.close();
    try {
      week.addEvent(this.event, dayToAddTo);
    } catch (IllegalArgumentException e) {
      Alert alert = new Alert(Alert.AlertType.WARNING,
          "You've reached your daily event max, consider redistributing your work!");
      alert.show();
    }
    controller.setTotalEventsView(this.week.getTotalEvents());
    controller.setWeek(this.week);
    clearSelections();
  }

  /**
   * clears all the contents of the create Event editor window
   */
  private void clearSelections() {
    dayOptions.getSelectionModel().clearSelection();
    startTimeInput.clear();
    durationInput.clear();
    nameInput.clear();
    descriptionInput.clear();
  }
}
