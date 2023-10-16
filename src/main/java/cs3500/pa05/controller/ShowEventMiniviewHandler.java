package cs3500.pa05.controller;

import cs3500.pa05.model.Event;
import cs3500.pa05.model.Week;
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
 * handler for showing event miniview
 */
public class ShowEventMiniviewHandler implements EventHandler {

  private Button editButton;
  private Stage miniViewer;
  private Event event;
  private String dayName;
  private Controller controller;
  private Week week;
  private Button eventViewButton;


  /**
   * constructor for ShowEventMiniviewHandler
   *
   * @param event the event to be displayed
   * @param day the day of the event
   * @param controller to handle state change
   * @param week the week of the event
   * @param evb the button to view the event
   */
  public ShowEventMiniviewHandler(Event event, String day, Controller controller,
                                  Week week, Button evb) {
    this.event = event;
    this.dayName = day;
    this.miniViewer = new Stage();
    this.editButton = new Button("Edit");
    this.controller = controller;
    this.week = week;
    this.eventViewButton = evb;
  }

  /**
   * handle displaying Event miniview
   *
   * @param event the event which occurred
   */
  @Override
  public void handle(javafx.event.Event event) {
    createMiniviewer();
    miniViewer.show();
    editButton.setOnAction(new EditEventButtonHandler(this.event, dayName, controller,
        miniViewer, week, eventViewButton));
  }

  /**
   * create the miniviewer
   */
  private void createMiniviewer() {
    VBox backDrop = new VBox();
    backDrop.setPadding(new Insets(10));
    backDrop.setBackground(Background.fill(Paint.valueOf("#ffddd4")));
    backDrop.getChildren().addAll(new Label("Name: " + event.getName()),
        new Label("Day: " + dayName),
        new Label("Start Time: " + event.getStartTime()),
        new Label("Duration: " + event.getDuration()),
        new Label("Description: " + event.getDescription()),
        editButton);
    editButton.setBackground(Background.fill(Paint.valueOf("#ffb3b0")));
    miniViewer.setScene(new Scene(backDrop));
  }
}