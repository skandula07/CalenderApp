package cs3500.pa05;

import cs3500.pa05.controller.BujoController;
import cs3500.pa05.controller.SplashScreenController;
import javafx.application.Application;

/**
 * the Driver of this program
 */
public class Driver {

  /**
   * the main method
   *
   * @param args no CLI arguments required
   */
  public static void main(String[] args) {
    System.setProperty("javafx.preloader", SplashScreenController.class.getName());
    Application.launch(BujoController.class, args);
  }
}
