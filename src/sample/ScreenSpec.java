package sample;

/**
 * Necessary functions to have in order to display screen details of a new videoplayer object
 */
public interface ScreenSpec {
    String getResolution();
    int getRefreshRate();
    int getResponseTime();
}
