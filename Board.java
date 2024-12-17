
import java.util.ArrayList;

public class Board {
    private Piece[][] board;
    private boolean initialized = false;

    private int[] latestClicked;

    public Board(int boardSize) {
        board = new Piece[boardSize][boardSize];
        try {
            initialize();
        } catch (Exception e) {
            System.out.println("Unable to initialize the board");
        }
    }

    public int[] getLatestClicked() {
        if (latestClicked != null) {
            return latestClicked.clone();
        }
        else {
            return null;
        }
    }

    public void setLatestClicked(int[] position) {
        latestClicked = position;
    }

    public final void initialize() throws Exception {
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

    public boolean move(int[] lastPosition, int[] newPosition) {
        if (board[lastPosition[0]][lastPosition[1]] == null) {
            setLatestClicked(null);
            return false;
        }

        boolean positionValid = getPositionValidity(lastPosition, newPosition);
        if (!positionValid) {
            setLatestClicked(null);
            return false;
        }

        // get an array of valid positions, and check if the target position is in it
        Piece temp = board[lastPosition[0]][lastPosition[1]];
        board[lastPosition[0]][lastPosition[1]] = null;
        board[newPosition[0]][newPosition[1]] = temp;
        setLatestClicked(null);
        return true;
    }

    public boolean getPositionValidity(int[] lastPosition, int[] newPosition) {
        ArrayList<int[]> validPositions = validPositions(lastPosition);
        for (int[] position : validPositions) {
            if (newPosition[0] == position[0] && newPosition[1] == position[1]) return true;
        }
        return false;
    }

    public ArrayList<int[]> validPositions(int[] position) {
        ArrayList<int[]> validPositions = new ArrayList<int[]>();
        Piece piece = board[position[0]][position[1]];
        if (piece instanceof Hopper || piece instanceof WASDHopper) {
            if (position[0] - 2 >= 0 && position[1] >= 0) {
                validPositions.add(new int[] { position[0] - 2, position[1] });
            }
            if (position[0] >= 0 && position[1] + 2 < getBoardSize()) {
                validPositions.add(new int[] { position[0], position[1] + 2 });
            }
            if (position[0] < getBoardSize() && position[1] - 2 >= 0) {
                validPositions.add(new int[] { position[0], position[1] - 2 });
            }
            if (position[0] + 2 < getBoardSize() && position[1] < getBoardSize()) {
                validPositions.add(new int[] { position[0] + 2, position[1] });
            }
        }
        if (piece instanceof WASDer || piece instanceof WASDHopper) {
            if (position[0] - 1 >= 0 && position[1] >= 0) {
                validPositions.add(new int[] { position[0] - 1, position[1] });
            }
            if (position[0] >= 0 && position[1] + 1 < getBoardSize()) {
                validPositions.add(new int[] { position[0], position[1] + 1 });
            }
            if (position[0] < getBoardSize() && position[1] - 1 >= 0) {
                validPositions.add(new int[] { position[0], position[1] - 1 });
            }
            if (position[0] + 1 < getBoardSize() && position[1] < getBoardSize()) {
                validPositions.add(new int[] { position[0] + 1, position[1] });
            }
        }
        // add diagonal positions
        if (position[0] - 1 >= 0 && position[1] - 1 >= 0) {
            validPositions.add(new int[] { position[0] - 1, position[1] - 1 });
        }
        if (position[0] - 1 >= 0 && position[1] + 1 < getBoardSize()) {
            validPositions.add(new int[] { position[0] - 1, position[1] + 1 });
        }
        if (position[0] + 1 < getBoardSize() && position[1] - 1 >= 0) {
            validPositions.add(new int[] { position[0] + 1, position[1] - 1 });
        }
        if (position[0] + 1 < getBoardSize() && position[1] + 1 < getBoardSize()) {
            validPositions.add(new int[] { position[0] + 1, position[1] + 1 });
        }
        for (int[] pos : validPositions) {
            System.out.println(pos[0] + ", " + pos[1]);
        }
        return validPositions;
    }
}