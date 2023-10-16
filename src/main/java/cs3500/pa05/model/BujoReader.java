package cs3500.pa05.model;

import java.util.Scanner;

/**
 * A class for a BujoReader
 */
public class BujoReader {

  /**
   * reads and returns the String contents of the given Scanner
   *
   * @param scanner the Scanner to read from
   *
   * @return the String representation of the contents
   */
  public String read(Scanner scanner) {
    StringBuilder sb = new StringBuilder();
    while (scanner.hasNextLine()) {
      sb.append(scanner.nextLine());
    }
    return sb.toString();
  }
}
