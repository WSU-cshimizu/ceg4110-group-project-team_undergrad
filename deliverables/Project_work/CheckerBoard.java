import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.scene.input.MouseEvent;

// UPDATED VERSION OF `CheckersBoardGUI.java` 

public class CheckerBoard {
    
    private static final int TILE_SIZE = 100;
    private static final int BOARD_SIZE = 8;
    private static Rectangle lastClickedTile = null;

    public void StartUI(Stage primaryStage) {
        GridPane board = new GridPane();

        // Create board
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                Rectangle tile = new Rectangle(TILE_SIZE, TILE_SIZE);
                // Set color to red for even squares and black for others.
                if ((row + col) % 2 == 0) {
                    tile.setFill(Color.RED);
                } else {
                    tile.setFill(Color.BLACK);
                }
                // Create outline around squares for highlighting.
                tile.setStroke(Color.TRANSPARENT);
                tile.setStrokeWidth(1);
                tile.setOnMouseClicked(this::handleTileClick);
                board.add(tile, col, row);
            }
        }

        // Make everything visible and add a title.
        Scene scene = new Scene(board);
        primaryStage.setTitle("Checkers Board");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false); // Not allowed to adjust size. (maybe change this later)
        primaryStage.sizeToScene(); // This makes the window the same size as the board.
        primaryStage.show();
    }

    // Used for enabling clicks.
    private void handleTileClick(MouseEvent event) {
        Rectangle tile = (Rectangle) event.getSource();
        // Grab x and y coordinates.
        int y = GridPane.getRowIndex(tile) * -1;
        int x = GridPane.getColumnIndex(tile);
        // Small output to show which tile was clicked.
        System.out.println("Tile clicked at x: " + x + ", y: " + y);

        if (lastClickedTile != null) {
            // Reset the stroke of the previously clicked tile.
            lastClickedTile.setStroke(Color.TRANSPARENT);
        }

        // Highlight the clicked tile with a yellow outline.
        tile.setStroke(Color.YELLOW);
        lastClickedTile = tile;
    }

}
