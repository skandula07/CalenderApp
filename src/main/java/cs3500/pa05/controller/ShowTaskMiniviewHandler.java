package cs3500.pa05.controller;

import cs3500.pa05.model.Task;
import cs3500.pa05.model.Week;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

/**
 * handler for showing task miniview
 */
public class ShowTaskMiniviewHandler implements EventHandler {

  private Button editButton;
  private Stage miniViewer;
  private Task task;
  private String dayName;
  private Controller controller;
  private Week week;
  Button taskViewButton;

  /**
   * constructor for ShowTaskMiniViewHandler
   *
   * @param task to be shown
   * @param day of the task
   * @param controller to handle state change
   * @param week of the task
   * @param tvb to view the task
   */
  public ShowTaskMiniviewHandler(Task task, String day, Controller controller,
                                 Week week, Button tvb) {
    this.task = task;
    this.dayName = day;
    this.miniViewer = new Stage();
    this.editButton = new Button("Edit");
    this.controller = controller;
    this.week = week;
    this.taskViewButton = tvb;
  }

  /**
   * to handle displaying the task miniview
   *
   * @param event the event which occurred
   */
  @Override
  public void handle(Event event) {
    createMiniviewer();
    miniViewer.show();
    editButton.setOnAction(new EditTaskButtonHandler(task, dayName, controller,
        miniViewer, week, taskViewButton));

  }

  /**
   * creating the miniviewer
   */
  private void createMiniviewer() {
    VBox backDrop = new VBox();
    backDrop.setPadding(new Insets(10));
    backDrop.setBackground(Background.fill(Paint.valueOf("#d4f6ff")));
    backDrop.getChildren().addAll(new Label("Name: " + task.getName()),
        new Label("Day: " + dayName),
        new Label("Completion Status: " + task.getComplete()),
        new Label("Description: " + task.getDescription()),
        editButton);
    editButton.setBackground(Background.fill(Paint.valueOf("#87b9ff")));
    miniViewer.setScene(new Scene(backDrop));
  }
}