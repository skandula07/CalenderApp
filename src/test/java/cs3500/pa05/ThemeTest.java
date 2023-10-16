package cs3500.pa05;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cs3500.pa05.model.Theme;
import org.junit.jupiter.api.Test;

/**
 * tests for the Theme enum
 */
public class ThemeTest {

  Theme sree = Theme.SREE;
  Theme vithya = Theme.VITHYA;
  Theme grace = Theme.GRACE;
  Theme dflt = Theme.DEFAULT;

  /**
   * test toString() method
   */
  @Test
  public void testToString() {
    assertEquals("Sree's Theme", sree.toString());
    assertEquals("Vithya's Theme", vithya.toString());
    assertEquals("Grace's Theme", grace.toString());
    assertEquals("default", dflt.toString());
  }
}