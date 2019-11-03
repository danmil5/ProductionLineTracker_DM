package sample;

/**
 * Movieplayer object extends from product to specfically support video-related functions
 */
public class MoviePlayer extends Product implements MultimediaControl {
  Screen screen;
  MonitorType monitorType;

  MoviePlayer(String n, String m, Screen s, MonitorType mt) {
    super(n, m, "VISUAL");
    this.screen = s;
    this.monitorType = mt;
  }

  @Override
  public String toString() {
    return super.toString() + "\nScreen: " + screen + "\nMonitor Type: " + monitorType;
  }

  @Override
  public void play() {
    System.out.println("Playing video");
  }

  @Override
  public void stop() {
    System.out.println("Stopping video");
  }

  @Override
  public void previous() {
    System.out.println("Previous video");
  }

  @Override
  public void next() {
    System.out.println("Next video");
  }
}
