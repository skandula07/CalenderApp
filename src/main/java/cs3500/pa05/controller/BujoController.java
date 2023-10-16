package cs3500.pa05.controller;

import static javafx.scene.paint.Color.rgb;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import cs3500.pa05.model.BujoReader;
import cs3500.pa05.model.Day;
import cs3500.pa05.model.DayName;
import cs3500.pa05.model.Event;
import cs3500.pa05.model.Task;
import cs3500.pa05.model.Theme;
import cs3500.pa05.model.Week;
import cs3500.pa05.model.json.WeekJson;
import cs3500.pa05.view.BujoGuiView;
import cs3500.pa05.view.GuiView;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ProgressBar;
import javafx.scene.effect.BlendMode;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;


/**
 * A class for a BujoController
 */
public class BujoController extends Application implements Controller {
  @FXML
  private Label name;
  @FXML
  private Button taskButton;
  @FXML
  private Button eventButton;
  @FXML
  private Label sundayTitle;
  @FXML
  private ProgressBar sundayProgressBar;
  @FXML
  private Label sundayTasksLeft;
  @FXML
  private Pane sundayVbox;
  @FXML
  private Pane sundayContents;
  @FXML
  private Label mondayTitle;
  @FXML
  private ProgressBar mondayProgressBar;
  @FXML
  private Label mondayTasksLeft;
  @FXML
  private Pane mondayVbox;
  @FXML
  private Pane mondayContents;
  @FXML
  private Label tuesdayTitle;
  @FXML
  private ProgressBar tuesdayProgressBar;
  @FXML
  private Label tuesdayTasksLeft;
  @FXML
  private Pane tuesdayVbox;
  @FXML
  private Pane tuesdayContents;
  @FXML
  private Label wednesdayTitle;
  @FXML
  private ProgressBar wednesdayProgressBar;
  @FXML
  private Label wednesdayTasksLeft;
  @FXML
  private Pane wednesdayVbox;
  @FXML
  private Pane wednesdayContents;
  @FXML
  private Label thursdayTitle;
  @FXML
  private ProgressBar thursdayProgressBar;
  @FXML
  private Label thursdayTasksLeft;
  @FXML
  private Pane thursdayVbox;
  @FXML
  private Pane thursdayContents;
  @FXML
  private Label fridayTitle;
  @FXML
  private ProgressBar fridayProgressBar;
  @FXML
  private Label fridayTasksLeft;
  @FXML
  private Pane fridayVbox;
  @FXML
  private Pane fridayContents;
  @FXML
  private Label saturdayTitle;
  @FXML
  private ProgressBar saturdayProgressBar;
  @FXML
  private Label saturdayTasksLeft;
  @FXML
  private Pane saturdayVbox;
  @FXML
  private Pane saturdayContents;
  @FXML
  private Button newButton;
  @FXML
  private Button openButton;
  @FXML
  private Button saveAsButton;
  @FXML
  private Button saveButton;
  @FXML
  private Button themeButton;
  @FXML
  private VBox background;
  @FXML
  private HBox bottomBar;
  @FXML
  private Label maxTasks;
  @FXML
  private Label maxEvents;
  @FXML
  private Label totalTasks;
  @FXML
  private Label totalEvents;
  @FXML
  private Label tasksCompleted;
  @FXML
  private VBox menuBar;
  private GuiView view;
  private Stage primaryStage;
  private Week week;
  private SaveAsButtonHandler sbh;
  private SaveButtonHandler saveButtonHandler;
  private String currentFileName;
  private boolean readyToStart;
  private String theme;
  @FXML
  private Button layoutButton;

  /**
   * Creates a BujoController in default starting state
   */
  public BujoController() {
    this.view = new BujoGuiView(this);
    this.week = new Week("default", "My Week", 5, 5,
        new ArrayList<>(Arrays.asList(new Day(DayName.SUNDAY), new Day(DayName.MONDAY),
            new Day(DayName.TUESDAY), new Day(DayName.WEDNESDAY), new Day(DayName.THURSDAY),
            new Day(DayName.FRIDAY), new Day(DayName.SATURDAY))));
    this.sbh = new SaveAsButtonHandler(this.week, this);
    this.saveButtonHandler = new SaveButtonHandler(this.sbh, this);
    this.currentFileName = "";
    this.readyToStart = false;
    this.theme = "default";

  }

