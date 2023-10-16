package cs3500.pa05.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.List;

/**
 * A class for days of the week
 */
public class Day {
  private DayName name;
  private List<Task> tasks;
  private List<Event> events;

  /**
   * Creates a Day with the given List of Task and List of Event
   *
   * @param name the name of the Day
   */
  public Day(DayName name) {
    this.name = name;
    this.tasks = new ArrayList<>();
    this.events = new ArrayList<>();
  }

  /**
   * A JsonCreator for a Day
   *
   * @param name the name of the day
   *
   * @param tasks the List of tasks for this day
   *
   * @param events the List of events for this day
   */
  @JsonCreator
  public Day(@JsonProperty("name") DayName name, @JsonProperty("tasks")List<Task> tasks,
             @JsonProperty("events")List<Event> events) {
    this.name = name;
    this.tasks = tasks;
    this.events = events;
  }

  /**
   * getter for the events field
   *
   * @return the events field
   */
  public List<Event> getEvents() {
    return events;
  }

  /**
   * getter for the tasks field
   *
   * @return the tasks field
   */
  public List<Task> getTasks() {
    return tasks;
  }

  /**
   * getter for the name field as a String
   *
   * @return the name field
   */
  public String getName() {
    return name.toString();
  }
}

