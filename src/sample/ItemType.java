package sample;

/**
 * @author Daniel Miller
 *     <p>The ItemType Enum serves to retroactively create multiple objects of type ItemType to be
 *     used when creating, defining and using objects of type Product and Product's subclasses. The
 *     ItemType type object that is assigned to a given product is indicative of whether or not that
 *     product is designed to be used for audio, visual, audio-mobile, or visual-mobile purposes.
 *     The code field is used to indicate the item type of a product when generating its serial
 *     number.
 */
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
