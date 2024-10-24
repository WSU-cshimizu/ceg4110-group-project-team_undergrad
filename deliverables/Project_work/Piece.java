public class Piece {

    boolean botPiece;
    int x;
    int y;

    // Default constructor
    public Piece() {
        botPiece = false;
        x = 0;
        y = 0;
    }

    // Param Constructor: Creates piece with botpiece true/false
    public Piece(boolean botPiece, int x, int y) {
        this.botPiece = botPiece;
        this.x = x;
        this.y = y;
    }

    public boolean getBotPiece() {
        return botPiece;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}
