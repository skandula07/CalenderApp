package cs3500.pa05;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cs3500.pa05.model.Day;
import cs3500.pa05.model.DayName;
import cs3500.pa05.model.Event;
import cs3500.pa05.model.Task;
import cs3500.pa05.model.json.WeekJson;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * A class to test the WeekJson methods
 */
public class WeekJsonTest {
  private WeekJson wj;
  private Day sunday;
  private Day monday;
  private Task task1;
  private Task task2;
  private Event event1;
  private Event event2;
  private List<Day> days;

  /**
   * creates tasks, events, days, and a WeekJson for testing
   */
  @BeforeEach
  public void setup() {
    task1 = new Task("test task", false);
    task2 = new Task("test task 2", true);
    event1 = new Event("test event", "12:00", 20);
    event2 = new Event("test event 2", "12:30", 30);
    sunday = new Day(DayName.SUNDAY, new ArrayList<>(Arrays.asList(task1, task2)),
        new ArrayList<>(Arrays.asList(event1, event2)));
    monday = new Day(DayName.MONDAY);
    days = new ArrayList<>(Arrays.asList(sunday, monday));
    wj = new WeekJson("default", "My Week", 3, 3, days);

  }

  /**
   * tests the toString method
   */
  @Test
  public void testToString() {
    String ex = "{\n\"theme\":\"default\",\n"
        + "\"name\":\"My Week\",\n"
        + "\"max-tasks\":3,\n"
        + "\"max-events\":3,\n"
        + "\"days\":["
        + "{\"name\":\"SUNDAY\",\n"
        + "\"tasks\":["
        + "{\n      \"task-name\": \"test task\",\n      \"description\": \"\",\n"
        + "      \"completion-status\": \"false\"},"
        + "{\n      \"task-name\": \"test task 2\",\n      \"description\": \"\",\n"
        + "      \"completion-status\": \"true\"}],"
        + "\n\"events\": ["
        + "{\n      \"event-name\": \"test event\",\n"
        + "      \"description\": \"\",\n"
        + "      \"start-time\": \"12:00\",\n"
        + "      \"duration\": 20\n},"
        + "{\n      \"event-name\": \"test event 2\",\n"
        + "      \"description\": \"\",\n"
        + "      \"start-time\": \"12:30\",\n"
        + "      \"duration\": 30\n}]"
        + "\n    },{\"name\":\"MONDAY\",\n"
        + "\"tasks\":[],\n\"events\": []"
        + "\n    }]}";
    assertEquals(ex, wj.toString());
  }

  /**
   * tests the theme method
   */
  @Test
  public void testTheme() {
    assertEquals("default", wj.theme());
  }

  /**
   * tests the name method
   */
  @Test
  public void testName() {
    assertEquals("My Week", wj.name());
  }

  /**
   * tests the maxDailyTasks method
   */
  @Test
  public void testMaxDailyTasks() {
    assertEquals(3, wj.maxDailyTasks());
  }

  /**
   * tests the maxDailyEvents method
   */
  @Test
  public void testMaxDailyEvents() {
    assertEquals(3, wj.maxDailyEvents());
  }

  /**
   * tests the days method
   */
  @Test
  public void testDays() {
    assertEquals(days, wj.days());
  }
}
