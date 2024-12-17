import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SwingGUI {
    static ActionListener taskPerformer;

    JFrame frame;
    JPanel contentPane;

    public SwingGUI(Board board) {
        int boardSize = board.getBoardSize();
        
        frame = new JFrame("board?");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(boardSize * 100, boardSize * 100));

        contentPane = new JPanel();
        contentPane.setLayout(new GridLayout(boardSize, boardSize));

        boolean whiteTile = true;
        for (int x = 0; x < boardSize; x++) {
            for (int y = 0; y < boardSize; y++) {
                JButton button = new JButton();
                button.addActionListener(taskPerformer);
                button.setBackground(whiteTile ? new Color(255, 238, 212) : new Color(85, 50, 0));
                button.setBorderPainted(false);
                button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                whiteTile = !whiteTile;
                if (board.getPieceAt(x, y) != null) button.setIcon(Utils.scaleImage(board.getPieceAt(x, y).getImage().toString(), 90, 1008, 1008));
                contentPane.add(button);
            }
        }

        frame.setContentPane(contentPane);
        frame.pack();
        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent event) {
        System.out.println();
    }
}