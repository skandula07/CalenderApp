package cs3500.pa05.model.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import cs3500.pa05.model.DayName;
import cs3500.pa05.model.Event;
import cs3500.pa05.model.Task;
import java.util.List;

/**
 * A Json for a Day
 *
 * @param name the name of the Day
 *
 * @param tasks the list of tasks for the day
 *
 * @param events the list of events for the day
 */
public record DayJson(
    @JsonProperty("name") DayName name,
    @JsonProperty("tasks") List<Task> tasks,
    @JsonProperty("events") List<Event> events) {

  /**
   * returns the String representation of this DayJson
   *
   * @return the String representation
   */
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("{");
    sb.append("\"name\":\"" + name + "\",\n");
    sb.append("\"tasks\":[");
    int i = 0;
    for (Task t : tasks) {
      sb.append(new TaskJson(t.getName(), t.getDescription(), t.getComplete()).toString());
      if (i < tasks.size() - 1) {
        sb.append(",");
      }
      i++;
    }
    sb.append("],\n");
    sb.append("\"events\": [");
    int j = 0;
    for (Event e : events) {
      sb.append(new EventJson(e.getName(), e.getDescription(), e.getStartTime(), e.getDuration()));
      if (j < events.size() - 1) {
        sb.append(",");
      }
      j++;
    }
    sb.append("]\n");
    sb.append("    }");
    return sb.toString();
  }
}