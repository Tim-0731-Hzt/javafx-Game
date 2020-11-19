package unsw.dungeon;

import java.io.File;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;

public class MenuController {

  private Stage window;

  @FXML
  private Button playButton;
  @FXML
  private Button instructioButton;
  @FXML
  private Button exitButton;
  @FXML
  private Pane pane;

  @FXML
  private void handlePlayButton(ActionEvent event) throws Exception {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("MapSelection.fxml"));
    Parent root = loader.load();
    Scene scene = new Scene(root);
    root.requestFocus();
    window = (Stage) ((Node) event.getSource()).getScene().getWindow();
    window.setScene(scene);
    window.show();

  }

  @FXML
  private void handleInstructionButton(ActionEvent event) throws Exception {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("Instruction.fxml"));
    Parent root = loader.load();
    Scene scene = new Scene(root);
    root.requestFocus();
    window = (Stage) ((Node) event.getSource()).getScene().getWindow();
    window.setScene(scene);
    window.show();
  }

  @FXML
  private void handleExitButton(ActionEvent event) {
    Platform.exit();
    System.exit(0);
  }

  @FXML
  void initialize() {

    BackgroundImage backgroundimage = new BackgroundImage(
        new Image((new File("images/background.jpg")).toURI().toString()), BackgroundRepeat.NO_REPEAT,
        BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);

    // create Background
    Background background = new Background(backgroundimage);

    // set background
    pane.setBackground(background);

  }
}