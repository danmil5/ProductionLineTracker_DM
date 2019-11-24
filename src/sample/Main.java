package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @author Daniel Miller
 *     <p>The Main method gives the application a pointer to start at, which leads to the start
 *     method used to set and open to initial scene.
 */
public class Main extends Application {

  /**
   * @param primaryStage start method only serves to call primaryStage and set the scene for the
   *     project GUI
   */
  @Override
  public void start(Stage primaryStage) throws Exception {
    Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
    primaryStage.setTitle("Production Line Tracker");
    primaryStage.setScene(new Scene(root, 800, 400));
    primaryStage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }
}
