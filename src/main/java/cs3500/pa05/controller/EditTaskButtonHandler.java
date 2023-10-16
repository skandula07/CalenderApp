package cs3500.pa05.controller;

import cs3500.pa05.model.Task;
import cs3500.pa05.model.Week;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * handler for editing tasks
 */
public class EditTaskButtonHandler implements EventHandler {

  private Task task;
  private String dayName;
  private Controller controller;
  private Stage stage;
  private Week week;
  private Button taskViewButton;

  /**
   * constructor for EditTaskButtonHandler
   *
   * @param task to be edited
   * @param dayName the day of the task
   * @param controller to handle state changes
   * @param stage to display information
   * @param week to add event to
   * @param button to view the event
   */
  public EditTaskButtonHandler(Task task, String dayName,
                               Controller controller, Stage stage, Week week, Button button) {
    this.task = task;
    this.dayName = dayName;
    this.controller = controller;
    this.stage = stage;
    this.week = week;
    this.taskViewButton = button;
  }

  /**
   * handle task editing
   *
   * @param event the event which occurred
   */
  @Override
  public void handle(Event event) {
    stage.close();
    CreateTaskButtonHandler updater =
        new CreateTaskButtonHandler(task, controller, dayName,
            new TextField(task.getName()), new TextArea(task.getDescription()));
    updater.handle(event);
    controller.removeButton(taskViewButton, this.dayName);
    week.getDay(dayName).getTasks().remove(task);
  }
}
