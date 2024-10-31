import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.layout.CornerRadii;
import javafx.scene.image.Image;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.stage.Stage;

public class FirstEx extends Application {

    @Override
    public void start(Stage stage) {

        initUI(stage);
    }

    // The actual start of the intialization
    public static void initUI(Stage stage) {

        // Base pane
        StackPane root = new StackPane();

        // VBox to hold the buttons vertically
        VBox vBox = new VBox(20); // 20 is the spacing between the buttons
        vBox.setAlignment(Pos.CENTER); // Align VBox in the center

        // Creating buttons with specific names
        Button btnStartGame = new Button("Start Game");
        Button btnBotDifficulty = new Button("Bot Difficulty");
        Button btnSettings = new Button("Settings");
        Button btnSeeStats = new Button("See Stats");
        Button btnExit = new Button("Exit");

        // Set button size
        setButtonSize(btnStartGame);
        setButtonSize(btnBotDifficulty);
        setButtonSize(btnSettings);
        setButtonSize(btnSeeStats);
        setButtonSize(btnExit);

        // Exit action for buttons (temporary, for testing)
        // btnStartGame.setOnAction((ActionEvent event) -> Platform.exit());
        btnStartGame.setOnAction((ActionEvent event) -> {
            // TODO: Make further implementation
            // CheckersBoardGUI.StartUI(stage);
            CheckerBoard test = new CheckerBoard(); //
            test.StartUI(stage);
        });
        btnBotDifficulty.setOnAction((ActionEvent event) -> {
            botDifficulty.showBotDifficulty(stage); // Call method to switch scenes
        });
        btnSettings.setOnAction((ActionEvent event) -> {
            // TODO: Make further implementation
            showSettingsScences(stage); // Call method to switch scenes
        });
        btnSeeStats.setOnAction((ActionEvent event) -> {
            // TODO: Make further implementation
            stats.showStats(stage);
        });
        btnExit.setOnAction((ActionEvent event) -> Platform.exit());

        

        // Adding buttons to VBox
        vBox.getChildren().addAll(btnStartGame, btnBotDifficulty, btnSettings, btnSeeStats, btnExit);

        // Adding VBox to the root pane
        root.getChildren().add(vBox);

        // Set background color or image (choose one)
        setBackgroundColor(root, Color.LIGHTBLUE); // You can change the color here
        // setBackgroundImage(root, "path_to_image.jpg"); // Set your image path here
        setBackgroundImage(root, "test.png");

        // Scene setup
        Scene scene = new Scene(root, 1000, 800);

        // Stage setup
        stage.setTitle("Game Menu");
        stage.setScene(scene);
        stage.show();
    }

    // Method to set the button size
    private static void setButtonSize(Button button) {
        button.setPrefWidth(200); // Width of the button
        button.setPrefHeight(50); // Height of the button
    }

    // Method to set background color
    private static void setBackgroundColor(StackPane pane, Paint color) {
        pane.setBackground(new Background(new BackgroundFill(color, CornerRadii.EMPTY, null)));
    }

    // Method to set background image
    private static void setBackgroundImage(StackPane pane, String imagePath) {
        Image image = new Image(imagePath);
        BackgroundImage bgImage = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        pane.setBackground(new Background(bgImage));
    }

    // Method to switch scenes
    private static void showGameScene(Stage stage) {
        // Create new pane for the game
        StackPane gamePane = new StackPane();
        Scene gameScene = new Scene(gamePane, 1000, 800);

        // Add some example content (this is where your game logic would go)
        Label gameLabel = new Label("Game Started!");
        gamePane.getChildren().add(gameLabel);

        // Set the new scene to the stage
        stage.setScene(gameScene);
        stage.show();
    }

    private static void showSettingsScences(Stage stage) {

        StackPane settingsPane = new StackPane();
        Scene settingScene = new Scene(settingsPane, 1000, 800);

        // Add some example content (this is where your game logic would go)
        Label settingsLabel = new Label("Game Started!");
        settingsPane.getChildren().add(settingsLabel);

        // Set the new scene to the stage
        stage.setScene(settingScene);
        stage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
