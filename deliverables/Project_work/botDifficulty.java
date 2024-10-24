import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class botDifficulty {
    
    public static void showBotDifficulty(Stage stage) {

        // Create window pane
        StackPane settingsPane = new StackPane();

        VBox vBox = new VBox(20); // 20 is the spacing between the buttons
        vBox.setAlignment(Pos.CENTER);

        // Difficulty selection if all is wanted
        Button Easy = new Button("Easy");
        Button Medium = new Button("Medium");
        Button Hard = new Button("Hard");

        // Testing button visibility
        Easy.setOnAction(null);

        setButtonSize(Easy);
        setButtonSize(Medium);
        setButtonSize(Hard);

        // Create an area to hold the buttons
        vBox.getChildren().addAll(Easy, Medium, Hard);


        Label settingsLabel = new Label("Choose Bot Difficulty");
        settingsPane.getChildren().add(settingsLabel);

        settingsPane.getChildren().add(vBox);

        
        Scene settingScene = new Scene(settingsPane, 1000, 800);
    

        // Set the new scene to the stage
        stage.setScene(settingScene);
        stage.show();
    }

    // Method to set the button size
    private static void setButtonSize(Button button) {
        button.setPrefWidth(200); // Width of the button
        button.setPrefHeight(50); // Height of the button
    }
}
