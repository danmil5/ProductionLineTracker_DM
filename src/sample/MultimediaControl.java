package sample;

/**
 * Daniel Miller
 * The MultimediaControl class contains method headers that are universally used among all
 * media devices. Because of this, MultimediaControl is implemented by the two types of
 * media-related products: AudioPlayer and MoviePlayer.
 */
public interface MultimediaControl {
  void play();

  void stop();

  void previous();

  void next();
}
