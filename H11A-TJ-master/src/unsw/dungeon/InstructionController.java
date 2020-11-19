package unsw.dungeon;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Node;
import javafx.geometry.Insets;

public class InstructionController {
  private Stage window;
  @FXML
  private Button backButton;
  @FXML
  private Pane pane;
  @FXML
  private GridPane header;
  @FXML
  private GridPane content;

  private ArrayList<String> names;
  private ArrayList<String> imgs;
  private ArrayList<String> descriptions;

  /**
   * prepare the data that will be displayed on the page
   */
  public InstructionController() {
    names = new ArrayList<>();
    imgs = new ArrayList<>();
    descriptions = new ArrayList<>();
    names.addAll(Arrays.asList("Player", "Wall", "Exit", "Treasure", "Door", "Key", "Boulder", "Floor switch", "Portal",
        "Enemy", "Sword", "Invincibility potion", "Hound", "Gnome", "Bubbly"));
    descriptions.add(
        "Can be moved up, down, left, and right into adjacent squares, provided another entity doesn't stop them (e.g. a wall).");
    descriptions.add("Blocks the movement of the player, enemies and boulders.");
    descriptions.add("If the player goes through it the puzzle is complete.");
    descriptions.add("Can be collected by the player.");
    descriptions.add(
        "Exists in conjunction with a single key that can open it. If the player holds the key, they can open the door by moving through it. Once open it remains so.");
    descriptions.add(
        "Can be picked up by the player when they move into the square containing it. The player can carry only one key at a time, and only one door has a lock that fits the key. It disappears once it is used to open its corresponding door.");
    descriptions.add(
        "Acts like a wall in most cases. The only difference being that it can be pushed by the player into adjacent squares. The player is only strong enough to push one boulder at a time.");
    descriptions.add(
        "Switches behave like empty squares, so other entities can appear on top of them. When a boulder is pushed onto a floor switch, it is triggered. Pushing a boulder off the floor switch untriggers it.");
    descriptions.add("Teleports entities to a corresponding portal.");
    descriptions.add(
        "Constantly moves toward the player, stopping if it cannot move any closer. The player dies upon collision with an enemy.");
    descriptions.add(
        "This can be picked up the player and used to kill enemies. Only one sword can be carried at once. Each sword is only capable of 5 hits and disappears after that. One hit of the sword is sufficient to destroy any enemy.");
    descriptions.add(
        "If the player picks this up they become invincible to enemies. Colliding with an enemy should result in their immediate destruction. Because of this, all enemies will run away from the player when they are invincible. The effect of the potion only lasts a limited time.");
    descriptions.add(
        "Constantly moves toward the player, stopping if it cannot move any closer. The player lose a life upon collision with an enemy. Its movement speed is twice as the Enemy");
    descriptions.add(
        "When the player encounters a Gnome, it will bless the player and give the player one more life. The Gnome will disappear afterwards.");
    descriptions.add(
        "If the player picks this up they become poisoned. After 20 seconds, the player will lose one life because of the poison and the poison effect will disappear.");
    imgs.add("human_new");
    imgs.add("brick_brown_0");
    imgs.add("exit");
    imgs.add("gold_pile");
    imgs.add("closed_door");
    imgs.add("key");
    imgs.add("boulder");
    imgs.add("pressure_plate");
    imgs.add("portal");
    imgs.add("deep_elf_master_archer");
    imgs.add("greatsword_1_new");
    imgs.add("brilliant_blue_new");
    imgs.add("hound");
    imgs.add("gnome");
    imgs.add("bubbly");
  }

  @FXML
  private void handleBackButton(ActionEvent event) throws Exception {
    FXMLLoader menuLoader = new FXMLLoader(getClass().getResource("Menu.fxml"));
    Parent root = menuLoader.load();
    Scene scene = new Scene(root);
    root.requestFocus();
    window = (Stage) ((Node) event.getSource()).getScene().getWindow();
    window.setScene(scene);
    window.show();
  }

  @FXML
  void initialize() {

    BackgroundFill background_fill = new BackgroundFill(Color.GREY, CornerRadii.EMPTY, Insets.EMPTY);
    // create Background
    Background backgroundh = new Background(background_fill);

    // set background
    header.setBackground(backgroundh);
    BackgroundImage backgroundimage = new BackgroundImage(
        new Image((new File("images/background.jpg")).toURI().toString()), BackgroundRepeat.NO_REPEAT,
        BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);

    // create Background
    Background background = new Background(backgroundimage);

    // set background
    pane.setBackground(background);

    // add entity names to the page
    for (int i = 0; i < 15; i++) {
      VBox box = new VBox();
      Text name = new Text(names.get(i));
      name.setWrappingWidth(60);
      box.getChildren().add(name);
      box.setPadding(new Insets(5, 5, 5, 5));
      content.add(box, 0, i);
    }

    // add eneity images to the page
    for (int i = 0; i < 15; i++) {
      VBox box = new VBox();
      ImageView image = new ImageView(new Image((new File("images/" + imgs.get(i) + ".png")).toURI().toString()));
      box.getChildren().add(image);
      box.setPadding(new Insets(5, 5, 5, 5));
      content.add(box, 1, i);
    }

    // add entity desctriptions to the page
    for (int i = 0; i < 15; i++) {
      VBox box = new VBox();
      Text d = new Text(descriptions.get(i));
      d.setWrappingWidth(360);
      box.getChildren().add(d);
      box.setPadding(new Insets(5, 5, 5, 5));
      content.add(box, 2, i);
    }

  }

}