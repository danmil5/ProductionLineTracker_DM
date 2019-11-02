package sample;

public class AudioPlayer extends Product implements MultimediaControl {
  String supportedAudioFormats;
  String supportedPlaylistFormats;

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
