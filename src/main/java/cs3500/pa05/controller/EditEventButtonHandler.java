package cs3500.pa05.controller;

import cs3500.pa05.model.Event;
import cs3500.pa05.model.Week;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * handler for editing events
 */
public class EditEventButtonHandler implements EventHandler {

  private Event event;
  private String dayName;
  private Controller controller;
  private Stage stage;
  private Week week;
  private Button eventViewButton;

  /**
   * constructor for EditEventButtonHandler
   *
   * @param event to be edited
   * @param dayName the day of the event
   * @param controller to handle state changes
   * @param stage to display information
   * @param week to add event to
   * @param button to view the event
   */
  public EditEventButtonHandler(Event event, String dayName,
                                Controller controller, Stage stage, Week week, Button button) {
    this.event = event;
    this.dayName = dayName;
    this.controller = controller;
    this.stage = stage;
    this.week = week;
    this.eventViewButton = button;
  }

  /**
   * handle event editing
   *
   * @param e the event which occurred
   */
  @Override
  public void handle(javafx.event.Event e) {
    stage.close();
    CreateEventButtonHandler updater =
        new CreateEventButtonHandler(event,  dayName, controller,
            new TextField(event.getStartTime()), new TextField(event.getDuration() + ""),
            new TextField(event.getName()), new TextArea(event.getDescription()));
    updater.handle(e);
    controller.removeButton(eventViewButton, this.dayName);
    week.getDay(dayName).getEvents().remove(event);
  }
}