  /**
   * starts the application by setting and showing the given Stage
   *
   * @param stage the primary stage for this application, onto which
   *              the application scene can be set.
   *              Applications may create other stages, if needed, but they will not be
   *              primary stages.
   */
  @Override
  public void start(Stage stage) {
    this.view = new BujoGuiView(this);
    this.primaryStage = stage;
    try {
      primaryStage.setScene(this.view.load());
      setAllButtons();
      primaryStage.show();
    } catch (RuntimeException e) {
      throw new RuntimeException("Unable to load layout.");
    }
  }

  /**
   * initializes the application
   */
  @Override
  public void init() {
    try {
      Thread.sleep(2000);
    } catch (InterruptedException e) {
      //do nothing
    }
  }


  /**
   * sets the buttons and other functionalities
   */
  private void setAllButtons() {
    resetTheme();
    openButton.setOnAction(new OpenButtonHandler(primaryStage, this));
    saveAsButton.setOnAction(sbh);
    saveButton.setOnAction(this.saveButtonHandler);
    newButton.setOnAction(new NewWeekButtonHandler());
    taskButton.setOnAction(new CreateTaskButtonHandler(this));
    eventButton.setOnAction(new CreateEventButtonHandler(this));
    themeButton.setOnAction(new ThemeButtonHandler(new Stage(), this));
    name.setOnMouseClicked(new NameClickHandler(name, this));
    maxTasks.setOnMouseClicked(new ChangeMaxItemHandler(maxTasks, this));
    maxEvents.setOnMouseClicked(new ChangeMaxItemHandler(maxEvents, this));
    layoutButton.setOnAction(new LayoutButtonHandler(primaryStage, this));
    setMenuBar();
    setAllKeyboardShortcuts();
  }

  /**
   * sets the keyboard shortcuts
   */
  private void setAllKeyboardShortcuts() {
    setKeyboardShortcut(KeyCode.T, taskButton);
    setKeyboardShortcut(KeyCode.E, eventButton);
    setKeyboardShortcut(KeyCode.S, saveAsButton);
    setKeyboardShortcut(KeyCode.O, openButton);
    setKeyboardShortcut(KeyCode.N, newButton);
    setKeyboardShortcut(KeyCode.M, themeButton);
    this.primaryStage.getScene().getAccelerators().put(
        new KeyCodeCombination(KeyCode.S, KeyCombination.SHIFT_DOWN,
            KeyCombination.SHORTCUT_DOWN), () -> saveButton.fire());
  }

  /**
   * sets and assigns a keyboard shortcut
   *
   * @param code   the keycode to be assigned
   * @param button the button to be assigned a keycode
   */
  private void setKeyboardShortcut(KeyCode code, Button button) {
    this.primaryStage.getScene().getAccelerators().put(
        new KeyCodeCombination(code, KeyCombination.SHORTCUT_DOWN), () -> button.fire());
  }

  /**
   * sets the progress stats for the given Day
   *
   * @param day the Day to calculate progress stats for
   */
  @Override
  public void setProgress(Day day) {
    int planned = day.getTasks().size();
    int completed = 0;
    int remaining = 0;
    for (Task task : day.getTasks()) {
      if (task.getComplete()) {
        completed++;
      } else {
        remaining++;
      }
    }
    this.getProgressBar(day.getName()).setProgress(((double) completed) / planned);
    this.getTasksLeftLabel(day.getName()).setText("Tasks Left: " + remaining);
  }

