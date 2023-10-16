package cs3500.pa05;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cs3500.pa05.model.json.EventJson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * A class to test the EventJson methods
 */
public class EventJsonTest {
  private EventJson ej;

  /**
   * Creates an EventJson for testing
   */
  @BeforeEach
  public void setup() {
    ej = new EventJson("test event", "", "12:00", 20);
  }

  /**
   * tests the toString method
   */
  @Test
  public void testToString() {
    String ex = "{\n      \"event-name\": \"test event\",\n"
        + "      \"description\": \"\",\n"
        + "      \"start-time\": \"12:00\",\n"
        + "      \"duration\": 20\n}";
    assertEquals(ex, ej.toString());
  }

  /**
   * tests the name method
   */
  @Test
  public void testName() {
    assertEquals("test event", ej.name());
  }

  /**
   * tests the description method
   */
  @Test
  public void testDescription() {
    assertEquals("", ej.description());
  }

  /**
   * tests the startTime method
   */
  @Test
  public void testStartTime() {
    assertEquals("12:00", ej.startTime());
  }

  /**
   * tests the duration method
   */
  @Test
  public void testDuration() {
    assertEquals(20, ej.duration());
  }

}
