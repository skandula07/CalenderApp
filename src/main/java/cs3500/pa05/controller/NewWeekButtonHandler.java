package cs3500.pa05.controller;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.stage.Stage;

/**
 * A class for a NewWeekButtonHandler
 */
public class NewWeekButtonHandler implements EventHandler {

  /**
   * handles the pressing of new week button
   *
   * @param e the event which occurred
   */
  @Override
  public void handle(Event e) {
    BujoController controller = new BujoController();
    Stage stage = new Stage();
    controller.start(stage);
  }
}
