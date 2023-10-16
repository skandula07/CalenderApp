package cs3500.pa05.controller;

import cs3500.pa05.model.Day;
import cs3500.pa05.model.Week;
import java.util.List;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

/**
 * represents Controllers for bullet journal applications
 */
public interface Controller {

  /**
   * sets the progress stats for the given Day
   *
   * @param day the Day to calculate progress stats for
   */
  void setProgress(Day day);

  /**
   * sets the theme based on the given string
   *
   * @param theme the string representation of the theme
   */
  void setTheme(String theme);

  /**
   * loads the contents from the .bujo file with the given String name
   *
   * @param fileName the name of the .bujo file
   */
  @FXML
  void loadFromExisting(String fileName);

  /**
   * Adds the given button to the given String's corresponding day's view
   *
   * @param day    the day to be added to
   * @param button the button to be added
   */
  void addItemView(String day, Button button);

  /**
   * removes the given button from the Day corresponding to the given String
   *
   * @param b   the button to be removed
   * @param day the String representation of a Day
   */
  @FXML
  void removeButton(Button b, String day);

  /**
   * sets the number of total tasks so far in the Week view summary
   *
   * @param total the current number of tasks
   */
  @FXML
  void setTotalTasksView(int total);

  /**
   * sets the number of total events so far in the Week view summary
   *
   * @param total the current number of events
   */
  @FXML
  void setTotalEventsView(int total);

  /**
   * sets the percentage of total tasks completed so far in the Week view summary
   *
   * @param days  the days in the week
   * @param total the total tasks in the week
   */
  @FXML
  void setPercentTasksCompleted(List<Day> days, double total);

  /**
   * sets the current file being displayed
   *
   * @param file the path to set the current file to
   */
  void setCurrentFileName(String file);

  /**
   * sets the updated week
   *
   * @param w the week to set the save/save as handlers to
   */
  void setWeek(Week w);

  /**
   * getter for this Controller's week field
   *
   * @return this Controller's week field
   */
  Week getWeek();

  /**
   * getter for this Controller's current file path
   *
   * @return this Controller's current file path
   */
  String getCurrentFileName();
}
