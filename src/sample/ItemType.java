package sample;

public enum ItemType {

    AUDIO("AU"),
    VISUAL("VI"),
    AUDIO_MOBILE("AM"),
    VISUAL_MOBILE("VM");

    public String code;

    ItemType(String c) {
        this.code = c;
    }
}