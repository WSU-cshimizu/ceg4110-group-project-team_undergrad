package org.undergrad.checkers.gui;

import org.undergrad.checkers.game.*;
import org.undergrad.checkers.bot.*;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.scene.input.MouseEvent;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class CheckersBoardGUI extends Application {

    private static final int TILE_SIZE = 70;
    private static final int BOARD_SIZE = 8; // 8x8 board for checkers
    private Circle selectedPieceCircle = null; // Stores the visual representation of the piece currently selected.
    private Piece selectedPiece = null; // Stores the actual piece currently selected.
    private GridPane boardGrid; // Used for storing the board.
    private boolean playerIsBot; // Determines if the player is controlling bot pieces or not


    private Game game; // Game instance to manage game logic.

    @Override
    public void start(Stage primaryStage) {
        // Initialize the game
        game = new Game(); // This creates a new board and sets initial positions for pieces.

        boardGrid = new GridPane();

        playerIsBot = false; // Default to player controlling player pieces.


        // Create the board
        drawBoard();

        // Place the pieces on the board according to the game state
        drawPieces();

        // Layout
        BorderPane root = new BorderPane();
        root.setCenter(boardGrid);

        // Create a top panel with buttons and settings
        HBox topPanel = new HBox(10);
        Button settingsButton = new Button("Settings");
        ComboBox<String> difficultyComboBox = new ComboBox<>();
        difficultyComboBox.getItems().addAll("Medium", "Hard");
        difficultyComboBox.setValue("Medium"); // Default selection        

        // Create a reset button
        Button resetButton = new Button("Reset");
        resetButton.setOnAction(e -> resetBoard()); // Set action to reset the board

        topPanel.getChildren().addAll(settingsButton, difficultyComboBox, resetButton);
        root.setTop(topPanel);

        // Make everything visible and add a title.
        Scene scene = new Scene(root);
        primaryStage.setTitle("Checkers Board");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.sizeToScene();
        primaryStage.show();
    }

    private void resetBoard() {
        game = new Game(); // Reinitialize the game with default board setup
        drawPieces(); // Redraw the pieces
    }

    // Method to draw the board (tiles)
    private void drawBoard() {
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                Rectangle tile = new Rectangle(TILE_SIZE, TILE_SIZE);
                if ((row + col) % 2 == 0) {
                    tile.setFill(Color.rgb(168, 169, 174));
                } else {
                    tile.setFill(Color.BLACK);
                }
                tile.setOnMouseClicked(this::handleTileClick);
                boardGrid.add(tile, col, row);
            }
        }
    }

    // Method to draw the pieces on the board based on the game state
    private void drawPieces() {
        boardGrid.getChildren().removeIf(node -> node instanceof Circle); // Clear existing pieces

        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                Piece piece = game.getBoard().getPiece(col, row);
                if (piece != null) {
                    Color pieceColor = piece.getBotPiece() ? Color.BLACK : Color.rgb(229, 26, 56);
                    Circle pieceCircle = new Circle(TILE_SIZE / 2.5, pieceColor);
                    pieceCircle.setStroke(Color.WHITE);
                    pieceCircle.setStrokeWidth(1.5);
                    pieceCircle.setOnMouseClicked(this::handlePieceClick);
                    boardGrid.add(pieceCircle, col, row);
                    GridPane.setHalignment(pieceCircle, HPos.CENTER);
                    GridPane.setValignment(pieceCircle, VPos.CENTER);
                }
            }
        }
    }

    // Handle tile clicks for moving pieces
    private void handleTileClick(MouseEvent event) {
        if (selectedPiece == null) {
            return; // No piece is selected, so nothing to do here.
        }

        Rectangle tile = (Rectangle) event.getSource();
        int targetY = GridPane.getRowIndex(tile);
        int targetX = GridPane.getColumnIndex(tile);

        int currentX = selectedPiece.getX();
        int currentY = selectedPiece.getY();

        // Attempt to move the selected piece using the game logic
        if (game.movePiece(currentX, currentY, targetX, targetY, true)) {
            System.out.println("Moved player piece.");
            // Update the GUI after a successful player move
            drawPieces();
            deselect();
            checkGameOver(); // Check if the game is over after the player's move

            // Trigger bot move after player move, if player is not controlling bot pieces
            botMove();
        } else {
            System.out.println("Invalid move. Try again.");
        }
    }

    private void handlePieceClick(MouseEvent event) {
        Circle pieceCircle = (Circle) event.getSource();
        int x = GridPane.getColumnIndex(pieceCircle);
        int y = GridPane.getRowIndex(pieceCircle);
    
        Piece piece = game.getBoard().getPiece(x, y);
        if (piece == null) {
            return;
        }
    
        // Prevent the player from selecting bot-controlled pieces
        if (piece.getBotPiece()) {
            System.out.println("You cannot move the bot's pieces.");
            return; // Exit the method without selecting the piece
        }
    
        if (selectedPiece == null) {
            // Select the piece
            selectedPiece = piece;
            selectedPieceCircle = pieceCircle;
            selectedPieceCircle.setStroke(Color.YELLOW); // Highlight the selected piece
            System.out.println("Piece selected at x: " + x + ", y: " + y);
        } else if (selectedPiece == piece) {
            // Deselect the piece if the same piece is clicked again
            deselect();
            System.out.println("Piece deselected.");
        }
    }    

private void botMove() {
    if (!playerIsBot) {
        BotPlayer.Move botMove = game.getBotPlayer().determineMove();
        if (botMove != null) {
            new Thread(() -> {
                try {
                    Thread.sleep(2000); // Simulate bot "thinking"
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
    
                Platform.runLater(() -> {
                    game.movePiece(botMove.getStartX(), botMove.getStartY(), botMove.getEndX(), botMove.getEndY(), true);
                    drawPieces();
                    System.out.println("Bot moved from (" + botMove.getStartX() + ", " + botMove.getStartY() + ") to (" + botMove.getEndX() + ", " + botMove.getEndY() + ")");
                    checkGameOver(); // Check if the game is over after the bot's move
                });
            }).start();
        }
    }    
}

private void checkGameOver() {
    int playerPieces = 0;
    int botPieces = 0;

    // Count remaining pieces for both sides
    for (int row = 0; row < BOARD_SIZE; row++) {
        for (int col = 0; col < BOARD_SIZE; col++) {
            Piece piece = game.getBoard().getPiece(col, row);
            if (piece != null) {
                if (piece.getBotPiece()) {
                    botPieces++;
                } else {
                    playerPieces++;
                }
            }
        }
    }

    // If either player or bot has no pieces left, the game is over
    if (playerPieces == 0 || botPieces == 0) {
        String winner = playerPieces == 0 ? "Bot" : "Player";

        // Show game over popup
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Game Over");
        alert.setHeaderText(null);
        alert.setContentText(winner + " wins the game!");
        alert.showAndWait();

        // Optionally reset the board for a new game
        resetBoard();
    }
}

    

    private void deselect() {
        selectedPieceCircle.setStroke(Color.WHITE); // Reset highlight to white
        selectedPiece = null;
        selectedPieceCircle = null;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
