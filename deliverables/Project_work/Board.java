public class Board {

    public Piece[][] board = new Piece[8][8];

    // Default Constructor: Initialize new board
    public Board() {
        // Iterate through tiles and initialize pieces
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                // Player pieces
                if ((i == 0 && (j % 2 == 0)) || (i == 1 && (j % 2 == 1))) {
                    board[j][i] = new Piece(false, j, i);
                }
                // Bot pieces
                else if ((i == 6 && (j % 2 == 0)) || (i == 7 && (j % 2 == 1))) {
                    board[j][i] = new Piece(true, j, i);
                }
                // Empty tiles
                else {
                    board[j][i] = null;
                }
            }
    }
    }

    // Constructor with input board array: Loads saved board
    public Board(Piece[][] boardSave) {
        // Set board to boardSave
        board = boardSave;
    }

    // Get piece from coords
    public Piece getPiece(int x, int y) {
        return board[x][y];
    }

    // Set piece given coords and piece object
    public void setPiece(int x, int y, Piece p) {
        board[x][y] = p;
    }

    // Print
    // Iterates through array to print checkerboard
    public void print() {
        for (int i = 7; i >= 0; i--) {
            for (int j = 0; j < 8; j++) {
                if (board[j][i] == null) {
                    System.out.print(" - ");
                } else if (board[j][i].getBotPiece()){
                    System.out.print(" X ");
                } else {
                    System.out.print(" 0 ");
                }
            }
            System.out.println();
        }
    }
}
