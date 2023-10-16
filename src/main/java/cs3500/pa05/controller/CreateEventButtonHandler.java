package cs3500.pa05.controller;

import cs3500.pa05.model.DayName;
import cs3500.pa05.model.Event;
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
 * A class for a CreateEventButtonHandler
 */
public class CreateEventButtonHandler implements EventHandler {

  private ComboBox<String> dayOptions;
  private TextField nameInput;
  private TextField startTimeInput;
  private TextField durationInput;
  private TextArea descriptionInput;
  private VBox display;
  private Button createButton;
  private Scene scene;
  private Controller controller;
  private Event event;
  private Stage stage;

  /**
   * Creates a CreateEventButtonHandler object
   *
   * @param controller the Controller to handle state changes
   */
  public CreateEventButtonHandler(Controller controller) {
    dayOptions = new ComboBox<>();
    for (DayName dn : DayName.values()) {
      dayOptions.getItems().add(dn.toString());
    }
    startTimeInput = new TextField();
    durationInput = new TextField();
    this.nameInput = new TextField();
    this.descriptionInput = new TextArea();
    this.controller = controller;
    this.stage = new Stage();
  }

  /**
   * Creates a CreateEventButtonHandler object
   *
   * @param e the event to be handled
   *
   * @param dayName the name of the day of the event being created
   *
   * @param controller the Controller
   *
   * @param startTime the text field with a given start time text
   *
   * @param duration the text field with a given duration text
   *
   * @param nameInput the text field with a given name input text
   *
   * @param descriptionInput the text area with a given description text
   */
  public CreateEventButtonHandler(Event e, String dayName, Controller controller,
                                  TextField startTime, TextField duration, TextField nameInput,
                                  TextArea descriptionInput) {
    this.event = e;
    dayOptions = new ComboBox<>();
    for (DayName dn : DayName.values()) {
      dayOptions.getItems().add(dn.toString());
    }
    dayOptions.getSelectionModel().select(dayName);
    startTimeInput = startTime;
    durationInput = duration;
    this.nameInput = nameInput;
    this.descriptionInput = descriptionInput;
    this.controller = controller;
    this.stage = new Stage(StageStyle.UTILITY);
    stage.setOnCloseRequest(ee -> ee.consume());
  }

  /**
   * handles the pressing of a create event button
   *
   * @param e the event which occurred
   */
  @Override
  public void handle(javafx.event.Event e) {
    makeBackground();
    setColors();
    scene = new Scene(display);
    stage.setScene(scene);
    stage.show();
    createButton.setOnAction(new FinishCreateEventHandler(dayOptions, durationInput,
        startTimeInput, nameInput, descriptionInput, stage, controller));
  }

  /**
   * makes the background
   */
  private void makeBackground() {
    createButton = new Button("Create");
    Label title = new Label("Event Editor!!!!!");
    title.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, FontPosture.ITALIC, 24));
    GridPane grid = new GridPane();
    grid.setVgap(4);
    grid.setHgap(5);
    grid.setPadding(new Insets(5, 5, 5, 5));
    grid.add(new Label("Day: "), 0, 0);
    grid.add(dayOptions, 1, 0);
    grid.add(startTimeInput, 2, 0, 1, 1);
    startTimeInput.setPromptText("Start Time (ex: \"11:11\")");
    grid.add(durationInput, 4, 0, 1, 1);
    durationInput.setPromptText("Duration");
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
    createButton.setBackground(Background.fill(Paint.valueOf("#ffb3b0")));
    dayOptions.setBackground(Background.fill(Paint.valueOf("#fff6f2")));
    startTimeInput.setBackground(Background.fill(Paint.valueOf("#fff6f2")));
    durationInput.setBackground(Background.fill(Paint.valueOf("#fff6f2")));
    nameInput.setBackground(Background.fill(Paint.valueOf("#fff6f2")));
    descriptionInput.setBackground(Background.fill(Paint.valueOf("#fff6f2")));
    display.setBackground(Background.fill(Paint.valueOf("#f5dcd7")));
    display.setAlignment(Pos.CENTER);
  }
}