  /**
   * getter for the TasksLeft label corresponding to the given day
   *
   * @param day the String value of a Day
   * @return the TasksLeft label for that Day
   */
  private Label getTasksLeftLabel(String day) {
    if (day.equalsIgnoreCase(DayName.SUNDAY.toString())) {
      return sundayTasksLeft;
    } else if (day.equalsIgnoreCase(DayName.MONDAY.toString())) {
      return mondayTasksLeft;
    } else if (day.equalsIgnoreCase(DayName.TUESDAY.toString())) {
      return tuesdayTasksLeft;
    } else if (day.equalsIgnoreCase(DayName.WEDNESDAY.toString())) {
      return wednesdayTasksLeft;
    } else if (day.equalsIgnoreCase(DayName.THURSDAY.toString())) {
      return thursdayTasksLeft;
    } else if (day.equalsIgnoreCase(DayName.FRIDAY.toString())) {
      return fridayTasksLeft;
    } else {
      return saturdayTasksLeft;
    }
  }

  /**
   * getter for the ProgressBar corresponding to the given day
   *
   * @param day the String value of a Day
   * @return the TasksLeft label for that Day
   */
  private ProgressBar getProgressBar(String day) {
    if (day.equalsIgnoreCase(DayName.SUNDAY.toString())) {
      return sundayProgressBar;
    } else if (day.equalsIgnoreCase(DayName.MONDAY.toString())) {
      return mondayProgressBar;
    } else if (day.equalsIgnoreCase(DayName.TUESDAY.toString())) {
      return tuesdayProgressBar;
    } else if (day.equalsIgnoreCase(DayName.WEDNESDAY.toString())) {
      return wednesdayProgressBar;
    } else if (day.equalsIgnoreCase(DayName.THURSDAY.toString())) {
      return thursdayProgressBar;
    } else if (day.equalsIgnoreCase(DayName.FRIDAY.toString())) {
      return fridayProgressBar;
    } else {
      return saturdayProgressBar;
    }
  }

  /**
   * stylizes the given labels with the given font and color
   *
   * @param font   the font to set the text to
   * @param color  the color to set the text to
   * @param labels the labels to be stylized
   */
  private void styleLabels(Font font, Color color, Label... labels) {
    for (Label l : labels) {
      l.setFont(font);
      l.setTextFill(color);
    }
  }

  /**
   * stylizes the given buttons with the given font and color
   *
   * @param font      the font to set the text to
   * @param textColor the color to set the text to
   * @param buttons   the buttons to be stylized
   */
  private void styleButtons(Font font, Color textColor, Button... buttons) {
    for (Button b : buttons) {
      b.setFont(font);
      b.setTextFill(textColor);
    }
  }

  /**
   * stylizes the given Panes with the given color
   *
   * @param color the color to set the background to
   */
  private void stylePanes(Color color, Pane... boxes) {
    for (Pane b : boxes) {
      b.setBackground(Background.fill(color));
    }
  }

  /**
   * sets the theme based on the given string
   *
   * @param theme the string representation of the theme
   */
  @Override
  public void setTheme(String theme) {
    this.week.setTheme(theme);
    this.setWeek(this.week);
    saveButtonHandler.setWeek(this.week);
    if (theme.equals(Theme.SREE.toString())) {
      setSreeTheme();
    } else if (theme.equals(Theme.VITHYA.toString())) {
      setVithyaTheme();
      primaryStage.show();
    } else if (theme.equals(Theme.GRACE.toString())) {
      setGraceTheme();
      primaryStage.show();
    } else if (theme.equals(Theme.DEFAULT.toString())) {
      resetTheme();
      primaryStage.show();
    }
  }

  /**
   * resets the theme to default
   */
  private void resetTheme() {
    Font font = Font.font("Regular", FontWeight.NORMAL, 13);
    name.setBackground(Background.EMPTY);
    styleLabels(font, Color.BLACK, name, sundayTitle, mondayTitle, tuesdayTitle, wednesdayTitle,
        thursdayTitle, fridayTitle, saturdayTitle, totalTasks, totalEvents, tasksCompleted,
        saturdayTasksLeft, mondayTasksLeft, tuesdayTasksLeft, wednesdayTasksLeft,
        thursdayTasksLeft, fridayTasksLeft, saturdayTasksLeft, sundayTasksLeft, maxTasks,
        maxEvents);

    styleButtons(font, Color.rgb(255, 255, 255), newButton,

        openButton, saveAsButton, themeButton, taskButton, eventButton, saveButton, layoutButton);
    resetGraphics(name, sundayTitle, mondayTitle, tuesdayTitle, wednesdayTitle,
        thursdayTitle, fridayTitle, saturdayTitle, sundayTasksLeft, mondayTasksLeft,
        tuesdayTasksLeft, wednesdayTasksLeft, thursdayTasksLeft, fridayTasksLeft,
        saturdayTasksLeft, totalTasks, totalEvents, tasksCompleted, maxTasks, maxEvents,
        newButton, openButton, saveAsButton, themeButton, taskButton,
        eventButton, saveButton, layoutButton);
  }

