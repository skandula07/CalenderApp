package cs3500.pa05;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import cs3500.pa05.model.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * tests the Task class's methods
 */
public class TaskTest {
  private Task testTask1;
  private Task testTask2;

  /**
   * creates 2 test tasks
   */
  @BeforeEach
  public void setup() {
    testTask1 = new Task("test task", "des", false);
    testTask2 = new Task("test task 2", false);
  }

  /**
   * tests constructor exception thrown for empty name
   */
  @Test
  public void testException() {
    assertThrows(IllegalStateException.class,
        () -> new Task("", true));
  }

  /**
   * tests the getComplete method
   */
  @Test
  public void testGetComplete() {
    assertEquals(false, testTask1.getComplete());
  }

  /**
   * tests the getName method
   */
  @Test
  public void testGetName() {
    assertEquals("test task", testTask1.getName());
  }

  /**
   * tests the getDescription method
   */
  @Test
  public void testGetDescription() {
    assertEquals("des", testTask1.getDescription());
    assertEquals("", testTask2.getDescription());
  }
}
