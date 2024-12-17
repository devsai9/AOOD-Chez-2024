import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class SwingGUI {
    JFrame frame;
    JPanel contentPane;
    Board board;

    public SwingGUI(Board board) {
        this.board = board;
        int boardSize = board.getBoardSize();
        
        frame = new JFrame("board?");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(boardSize * 100, boardSize * 100));

        contentPane = new JPanel();
        contentPane.setLayout(new GridLayout(boardSize, boardSize));

        drawBoard();

        frame.setContentPane(contentPane);
        frame.pack();
        frame.setVisible(true);
    }
    
    public final void drawBoard() {
        contentPane.removeAll();
        contentPane.revalidate();
        contentPane.repaint();
        int boardSize = board.getBoardSize();
        boolean whiteTile = true;
        for (int x = 0; x < boardSize; x++) {
            for (int y = 0; y < boardSize; y++) {
                JButton button = new JButton();
                button.addActionListener(new EventHandling(x, y, board, this));
                button.setBackground(whiteTile ? new Color(255, 238, 212) : new Color(85, 50, 0));
                button.setBorderPainted(false);
                button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                whiteTile = !whiteTile;
                if (board.getPieceAt(x, y) != null) button.setIcon(Utils.scaleImage(board.getPieceAt(x, y).getImage().toString(), 90, 1008, 1008));
                contentPane.add(button);
            }
        }
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
            boolean moved = board.move(board.getLatestClicked(), justClicked);
            if (moved) {
                gooey.drawBoard();
            }
        }
        else {
            board.setLatestClicked(justClicked);
        }
    }

    public EventHandling(int x, int y, Board board, SwingGUI gooey) {
        this.x = x;
        this.y = y;
        this.board = board;
        this.gooey = gooey;
    }
}