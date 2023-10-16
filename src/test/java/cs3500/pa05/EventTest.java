package cs3500.pa05;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import cs3500.pa05.model.Event;
import java.time.format.DateTimeParseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * A class to test the Event class's methods
 */
public class EventTest {

  private Event testEvent1;
  private Event testEvent2;

  /**
   * creates 2 test events
   */
  @BeforeEach
  public void setup() {
    testEvent1 = new Event("test event 1", "des", "12:00", 20);
    testEvent2 = new Event("test event 2", "12:00", 20);
  }

  /**
   * tests constructor exceptions
   */
  @Test
  public void testException() {
    assertThrows(IllegalStateException.class,
        () -> new Event("", "10:00", 10));
    assertThrows(IllegalArgumentException.class,
        () -> new Event("event", "10:00", 0));
    assertThrows(DateTimeParseException.class,
        () -> new Event("event", "idk", 20));
  }

  /**
   * tests the getName method
   */
  @Test
  public void testGetName() {
    assertEquals("test event 1", testEvent1.getName());
  }

  /**
   * tests the getDescription method
   */
  @Test
  public void testGetDescription() {
    assertEquals("des", testEvent1.getDescription());
    assertEquals("", testEvent2.getDescription());
  }

  /**
   * tests the getDuration method
   */
  @Test
  public void testGetDuration() {
    assertEquals(20, testEvent1.getDuration());
  }

  /**
   * tests the setDuration method
   */
  @Test
  public void testSetDuration() {
    assertEquals(20, testEvent1.getDuration());
    Exception exc = assertThrows(IllegalArgumentException.class,
        () -> new Event("event", "10:00", -1));
    assertEquals("Duration must be positive.", exc.getMessage());
  }

  /**
   * tests the getStartTime method
   */
  @Test
  public void testGetStartTime() {
    assertEquals("12:00", testEvent1.getStartTime());
  }
}