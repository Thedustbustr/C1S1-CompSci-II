package me.thedustbuster;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {
  private static Scene scene;

  /**
   * Stars javaFX 
   * @param stage
   * @throws IOException
   */
  @Override
  public void start(Stage stage) throws IOException {
    scene = new Scene(loadFXML("connect"), 1280, 720);
    stage.setScene(scene);
    stage.show();
  }

  
  /** 
   * Sets root to loaded fxml
   * @param fxml Path to fxml
   * @throws IOException
   */
  static void setRoot(String fxml) throws IOException {
    scene.setRoot(loadFXML(fxml));
  }

  
  /** 
   * Loads fmxl
   * @param fxml Path to fmxl
   * @return Parent Fmxl data
   * @throws IOException
   */
  private static Parent loadFXML(String fxml) throws IOException {
    FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
    return fxmlLoader.load();
  }

  
  /** 
   * Main method
   * @param args
   */
  public static void main(String[] args) {
    launch();
  }
}
