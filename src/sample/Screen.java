package sample;

/**
 * Screen object to be included in a new videoplayer object
 */
public class Screen implements ScreenSpec {
    String resolution;
    int refreshRate;
    int responseTime;

    Screen(String resolution, int refreshRate, int responseTime) {
        this.resolution = resolution;
        this.refreshRate = refreshRate;
        this.responseTime = responseTime;
    }

    @Override
    public String toString() {
        return "Resolution: " + resolution + "\nRefresh Rate: " + refreshRate + "\nResponse Time: " + responseTime;
    }

    @Override
    public String getResolution() {
        return null;
    }

    @Override
    public int getRefreshRate() {
        return 0;
    }

    @Override
    public int getResponseTime() {
        return 0;
    }
}
