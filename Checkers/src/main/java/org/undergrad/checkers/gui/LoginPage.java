package org.undergrad.checkers.gui;

import org.undergrad.checkers.gui.*;
import javafx.event.ActionEvent;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.undergrad.checkers.db.*;

public class LoginPage extends Application {


    @Override
    public void start(Stage stage) {

        mainMenuUI(stage);
    }

    public void mainMenuUI(Stage stage) {

        // Create Username and Password fields
        TextField usernameField = new TextField();
        usernameField.setPromptText("Username");

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Password");

        // Initial width, min and max width for text fields
        double initialWidth = 300;  // Set initial width
        usernameField.setPrefWidth(initialWidth);
        passwordField.setPrefWidth(initialWidth);
        usernameField.setMinWidth(200);  // Set minimum width
        passwordField.setMinWidth(200);
        usernameField.setMaxWidth(400);  // Set maximum width
        passwordField.setMaxWidth(400);

        // Create buttons for Login, Guest, and Quit
        Button loginButton = new Button("Login");
        Button guestButton = new Button("Play as Guest");
        Button quitButton = new Button("Quit");

        // Set actions for buttons (assuming you have other scenes to navigate to)
        loginButton.setOnAction((ActionEvent event) -> {

            // Declare variables
            String userName = null;
            String password = null;

            // Assign user information values
            userName = usernameField.getText();
            password = passwordField.getText();

            // Check code
            System.out.println(userName + " " + password);

            //Check if valid login
            int userID = GameDB.findUser(userName, password);

            if (userID == 0) {
                System.out.println("User not found... Creating new user");
                GameDB.createUser(userName, password);
                userID = GameDB.findUser(userName, password);
                FirstEx.setUserID(userID);
            } else if (userID == -1) {
                System.out.println("Username already exists or incorrect password");
            }

            // Further change this once login is fully completed
            goToMenu(stage);  // Method to go to main menu

        }); // Method to go to main menu
        guestButton.setOnAction(e -> goToMenu(stage));
        quitButton.setOnAction(e -> stage.close());

        // Layout for buttons
        HBox buttonBox = new HBox(10, loginButton, guestButton, quitButton);
        buttonBox.setAlignment(Pos.CENTER);

        // Create a VBox layout and add the fields and buttons
        VBox layout = new VBox(15);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(20));
        layout.getChildren().addAll(new Label("Please Log In"), usernameField, passwordField, buttonBox);

        // Set up the scene
        Scene scene = new Scene(layout, 1000, 800);
        scene.getStylesheets().add(LoginPage.class.getResource("/org/undergrad/checkers/style.css").toExternalForm());
        stage.setScene(scene);
        stage.setTitle("Login Page");
        stage.show();

        // Resize the text boxes when the window is resized
        scene.widthProperty().addListener((obs, oldVal, newVal) -> {
            double newWidth = newVal.doubleValue() * 0.7; // Make text boxes 70% of window width
            usernameField.setPrefWidth(newWidth);
            passwordField.setPrefWidth(newWidth);
        });
    }

    // Dummy method to represent navigating to the main menu
    private void goToMenu(Stage stage) {
        // Initialize FirstEx or another page here
        FirstEx mainMenu = new FirstEx();
        mainMenu.start(stage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}