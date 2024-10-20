// Essential JavaFx clases, collections, and properties
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Tooltip;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class FirstEx extends Application {

    @OaSDverride
    // Application is the main class of a JavaFX program
    // Application start method is overridden. The start method is
    // the main entry point to the JavaFX program. It receives a Stage
    // as itss only parameter. (Stage is the main application window
    // or area.) The user interface is built in the initUI method.
    public void start(Stage stage) {

        initUI(stage);
    }

    private void initUI(Stage stage) {

        StackPane root = new StackPane();

        // Test button
        Button btn = new Button();
        btn.setText("Click me");
        Tooltip tooltip = new Tooltip("This is a button."); // Create a tooltip
        btn.setOnAction((ActionEvent event) -> {
            Platform.exit();
        });
        
        // TODO: THIS DOES NOT WORK
        Tooltip.install(btn, tooltip);

        HBox hBox = new HBox();
        hBox.setPadding(new Insets(25, 50, 50, 100));
        hBox.getChildren().addAll(btn);

        // Window box size
        Scene scene = new Scene(root, 1000, 800);

        // Final touches for the program
        Label lbl = new Label("Simple JavaFX application.");
        lbl.setFont(Font.font("Serif", FontWeight.NORMAL, 20));
        root.getChildren().add(lbl);
        root.getChildren().add(hBox);

        stage.setTitle("Simple application");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}