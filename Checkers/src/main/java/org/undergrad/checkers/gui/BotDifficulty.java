package org.undergrad.checkers.gui;

import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

public class BotDifficulty {

    // CHECKME
    private static String difficulty;

    public static void showBotDifficulty(Stage stage) {
        // Root pane for the bot difficulty scene
        BorderPane rootPane = new BorderPane();

        // Vertical box to hold the label, buttons, and display text
        VBox vBox = new VBox(20); // 20 is the spacing between elements
        vBox.setAlignment(Pos.CENTER);

        // Main label for the difficulty selection screen
        Label settingsLabel = new Label("Select Bot Difficulty:");

        // Difficulty buttons
        Button Easy = new Button("Easy");
        Button Medium = new Button("Medium");
        Button Hard = new Button("Hard");

        // Label to display the selected difficulty
        Label selectedDifficultyLabel = new Label(); // This will show the selected difficulty

        // Set button sizes
        setButtonSize(Easy);
        setButtonSize(Medium);
        setButtonSize(Hard);

        // Action for difficulty buttons to update the selected difficulty label.
        Easy.setOnAction((ActionEvent e) -> {
            selectedDifficultyLabel.setText("Selected Difficulty: Easy");
            difficulty = "Easy"; // Store current difficulty selected
            System.out.println(difficulty);
        });
        Medium.setOnAction((ActionEvent e) -> {
            selectedDifficultyLabel.setText("Selected Difficulty: Medium");
            difficulty = "Medium"; // Store current difficulty selected
            System.out.println(difficulty);
        });
        Hard.setOnAction((ActionEvent e) -> {
            selectedDifficultyLabel.setText("Selected Difficulty: Hard");
            difficulty = "Hard"; // Store current difficulty selected
            System.out.println(difficulty);
        });

        // Add label, buttons, and selected difficulty label to the VBox
        vBox.getChildren().addAll(settingsLabel, Easy, Medium, Hard, selectedDifficultyLabel);

        // Place the VBox in the center of the BorderPane
        rootPane.setCenter(vBox);

        // Back button to return to the main menu, aligned to the bottom right
        Button backButton = new Button("Back");
        setButtonSize(backButton);
        // Closes this menu and returns back to the main menu
        backButton.setOnAction(event -> FirstEx.initUI(stage));

        // Place the Back button in a StackPane for positioning in the bottom right
        StackPane bottomRightPane = new StackPane(backButton);
        bottomRightPane.setAlignment(Pos.BOTTOM_RIGHT);
        bottomRightPane.setPadding(new Insets(0, 20, 20, 0)); // Add padding (top, right, bottom, left)
        rootPane.setBottom(bottomRightPane);

        setBackgroundColor(rootPane, Color.LIGHTBLUE); // You can change the color here


        // Set up the scene
        Scene settingScene = new Scene(rootPane, 1000, 800);
        stage.setScene(settingScene);
        stage.show();
    }

    // Method to set the button size
    private static void setButtonSize(Button button) {
        button.setPrefWidth(200); // Width of the button
        button.setPrefHeight(50); // Height of the button
    }

    private static void setBackgroundColor(BorderPane pane, Paint color) {
        pane.setBackground(new Background(new BackgroundFill(color, CornerRadii.EMPTY, null)));
    }

    // Getter method for difficulty
    public String getDifficulty() {
        return difficulty;
    }
}
