public class Board {
    private Piece[][] board;
    private boolean initialized = false;

    public void initialize() {
        // constructor has isWhite boolean parameter
        Piece[] pieces = new Piece[] {new WASDer(false), new Hopper(false), new WASDHopper(false)};
    }

    public Board(int boardSize) {
        board = new Piece[boardSize][boardSize];
    }

    public int getBoardSize() { return board.length; }
    
}