  /**
   * reset the given Labeled to a new ImageView
   *
   * @param labeled to reset
   */
  public void resetGraphics(Labeled... labeled) {
    for (Labeled l : labeled) {
      l.setGraphic(new ImageView());
    }


    Background pbc = Background.EMPTY;
    styleProgressBars(pbc, sundayProgressBar, mondayProgressBar, tuesdayProgressBar,
        wednesdayProgressBar, thursdayProgressBar, fridayProgressBar, saturdayProgressBar);

    newButton.setStyle("-fx-background-color: rgb(0,0,0);");
    openButton.setStyle("-fx-background-color: rgb(0,0,0)");
    saveAsButton.setStyle("-fx-background-color: rgb(0,0,0)");
    themeButton.setStyle("-fx-background-color: rgb(0,0,0)");
    taskButton.setStyle("-fx-background-color: rgb(0,0,0)");
    eventButton.setStyle("-fx-background-color: rgb(0,0,0)");
    saveButton.setStyle("-fx-background-color: rgb(0,0,0)");
    layoutButton.setStyle("-fx-background-color: rgb(0,0,0)");
    background.setStyle("-fx-background-color: rgb(233,234,226)");
    bottomBar.setStyle("-fx-background-color: rgb(233,234,226)");


    stylePanes(Color.rgb(185, 205, 190), sundayVbox, mondayVbox,
        tuesdayVbox, wednesdayVbox, thursdayVbox, fridayVbox, saturdayVbox);
  }

  private void styleProgressBars(Background b, ProgressBar... bars) {
    for (ProgressBar pb : bars) {
      pb.setBlendMode(BlendMode.DARKEN);
      pb.setBackground(b);
    }

  }

  /**
   * sets the theme to Sree's theme
   */
  @FXML
  private void setSreeTheme() {
    resetTheme();
    newButton.setStyle("-fx-background-color: #d8e2dc");
    openButton.setStyle("-fx-background-color: #ffe5d9");
    saveAsButton.setStyle("-fx-background-color: #ffcad4");
    themeButton.setStyle("-fx-background-color: #f4acb7");
    taskButton.setStyle("-fx-background-color: #FFE5D9");
    eventButton.setStyle("-fx-background-color: #FDDEDE");
    saveButton.setStyle("-fx-background-color: #ffcad4");
    layoutButton.setStyle("-fx-background-color: #d8e2dc");

    Color dark = Color.valueOf("#294B43");
    Font font = Font.font("American Typewriter", FontWeight.LIGHT, 12);
    styleLabels(font, dark, name, sundayTitle, mondayTitle, tuesdayTitle, wednesdayTitle,
        thursdayTitle, fridayTitle, saturdayTitle, totalTasks, totalEvents, tasksCompleted,
        saturdayTasksLeft, mondayTasksLeft, tuesdayTasksLeft, wednesdayTasksLeft,
        thursdayTasksLeft, fridayTasksLeft, saturdayTasksLeft, sundayTasksLeft, maxTasks,
        maxEvents);
    styleButtons(font, dark, newButton, openButton, saveButton,
        saveAsButton, themeButton, taskButton, eventButton, layoutButton);
    name.setFont(Font.font("American Typewriter", FontWeight.EXTRA_BOLD, 40));

    name.setBackground(Background.fill(Paint.valueOf("#ffe5d9")));
    setLabelIcon("src/main/resources/icons/lollipop.png", name);
    name.getGraphic().setScaleX(2.0);
    name.getGraphic().setScaleY(2.0);
    name.setText(" " + name.getText());

    sundayVbox.setBackground(Background.fill(Paint.valueOf("#fae1dd")));
    mondayVbox.setBackground(Background.fill(Paint.valueOf("#f8edeb")));
    tuesdayVbox.setBackground(Background.fill(Paint.valueOf("#e8e8e4")));
    wednesdayVbox.setBackground(Background.fill(Paint.valueOf("#d8e2dc")));
    thursdayVbox.setBackground(Background.fill(Paint.valueOf("#ece4db")));
    fridayVbox.setBackground(Background.fill(Paint.valueOf("#ffe5d9")));
    saturdayVbox.setBackground(Background.fill(Paint.valueOf("#ffd7ba")));
    background.setBackground(Background.fill(Paint.valueOf("#fff2ed")));
    bottomBar.setBackground(Background.fill(Paint.valueOf("#ffe5d9")));
  }

