package unsw.dungeon;

import unsw.dungeon.Entities.*;

import java.util.ArrayList;
import java.util.List;

import javafx.animation.KeyFrame;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * A JavaFX controller for the dungeon.
 * 
 * @author Robert Clifton-Everest
 *
 */
public class DungeonController {
    @FXML
    private Pane pane;
    @FXML
    private GridPane squares;
    @FXML
    private Menu Options;
    @FXML
    private MenuItem restart;
    @FXML
    private MenuItem PauseContinue;
    @FXML
    private MenuItem BackMenu;
    @FXML
    private MenuItem Close;
    @FXML
    private MenuBar bar;
    @FXML
    private Text GoalText;
    @FXML
    private Text LifeText;
    private List<ImageView> initialEntities;
    private Player player;
    private Dungeon dungeon;
    private Timeline EnemyTimeline;
    private Timeline LifeTimeline;
    private Timeline GoalTimeline;
    private Timeline timelineHound;
    private Timeline checkLife;
    private Stage window;
    private int life;
    private boolean paused;
    private Dialog<ButtonType> dialog;

    public DungeonController(Dungeon dungeon, List<ImageView> initialEntities) {
        this.dungeon = dungeon;
        this.player = dungeon.getPlayer();
        this.initialEntities = new ArrayList<>(initialEntities);
        this.squares = new GridPane();
        this.life = player.getLife();
        this.paused = false;
    }

