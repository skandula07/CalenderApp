package cs3500.pa05.model;

/**
 * represents an Item on the schedule
 */
public abstract class Item {

  protected String name;

  protected String description;

  /**
   * constructs an Item with a description
   *
   * @param name name of the Item
   *
   * @param description description of the Item
   */
  public Item(String name, String description) {
    validateName(name);
    this.description = description;
  }

  /**
   * constructs an Item without a description
   *
   * @param name name of the Item
   */
  public Item(String name) {
    validateName(name);
    this.description = "";
  }

  /**
   * checks if the name is empty or not
   *
   * @param name to be validated
   */
  private void validateName(String name) {
    if (name.trim().isEmpty()) {
      throw new IllegalStateException("must have a name");
    } else {
      this.name = name;
    }
  }

  /**
   * getter for the name of the Item
   *
   * @return the name of the Item
   */
  public String getName() {
    return name;
  }

  /**
   * getter for this Item's description field
   *
   * @return the description field
   */
  public String getDescription() {
    return description;
  }
}