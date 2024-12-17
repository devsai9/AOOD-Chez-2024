public class Main {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            Board board = new Board(7);
            SwingGUI ui = new SwingGUI(board, 0, false);
            SwingGUI ui2 = new SwingGUI(board, 100, true);
            board.addGooey(ui);
            board.addGooey(ui2);
        });
    }
}

