package sample;

/**
 * Daniel Miller
 * ScreenSpec contains methods that get certain specifications that should be found in any and all
 * screens. It is implemented by the class Screen to ensure that all objects created from class
 * Screen have the necessary fields that corresponding to the methods found in ScreenSpec.
 */
public interface ScreenSpec {
    String getResolution();
    int getRefreshRate();
    int getResponseTime();
}
