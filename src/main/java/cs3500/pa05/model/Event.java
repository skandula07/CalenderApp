package cs3500.pa05.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalTime;

/**
 * represents an Event in the schedule
 */
public class Event extends Item {

  private LocalTime startTime;
  private int duration;


  /**
   * constructs an event with a description
   *
   * @param name name of the event
   *
   * @param description description of the event
   *
   * @param startTime the time the event begins
   *
   * @param duration how long the event is in minutes
   */
  @JsonCreator
  public Event(@JsonProperty("event-name") String name,
               @JsonProperty("description") String description,
               @JsonProperty("start-time") String startTime,
               @JsonProperty("duration") int duration) {
    super(name, description);
    this.startTime = LocalTime.parse(startTime);
    setDuration(duration);
  }

  /**
   * constructs an event without a description
   *
   * @param name name of the event
   *
   * @param startTime the time the event begins
   *
   * @param duration how long the event is in minutes
   */
  public Event(String name, String startTime, int duration) {
    super(name);
    this.startTime = LocalTime.parse(startTime);
    this.setDuration(duration);
  }

  /**
   * setter for the duration field that verifies that the input is positive
   *
   * @param duration the int to set the duration to
   */
  private void setDuration(int duration) {
    if (duration > 0) {
      this.duration = duration;
    } else {
      throw new IllegalArgumentException("Duration must be positive.");
    }
  }

  /**
   * getter for this Event's startTime
   *
   * @return this Event's startTime
   */
  public String getStartTime() {
    return startTime.toString();
  }

  /**
   * getter for this Event's duration
   *
   * @return this Event's duration
   */
  public int getDuration() {
    return duration;
  }
}