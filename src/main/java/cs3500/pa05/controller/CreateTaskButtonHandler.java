package cs3500.pa05.controller;

import cs3500.pa05.model.Completion;
import cs3500.pa05.model.DayName;
import cs3500.pa05.model.Task;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * A class for a CreateTaskButtonHandler
 */
public class CreateTaskButtonHandler implements EventHandler {

  private ComboBox<String> dayOptions;
  private ComboBox<String> completionStatus;
  private TextField nameInput;
  private TextArea descriptionInput;
  private VBox display;
  private Button createButton;
  private Scene scene;
  private Controller controller;
  private Task task;
  private Stage stage;

  /**
   * Creates a CreateTaskButtonHandler
   *
   * @param controller the controller to handle state changes
   */
  public CreateTaskButtonHandler(Controller controller) {
    dayOptions = new ComboBox<>();
    for (DayName dn : DayName.values()) {
      dayOptions.getItems().add(dn.toString());
    }
    completionStatus = new ComboBox<>();
    completionStatus.getItems().addAll(Completion.INCOMPLETE.toString(),
        Completion.COMPLETE.toString());
    this.nameInput = new TextField();
    this.descriptionInput = new TextArea();
    this.controller = controller;
    this.stage = new Stage();
  }

  /**
   * Creates a CreateTaskButtonHandler for editing preexisting tasks
   *
   * @param task the Task to be edited
   *
   * @param controller the controller to handle state changes
   *
   * @param dayName the previously selected day
   *
   * @param nameInput the text field with the specified name input text
   *
   * @param ta the text area with a specified description text
   */
  public CreateTaskButtonHandler(Task task, Controller controller, String dayName,
                                 TextField nameInput, TextArea ta) {
    this.task = task;
    dayOptions = new ComboBox<>();
    for (DayName dn : DayName.values()) {
      dayOptions.getItems().add(dn.toString());
    }
    dayOptions.getSelectionModel().select(dayName);
    completionStatus = new ComboBox<>();
    completionStatus.getItems().addAll(Completion.INCOMPLETE.toString(),
        Completion.COMPLETE.toString());
    if (this.task.getComplete()) {
      completionStatus.getSelectionModel().select(Completion.COMPLETE.toString());
    } else {
      completionStatus.getSelectionModel().select(Completion.INCOMPLETE.toString());
    }
    this.nameInput = nameInput;
    this.descriptionInput = ta;
    this.controller = controller;
    this.stage = new Stage(StageStyle.UTILITY);
    stage.setOnCloseRequest(ee -> ee.consume());
  }


  /**
   * handles the pressing of a create task button
   *
   * @param e the event which occurred
   */
  @Override
  public void handle(Event e) {
    makeBackground();
    setColors();
    scene = new Scene(display);
    stage.setScene(scene);
    stage.show();
    createButton.setOnAction(new FinishCreateTaskHandler(dayOptions, completionStatus,
        nameInput, descriptionInput, controller, stage));
  }

  /**
   * makes the background
   */
  private void makeBackground() {
    createButton = new Button("Create");
    Label title = new Label("Task Editor!!!!!");
    title.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, FontPosture.ITALIC, 24));
    GridPane grid = new GridPane();
    grid.setVgap(4);
    grid.setHgap(10);
    grid.setPadding(new Insets(5, 5, 5, 5));
    grid.add(new Label("Day: "), 0, 0);
    grid.add(dayOptions, 1, 0);
    grid.add(new Label("Completion Status: "), 2, 0);
    grid.add(completionStatus, 3, 0);
    grid.add(new Label("Name: "), 0, 1);
    grid.add(nameInput, 1, 1, 3, 1);
    grid.add(descriptionInput, 0, 2, 4, 1);
    grid.add(createButton, 0, 3);
    display = new VBox(title, grid);
  }

  /**
   * sets background colors
   */
  private void setColors() {
    createButton.setBackground(Background.fill(Paint.valueOf("#87b9ff")));
    dayOptions.setBackground(Background.fill(Paint.valueOf("#e0faff")));
    completionStatus.setBackground(Background.fill(Paint.valueOf("#e0faff")));
    nameInput.setBackground(Background.fill(Paint.valueOf("#e0faff")));
    descriptionInput.setBackground(Background.fill(Paint.valueOf("#e0faff")));
    display.setBackground(Background.fill(Paint.valueOf("#c2e7ff")));
    display.setAlignment(Pos.CENTER);
  }
}