  /**
   * sets the view to Grace's theme
   */
  private void setGraceTheme() {
    resetTheme();
    styleLabels(Font.font("Bradley Hand", FontWeight.NORMAL, 60), Color.DARKBLUE, name);
    File surferPenguin = new File("src/main/resources/icons/clubpenguin/surferPenguin.png");
    ImageView surferView = new ImageView(surferPenguin.toURI().toString());
    surferView.setFitHeight(50);
    surferView.setPreserveRatio(true);
    name.setGraphic(surferView);

    setLabelIcon("src/main/resources/icons/clubpenguin/redPuffle.jpg", sundayTitle);
    setLabelIcon("src/main/resources/icons/clubpenguin/orangePuffle.jpg", mondayTitle);
    setLabelIcon("src/main/resources/icons/clubpenguin/yellowPuffle.jpg", tuesdayTitle);
    setLabelIcon("src/main/resources/icons/clubpenguin/greenPuffle.jpg", wednesdayTitle);
    setLabelIcon("src/main/resources/icons/clubpenguin/bluePuffle.jpg", thursdayTitle);
    setLabelIcon("src/main/resources/icons/clubpenguin/purplePuffle.jpg", fridayTitle);
    setLabelIcon("src/main/resources/icons/clubpenguin/pinkPuffle.jpg", saturdayTitle);


    styleButtons(Font.font("Bradley Hand"), Color.DARKBLUE, newButton, openButton,
        saveButton, saveAsButton, themeButton, taskButton, eventButton, layoutButton);

    name.setBackground(Background.fill(Paint.valueOf("#F5F5F5")));
    newButton.setStyle("-fx-background-color: rgba(252,69,126,0.54); ");
    openButton.setStyle("-fx-background-color: rgba(252,151,69,0.65); ");
    saveButton.setStyle("-fx-background-color: rgba(232,230,83,0.7); ");
    saveAsButton.setStyle("-fx-background-color: rgba(13,107,31,0.51); ");
    themeButton.setStyle("-fx-background-color: rgba(19,197,197,0.49); ");
    layoutButton.setStyle("-fx-background-color: rgba(136,70,238,0.35); ");

    taskButton.setStyle("-fx-background-color: rgba(177,223,250,0.55); ");
    eventButton.setStyle("-fx-background-color: rgba(250,201,177,0.55); ");


    styleLabels(Font.font("Bradley Hand", FontWeight.NORMAL, 12), Color.DARKBLUE, sundayTitle,
        mondayTitle, tuesdayTitle, wednesdayTitle, thursdayTitle, fridayTitle, saturdayTitle,
        maxTasks, maxEvents, totalTasks, totalEvents, tasksCompleted);
    styleLabels(Font.font("Bradley Hand", FontWeight.NORMAL, 10), Color.DARKBLUE,
        sundayTasksLeft, mondayTasksLeft, tuesdayTasksLeft, wednesdayTasksLeft, thursdayTasksLeft,
        fridayTasksLeft, saturdayTasksLeft);

    sundayVbox.setBackground(Background.fill(Color.LIGHTCORAL));
    mondayVbox.setBackground(Background.fill(Color.LIGHTSALMON));
    tuesdayVbox.setBackground(Background.fill(Color.LIGHTGOLDENRODYELLOW));
    wednesdayVbox.setBackground(Background.fill(Color.LIGHTGREEN));
    thursdayVbox.setBackground(Background.fill(Color.LIGHTBLUE));
    fridayVbox.setBackground(Background.fill(Color.LAVENDER));
    saturdayVbox.setBackground(Background.fill(Color.LIGHTPINK));
    background.setBackground(Background.fill(Paint.valueOf("#F5F5F5")));
    bottomBar.setBackground(Background.fill(Paint.valueOf("#F5F5F5")));

  }

