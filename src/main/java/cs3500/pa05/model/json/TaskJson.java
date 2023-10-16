package cs3500.pa05.model.json;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * A Json for a Task
 *
 * @param name the name of the task
 *
 * @param description the description
 *
 * @param completionStatus the completion status
 */
public record TaskJson(
    @JsonProperty("task-name") String name,
    @JsonProperty("description") String description,
    @JsonProperty("completion-status") boolean completionStatus) {


  /**
   * returns the String representation of this TaskJson
   *
   * @return the String representation
   */
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("{\n");
    sb.append("      \"task-name\": \"" + name + "\",\n");
    sb.append("      \"description\": \"" + description + "\",\n");
    sb.append("      \"completion-status\": \"" + completionStatus + "\"}");
    return sb.toString();
  }

}
