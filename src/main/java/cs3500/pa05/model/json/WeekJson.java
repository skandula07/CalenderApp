package cs3500.pa05.model.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import cs3500.pa05.model.Day;
import cs3500.pa05.model.DayName;
import java.util.List;

/**
 * A Json for a Week
 *
 * @param theme the theme of this Week
 *
 * @param name the name of this Week
 *
 * @param maxDailyTasks the max daily tasks
 *
 * @param maxDailyEvents the max daily events
 *
 * @param days the days of this Week
 */
public record WeekJson(
    @JsonProperty("theme") String theme,
    @JsonProperty("name") String name,
    @JsonProperty("max-tasks") int maxDailyTasks,
    @JsonProperty("max-events") int maxDailyEvents,
    @JsonProperty("days") List<Day> days) {

  /**
   * returns the String representation of this WeekJson
   *
   * @return the String representation
   */
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("{\n");
    sb.append("\"theme\":\"" + theme + "\",\n");
    sb.append("\"name\":\"" + name + "\",\n");
    sb.append("\"max-tasks\":" + maxDailyTasks + ",\n");
    sb.append("\"max-events\":" + maxDailyEvents + ",\n");
    sb.append("\"days\":[");
    int i = 0;
    for (Day d : days) {
      sb.append(new DayJson(DayName.valueOf(d.getName()), d.getTasks(), d.getEvents()).toString());
      if (i < days.size() - 1) {
        sb.append(",");
      }
      i++;
    }
    sb.append("]}");
    return sb.toString();
  }
}