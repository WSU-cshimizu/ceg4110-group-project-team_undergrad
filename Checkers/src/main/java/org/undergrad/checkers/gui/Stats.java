package org.undergrad.checkers.gui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Stats  {

    public void showStats(Stage stage) {
        // User stats (replace these values with actual stats from your data)
        String username = "Player123";
        int wins = 25;
        int losses = 10;
        int totalGames = wins + losses;
        double winRatio = totalGames > 0 ? (double) wins / totalGames * 100 : 0;

        // Create labels for stats
        Text usernameText = new Text("Username: " + username);
        Text winsText = new Text("Wins: " + wins);
        Text lossesText = new Text("Losses: " + losses);
        Text totalGamesText = new Text("Total Games Played: " + totalGames);
        Text winRatioText = new Text(String.format("Win Ratio: %.2f%%", winRatio));

        // VBox to hold the stats
        VBox statsBox = new VBox(10, usernameText, winsText, lossesText, totalGamesText, winRatioText);
        statsBox.setAlignment(Pos.CENTER);

        // Back button
        Button backButton = new Button("Back");
        backButton.setOnAction(event -> FirstEx.initUI(stage));
        setButtonSize(backButton);

        // Bottom-right placement of the button
        BorderPane root = new BorderPane();
        root.setCenter(statsBox);
        BorderPane.setAlignment(backButton, Pos.BOTTOM_RIGHT);
        root.setPadding(new Insets(20));
        root.setBottom(backButton);

        // Create the scene and show the stage
        Scene scene = new Scene(root, 1000, 800);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());

        stage.setTitle("Player Stats");
        stage.setScene(scene);
        stage.show();
    }

    // Method to set the button size
    private static void setButtonSize(Button button) {
        button.setPrefWidth(200); // Width of the button
        button.setPrefHeight(50); // Height of the button
    }

}
