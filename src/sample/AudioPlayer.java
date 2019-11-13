package sample;

/**
 * Daniel Miller
 * The AudioPlayer class is an extension of the Product class. It serves all of the intended
 * functions that the product class serves, and also has extra implementations such as extra and
 * overridden methods to specifically support audio-related functions.
 */
public class AudioPlayer extends Product implements MultimediaControl {
  private String supportedAudioFormats;
  private String supportedPlaylistFormats;

  AudioPlayer(String n, String m, String supportedAudioFormats, String supportedPlaylistFormats) {
    super(n, m, "AUDIO");
    this.supportedAudioFormats = supportedAudioFormats;
    this.supportedPlaylistFormats = supportedPlaylistFormats;
  }

  @Override
  public String toString() {
    return super.toString()
        + "\nSupported Audio Formats: "
        + supportedAudioFormats
        + "\nSupported Playlist Formats: "
        + supportedPlaylistFormats;
  }

  @Override
  public void play() {
    System.out.println("Playing song");
  }

  @Override
  public void stop() {
    System.out.println("Stopping song");
  }

  @Override
  public void previous() {
    System.out.println("Previous song");
  }

  @Override
  public void next() {
    System.out.println("Next song");
  }
}
