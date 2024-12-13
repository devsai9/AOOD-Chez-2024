public class Main {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            Board board = new Board(7);
            SwingGUI ui = new SwingGUI(board);
        });
    }
}