    @FXML
    public void initialize() {
        Image ground = new Image((new File("images/dirt_0_new.png")).toURI().toString());
        // Add the ground first so it is below all other entities
        for (int x = 0; x < dungeon.getWidth(); x++) {
            for (int y = 0; y < dungeon.getHeight(); y++) {
                squares.add(new ImageView(ground), x, y);
            }
        }

        for (ImageView entity : initialEntities)
            squares.getChildren().add(entity);

        // get the size of pane
        double paneWidth = dungeon.getWidth() * 32;
        double paneHeight = dungeon.getHeight() * 32 + 25;

        // set the bar size to fit the pane
        bar.setPrefWidth(paneWidth);
        bar.setPrefHeight(50);
        bar.setLayoutX(0);
        bar.setLayoutY(0);

        GoalText = new Text("Your Goal: " + dungeon.getGoal().toString());
        GoalText.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 11));
        GoalText.setX(paneWidth / 2);
        GoalText.setY(20);

        if (dungeon.getGoal().toString().contains("Enemy")) {
            LifeText = new Text("Your Life: " + life);
            LifeText.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 11));
            LifeText.setX(paneWidth / 3);
            LifeText.setY(20);
        } else {
            LifeText = new Text("");
        }
        // set the size of pane based on the input dungeon width and height
        pane.setPrefWidth(paneWidth);
        pane.setPrefHeight(paneHeight);
        pane.getChildren().addAll(squares, GoalText, LifeText);
        // set the gridpane position below the menu bar
        squares.setLayoutX(0);
        squares.setLayoutY(25);

        // timeline event to make enemy move auto, enemy move one step every 0.6s
        EnemyTimeline = new Timeline();
        EnemyTimeline.setCycleCount(Timeline.INDEFINITE);

        // notify enemies every 0.5 seconds
        EventHandler<ActionEvent> onFinished_Enemy = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                player.notifyEnemies();
            }
        };
        KeyFrame EnemyPlayFrame = new KeyFrame(Duration.millis(600), onFinished_Enemy);
        EnemyTimeline.getKeyFrames().add(EnemyPlayFrame);
        EnemyTimeline.play();

        // timeline event to make enemy move auto, enemy move one step every 0.3s
        timelineHound = new Timeline();
        timelineHound.setCycleCount(Timeline.INDEFINITE);
        // notify hounds every 0.3 second
        EventHandler<ActionEvent> onFinished_hound = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                player.notifyHounds();
            }
        };
        KeyFrame playFrameHound = new KeyFrame(Duration.millis(300), onFinished_hound);
        timelineHound.getKeyFrames().add(playFrameHound);
        timelineHound.play();

        // timeline that checks the life of the player every 100 miliseconds
        LifeTimeline = new Timeline();
        LifeTimeline.setCycleCount(Timeline.INDEFINITE);

        EventHandler<ActionEvent> onFinished_Life = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                if (!player.isAlive()) {
                    checkLife.stop();
                    LifeText.setText("Your Life: 0");
                    EnemyTimeline.stop();
                    timelineHound.stop();
                    GoalTimeline.stop();
                    LifeTimeline.stop();
                    pane.setOnKeyPressed(null);
                    String operation = "dead";
                    Dialog(operation);
                }
            }
        };
        KeyFrame LifeKeyFrame = new KeyFrame(Duration.millis(100), onFinished_Life);
        LifeTimeline.getKeyFrames().add(LifeKeyFrame);
        LifeTimeline.play();

        // timeline that checks the the goal every 100 miliseconds
        GoalTimeline = new Timeline();
        GoalTimeline.setCycleCount(Timeline.INDEFINITE);

        EventHandler<ActionEvent> onFinished_Goal = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                if (dungeon.checkGoals()) {
                    checkLife.stop();
                    EnemyTimeline.stop();
                    timelineHound.stop();
                    LifeTimeline.stop();
                    GoalTimeline.stop();
                    pane.setOnKeyPressed(null);
                    String operation = "Goal";
                    Dialog(operation);
                }
            }
        };
        KeyFrame GoalPlayFrame = new KeyFrame(Duration.millis(100), onFinished_Goal);
        GoalTimeline.getKeyFrames().add(GoalPlayFrame);
        GoalTimeline.play();

        // a timeline that update the life of the player
        checkLife = new Timeline();
        checkLife.setCycleCount(Timeline.INDEFINITE);
        EventHandler<ActionEvent> onFinished_life = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                LifeText.setText("Your Life: " + player.getLife());
            }
        };
        KeyFrame lifKeyFrame = new KeyFrame(Duration.millis(100), onFinished_life);
        checkLife.getKeyFrames().add(lifKeyFrame);
        checkLife.play();
    }

    @FXML
    private void handleKeyPress(KeyEvent event) {
        // if the game is paused, disable the controll
        if (!paused) {
            switch (event.getCode()) {
                case UP:
                    player.moveUp();
                    break;
                case DOWN:
                    player.moveDown();
                    break;
                case LEFT:
                    player.moveLeft();
                    break;
                case RIGHT:
                    player.moveRight();
                    break;
                default:
                    break;
            }
        }
        if (event.getCode() == KeyCode.ESCAPE) {
            handlePauseContinue();
        }
    }

    @FXML
    private void handlePauseContinue() {
        if (PauseContinue.getText().equals("Pause")) {
            paused = true;
            PauseContinue.setText("Continue");
            EnemyTimeline.pause();
            timelineHound.pause();
        } else if (PauseContinue.getText().equals("Continue")) {
            paused = false;
            PauseContinue.setText("Pause");
            EnemyTimeline.play();
            timelineHound.play();
        }
    }

    @FXML
    public void handleExit(ActionEvent event) {
        Platform.exit();
        System.exit(0);
    }

    @FXML
    public void handleBackToMenu(ActionEvent event) {
        loadMenu();
    }

    @FXML
    public void handleRestart(ActionEvent event) {
        reloadGame();
    }

    private void Dialog(String operation) {

        dialog = new Dialog<ButtonType>();
        dialog.getDialogPane().getButtonTypes().add(ButtonType.APPLY);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CLOSE);

        Button button0 = (Button) dialog.getDialogPane().lookupButton(ButtonType.APPLY);
        button0.setText("Play Again");
        button0.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                reloadGame();
            }
        });

        Button button1 = (Button) dialog.getDialogPane().lookupButton(ButtonType.OK);
        button1.setText("Back To Main");
        button1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                loadMenu();
            }
        });

        Button button2 = (Button) dialog.getDialogPane().lookupButton(ButtonType.CLOSE);
        button2.setText("Exit");
        button2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Platform.exit();
                System.exit(0);
            }
        });
        if (operation.equals("dead")) {
            dialog.setHeaderText("Game Over ! You died !!!!! Hahaha!!!!");
        } else if (operation.equals("Goal")) {
            dialog.setHeaderText("Congratulations !!!!!! You Win The Game !!!! ");
        } else if (operation.equals("Pause")) {
            dialog.setHeaderText("Paused\nPress continue from option menu to resume.");
        }
        dialog.show();
    }

    private void loadMenu() {
        checkLife.stop();
        EnemyTimeline.stop();
        timelineHound.stop();
        LifeTimeline.stop();
        GoalTimeline.stop();
        FXMLLoader menuLoader = new FXMLLoader(getClass().getResource("Menu.fxml"));
        Parent root = null;
        try {
            root = menuLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(root);
        root.requestFocus();
        window = (Stage) pane.getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    private void reloadGame() {
        checkLife.stop();
        EnemyTimeline.stop();
        timelineHound.stop();
        LifeTimeline.stop();
        GoalTimeline.stop();
        DungeonControllerLoader dungeonLoader = null;
        try {
            String filename = dungeon.getMapFilename();
            dungeonLoader = new DungeonControllerLoader(filename);
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }
        DungeonController gameController = null;
        try {
            gameController = dungeonLoader.loadController();
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }
        FXMLLoader loader = new FXMLLoader(getClass().getResource("DungeonView.fxml"));
        loader.setController(gameController);
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(root);
        root.requestFocus();
        window = (Stage) pane.getScene().getWindow();
        window.setScene(scene);
        window.show();
    }
}
