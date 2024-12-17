import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class SwingGUI {
    JFrame frame;
    JPanel contentPane;
    Board board;
    JButton[][] squares;
    boolean flipped;

    public SwingGUI(Board board, int xPosition, boolean flipped) {
        this.board = board;
        int boardSize = board.getBoardSize();

        this.flipped = flipped;

        squares = new JButton[boardSize][boardSize];
        
        frame = new JFrame("board?");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(boardSize * 100, boardSize * 100));
        frame.setMinimumSize(new Dimension(boardSize * 100, boardSize * 100));
        frame.setLocation((xPosition != 0 ? boardSize * 100 + xPosition : 0), 0);

        contentPane = new JPanel();
        contentPane.setLayout(new GridLayout(boardSize, boardSize));

        initBoard();

        frame.setContentPane(contentPane);
        frame.pack();
        frame.setVisible(true);
    }

    public final void initBoard() {
        int boardSize = board.getBoardSize();
        boolean whiteTile = true;
        if (!flipped) {
            for (int x = 0; x < boardSize; x++) {
                for (int y = 0; y < boardSize; y++) {
                    createButton(x, y, whiteTile);
                    whiteTile = !whiteTile;
                }
            }
        } else {
            for (int x = boardSize - 1; x >= 0; x--) {
                for (int y = boardSize - 1; y >= 0; y--) {
                    createButton(x, y, whiteTile);
                    whiteTile = !whiteTile;
                }
            }
        }
    }

    public void refresh() {
        for (int x = 0; x < board.getBoardSize(); x++) {
            for (int y = 0; y < board.getBoardSize(); y++) {
                if (board.getPieceAt(x, y) != null) squares[x][y].setIcon(Utils.scaleImage(board.getPieceAt(x, y).getImage().toString(), 90, 1008, 1008));
                else squares[x][y].setIcon(null);
            }
        }
    }

    public void createButton(int x, int y, boolean whiteTile) {
            JButton button = new JButton();
            button.addActionListener(new EventHandling(x, y, board, this));
            button.setBackground(whiteTile ? new Color(255, 238, 212) : new Color(85, 50, 0));
            button.setBorderPainted(false);
            button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            if (board.getPieceAt(x, y) != null) button.setIcon(Utils.scaleImage(board.getPieceAt(x, y).getImage().toString(), 90, 1008, 1008));
            contentPane.add(button);
            squares[x][y] = button;
        }

    public void updatePosition(int oldX, int oldY, int x, int y) {
        squares[oldX][oldY].setIcon(null);
        squares[x][y].setIcon(Utils.scaleImage(board.getPieceAt(x, y).getImage().toString(), 90, 1008, 1008));
    }

    public void highlightBackground(int x, int y) {
        squares[x][y].setBackground(Color.GREEN);
    }

    public void unhighlightBackground(int x, int y) {
        squares[x][y].setBackground(getNeighborsColor(x, y));
    }

    public void clearAllBakgrounds() {
        for (int x = 0; x < board.getBoardSize(); x++) {
            for (int y = 0; y < board.getBoardSize(); y++) {
                squares[x][y].setBackground(getNeighborsColor(x, y));
            }
        }
    }

    public Color getNeighborsColor(int x, int y) {
        boolean isWhiteTile = (x + y) % 2 == 0;
        return isWhiteTile ? new Color(255, 238, 212) : new Color(85, 50, 0);
    }
}

class EventHandling implements ActionListener {
    private int x, y;
    private Board board;
    private SwingGUI gooey;
    
    @Override
    public void actionPerformed(ActionEvent e) {
        int[] justClicked = new int[] {x, y};
        if (board.getLatestClicked() != null) {
            int[] latestClicked = board.getLatestClicked().clone();
            boolean moved = board.move(board.getLatestClicked(), justClicked);
            if (moved) {
                board.updateGooeyPositions(latestClicked[0], latestClicked[1], x, y);
            }
        }
        else if (board.getPieceAt(justClicked[0], justClicked[1]) != null) {
            if (board.getPieceAt(justClicked[0], justClicked[1]).isWhite() == board.getCurrentTurn()) {
                board.focusPiece(justClicked);
                board.highlightBackgrounds();
            }
        }
    }

    public EventHandling(int x, int y, Board board, SwingGUI gooey) {
        this.x = x;
        this.y = y;
        this.board = board;
        this.gooey = gooey;
    }
}