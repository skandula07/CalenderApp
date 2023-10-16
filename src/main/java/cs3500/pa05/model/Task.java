package cs3500.pa05.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

/**
 * Represents a Task
 */
public class Task extends Item {
  private boolean complete;

  /**
   * constructs a task with a description
   *
   * @param name name of the Task
   *
   * @param des description of the Task
   *
   * @param bool whether the task is complete
   */
  @JsonCreator
  public Task(@JsonProperty("task-name") String name, @JsonProperty("description")String des,
              @JsonProperty("completion-status")boolean bool) {
    super(name, des);
    this.setComplete(bool);
  }

  /**
   * constructs a task without a description
   *
   * @param name name of the Task
   *
   * @param bool whether the task is complete
   */
  public Task(String name, boolean bool) {
    super(name);
    this.setComplete(bool);
  }

  /**
   * sets the completion status of the task
   *
   * @param c the completion status to be set
   */
  private void setComplete(boolean c) {
    if (Objects.isNull(c)) {
      this.complete = false;
    } else {
      this.complete = c;
    }
  }

  /**
   * getter for this task's complete field
   *
   * @return the completion status
   */
  public boolean getComplete() {
    return complete;
  }
}

