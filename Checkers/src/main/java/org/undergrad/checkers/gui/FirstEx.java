package org.undergrad.checkers.gui;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
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
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.image.Image;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class FirstEx extends Application {

    @Override
    public void start(Stage stage) {

        initUI(stage);
    }

    // The actual start of the intialization
    public static void initUI(Stage stage) {

        BorderPane newRoots = new BorderPane();
        // Base pane
        GridPane gridPane = new GridPane();    

        gridPane.setMinSize(1000, 800); 
        gridPane.setMaxWidth(1000);
        gridPane.setMaxHeight(800);
        gridPane.setMinWidth(1000);
        gridPane.setMinHeight(800);
        //Setting the padding  
        gridPane.setPadding(new Insets(10, 10, 10, 10)); 
        
        //Setting the vertical and horizontal gaps between the columns 
        gridPane.setVgap(5); 
        gridPane.setHgap(5);       
        
        //Setting the Grid alignment 
        gridPane.setAlignment(Pos.CENTER); 

        // StackPane root = new StackPane();

        // // VBox to hold the buttons vertically
        // VBox vBox = new VBox(20); // 20 is the spacing between the buttons
        // vBox.setAlignment(Pos.CENTER); // Align VBox in the center

        // VBox right = new VBox(20);
        // right.setAlignment(Pos.BOTTOM_RIGHT);

        // HBox logoutBox = new HBox();
        // logoutBox.setAlignment(Pos.BOTTOM_RIGHT);

        // Creating buttons with specific names
        Button btnStartGame = new Button("Start Game");
        Button btnBotDifficulty = new Button("Bot Difficulty");
        Button btnSettings = new Button("Settings");
        Button btnSeeStats = new Button("See Stats");
        Button btnExit = new Button("Exit");
        Button btnLogout = new Button("Logout");
        Button test1 = new Button("Test");
        Button test2 = new Button("test2");
        Button test3 = new Button("test3");
        Button test4 = new Button("test4");
        Button test5 = new Button("test5");
        Button test6 = new Button("test6");
        Button test7 = new Button("test7");
        Button test8 = new Button("test8");
        Button test9 = new Button("test9");

        // test1-9 are invisible buttons to fill up and create
        // a perfect center of buttons and 1 logout on the bottom right
        test1.setVisible(false);
        test2.setVisible(false);
        test3.setVisible(false);
        test4.setVisible(false);
        test5.setVisible(false);
        test6.setVisible(false);
        test7.setVisible(false);
        test8.setVisible(false);
        test9.setVisible(false);

        // Set button size
        setButtonSize(btnStartGame);
        setButtonSize(btnBotDifficulty);
        setButtonSize(btnSettings);
        setButtonSize(btnSeeStats);
        setButtonSize(btnExit);
        setButtonSize(btnLogout);
    
        // Set button size for fuller locations
        setButtonSize(test1);
        setButtonSize(test2);
        setButtonSize(test3);
        setButtonSize(test4);
        setButtonSize(test5);
        setButtonSize(test6);
        setButtonSize(test7);
        setButtonSize(test8);
        setButtonSize(test9);


        // Exit action for buttons (temporary, for testing)
        // btnStartGame.setOnAction((ActionEvent event) -> Platform.exit());
        btnStartGame.setOnAction((ActionEvent event) -> {
            // CheckersBoardGUI.StartUI(stage);
            CheckersBoardGUI test = new CheckersBoardGUI(); //
            test.start(stage);
        });
        btnBotDifficulty.setOnAction((ActionEvent event) -> {
            BotDifficulty.showBotDifficulty(stage); // Call method to switch scenes
        });
        btnSettings.setOnAction((ActionEvent event) -> {
            // TODO: Make further implementation
            Settings.showSettings(stage); // Call method to switch scenes
        });
        btnSeeStats.setOnAction((ActionEvent event) -> {
            // TODO: Make further implementation
            Stats.showStats(stage);
        });
        btnExit.setOnAction((ActionEvent event) -> Platform.exit());

 
        btnLogout.setOnAction((ActionEvent event) -> {
            LoginPage test = new LoginPage();
            test.start(stage);
        });

        
        gridPane.add(test1, 4, 5);
        gridPane.add(test2, 1, 0);
        gridPane.add(test3, 2, 1);
        gridPane.add(test4, 3, 2);
        gridPane.add(test5, 2, 9);
        gridPane.add(test6, 1, 10);
        gridPane.add(test7, 5, 11);
        gridPane.add(test8, 5, 12);
        gridPane.add(btnStartGame, 3, 3);
        gridPane.add(btnBotDifficulty, 3, 4);
        gridPane.add(btnSettings, 3, 5);
        gridPane.add(btnSeeStats, 3, 6);
        gridPane.add(btnExit, 3, 7);
        gridPane.add(btnLogout, 5, 13);

    //     // Adding buttons to VBox
    //     vBox.getChildren().addAll(btnStartGame, btnBotDifficulty, btnSettings, btnSeeStats, btnExit);

    //     // right.getChildren().addAll(btnLogout);
    //     logoutBox.getChildren().add(btnLogout);


    //     // Adding VBox to the root pane
    //    // Adding VBox to the root pane
    //     root.getChildren().add(vBox); // Centered by default in StackPane
    //     // root.getChildren().add(logoutBox); // Align to bottom-right

    //     // Set the alignment for logoutBox
    //     StackPane.setAlignment(logoutBox, Pos.BOTTOM_RIGHT);

        

    // Set background color or image (choose one)
    gridPane.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
    //     // setBackgroundImage(root, "path_to_image.jpg"); // Set your image path here
    setBackgroundImage(gridPane, "test.png");


        // // Create the logout button
        // Button btnLogout = new Button("Logout");
        // setButtonSize(btnLogout);

        // // Handle the logout action
        // btnLogout.setOnAction(e -> {
        //     loginPage loginPage = new loginPage();
        //     try {
        //         loginPage.start(stage); // Return to the login page
        //     } catch (Exception ex) {
        //         ex.printStackTrace();
        //     }
        // });

        // // Place the logout button in the bottom-right corner
        // HBox logoutBox = new HBox();
        // logoutBox.setAlignment(Pos.BOTTOM_RIGHT);
        // logoutBox.getChildren().add(btnLogout);
        // StackPane.setAlignment(logoutBox, Pos.BOTTOM_RIGHT); // Position it at the bottom-right of the StackPane
        // root.getChildren().add(logoutBox);
        

        // Scene setup
        Scene scene = new Scene(gridPane, 1000, 800);
    

        // Stage setup
        stage.setTitle("Game Menu");
        stage.setScene(scene);
        stage.setResizable(false);
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
private static void setBackgroundImage(GridPane pane, String imagePath) {
    // Load the image
    Image image = new Image(imagePath);
    
    // Configure the BackgroundImage
    BackgroundImage bgImage = new BackgroundImage(
        image, 
        BackgroundRepeat.NO_REPEAT, // No repeating
        BackgroundRepeat.NO_REPEAT, // No repeating
        BackgroundPosition.CENTER,  // Center the image
        new BackgroundSize(
            BackgroundSize.DEFAULT.getWidth(), // Use default width (image's original width)
            BackgroundSize.DEFAULT.getHeight(), // Use default height (image's original height)
            true, // Scale width proportionally
            true, // Scale height proportionally
            true, // Cover the entire pane
            false // Do not contain within original aspect ratio
        )
    );

    // Set the background of the pane
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
