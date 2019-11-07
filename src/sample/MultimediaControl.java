package sample;

/**
 * Common controls that each product can do, in the form of an interface which each product should
 * implement
 */
public interface MultimediaControl {
  void play();

  void stop();

  void previous();

  void next();
}
