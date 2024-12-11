public class Main {
    public static void main(String[] args) {
        System.out.println("hi");
    }
}



/*
swing stuff
 
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MyProgram implements ActionListener {
    static ActionListener taskPerformer;

    JFrame frame;
    JPanel contentPane;

    JLabel question;
    JLabel answer;

    JButton noButton;
    JButton yesButton;

    int lowerBound = 20;
    int upperBound = 40;
    int number;

    public MyProgram() {
        setNumber();

        frame = new JFrame("Is it Divisible by Three?");
        frame.setPreferredSize(new Dimension(600, 120));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        contentPane = new JPanel();
        contentPane.setLayout(new GridLayout(2, 2));

        question = new JLabel("Is " + number + " divisible by 3?");
        answer = new JLabel("You are right/wrong");
        answer.setVisible(false);

        noButton = new JButton("NO!");
        noButton.setActionCommand("No");
        noButton.addActionListener(this);

        yesButton = new JButton("PROBABLY!");
        yesButton.setActionCommand("Yes");
        yesButton.addActionListener(this);

        // Add components to the frame
        contentPane.add(question);
        contentPane.add(answer);
        contentPane.add(noButton);
        contentPane.add(yesButton);

        frame.setContentPane(contentPane);
        frame.pack();
        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent event) {
        String eventName = event.getActionCommand();
        boolean answeredYes = eventName.equals("Yes") ? true : false;
        boolean answerIsYes = number % 3 == 0 ? true : false;
        if (answeredYes == answerIsYes) {
            JOptionPane.showMessageDialog(null, "You are Right!", "Result", JOptionPane.INFORMATION_MESSAGE);
            answer.setText("You are RIGHT!");
        } else {
            JOptionPane.showMessageDialog(null, "You are Wrong!", "Result", JOptionPane.INFORMATION_MESSAGE);
            answer.setText("You are WRONG!");
        }
        answer.setVisible(true);

        lowerBound = upperBound;
        upperBound *= 2;
        setNumber();
        answer.setVisible(false);
        question.setText("Is " + number + " divisible by 3?");
    }

    private void setNumber() {
        number = (int) (Math.random() * (upperBound - lowerBound + 1) + lowerBound);
    }

    private static void runGUI() {
        JFrame.setDefaultLookAndFeelDecorated(true);
        MyProgram greeting = new MyProgram();
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                runGUI();
            }
        });
    }
}
    
 */