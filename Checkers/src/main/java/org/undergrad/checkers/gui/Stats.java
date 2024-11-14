package org.undergrad.checkers.gui;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/* =================================================
 * This class is solely responsible for setting up
 * the window for stats in FirstEx.java
 * =================================================
 */

public class Stats {


    public static void showStats(Stage stage) {

        StackPane settingsPane = new StackPane();
        Scene settingScene = new Scene(settingsPane, 1000, 800);

        // Add some example content (this is where your game logic would go)
        Label settingsLabel = new Label("Game Started!");
        settingsPane.getChildren().add(settingsLabel);

        // Set the new scene to the stage
        stage.setScene(settingScene);
        stage.show();
    }
}
