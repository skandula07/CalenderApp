package cs3500.pa05.controller;

import cs3500.pa05.model.Week;
import cs3500.pa05.model.json.WeekJson;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javafx.event.Event;
import javafx.event.EventHandler;

/**
 * a handler for a save button
 */
public class SaveButtonHandler implements EventHandler {

  private String file;
  private SaveAsButtonHandler sbh;
  private Week week;
  private Controller controller;

  /**
   * constructor for a SaveButtonHandler
   *
   * @param sbh saveAsButtonHandler to delegate to when saving file for the first time
   * @param controller to handle state change
   */
  public SaveButtonHandler(SaveAsButtonHandler sbh, Controller controller) {
    this.controller = controller;
    this.sbh = sbh;
    this.week = this.controller.getWeek();
    this.file = this.controller.getCurrentFileName();
  }


  /**
   * handle saving a .bujo file
   *
   * @param event the event which occurred
   */
  @Override
  public void handle(Event event) {
    if (this.file.equals("")) {
      sbh.handle(event);
    } else {
      try {
        FileWriter fw = new FileWriter(this.file);
        String toWrite = new WeekJson(this.week.getTheme(), this.week.getName(),
            this.week.getMaxDailyTasks(), this.week.getMaxDailyEvents(),
            this.week.getDays()).toString();
        clearTheFile();
        fw.write(toWrite);
        fw.close();
      } catch (IOException e) {
        throw new RuntimeException("couldn't write to file");
      }
    }
  }


  /**
   * clear the contents of this SaveButtonHandler's file field
   */
  private void clearTheFile() {
    try {
      FileWriter fwOb = new FileWriter(this.file, false);
      PrintWriter pwOb = new PrintWriter(fwOb, false);
      pwOb.flush();
      pwOb.close();
      fwOb.close();
    } catch (IOException e) {
      throw new RuntimeException("couldn't clear the file");
    }
  }

  /**
   * sets this SaveButtonHandler's file field
   *
   * @param file to be set
   */
  public void setFile(String file) {
    this.file = "src/main/resources/bujoFiles/" + file;
  }

  /**
   * sets this SaveButtonHandler's week field
   *
   * @param week to be set
   */
  public void setWeek(Week week) {
    this.week = week;
  }
}
