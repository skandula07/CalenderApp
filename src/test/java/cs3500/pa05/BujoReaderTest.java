package cs3500.pa05;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cs3500.pa05.model.BujoReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * A class for testing the BujoReader class's methods
 */
public class BujoReaderTest {
  private BujoReader reader;


  /**
   * Creates a BujoReader for testing
   */
  @BeforeEach
  public void setup() {
    reader = new BujoReader();
  }

  /**
   * tests the read method
   *
   * @throws FileNotFoundException if the file is not found
   */
  @Test
  public void testRead() throws FileNotFoundException {
    String s = reader.read(new Scanner(
        new File("src/main/resources/bujoFiles/TestRead.bujo")));
    assertEquals(true, s.contains("\"theme\":\"Grace's Theme\""));
    assertEquals(true, s.contains("SUNDAY"));
    assertEquals(true, s.contains("task"));
  }
}