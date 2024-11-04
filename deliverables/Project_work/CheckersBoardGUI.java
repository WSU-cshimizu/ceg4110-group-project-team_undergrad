import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.scene.input.MouseEvent;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import java.util.ArrayList;
import java.util.Arrays;

public class CheckersBoardGUI extends Application {

    private static final int TILE_SIZE = 70;
    private static final int BOARD_SIZE = 8; // 8x8 board for checkers
    private Rectangle lastClickedTile = null; // Keeps track of the last clicked tile for highlighting.
    private Circle selectedPiece = null; // Stores the piece currently selected.
    private GridPane board; // Used for storing the board.
    private ArrayList<Rectangle> highlightedTiles = new ArrayList<>(); // Used for storing the list of currently highlighted tiles for later clearing highlights.

    @Override
    public void start(Stage primaryStage) {
        board = new GridPane();

        // Create board (8x8)
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                Rectangle tile = new Rectangle(TILE_SIZE, TILE_SIZE);
                // Set color to grey for even squares and black for others.
                if ((row + col) % 2 == 0) {
                    tile.setFill(Color.rgb(168, 169, 174));
                } else {
                    tile.setFill(Color.BLACK);
                }
                // Create outline around squares for highlighting.
                tile.setStroke(Color.TRANSPARENT);
                tile.setStrokeWidth(2); // Size of highlight.
                tile.setOnMouseClicked(this::handleTileClick); // Each time a tile (this) is clicked, it calls handleTileClick.
                board.add(tile, col, row);
            }
        }

        // Layout
        BorderPane root = new BorderPane();
        root.setCenter(board);

        // Make everything visible and add a title.
        Scene scene = new Scene(root);
        primaryStage.setTitle("Checkers Board");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false); // Not allowed to adjust size. (maybe change this later)
        primaryStage.sizeToScene(); // This makes the window the same size as the board.
        primaryStage.show();

        // Test ArrayList to fill the board with pieces (x, y, color) - 0 for red, 1 for black
        // Comment out/delete this for final game. 
        ArrayList<ArrayList<Object>> testCoordinates = new ArrayList<>();
        testCoordinates.add(new ArrayList<>(Arrays.asList(1, 0, 0)));
        testCoordinates.add(new ArrayList<>(Arrays.asList(3, 0, 0)));
        testCoordinates.add(new ArrayList<>(Arrays.asList(5, 0, 0)));
        testCoordinates.add(new ArrayList<>(Arrays.asList(7, 0, 0)));
        testCoordinates.add(new ArrayList<>(Arrays.asList(0, 1, 0)));
        testCoordinates.add(new ArrayList<>(Arrays.asList(2, 1, 0)));
        testCoordinates.add(new ArrayList<>(Arrays.asList(4, 1, 0)));
        testCoordinates.add(new ArrayList<>(Arrays.asList(6, 1, 0)));
        testCoordinates.add(new ArrayList<>(Arrays.asList(1, 2, 0)));
        testCoordinates.add(new ArrayList<>(Arrays.asList(3, 2, 0)));
        testCoordinates.add(new ArrayList<>(Arrays.asList(5, 2, 0)));
        testCoordinates.add(new ArrayList<>(Arrays.asList(7, 2, 0)));

        testCoordinates.add(new ArrayList<>(Arrays.asList(0, 5, 1)));
        testCoordinates.add(new ArrayList<>(Arrays.asList(2, 5, 1)));
        testCoordinates.add(new ArrayList<>(Arrays.asList(4, 5, 1)));
        testCoordinates.add(new ArrayList<>(Arrays.asList(6, 5, 1)));
        testCoordinates.add(new ArrayList<>(Arrays.asList(1, 6, 1)));
        testCoordinates.add(new ArrayList<>(Arrays.asList(3, 6, 1)));
        testCoordinates.add(new ArrayList<>(Arrays.asList(5, 6, 1)));
        testCoordinates.add(new ArrayList<>(Arrays.asList(7, 6, 1)));
        testCoordinates.add(new ArrayList<>(Arrays.asList(0, 7, 1)));
        testCoordinates.add(new ArrayList<>(Arrays.asList(2, 7, 1)));
        testCoordinates.add(new ArrayList<>(Arrays.asList(4, 7, 1)));
        testCoordinates.add(new ArrayList<>(Arrays.asList(6, 7, 1)));

        fillBoardWithPieces(testCoordinates); // Places pieces on board
    }

    // Used for enabling clicks.
    private void handleTileClick(MouseEvent event) {
        Rectangle tile = (Rectangle) event.getSource();
        int y = GridPane.getRowIndex(tile);
        int x = GridPane.getColumnIndex(tile);

        if (selectedPiece == null) {
            // Select a piece if one exists on the clicked tile
            for (javafx.scene.Node node : board.getChildren()) {
                if (node instanceof Circle) {
                    int pieceY = GridPane.getRowIndex(node);
                    int pieceX = GridPane.getColumnIndex(node);
                    if (pieceX == x && pieceY == y) {
                        selectedPiece = (Circle) node;
                        selectedPiece.setStroke(Color.YELLOW); // Change highlight to yellow when selected
                        System.out.println("Piece selected at x: " + x + ", y: " + y);
                        highlightValidMoves(x, y); // Calls this method to check for valid moves to highlight.
                        return;
                    }
                }
            }
        } else {
            // Only allow diagonal moves
            int selectedX = GridPane.getColumnIndex(selectedPiece);
            int selectedY = GridPane.getRowIndex(selectedPiece);
            int deltaX = Math.abs(x - selectedX); // Determine the absolute distance between moves. If the result is 1, we know it's 1 step away and a valid move.
            int deltaY = Math.abs(y - selectedY);

            if (deltaX == 1 && deltaY == 1 && isTileEmpty(x, y)) { // Ensure move is diagonal by 1 step and tile is empty
                // Move the selected piece to the clicked tile
                board.getChildren().remove(selectedPiece);
                board.add(selectedPiece, x, y);
                GridPane.setHalignment(selectedPiece, HPos.CENTER);
                GridPane.setValignment(selectedPiece, VPos.CENTER);
                System.out.println("Piece moved to x: " + x + ", y: " + y);
                selectedPiece.setStroke(Color.WHITE); // Reset highlight to white after moving
                selectedPiece = null;
                clearHighlightedTiles();
            } else {
                System.out.println("Invalid move. You can only move diagonally by one tile to an empty space.");
            }
        }

        if (lastClickedTile != null) {
            lastClickedTile.setStroke(Color.TRANSPARENT);
        }
        tile.setStroke(Color.YELLOW);
        lastClickedTile = tile;
    }

    // Method to place pieces on the board based on an ArrayList of coordinates and value (0 or 1).
    // The method takes one parameter: coordinates, which is an ArrayList of ArrayList<Object>.
    // Each inner ArrayList<Object> represents a set of 3 values: (x, y, colorValue).
    public void fillBoardWithPieces(ArrayList<ArrayList<Object>> coordinates) {
        for (ArrayList<Object> coordinate : coordinates) {
            if (coordinate.size() == 3) {
                int x = (int) coordinate.get(0);
                int y = (int) coordinate.get(1);
                int colorValue = (int) coordinate.get(2);

                // Convert coordinates (0 to 7) to board indices (0 to 7)
                int col = x;
                int row = y;

                if (col >= 0 && col < BOARD_SIZE && row >= 0 && row < BOARD_SIZE) {
                    Color pieceColor = (colorValue == 1) ? Color.BLACK : Color.rgb(229, 26, 56); // Setting piece colors based on 3rd ArrayList value (colorValue).
                    Circle piece = new Circle(TILE_SIZE / 2.5, pieceColor); // Taking the tile size and dividing by 2.5 for the radius. 
                    piece.setStroke(Color.WHITE);
                    piece.setStrokeWidth(1.5);
                    piece.setOnMouseClicked(this::handlePieceClick);
                    board.add(piece, col, row);
                    // Adjust piece position.
                    GridPane.setHalignment(piece, HPos.CENTER);
                    GridPane.setValignment(piece, VPos.CENTER);
                } else {
                    System.out.println("Coordinates out of bounds: (" + x + ", " + y + ").");
                }
            } else {
                System.out.println("Invalid coordinate pair: " + coordinate);
            }
        }
    }

    private void handlePieceClick(MouseEvent event) {
        Circle piece = (Circle) event.getSource();
        if (selectedPiece == null) {
            selectedPiece = piece;
            selectedPiece.setStroke(Color.YELLOW); // Change highlight to yellow when selected
            System.out.println("Piece selected for movement.");
            int x = GridPane.getColumnIndex(piece);
            int y = GridPane.getRowIndex(piece);
            highlightValidMoves(x, y);
        } else if (selectedPiece == piece) {
            // Deselect the piece if the same piece is clicked again
            selectedPiece.setStroke(Color.WHITE); // Reset highlight to white
            selectedPiece = null;
            clearHighlightedTiles();
            System.out.println("Piece deselected.");
        } else {
            // Deselect the currently selected piece and select the new one
            selectedPiece.setStroke(Color.WHITE); // Reset highlight to white
            selectedPiece = piece;
            selectedPiece.setStroke(Color.YELLOW); // Change highlight to yellow when selected
            System.out.println("New piece selected for movement.");
            int x = GridPane.getColumnIndex(piece);
            int y = GridPane.getRowIndex(piece);
            highlightValidMoves(x, y);
        }
    }

    private void highlightValidMoves(int x, int y) {
        clearHighlightedTiles(); // Calls method to clear all previously highlighted tiles.
        int[][] directions = {{1, 1}, {1, -1}, {-1, 1}, {-1, -1}}; // Define all possible move directions (diagonal) - (Down/Right, Down/Left, Up/Right, Up/Left).
        for (int[] direction : directions) { // Iterate over each possible direction to determine validity.
            int newX = x + direction[0];
            int newY = y + direction[1];
            if (newX >= 0 && newX < BOARD_SIZE && newY >= 0 && newY < BOARD_SIZE && isTileEmpty(newX, newY)) { // Check that the new coordinates are within the board boundary and if the tile is empty.
                for (javafx.scene.Node node : board.getChildren()) {
                    if (node instanceof Rectangle) { // First check if the current node is a Rectangle.
                        int tileX = GridPane.getColumnIndex(node);
                        int tileY = GridPane.getRowIndex(node);
                        if (tileX == newX && tileY == newY) { // Check if the tile's coordinates match the calculated new coordinates.
                            ((Rectangle) node).setStroke(Color.rgb(72, 178, 0)); // Set a green highlight on valid moves.
                            ((Rectangle) node).setStrokeWidth(2);
                            highlightedTiles.add((Rectangle) node);
                        }
                    }
                }
            }
        }
    }

    // Method for checking if the current tile is empty or occupied by a piece.
    private boolean isTileEmpty(int x, int y) {
        for (javafx.scene.Node node : board.getChildren()) { // return a list of all the nodes currently added to the gridpane.
            if (node instanceof Circle) { // Loop over each node to determine if there's a circle on each x/y coordinate.
                int pieceX = GridPane.getColumnIndex(node);
                int pieceY = GridPane.getRowIndex(node);
                if (pieceX == x && pieceY == y) {
                    return false; // return false if occupied.
                }
            }
        }
        return true; // return true if empty.
    }

    // Method to iterate over the list of highlighted tiles and revert their color to transparent.
    private void clearHighlightedTiles() {
        for (Rectangle tile : highlightedTiles) {
            tile.setStroke(Color.TRANSPARENT);
        }
        highlightedTiles.clear();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
