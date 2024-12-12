public class Board {
    private Piece[][] board = new Piece[5][5];
    private boolean initialized = false;

    public void initialize() {
        Piece[] pieces = new Piece[] {new WASDer(), new Hopper(), new WASDHopper()};
    }
}