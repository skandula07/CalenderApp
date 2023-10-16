package cs3500.pa05;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cs3500.pa05.model.Day;
import cs3500.pa05.model.DayName;
import cs3500.pa05.model.Event;
import cs3500.pa05.model.Task;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * A class for testing the Day class's methods
 */
public class DayTest {
  private Day day1;
  private Day day2;
  private List<Task> tasks;
  private List<Event> events;

  /**
   * creates a list of tasks and events, and 2 days for testing
   */
  @BeforeEach
  public void setup() {
    tasks = new ArrayList<>(Arrays.asList(new Task("task", false)));
    events = new ArrayList<>(Arrays.asList(new Event("event", "12:00", 20)));
    day1 = new Day(DayName.SUNDAY);
    day2 = new Day(DayName.MONDAY, tasks, events);
  }

  /**
   * tests the getTasks method
   */
  @Test
  public void testGetTasks() {
    assertEquals(new ArrayList<>(), day1.getTasks());
    assertEquals(tasks, day2.getTasks());
  }

  /**
   * tests the getEvents method
   */
  @Test
  public void testGetEvents() {
    assertEquals(new ArrayList<>(), day1.getEvents());
    assertEquals(events, day2.getEvents());
  }

  /**
   * tests the getName method
   */
  @Test
  public void testGetName() {
    assertEquals("SUNDAY", day1.getName());
    assertEquals("MONDAY", day2.getName());
  }
}
