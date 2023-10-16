package cs3500.pa05.model;

import java.util.List;

/**
 * some prolonged length of time to be recorded in a bullet journal
 */
public interface TimeSpan {

  /**
   * adds a Task to the TimeSpan if adding it doesn't go over the max
   *
   * @param task the Task to add
   *
   * @param day a Day to add to
   */
  void addTask(Task task, Day day);

  /**
   * adds an Event to the TimeSpan if adding it doesn't go over the max
   *
   * @param event the Event to add
   *
   * @param day a Day to add to
   */
  void addEvent(Event event, Day day);

  /**
   * setter for the theme of this TimeSpan
   *
   * @param theme the theme to set this TimeSpan to
   */
  void setTheme(String theme);

  /**
   * getter for the TimeSpan's theme field
   *
   * @return the theme field
   */
  String getTheme();

  /**
   * returns the corresponding Day object of the given String
   *
   * @param dayName the String name of a day
   *
   * @return the corresponding Day
   */
  Day getDay(String dayName);

  /**
   * getter for this TimeSpan's list of Days
   *
   * @return the list of Days
   */
  List<Day> getDays();

  /**
   * getter for the TimeSpan's max daily tasks
   *
   * @return the max number of daily tasks
   */
  int getMaxDailyTasks();

  /**
   * getter for the TimeSpan's max daily events
   *
   * @return the max number of daily events
   */
  int getMaxDailyEvents();

  /**
   * getter for the TimeSpan's name
   *
   * @return the Week's name
   */
  String getName();

  /**
   * setter for the TimeSpan's name
   *
   * @param name to set as the name
   */
  void setName(String name);

  /**
   * getter for the TimeSpan's total task count
   *
   * @return the total number of tasks
   */
  int getTotalTasks();

  /**
   * getter for the TimeSpan's total event count
   *
   * @return the total number of events
   */
  int getTotalEvents();

  /**
   * setter for the TimeSpan's maxDailyTasks
   *
   * @param maxTasks to set the max daily tasks
   */
  void setMaxDailyTasks(int maxTasks);

  /**
   * setter for the TimeSpan's maxDailyEvents
   *
   * @param maxEvents to set the max daily events
   */
  void setMaxDailyEvents(int maxEvents);
}