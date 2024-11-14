public class Game {

    private int playerScore;
    private int botScore;
    private Board board;
    private BotPlayer botPlayer; // Add this to create an instance of the bot player

    // Default constructor
    public Game() {
        board = new Board();
        playerScore = 0;
        botScore = 0;
        botPlayer = new BotPlayer(this); // Initialize the bot player
    }

    // Game constructor that loads saved score and board state
    public Game(int playerScore, int botScore, Piece[][] boardSave) {
        board = new Board(boardSave);
        this.playerScore = playerScore;
        this.botScore = botScore;
    }

        // Add a getter method to access the BotPlayer
        public BotPlayer getBotPlayer() {
            return botPlayer;
        }

        // Add the missing getBoard method
        public Board getBoard() {
            return board;
        }
        

    // movePiece
    // Params: piece to move, target x coord, target y coord
    // Returns: true if move successful, false if move unsuccessful.
    public boolean movePiece(int currentX, int currentY, int targetX, int targetY) {

        // Check that current spot is actually a piece
        if (board.getPiece(currentX, currentY) == null) {
            return false;
        }

        // Check that target spot is open
        if (board.getPiece(targetX, targetY) != null) {
            return false;
        }

        Piece piece = board.getPiece(currentX, currentY);

        // Calculate target vs current coordinate offset
        int xOffset = piece.getX() - targetX;
        int yOffset = piece.getY() - targetY;

        // Check if attempting to jump a piece based on absolute value of offsets
        if (Math.abs(xOffset) + Math.abs(yOffset) == 4) {

            // Calculated by adding subtracting/adding 1 based on the offset sign
            // Calculate coordinates of piece being jumped
            int jumpX = targetX + Integer.signum(xOffset);
            int jumpY = targetY + Integer.signum(yOffset);

            // Check that piece both exists, and is not on the same team as moving piece
            if (board.getPiece(jumpX, jumpY) != null && board.getPiece(jumpX, jumpY).getBotPiece() != piece.getBotPiece()) {
                //Capture piece
                capture(board.getPiece(jumpX, jumpY));

                // Set new piece/board values
                board.setPiece(piece.getX(), piece.getY(), null);
                piece.setX(targetX);
                piece.setY(targetY);
                board.setPiece(targetX, targetY, piece);

                // Check if chain jump possible
                if (chainJump(targetX, targetY, piece.getBotPiece())) {
                    // TODO: insert handling
                };

                // Return true
                return true;
            }

        } else {
            // Set new piece/board values
            board.setPiece(piece.getX(), piece.getY(), null);
            piece.setX(targetX);
            piece.setY(targetY);
            board.setPiece(targetX, targetY, piece);

            return true;
        }
        return false;
    }

    // Capture piece and increment appropriate score
    public void capture(Piece piece) {
        board.setPiece(piece.getX(), piece.getY(), null);
        if (piece.getBotPiece()) {
            playerScore++;
        } else {
            botScore++;
        }
    }

    // chainJump
    // input: current x and y coordinates and if bot
    // returns true if chain jump is possible
    public boolean chainJump(int x, int y, boolean bot) {
        // Check four corners around piece for an enemy piece
        // If true, check if following tile is empty and return true if so
        if ((board.getPiece(x + 1, y + 1) != null) &&
                (board.getPiece(x + 1, y + 1).getBotPiece() != bot)) {
            return (board.getPiece(x + 2, y + 2) == null);
        }

        else if ((board.getPiece(x + 1, y - 1) != null) &&
                (board.getPiece(x + 1, y + 1).getBotPiece() != bot)) {
            return (board.getPiece(x + 2, y - 2) == null);
        }

        else if ((board.getPiece(x - 1, y + 1) != null) &&
                (board.getPiece(x + 1, y + 1).getBotPiece() != bot)) {
            return (board.getPiece(x - 2, y + 2) == null);
        }

        else if ((board.getPiece(x - 1, y - 1) != null) &&
                (board.getPiece(x + 1, y + 1).getBotPiece() != bot)) {
            return (board.getPiece(x - 2, y - 2) == null);
        }

        else {
            return false;
        }
    }

    public int getPlayerScore() {
        return playerScore;
    }

    public int getBotScore() {
        return botScore;
    }

    public void setPlayerScore(int playerScore) {
        this.playerScore = playerScore;
    }

    public void setBotScore(int botScore) {
        this.botScore = botScore;
    }

    // Print board
    public void printBoard() {
        board.print();
    }

    // Prints scores
    public void printScore() {
        System.out.println("Player Score: " + playerScore);
        System.out.println("Bot Score: " + botScore);
    }

    public void undoMove(int startX, int startY, int endX, int endY) {
        Piece movedPiece = board.getPiece(endX, endY);
        board.setPiece(startX, startY, movedPiece);
        board.setPiece(endX, endY, null);
        if (movedPiece != null) {
            movedPiece.setX(startX);
            movedPiece.setY(startY);
        }
    }

    public void saveGame() {
        /* TODO: Implement save game mechanic
        *   save board array to file and then save file name and scores to database */

    }





}
