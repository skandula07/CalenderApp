package cs3500.pa05;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cs3500.pa05.model.DayName;
import cs3500.pa05.model.Event;
import cs3500.pa05.model.Task;
import cs3500.pa05.model.json.DayJson;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * A class for testing the DayJson methods
 */
public class DayJsonTest {
  private DayJson dj;
  private List<Task> tasks;
  private List<Event> events;

  /**
   * creates a list of tasks and events, and DayJson for testing
   */
  @BeforeEach
  public void setup() {
    tasks = new ArrayList<>(Arrays.asList(new Task("test task", false),
        new Task("test task 2", true)));
    events = new ArrayList<>(Arrays.asList(new Event("test event", "12:00", 20),
        new Event("test event 2", "", "12:30", 30)));
    dj = new DayJson(DayName.SUNDAY, tasks, events);
  }

  /**
   * tests the toString method
   */
  @Test
  public void testToString() {
    String ex = "{\"name\":\"SUNDAY\",\n"
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
        + "      \"duration\": 30\n}]\n    }";
    assertEquals(ex, dj.toString());
  }

  /**
   * tests the name method
   */
  @Test
  public void testName() {
    assertEquals(DayName.SUNDAY, dj.name());
  }

  /**
   * tests the tasks method
   */
  @Test
  public void testTask() {
    assertEquals(tasks, dj.tasks());
  }

  /**
   * tests the events method
   */
  @Test
  public void testEvents() {
    assertEquals(events, dj.events());
  }
}