package sample;

/** Create list of available item types for the user to choose from */
public enum ItemType {
  AUDIO("AU"),
  VISUAL("VI"),
  AUDIO_MOBILE("AM"),
  VISUAL_MOBILE("VM");

  private String code;

  ItemType(String c) {
    this.code = c;
  }

  public String getCode() {
    return code;
  }
}
