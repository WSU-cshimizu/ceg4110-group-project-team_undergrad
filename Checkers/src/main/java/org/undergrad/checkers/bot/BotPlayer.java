package org.undergrad.checkers.bot;
import org.undergrad.checkers.game.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BotPlayer {

    private Game game;
    private Board board;
    private Random random;
    private String difficulty; // Tracks difficulty level

    public BotPlayer(Game game) {
        this.game = game;
        this.board = game.getBoard();
        this.random = new Random();
        this.difficulty = "Medium"; // Default difficulty
    }

    // Set difficulty level
    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    // Determine the next move for the bot
    public Move determineMove() {
        List<Move> possibleMoves = getAllPossibleMoves();

        if (possibleMoves.isEmpty()) {
            return null; // No valid moves available
        }

        // Use weighted logic for "Hard" difficulty
        if ("Hard".equalsIgnoreCase(difficulty)) {
            return determineWeightedMove(possibleMoves);
        }

        // Default to random moves for "Medium"
        return possibleMoves.get(random.nextInt(possibleMoves.size()));
    }

    // Weighted move determination for "Hard" difficulty
    private Move determineWeightedMove(List<Move> possibleMoves) {
        List<Move> capturingMoves = new ArrayList<>();
        List<Move> nonCapturingMoves = new ArrayList<>();

        for (Move move : possibleMoves) {
            if (isCapturingMove(move)) {
                capturingMoves.add(move);
            } else {
                nonCapturingMoves.add(move);
            }
        }

        // Prioritize capturing moves
        if (!capturingMoves.isEmpty()) {
            return capturingMoves.get(random.nextInt(capturingMoves.size()));
        }

        // Otherwise, choose a random non-capturing move
        return nonCapturingMoves.get(random.nextInt(nonCapturingMoves.size()));
    }

    // Helper Method to Check if a Move is a Capturing Move
    private boolean isCapturingMove(Move move) {
        int midX = (move.getStartX() + move.getEndX()) / 2;
        int midY = (move.getStartY() + move.getEndY()) / 2;

        // Check if the middle position contains an opponent's piece
        Piece middlePiece = board.getPiece(midX, midY);
        if (middlePiece != null && middlePiece.getBotPiece() != board.getPiece(move.getStartX(), move.getStartY()).getBotPiece()) {
            return true; // Capturing move
        }

        return false;
    }

    private List<Move> getAllPossibleMoves() {
        List<Move> possibleMoves = new ArrayList<>();
        Board board = game.getBoard(); // Get the current board state

        for (int x = 0; x < board.getSize(); x++) {
            for (int y = 0; y < board.getSize(); y++) {
                Piece piece = board.getPiece(x, y);
                if (piece != null && piece.getBotPiece()) {
                    possibleMoves.addAll(getValidMovesForPiece(piece));
                }
            }
        }

        return possibleMoves;
    }

    private List<Move> getValidMovesForPiece(Piece piece) {
        List<Move> validMoves = new ArrayList<>();
        if (piece == null) {
            return validMoves; // Return empty list if the piece is null
        }

        int currentX = piece.getX();
        int currentY = piece.getY();

        // Check potential move directions (diagonal moves)
        int[][] directions = {{1, 1}, {1, -1}, {-1, 1}, {-1, -1}};

        for (int[] direction : directions) {
            int targetX = currentX + direction[0];
            int targetY = currentY + direction[1];

            if (isWithinBounds(targetX, targetY)) {
                if (game.movePiece(currentX, currentY, targetX, targetY, false)) {
                    validMoves.add(new Move(currentX, currentY, targetX, targetY));
                    game.undoMove(currentX, currentY, targetX, targetY); // Undo move to restore state
                }
            }

            // Check if a jump is possible
            int jumpX = currentX + 2 * direction[0];
            int jumpY = currentY + 2 * direction[1];

            if (isWithinBounds(jumpX, jumpY)) {
                if (game.movePiece(currentX, currentY, jumpX, jumpY, false)) {
                    validMoves.add(new Move(currentX, currentY, jumpX, jumpY));
                    game.undoMove(currentX, currentY, jumpX, jumpY); // Undo move to restore state
                }
            }
        }

        return validMoves;
    }

    // Helper method to check if coordinates are within bounds
    private boolean isWithinBounds(int x, int y) {
        return x >= 0 && x < 8 && y >= 0 && y < 8;
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
