import java.util.ArrayList;

import javax.swing.JDialog;
import javax.swing.JLabel;

public class Board {
    private Piece[][] board;

    // True: White; False: Brown
    private boolean isCurrentTurnWhite = true;
    private boolean dialogOpen = false;

    private int[] latestClicked;
    ArrayList<SwingGUI> gooies = new ArrayList<SwingGUI>();

    public Board(int boardSize) {
        board = new Piece[boardSize][boardSize];
        try {
            initialize();
        } catch (Sai e) {
            System.out.println("Unable to initialize the board");
        }
    }

    public void addGooey(SwingGUI gooey) {
        gooies.add(gooey);
    }

    public void updateGooeyPositions(int oldX, int oldY, int x, int y) {
        for (SwingGUI gooey : gooies) {
            gooey.updatePosition(oldX, oldY, x, y);
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

    public final void initialize() throws Sai {
        board = new Piece[getBoardSize()][getBoardSize()];
        if (getBoardSize() < 5) throw new Sai("Board size too small");
        if (getBoardSize() % 2 == 0) throw new Sai("Board size can not be an even number");

        int leftDifference = (getBoardSize() - 5) / 2;
        Piece[] topRow = new Piece[]{ new WASDer(true), new Hopper(true), new WASDHopper(true), new WASDer(true), new Hopper(true) };
        Piece[] bottomRow = new Piece[]{ new WASDer(false), new Hopper(false), new WASDHopper(false), new WASDer(false), new Hopper(false) };

        for (int i = leftDifference; i < getBoardSize(); i++) {
            if (i - leftDifference > topRow.length - 1) break;
            board[0][i] = topRow[i - leftDifference];
            board[getBoardSize() - 1][i] = bottomRow[i - leftDifference];
        }
    }

    public int getBoardSize() { return board.length; }

    public Piece getPieceAt(int row, int col) { return board[row][col]; }

    public boolean move(int[] lastPosition, int[] newPosition) {
        if (board[lastPosition[0]][lastPosition[1]] == null) return false;
        if (dialogOpen) return false;

        Piece piece = board[lastPosition[0]][lastPosition[1]];

        if (board[newPosition[0]][newPosition[1]] != null && piece.isWhite() == board[newPosition[0]][newPosition[1]].isWhite()) {
            focusPiece(newPosition);
            highlightBackgrounds();
            return false;
        }

        boolean positionValid = getPositionValidity(lastPosition, newPosition);
        if (!positionValid) {
            focusPiece(null);
            return false;
        }


        board[lastPosition[0]][lastPosition[1]] = null;
        board[newPosition[0]][newPosition[1]] = piece;
        focusPiece(null);

        if (board[newPosition[0]][newPosition[1]] != null) if (isEnemyCooked()) endGame();

        changeTurn();
        return true;
    }

    public boolean getPositionValidity(int[] lastPosition, int[] newPosition) {
        ArrayList<int[]> validPositions = validPositions(lastPosition);
        for (int[] position : validPositions) {
            if (newPosition[0] == position[0] && newPosition[1] == position[1]) return true;
        }
        return false;
    }

    public void focusPiece(int[] pos) {
        clearHighlights();
        if (pos == null) {
            setLatestClicked(null);
        }
        else {
            if (getPieceAt(pos[0], pos[1]).isWhite() == getCurrentTurn()) {
                setLatestClicked(new int[] { pos[0], pos[1] });
                highlightBackgrounds();
            }
        }
    }

    public void clearHighlights() {
        for (SwingGUI gooey : gooies) {
            gooey.clearAllBakgrounds();
        }
    }

    public void highlightBackgrounds() {
        ArrayList<int[]> validPositions = validPositions(getLatestClicked());
        for (int i = 0; i < validPositions.size(); i++) {
            for (SwingGUI gooey : gooies) {
                gooey.highlightBackground(validPositions.get(i)[0], validPositions.get(i)[1]);
            }
        }
    }

    public ArrayList<int[]> validPositions(int[] position) {
        ArrayList<int[]> validPositions = new ArrayList<int[]>();
        Piece piece = board[position[0]][position[1]];
        if (piece instanceof Hopper || piece instanceof WASDHopper) {
            if (position[0] - 2 >= 0 && position[1] >= 0) {
                addIf(validPositions, new int[] { position[0] - 2, position[1] });
            }
            if (position[0] >= 0 && position[1] + 2 < getBoardSize()) {
                addIf(validPositions, new int[] { position[0], position[1] + 2 });
            }
            if (position[0] < getBoardSize() && position[1] - 2 >= 0) {
                addIf(validPositions, new int[] { position[0], position[1] - 2 });
            }
            if (position[0] + 2 < getBoardSize() && position[1] < getBoardSize()) {
                addIf(validPositions, new int[] { position[0] + 2, position[1] });
            }
        }
        if (piece instanceof WASDer || piece instanceof WASDHopper) {
            if (position[0] - 1 >= 0 && position[1] >= 0) {
                addIf(validPositions, new int[] { position[0] - 1, position[1] });
            }
            if (position[0] >= 0 && position[1] + 1 < getBoardSize()) {
                addIf(validPositions, new int[] { position[0], position[1] + 1 });
            }
            if (position[0] < getBoardSize() && position[1] - 1 >= 0) {
                addIf(validPositions, new int[] { position[0], position[1] - 1 });
            }
            if (position[0] + 1 < getBoardSize() && position[1] < getBoardSize()) {
                addIf(validPositions, new int[] { position[0] + 1, position[1] });
            }
        }
        // add diagonal positions
        if (position[0] - 1 >= 0 && position[1] - 1 >= 0) {
            addIf(validPositions, new int[] { position[0] - 1, position[1] - 1 });
        }
        if (position[0] - 1 >= 0 && position[1] + 1 < getBoardSize()) {
            addIf(validPositions, new int[] { position[0] - 1, position[1] + 1 });
        }
        if (position[0] + 1 < getBoardSize() && position[1] - 1 >= 0) {
            addIf(validPositions, new int[] { position[0] + 1, position[1] - 1 });
        }
        if (position[0] + 1 < getBoardSize() && position[1] + 1 < getBoardSize()) {
            addIf(validPositions, new int[] { position[0] + 1, position[1] + 1 });
        }

        return validPositions;
    }

    public void addIf(ArrayList<int[]> validPositions, int[] position) {
        if (board[position[0]][position[1]] == null || board[position[0]][position[1]].isWhite() != isCurrentTurnWhite) {
            validPositions.add(position);
        }
    }

    public void changeTurn() {
        isCurrentTurnWhite = !isCurrentTurnWhite;
    }

    public boolean getCurrentTurn() {
        return isCurrentTurnWhite;
    }

    public boolean isEnemyCooked() {
        for (Piece[] pieceRow : board) {
            for (Piece piece : pieceRow) {
                if (piece != null && piece.isWhite() != isCurrentTurnWhite) {
                    return false;
                }
            }
        }
        return true;
    }

    public void endGame() {
        dialogOpen = true;
        JDialog dialog = new JDialog();
        dialog.setTitle("Message from Sai");
        dialog.add(new JLabel((isCurrentTurnWhite ? "White" : "Brown") + " won!"));
        dialog.setSize(200, 200);
        dialog.setVisible(true);
        // when dialog is closed, do thing
        dialog.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                dialogOpen = false;
                try {
                    clearHighlights();
                    initialize();
                    for (SwingGUI gooey : gooies) {
                        gooey.refresh();
                    }
                } catch (Sai e) {
                    System.out.println("something went wrong");
                }
            }
        });
    }
}