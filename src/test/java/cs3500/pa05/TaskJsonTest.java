package cs3500.pa05;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cs3500.pa05.model.json.TaskJson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * A class to test the TaskJson methods
 */
public class TaskJsonTest {
  private TaskJson tj;

  /**
   * creates a TaskJson for testing
   */
  @BeforeEach
  public void setup() {
    tj = new TaskJson("test task", "", false);
  }

  /**
   * tests the toString method
   */
  @Test
  public void testToString() {
    String ex = "{\n      \"task-name\": \"test task\",\n      \"description\": \"\",\n"
        + "      \"completion-status\": \"false\"}";
    assertEquals(ex, tj.toString());
  }

  /**
   * tests the name method
   */
  @Test
  public void testName() {
    assertEquals("test task", tj.name());
  }

  /**
   * tests the description method
   */
  @Test
  public void testDescription() {
    assertEquals("", tj.description());
  }

  /**
   * tests the completionStatus method
   */
  @Test
  public void testCompletionStatus() {
    assertEquals(false, tj.completionStatus());
  }
}
