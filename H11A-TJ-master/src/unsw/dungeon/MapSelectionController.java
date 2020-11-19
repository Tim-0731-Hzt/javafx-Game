package unsw.dungeon;

import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
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
import javafx.scene.control.Button;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import java.io.File;
import java.io.IOException;

public class MapSelectionController<E> {

    private Stage window;

    @FXML
    private Pane pane;
    @FXML
    private GridPane grid;
    @FXML
    private Button back;

    private ArrayList<Image> imgs;
    private ArrayList<ImageView> views;
    private ArrayList<VBox> boxs;
    private ArrayList<String> names;

    public MapSelectionController() {

        names = new ArrayList<>();
        names.add("maze");
        names.add("advanced-easy");
        names.add("boulders");
        names.add("advanced-normal");
        names.add("comprehensive");
        names.add("advanced-hard");

        imgs = new ArrayList<Image>();
        for (int i = 0; i < 6; i++) {
            imgs.add(new Image((new File("examples/" + names.get(i) + ".png")).toURI().toString()));
        }

        views = new ArrayList<ImageView>();
        for (int i = 0; i < 6; i++) {
            views.add(new ImageView(imgs.get(i)));
        }
        for (ImageView v : views) {
            v.setFitHeight(150);
            v.setFitWidth(150);
        }

        boxs = new ArrayList<VBox>();
        for (int i = 0; i < 6; i++) {
            VBox box = new VBox();
            box.getChildren().add(views.get(i));
            Text t = new Text(names.get(i));
            box.getChildren().add(t);
            box.setAlignment(Pos.CENTER);
            box.setMaxWidth(150);
            box.setPadding(new Insets(3, 0, 0, 0));
            BackgroundFill background_fill = new BackgroundFill(Color.WHITE, new CornerRadii(5), Insets.EMPTY);
            // create Background
            Background background = new Background(background_fill);

            // set background
            box.setBackground(background);

            boxs.add(box);
        }
        boxs.get(0).setOnMouseClicked(onClick0);
        boxs.get(1).setOnMouseClicked(onClick1);
        boxs.get(2).setOnMouseClicked(onClick2);
        boxs.get(3).setOnMouseClicked(onClick3);
        boxs.get(4).setOnMouseClicked(onClick4);
        boxs.get(5).setOnMouseClicked(onClick5);

    }

    @FXML
    public void back(ActionEvent e) throws IOException {
        FXMLLoader menuLoader = new FXMLLoader(getClass().getResource("Menu.fxml"));
        Parent root = menuLoader.load();
        Scene scene = new Scene(root);
        root.requestFocus();
        window = (Stage) ((Node) e.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();

    }

    EventHandler<? super MouseEvent> onClick0 = new EventHandler<>() {
        @Override
        public void handle(MouseEvent arg0) {
            try {
                startGame(0, arg0);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    };

    EventHandler<? super MouseEvent> onClick1 = new EventHandler<>() {
        @Override
        public void handle(MouseEvent arg0) {
            try {
                startGame(1, arg0);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    };

    EventHandler<? super MouseEvent> onClick2 = new EventHandler<>() {
        @Override
        public void handle(MouseEvent arg0) {
            try {
                startGame(2, arg0);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    };

    EventHandler<? super MouseEvent> onClick3 = new EventHandler<>() {
        @Override
        public void handle(MouseEvent arg0) {
            try {
                startGame(3, arg0);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    };

    EventHandler<? super MouseEvent> onClick4 = new EventHandler<>() {
        @Override
        public void handle(MouseEvent arg0) {
            try {
                startGame(4, arg0);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    };

    EventHandler<? super MouseEvent> onClick5 = new EventHandler<>() {
        @Override
        public void handle(MouseEvent arg0) {
            try {
                startGame(5, arg0);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    };

    /**
     * start the game given the position argument
     * @param i the position of the game
     * @param arg0 mouseevent
     * @throws IOException
     */
    private void startGame(int i, MouseEvent arg0) throws IOException {
        DungeonControllerLoader dungeonLoader = new DungeonControllerLoader(names.get(i) + ".json");
        DungeonController gameController = dungeonLoader.loadController();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("DungeonView.fxml"));
        loader.setController(gameController);
        Parent root = loader.load();
        Scene scene = new Scene(root);
        root.requestFocus();
        window = (Stage) ((Node) arg0.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();

    }

    @FXML
    void initialize() {
        int counter = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 2; j++) {
                grid.add(boxs.get(counter), i, j);
                GridPane.setHalignment(boxs.get(counter), HPos.CENTER);
                counter += 1;
            }
        }

        BackgroundImage backgroundimage = new BackgroundImage(
                new Image((new File("images/background.jpg")).toURI().toString()), BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);

        // create Background
        Background background = new Background(backgroundimage);

        // set background
        pane.setBackground(background);

    }

}