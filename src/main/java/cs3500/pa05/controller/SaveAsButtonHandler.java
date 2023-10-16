package cs3500.pa05.controller;

import cs3500.pa05.model.Week;
import cs3500.pa05.model.json.WeekJson;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


/**
 * A class for a SaveAsButtonHandler
 */
public class SaveAsButtonHandler implements EventHandler {

  private Week week;
  private Controller controller;

  /**
   * creates a SaveAsButtonHandler object with the given week
   *
   * @param week the week to be passed in
   *
   * @param controller the controller to handle state change
   */
  public SaveAsButtonHandler(Week week, Controller controller) {
    this.week = week;
    this.controller = controller;
  }

  /**
   * setter for the week field
   *
   * @param week the week to set the field to
   */
  public void setWeek(Week week) {
    this.week = week;
  }


  /**
   * handles the pressing of a save-as button
   *
   * @param e the event which occurred
   */
  @Override
  public void handle(Event e) {
    VBox vbox1 = new VBox();
    TextField tf = new TextField();
    vbox1.getChildren().addAll(new Label("What would you like to name your .bujo file?"), tf);
    Stage stage = new Stage();
    stage.setHeight(150);
    stage.setWidth(350);
    tf.setOnAction(new FileInputHandler(tf, new WeekJson(week.getTheme(),
        week.getName(), week.getMaxDailyTasks(), week.getMaxDailyEvents(),
        week.getDays()).toString(), stage, controller));
    Scene scene = new Scene(vbox1, 500, 500);
    stage.setScene(scene);
    stage.show();
  }
}