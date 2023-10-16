package cs3500.pa05.controller;


import cs3500.pa05.model.Theme;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;


/**
 * A class for a ThemeButtonHandler
 */
public class ThemeButtonHandler implements EventHandler {
  private String theme;
  private Stage stage;
  private Controller controller;

  /**
   * Creates a ThemeButtonHandler object
   *
   * @param stage to display information
   *
   * @param controller to handle state change
   */
  public ThemeButtonHandler(Stage stage, Controller controller) {
    this.stage = stage;
    this.controller = controller;
    this.theme = "default";
  }

  /**
   * shows a choice box and sets the theme to the selection
   *
   * @param event the event which occurred
   */
  @Override
  public void handle(Event event) {
    ChoiceBox choiceBox = new ChoiceBox();
    for (Theme theme : Theme.values()) {
      choiceBox.getItems().add(theme.toString());
    }
    choiceBox.setOnAction(e -> {
      this.theme = choiceBox.getValue().toString();
      choiceBox.hide();
      controller.setTheme(theme);
      stage.close();
    });
    stage.setScene(new Scene(choiceBox));
    stage.show();
  }
}
