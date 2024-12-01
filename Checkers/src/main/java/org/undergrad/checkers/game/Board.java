package org.undergrad.checkers.game;

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

    // Board Constructor with Save String
    // Populates the board based on the saved String
    public Board(String save) {
        int index = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                // Extract character from index and increment index
                char c = save.charAt(index);
                index++;

                // Create piece based on char
                if (c == '0') {
                    board[j][i] = null;
                } else if (c == '1') {
                    board[j][i] = new Piece(true, j, i);
                } else {
                    board[j][i] = new Piece(false, j, i);
                }
            }
        }
    }

    // Get piece from coords
    public Piece getPiece(int x, int y) {
        return board[x][y];
    }

    public int getSize() {
        return 8; // Since the board size is 8x8
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

    // Export
    // Exports board to a string where 0 = null, 1 = bot piece, 2 = player piece
    public String export() {
        String save = "";
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[j][i] == null) {
                    save += "0";
                } else if (board[j][i].getBotPiece()) {
                    save += "1";
                } else {
                    save += "2";
                }
            }
        }
        return save;
    }
}