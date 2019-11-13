package sample;

/**
 * Daniel Miller
 * The MoviePlayer class is an extension of the Product class. It serves all of the intended
 * functions that the product class serves, and also has extra implementations such as extra and
 * overridden methods to specifically support movie-related functions.
 */
public class MoviePlayer extends Product implements MultimediaControl {
  private Screen screen;
  private MonitorType monitorType;

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
