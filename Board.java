public class Board {
    private Piece[][] board;
    private boolean initialized = false;

    public Board(int boardSize) {
        board = new Piece[boardSize][boardSize];
        try {
            initialize();
        } catch (Exception e) {
            System.out.println("Unable to initialize the board");
        }
    }

    public void initialize() throws Exception {
        if (getBoardSize() < 5) throw new Exception("Board size too small");
        if (getBoardSize() % 2 == 0) throw new Exception("Board size can not be an even number");

        int leftDifference = (getBoardSize() - 5) / 2;
        Piece[] topRow = new Piece[]{ new WASDer(true), new Hopper(true), new WASDHopper(true), new Hopper(true), new WASDer(true) };
        Piece[] bottomRow = new Piece[]{ new WASDer(false), new Hopper(false), new WASDHopper(false), new Hopper(false), new WASDer(false) };

        for (int i = leftDifference; i < getBoardSize(); i++) {
            if (i - leftDifference > topRow.length - 1) break;
            board[0][i] = topRow[i - leftDifference];
            board[getBoardSize() - 1][i] = bottomRow[i - leftDifference];
        }
    }

    public int getBoardSize() { return board.length; }

    public Piece getPieceAt(int row, int col) { return board[row][col]; }
}