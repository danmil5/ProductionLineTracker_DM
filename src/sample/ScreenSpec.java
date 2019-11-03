package sample;

/**
 * Necessary functions to have in order to display screen details of a new videoplayer object
 */
public interface ScreenSpec {
    public String getResolution();
    public int getRefreshRate();
    public int getResponseTime();
}
