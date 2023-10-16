package cs3500.pa05.model.json;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * A Json for Events
 *
 * @param name the name of the event
 *
 * @param description the description
 *
 * @param startTime the start time
 *
 * @param duration the duration in minutes
 */
public record EventJson(
    @JsonProperty("event-name") String name,
    @JsonProperty("description") String description,
    @JsonProperty("start-time") String startTime,
    @JsonProperty("duration") int duration) {

  /**
   * returns the String representation of thie EventJson
   *
   * @return the String representation
   */
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("{\n");
    sb.append("      \"event-name\": \"" + name + "\",\n");
    sb.append("      \"description\": \"" + description + "\",\n");
    sb.append("      \"start-time\": \"" + startTime + "\",\n");
    sb.append("      \"duration\": " + duration + "\n");
    sb.append("}");
    return sb.toString();
  }
}
