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

        for (int x = 0; x < boardSize; x++) {
            for (int y = 0; y < boardSize; y++) {
                JButton button = new JButton(x + " " + y);
                button.addActionListener(taskPerformer);
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