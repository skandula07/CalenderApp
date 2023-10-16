package cs3500.pa05;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import cs3500.pa05.model.Day;
import cs3500.pa05.model.DayName;
import cs3500.pa05.model.Event;
import cs3500.pa05.model.Task;
import cs3500.pa05.model.Week;
import java.util.ArrayList;
import java.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * A class for testing the Week class's methods
 */
public class WeekTest {
  private Week testWeek;
  private Task testTask;
  private Event testEvent;
  private Day sunday;
  private Day monday;
  private Day tuesday;
  private Day wednesday;
  private Day thursday;
  private Day friday;
  private Day saturday;

  /**
   * Creates the 7 days of the week, tasks, and event for testing
   */
  @BeforeEach
  public void setup() {
    sunday = new Day(DayName.SUNDAY);
    monday = new Day(DayName.MONDAY);
    tuesday = new Day(DayName.TUESDAY);
    wednesday = new Day(DayName.WEDNESDAY);
    thursday = new Day(DayName.THURSDAY);
    friday = new Day(DayName.FRIDAY);
    saturday = new Day(DayName.SATURDAY);

    testWeek = new Week("default", "My Week", 2, 2,
        new ArrayList<>(Arrays.asList(sunday, monday,
            tuesday, wednesday, thursday, friday, saturday)));
    testTask = new Task("test task 1", "", false);
    testEvent = new Event("test event", "des", "12:00", 20);
  }

  /**
   * tests the addTask method
   */
  @Test
  public void testAddTask() {
    assertEquals(0, testWeek.getTotalTasks());
    testWeek.addTask(testTask, testWeek.getDays().get(0));
    assertEquals(1, testWeek.getTotalTasks());
    testWeek.addTask(testTask, testWeek.getDays().get(0));
    assertEquals(2, testWeek.getTotalTasks());
    Exception exc = assertThrows(IllegalStateException.class, () -> testWeek.addTask(testTask,
        testWeek.getDays().get(0)),
        "You've passed your daily task max, consider redistributing your work!");
    assertEquals("You've passed your daily task max, consider redistributing your work!",
        exc.getMessage());
  }

  /**
   * tests the addEvent method
   */
  @Test
  public void testAddEvent() {
    assertEquals(0, testWeek.getTotalEvents());
    testWeek.addEvent(testEvent, testWeek.getDays().get(0));
    assertEquals(1, testWeek.getTotalEvents());
    testWeek.addEvent(testEvent, testWeek.getDays().get(0));
    assertEquals(2, testWeek.getTotalEvents());
    Exception exc = assertThrows(IllegalStateException.class, () -> testWeek.addEvent(testEvent,
        testWeek.getDays().get(0)),
        "You've passed your daily event max, consider redistributing your work!");
    assertEquals("You've passed your daily event max, consider redistributing your work!",
        exc.getMessage());
  }

  /**
   * tests the getTotalTasks method
   */
  @Test
  public void testGetTotalTasks() {
    assertEquals(0, testWeek.getTotalTasks());
    testWeek.addTask(testTask, testWeek.getDays().get(0));
    assertEquals(1, testWeek.getTotalTasks());
    testWeek.addTask(testTask, testWeek.getDays().get(1));
    assertEquals(2, testWeek.getTotalTasks());
  }

  /**
   * tests the getTotalEvents method
   */
  @Test
  public void testGetTotalEvents() {
    assertEquals(0, testWeek.getTotalEvents());
    testWeek.addEvent(testEvent, testWeek.getDays().get(0));
    assertEquals(1, testWeek.getTotalEvents());
    testWeek.addEvent(testEvent, testWeek.getDays().get(1));
    assertEquals(2, testWeek.getTotalEvents());
  }

  /**
   * tests the getTheme method
   */
  @Test
  public void testGetTheme() {
    assertEquals("default", testWeek.getTheme());
    testWeek.setTheme("Sree's Theme");
    assertEquals("Sree's Theme", testWeek.getTheme());
  }

  /**
   * tests the setTheme method
   */
  @Test
  public void testSetTheme() {
    assertEquals("default", testWeek.getTheme());
    testWeek.setTheme("Sree's Theme");
    assertEquals("Sree's Theme", testWeek.getTheme());
  }

  /**
   * tests the getMaxDailyTasks method
   */
  @Test
  public void testGetMaxDailyTasks() {
    assertEquals(2, testWeek.getMaxDailyTasks());
    testWeek.setMaxDailyTasks(3);
    assertEquals(3, testWeek.getMaxDailyTasks());
  }

  /**
   * tests the setMaxDailyTasks method
   */
  @Test
  public void testSetMaxDailyTasks() {
    assertEquals(2, testWeek.getMaxDailyTasks());
    testWeek.setMaxDailyTasks(3);
    assertEquals(3, testWeek.getMaxDailyTasks());
    Exception exc = assertThrows(IllegalArgumentException.class,
        () -> testWeek.setMaxDailyTasks(-1),
        "Cannot set max daily tasks to: -1. Number cannot be negative.");
    assertEquals("Cannot set max daily tasks to: -1. Number cannot be negative.",
        exc.getMessage());

  }

  /**
   * tests the getMaxDailyEvents method
   */
  @Test
  public void testGetMaxDailyEvents() {
    assertEquals(2, testWeek.getMaxDailyEvents());
    testWeek.setMaxDailyEvents(3);
    assertEquals(3, testWeek.getMaxDailyEvents());
  }

  /**
   * tests the setMaxDailyEvents method
   */
  @Test
  public void testSetMaxDailyEvents() {
    assertEquals(2, testWeek.getMaxDailyEvents());
    testWeek.setMaxDailyEvents(3);
    assertEquals(3, testWeek.getMaxDailyEvents());
    Exception exc = assertThrows(IllegalArgumentException.class,
        () -> testWeek.setMaxDailyEvents(-1),
        "Cannot set max daily events to: -1. Number cannot be negative.");
    assertEquals("Cannot set max daily events to: -1. Number cannot be negative.",
        exc.getMessage());
  }

  /**
   * test the getDay method
   */
  @Test
  public void testGetDay() {
    assertEquals(sunday, testWeek.getDay("SUNDAY"));
    assertEquals(monday, testWeek.getDay("MONDAY"));
    assertEquals(tuesday, testWeek.getDay("TUESDAY"));
    assertEquals(wednesday, testWeek.getDay("WEDNESDAY"));
    assertEquals(thursday, testWeek.getDay("THURSDAY"));
    assertEquals(friday, testWeek.getDay("FRIDAY"));
    assertEquals(saturday, testWeek.getDay("SATURDAY"));
    assertEquals(sunday, testWeek.getDay("not a day"));
  }

  /**
   * test the getName method
   */
  @Test
  public void testGetName() {
    assertEquals("My Week", testWeek.getName());
    testWeek.setName("new name");
    assertEquals("new name", testWeek.getName());
  }

  /**
   * test the setName method
   */
  @Test
  public void testSetName() {
    assertEquals("My Week", testWeek.getName());
    testWeek.setName("new name");
    assertEquals("new name", testWeek.getName());
  }
}