  /**
   * set an icon for a label
   *
   * @param imgfilePath image to set
   * @param label       label to set the image to
   */
  public void setLabelIcon(String imgfilePath, Label label) {
    File imgFile = new File(imgfilePath);
    ImageView imgView = new ImageView(imgFile.toURI().toString());
    imgView.setFitHeight(15);
    imgView.setPreserveRatio(true);
    label.setGraphic(imgView);
  }

  /**
   * sets the view to Vithya's theme
   */
  private void setVithyaTheme() {
    resetTheme();
    this.styleLabels(Font.font("Comic Sans MS", FontWeight.NORMAL, 64), Color.BLACK,
        name);
    this.styleButtons(Font.font("Comic Sans MS"), Color.YELLOW, newButton, openButton,
        saveButton, saveAsButton, themeButton, taskButton, eventButton, layoutButton);
    this.styleLabels(Font.font("Comic Sans MS"), Color.YELLOW, sundayTitle, mondayTitle,
        tuesdayTitle, wednesdayTitle, thursdayTitle, fridayTitle, saturdayTitle);
    name.setBackground(Background.fill(Paint.valueOf("#F5F5F5")));
    newButton.setStyle("-fx-background-color: rgb(3, 223, 252); ");
    openButton.setStyle("-fx-background-color: rgb(3, 198, 252); ");
    saveButton.setStyle("-fx-background-color: rgb(3, 186, 252); ");
    saveAsButton.setStyle("-fx-background-color: rgb(3, 173, 252); ");
    themeButton.setStyle("-fx-background-color: rgb(3, 148, 252); ");
    taskButton.setStyle("-fx-background-color: rgb(3, 107, 252); ");
    eventButton.setStyle("-fx-background-color: rgb(3, 65, 252); ");
    layoutButton.setStyle("-fx-background-color: rgb(0,21,255); ");

    sundayVbox.setBackground(Background.fill(rgb(208, 164, 245)));
    mondayVbox.setBackground(Background.fill(rgb(190, 148, 224)));
    tuesdayVbox.setBackground(Background.fill(rgb(158, 123, 186)));
    wednesdayVbox.setBackground(Background.fill(rgb(134, 103, 158)));
    thursdayVbox.setBackground(Background.fill(rgb(108, 83, 128)));
    fridayVbox.setBackground(Background.fill(rgb(96, 67, 122)));
    saturdayVbox.setBackground(Background.fill(rgb(90, 60, 100)));
    background.setBackground(Background.fill(Paint.valueOf("#F5F5F5")));
    bottomBar.setBackground(Background.fill(Paint.valueOf("#F5F5F5")));

    setLabelIcon("src/main/resources/icons/emoji/pensive.png", sundayTitle);
    setLabelIcon("src/main/resources/icons/emoji/sob.png", mondayTitle);
    setLabelIcon("src/main/resources/icons/emoji/slant.png", tuesdayTitle);
    setLabelIcon("src/main/resources/icons/emoji/neutral.png", wednesdayTitle);
    setLabelIcon("src/main/resources/icons/emoji/blush.png", thursdayTitle);
    setLabelIcon("src/main/resources/icons/emoji/squint.png", fridayTitle);
    setLabelIcon("src/main/resources/icons/emoji/stareyes.png", saturdayTitle);
  }

