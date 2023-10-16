package cs3500.pa05.model;

/**
 * represents the available themes for the bujo
 */
public enum Theme {
  SREE,
  VITHYA,
  GRACE,
  DEFAULT;

  /**
   * override enum toString
   *
   * @return a String representation of the Theme
   */
  @Override
  public String toString() {
    switch (this) {
      case SREE:
        return "Sree's Theme";
      case VITHYA:
        return "Vithya's Theme";
      case GRACE:
        return "Grace's Theme";
      default:
        return "default";
    }
  }
}
