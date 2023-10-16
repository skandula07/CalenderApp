package cs3500.pa05.model;

import java.util.List;

/**
 * a week in the bullet journal
 */
public class Week implements TimeSpan {
  private String theme;
  private String name;

  private int maxDailyTasks;
  private int maxDailyEvents;
  private List<Day> days;

  /**
   * constructs a week
   *
   * @param theme to display the week
   * @param name name for the week
   * @param maxTasks the desired maximum number of tasks
   * @param maxEvents the desired maximum number of events
   * @param listDays list of Days in the week
   */
  public Week(String theme, String name, int maxTasks, int maxEvents, List<Day> listDays) {
    this.theme = theme;
    this.name = name;
    this.days = listDays;
    this.maxDailyTasks = maxTasks;
    this.maxDailyEvents = maxEvents;
  }

  /**
   * adds a Task to the schedule if adding it doesn't go over the max
   *
   * @param task the Task to add
   *
   * @param day a Day to add to
   */
  @Override
  public void addTask(Task task, Day day)  {
    day.getTasks().add(task);
    if (!(day.getTasks().size() <= maxDailyTasks)) {
      throw new IllegalStateException("You've passed your daily task max, "
          + "consider redistributing your work!");
    }
  }

  /**
   * adds an Event to the schedule if adding it doesn't go over the max
   *
   * @param event the Event to add
   *
   * @param day a Day to add to
   */
  @Override
  public void addEvent(Event event, Day day) {
    day.getEvents().add(event);
    if (!(day.getEvents().size() <= maxDailyEvents)) {
      throw new IllegalStateException("You've passed your daily event max, "
          + "consider redistributing your work!");
    }
  }

  /**
   * setter for the theme of this week
   *
   * @param theme the theme to set this week to
   */
  @Override
  public void setTheme(String theme) {
    this.theme = theme;
  }

  /**
   * getter for the theme field
   *
   * @return the theme field
   */
  @Override
  public String getTheme() {
    return theme;
  }


  /**
   * returns the corresponding Day object of the given String
   *
   * @param dayName the String name of a day
   *
   * @return the corresponding Day
   */
  @Override
  public Day getDay(String dayName) {
    if (dayName.equalsIgnoreCase(DayName.SUNDAY.toString())) {
      return days.get(0);
    } else if (dayName.equalsIgnoreCase(DayName.MONDAY.toString())) {
      return days.get(1);
    } else if (dayName.equalsIgnoreCase(DayName.TUESDAY.toString())) {
      return days.get(2);
    } else if (dayName.equalsIgnoreCase(DayName.WEDNESDAY.toString())) {
      return days.get(3);
    } else if (dayName.equalsIgnoreCase(DayName.THURSDAY.toString())) {
      return days.get(4);
    } else if (dayName.equalsIgnoreCase(DayName.FRIDAY.toString())) {
      return days.get(5);
    } else if (dayName.equalsIgnoreCase(DayName.SATURDAY.toString())) {
      return days.get(6);
    }
    return days.get(0);
  }

  /**
   * getter for this Week's list of Days
   *
   * @return the list of Days
   */
  @Override
  public List<Day> getDays() {
    return days;
  }

  /**
   * getter for the week's max daily tasks
   *
   * @return the max number of daily tasks
   */
  @Override
  public int getMaxDailyTasks() {
    return maxDailyTasks;
  }

  /**
   * getter for the week's max daily events
   *
   * @return the max number of daily events
   */
  @Override
  public int getMaxDailyEvents() {
    return maxDailyEvents;
  }

  /**
   * getter for the Week's name
   *
   * @return the Week's name
   */
  @Override
  public String getName() {
    return name;
  }

  /**
   * setter for the Week's name
   *
   * @param name to set as the name
   */
  @Override
  public void setName(String name) {
    this.name = name;
  }

  /**
   * getter for the week's total task count
   *
   * @return the total number of tasks
   */
  @Override
  public int getTotalTasks() {
    int count = 0;
    for (Day d : days) {
      count += d.getTasks().size();
    }
    return count;
  }

  /**
   * getter for the week's total event count
   *
   * @return the total number of events
   */
  @Override
  public int getTotalEvents() {
    int count = 0;
    for (Day d : days) {
      count += d.getEvents().size();
    }
    return count;
  }

  /**
   * setter for the week's maxDailyTasks
   *
   * @param maxTasks to set the max daily tasks
   */
  @Override
  public void setMaxDailyTasks(int maxTasks) {
    if (maxTasks >= 0) {
      this.maxDailyTasks = maxTasks;
    } else {
      throw new IllegalArgumentException("Cannot set max daily tasks to: "
          + maxTasks + ". Number cannot be negative.");
    }
  }

  /**
   * setter for the week's maxDailyEvents
   *
   * @param maxEvents to set the max daily events
   */
  @Override
  public void setMaxDailyEvents(int maxEvents) {
    if (maxEvents >= 0) {
      this.maxDailyEvents = maxEvents;
    } else {
      throw new IllegalArgumentException("Cannot set max daily events to: "
          + maxEvents + ". Number cannot be negative.");
    }
  }
}