  /**
   * loads the contents from the .bujo file with the given String name
   *
   * @param fileName the name of the .bujo file
   */
  @FXML
  @Override
  public void loadFromExisting(String fileName) {
    clearWeek(sundayContents, mondayContents, tuesdayContents, wednesdayContents,
        thursdayContents, fridayContents, saturdayContents);
    try {
      ObjectMapper mapper = new ObjectMapper();
      WeekJson w = mapper.readValue(new BujoReader().read(new Scanner(
          new File("src/main/resources/bujoFiles/" + fileName))), WeekJson.class);
      this.week = new Week(w.theme(), w.name(), w.maxDailyEvents(), w.maxDailyTasks(), w.days());
      name.setText(week.getName());
      maxEvents.setText("Max Tasks: " + week.getMaxDailyEvents());
      maxTasks.setText("Max Events: " + week.getMaxDailyTasks());
      setTheme(w.theme());
      for (Day d : week.getDays()) {
        loadEventButtons(d, d.getEvents());
        loadTaskButtons(d, d.getTasks());
        this.setProgress(d);
      }
      this.setWeek(this.week);
      this.setTotalEventsView(week.getTotalEvents());
      this.setTotalTasksView(week.getTotalTasks());
      this.setPercentTasksCompleted(week.getDays(), week.getTotalTasks());
    } catch (FileNotFoundException | JsonProcessingException e) {
      throw new RuntimeException("couldn't read the file properly");
    }
  }


  /**
   * loads all the tasks from the opened file
   *
   * @param day   day to add the task buttons to
   * @param tasks the tasks that need to be added
   */
  private void loadTaskButtons(Day day, List<Task> tasks) {
    for (Task t : tasks) {
      Button b = new Button(t.getName());
      b.setBackground(Background.fill(Paint.valueOf("#b1dffa")));
      b.setOnAction(new ShowTaskMiniviewHandler(t, day.getName(), this, this.week, b));
      getPane(day.getName()).getChildren().add(b);
    }
  }

  /**
   * loads all the events from the opened file
   *
   * @param day    day to add the event buttons to
   * @param events the events that need to be added as buttons
   */
  private void loadEventButtons(Day day, List<Event> events) {
    for (Event e : events) {
      Button b = new Button(e.getName());
      b.setBackground(Background.fill(Paint.valueOf("#fac9b1")));
      b.setOnAction(new ShowEventMiniviewHandler(e, day.getName(), this, this.week, b));
      getPane(day.getName()).getChildren().add(b);
    }
  }

  /**
   * Adds the given button to the given String's corresponding day's view
   *
   * @param day    the day to be added to
   * @param button the button to be added
   */
  @Override
  public void addItemView(String day, Button button) {
    getPane(day).getChildren().add(button);
  }

  /**
   * returns the Pane associated with the given String representation of a day
   *
   * @param day the String representation of a Day
   * @return the corresponding Pane
   */
  private Pane getPane(String day) {
    if (day.equalsIgnoreCase(DayName.SUNDAY.toString())) {
      return sundayContents;
    } else if (day.equalsIgnoreCase(DayName.MONDAY.toString())) {
      return mondayContents;
    } else if (day.equalsIgnoreCase(DayName.TUESDAY.toString())) {
      return tuesdayContents;
    } else if (day.equalsIgnoreCase(DayName.WEDNESDAY.toString())) {
      return wednesdayContents;
    } else if (day.equalsIgnoreCase(DayName.THURSDAY.toString())) {
      return thursdayContents;
    } else if (day.equalsIgnoreCase(DayName.FRIDAY.toString())) {
      return fridayContents;
    } else {
      return saturdayContents;
    }
  }

  /**
   * removes the given button from the Day corresponding to the given String
   *
   * @param b   the button to be removed
   * @param day the String representation of a Day
   */
  @FXML
  @Override
  public void removeButton(Button b, String day) {
    getPane(day).getChildren().remove(b);
  }

  /**
   * clears the entire week view
   */
  @FXML
  private void clearWeek(Pane... boxes) {
    for (Pane p : boxes) {
      p.getChildren().removeAll(p.getChildren());
    }
  }

  /**
   * sets the number of total tasks so far in the Week view summary
   *
   * @param total the current number of tasks
   */
  @FXML
  @Override
  public void setTotalTasksView(int total) {
    String text = this.totalTasks.getText();
    this.totalTasks.setText(text.substring(0, text.indexOf(":") + 1) + " " + total);
  }

  /**
   * sets the number of total events so far in the Week view summary
   *
   * @param total the current number of events
   */
  @FXML
  @Override
  public void setTotalEventsView(int total) {
    String text = this.totalEvents.getText();
    this.totalEvents.setText(text.substring(0, text.indexOf(":") + 1) + " " + total);
  }

