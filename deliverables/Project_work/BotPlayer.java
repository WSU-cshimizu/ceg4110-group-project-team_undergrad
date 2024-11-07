import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BotPlayer {

    private Game game;
    private Board board;
    private Random random;

    public BotPlayer(Game game) {
        this.game = game;
        this.board = game.getBoard();
        this.random = new Random();
    }

    // Determine the next move for the bot
    public Move determineMove() {
        List<Move> possibleMoves = getAllPossibleMoves();

        if (possibleMoves.isEmpty()) {
            return null; // No valid moves available
        }

        // Randomly choose a move from possible moves
        return possibleMoves.get(random.nextInt(possibleMoves.size()));
    }

    // Get all possible moves for the bot
    private List<Move> getAllPossibleMoves() {
        List<Move> possibleMoves = new ArrayList<>();

        for (int x = 0; x < board.getSize(); x++) {
            for (int y = 0; y < board.getSize(); y++) {
                Piece piece = board.getPiece(x, y);
                if (piece != null && piece.getBotPiece()) {
                    // Check all possible move directions for the piece
                    possibleMoves.addAll(getValidMovesForPiece(piece));
                }
            }
        }

        return possibleMoves;
    }

    // Get all valid moves for a specific piece
    private List<Move> getValidMovesForPiece(Piece piece) {
        List<Move> validMoves = new ArrayList<>();
        int currentX = piece.getX();
        int currentY = piece.getY();

        // Check potential move directions (diagonal moves)
        int[][] directions = {{1, 1}, {1, -1}, {-1, 1}, {-1, -1}};

        for (int[] direction : directions) {
            int targetX = currentX + direction[0];
            int targetY = currentY + direction[1];

            if (game.movePiece(currentX, currentY, targetX, targetY)) {
                validMoves.add(new Move(currentX, currentY, targetX, targetY));
                game.undoMove(currentX, currentY, targetX, targetY); // Undo move to restore state
            }

            // Check if a jump is possible
            int jumpX = currentX + 2 * direction[0];
            int jumpY = currentY + 2 * direction[1];

            if (game.movePiece(currentX, currentY, jumpX, jumpY)) {
                validMoves.add(new Move(currentX, currentY, jumpX, jumpY));
                game.undoMove(currentX, currentY, jumpX, jumpY); // Undo move to restore state
            }
        }

        return validMoves;
    }

    // Inner class to represent a move
    public static class Move {
        private int startX;
        private int startY;
        private int endX;
        private int endY;

        public Move(int startX, int startY, int endX, int endY) {
            this.startX = startX;
            this.startY = startY;
            this.endX = endX;
            this.endY = endY;
        }

        public int getStartX() {
            return startX;
        }

        public int getStartY() {
            return startY;
        }

        public int getEndX() {
            return endX;
        }

        public int getEndY() {
            return endY;
        }

        @Override
        public String toString() {
            return "Move from (" + startX + ", " + startY + ") to (" + endX + ", " + endY + ")";
        }
    }
}
