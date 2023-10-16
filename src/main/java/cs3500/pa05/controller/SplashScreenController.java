package cs3500.pa05.controller;

import cs3500.pa05.view.SplashScreenGuiView;
import javafx.application.Preloader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * a controller for a splash screen
 */
public class SplashScreenController extends Preloader {

  SplashScreenGuiView view;
  private Scene scene;
  private Stage splashStage;


  /**
   * a constructor for a SplashScreenController
   */
  public SplashScreenController() {
    this.view = new SplashScreenGuiView(this);
  }


  /**
   * initializes the application
   */
  @Override
  public void init() {
    scene = this.view.load();
  }

  /**
   * the main entry point for the application
   *
   * @param primaryStage the primary stage for this application, onto which
   *                     the application scene can be set.
   *                     Applications may create other stages, if needed, but they will not be
   *                     primary stages.
   */
  @Override
  public void start(Stage primaryStage) {
    this.splashStage = primaryStage;
    splashStage.setScene(scene);
    splashStage.initStyle(StageStyle.UNDECORATED);
    splashStage.show();
  }

  /**
   * handles changes in application state
   *
   * @param info the state change notification
   */
  @Override
  public void handleStateChangeNotification(Preloader.StateChangeNotification info) {
    StateChangeNotification.Type type = info.getType();
    if (type == StateChangeNotification.Type.BEFORE_START) {
      splashStage.hide();
    }
  }
}