  /**
   * resets the progress bars
   *
   * @param bars all the progress bars
   */
  @FXML
  public void resetProgressBars(ProgressBar... bars) {
    for (ProgressBar pb : bars) {
      pb.setProgress(0);
    }
  }

  /**
   * sets the number of total tasks completed so far in the Week view summary
   * sets the percentage of total tasks completed so far in the Week view summary
   *
   * @param days  the days in the week
   * @param total the total tasks in the week
   */
  @FXML
  @Override
  public void setPercentTasksCompleted(List<Day> days, double total) {
    int completed = 0;
    for (Day d : days) {
      for (Task t : d.getTasks()) {
        if (t.getComplete()) {
          completed++;
        }
      }
      int percent = (int) ((completed / total) * 100);
      String text = tasksCompleted.getText();
      tasksCompleted.setText(
          text.substring(0, text.indexOf(":") + 1) + " " + percent + "%");
    }
  }

  /**
   * sets the updated week
   *
   * @param w the week to set the save/save as handlers to
   */
  @Override
  public void setWeek(Week w) {
    this.saveButtonHandler.setWeek(w);
    this.sbh.setWeek(w);
  }

  /**
   * getter for this BujoController's week field
   *
   * @return this BujoController's week field
   */
  @Override
  public Week getWeek() {
    return this.week;
  }

  /**
   * getter for this BujoController's current file path
   *
   * @return this BujoController's current file path
   */
  @Override
  public String getCurrentFileName() {
    return currentFileName;
  }

  /**
   * sets the current file
   *
   * @param file the path to set the current file to
   */
  public void setCurrentFileName(String file) {
    this.currentFileName = file;
    this.saveButtonHandler.setFile(file);
    saveButton.setOnAction(saveButtonHandler);
  }

  /**
   * set the items in the menu bar
   */
  @FXML
  private void setMenuBar() {
    MenuBar mb = new MenuBar();
    mb.getMenus().add(new Menu("Files"));
    mb.getMenus().get(0).getItems().add(new MenuItem("New (cmd/ctrl + N)"));
    mb.getMenus().get(0).getItems().get(0).setOnAction(e -> newButton.fire());
    mb.getMenus().get(0).getItems().add(new MenuItem("Open (cmd/ctrl + O)"));
    mb.getMenus().get(0).getItems().get(1).setOnAction(e -> openButton.fire());
    mb.getMenus().get(0).getItems().add(new MenuItem("Save (cmd/ctrl + Shift + S)"));
    mb.getMenus().get(0).getItems().get(2).setOnAction(e -> saveButton.fire());
    mb.getMenus().get(0).getItems().add(new MenuItem("SaveAs (cmd/ctrl + S)"));
    mb.getMenus().get(0).getItems().get(3).setOnAction(e -> saveAsButton.fire());

    mb.getMenus().add(new Menu("Appearance"));
    mb.getMenus().get(1).getItems().add(new MenuItem("Theme (cmd/ctrl + M)"));
    mb.getMenus().get(1).getItems().get(0).setOnAction(e -> themeButton.fire());
    mb.getMenus().get(1).getItems().add(new MenuItem("Layout (cmd/ctrl + L)"));
    mb.getMenus().get(1).getItems().get(1).setOnAction(e -> layoutButton.fire());

    mb.getMenus().add(new Menu("Actions"));
    mb.getMenus().get(2).getItems().add(new MenuItem("new Event (cmd/ctrl + E)"));
    mb.getMenus().get(2).getItems().get(0).setOnAction(e -> eventButton.fire());
    mb.getMenus().get(2).getItems().add(new MenuItem("new Task (cmd/ctrl + T)"));
    mb.getMenus().get(2).getItems().get(1).setOnAction(e -> taskButton.fire());
    mb.setBlendMode(BlendMode.DARKEN);
    menuBar.getChildren().add(mb);
  }


  /**
   * sets the theme
   *
   * @param theme to be set
   */
  public void setThemeField(String theme) {
    this.theme = theme;
  }
}
