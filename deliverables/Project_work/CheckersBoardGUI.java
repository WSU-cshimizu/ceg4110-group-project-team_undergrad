package com.example;

import com.example.BotPlayer.Move;

import javafx.application.Application;
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
        difficultyComboBox.getItems().addAll("Easy", "Medium", "Hard");
        difficultyComboBox.setValue("Medium");

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
    if (game.movePiece(currentX, currentY, targetX, targetY)) {
        // Update the GUI after a successful player move
        drawPieces();
        selectedPieceCircle.setStroke(Color.WHITE); // Reset highlight to white after moving
        selectedPiece = null;
        selectedPieceCircle = null;

        // Trigger bot move after player move, if player is not controlling bot pieces
        if (!playerIsBot) {
            Move botMove = game.getBotPlayer().determineMove();
            if (botMove != null) {
                game.movePiece(botMove.getStartX(), botMove.getStartY(), botMove.getEndX(), botMove.getEndY());
                drawPieces(); // Update GUI after bot move
                System.out.println("Bot moved from (" + botMove.getStartX() + ", " + botMove.getStartY() + ") to (" + botMove.getEndX() + ", " + botMove.getEndY() + ")");
            }
        }
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

    if (selectedPiece == null) {
        // Select the piece
        selectedPiece = piece;
        selectedPieceCircle = pieceCircle;
        selectedPieceCircle.setStroke(Color.YELLOW); // Highlight the selected piece
        System.out.println("Piece selected at x: " + x + ", y: " + y);

        // Set the bot to move only the opposite color pieces
        playerIsBot = selectedPiece.getBotPiece(); // Player controls the opposite of what they selected
    } else if (selectedPiece == piece) {
        // Deselect the piece if the same piece is clicked again
        selectedPieceCircle.setStroke(Color.WHITE); // Reset highlight to white
        selectedPiece = null;
        selectedPieceCircle = null;
        System.out.println("Piece deselected.");
    }
}
    

    public static void main(String[] args) {
        launch(args);
    }
}
