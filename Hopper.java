import javax.swing.*;

public class Hopper extends Piece implements Hopping {
    public Hopper(boolean isWhite) {
        super(isWhite, new ImageIcon(isWhite ? "assets/Hopper_White.png" : "assets/Hopper_Black.png"));
    }

    @Override
    public boolean hop(int[] startPos, int[] endPos) {
        return false;
    }
}