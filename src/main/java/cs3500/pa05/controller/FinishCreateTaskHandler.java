package cs3500.pa05.controller;

import cs3500.pa05.model.Completion;
import cs3500.pa05.model.Day;
import cs3500.pa05.model.Task;
import cs3500.pa05.model.Week;
import java.util.Objects;
import javafx.event.Event;
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
 * A class for a FinishCreateTaskHandler
 */
public class FinishCreateTaskHandler implements EventHandler {

  private ComboBox<String> dayOptions;
  private TextField nameInput;
  private ComboBox<String> compStat;
  private TextArea descriptionInput;
  private Week week;
  private Stage stage;
  private Task task;
  private Button button;
  private Button editButton;
  private Stage miniViewer;
  private Controller controller;


  /**
   * Creates a FinishCreateTaskHandler object
   *
   * @param dayops     the day option
   * @param comps      the combo box
   * @param name       the text field with a specified name text
   * @param des        the text field with a specified description text
   * @param controller the controller
   * @param s          the stage
   */
  public FinishCreateTaskHandler(ComboBox<String> dayops, ComboBox<String> comps, TextField name,
                                 TextArea des, Controller controller, Stage s) {
    this.dayOptions = dayops;
    this.compStat = comps;
    this.nameInput = name;
    this.descriptionInput = des;
    this.week = controller.getWeek();
    this.stage = s;
    this.controller = controller;
    button = new Button();
    editButton = new Button("Edit");
    miniViewer = new Stage();
  }


  /**
   * handles finishing creating a task
   *
   * @param event the event which occurred
   */
  @Override
  public void handle(Event event) {
    if (Objects.isNull(dayOptions.getValue())) {
      Alert alert = new Alert(Alert.AlertType.WARNING,
          "Please enter a day for your task!");
      alert.show();
    }
    Day dayToAddTo = week.getDay(dayOptions.getValue());
    controller.removeButton(button, dayToAddTo.getName());
    try {
      task = new Task(nameInput.getText(), descriptionInput.getText(),
          Completion.COMPLETE.toString().equalsIgnoreCase(compStat.getValue()));
    } catch (IllegalStateException s) {
      Alert alert = new Alert(Alert.AlertType.WARNING, "Please enter a task name!");
      alert.show();
    }
    button.setText(this.task.getName());
    button.setBackground(Background.fill(Paint.valueOf("#b1dffa")));
    button.setOnAction(new ShowTaskMiniviewHandler(task, dayToAddTo.getName(),
        controller, week, button));
    this.stage.hide();
    try {
      week.addTask(this.task, week.getDay(dayToAddTo.getName()));
    } catch (IllegalStateException e) {
      Alert alert = new Alert(Alert.AlertType.WARNING,
          "You've reached your daily task max, consider redistributing your work!");
      alert.show();
    }
    controller.addItemView(dayToAddTo.getName(), button);
    controller.setTotalTasksView(this.week.getTotalTasks());
    clearSelections();
    controller.setPercentTasksCompleted(this.week.getDays(), this.week.getTotalTasks());
    controller.setWeek(this.week);
    controller.setProgress(dayToAddTo);
  }

  /**
   * clears all the contents of the create Task editor window
   */
  private void clearSelections() {
    dayOptions.getSelectionModel().clearSelection();
    compStat.getSelectionModel().clearSelection();
    nameInput.clear();
    descriptionInput.clear();
  }
}
