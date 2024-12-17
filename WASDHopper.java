import javax.swing.*;

public class WASDHopper extends Piece implements WASDing, Hopping {
    public WASDHopper(boolean isWhite) {
        super(isWhite, new ImageIcon(isWhite ? "assets/WASDHopper_White.png" : "assets/WASDHopper_Black.png"));
    }

    @Override
    public boolean move(int[] startPos, int[] endPos) {
        return false;
    }

    @Override
    public boolean hop(int[] startPos, int[] endPos) {
        return false;
    }
}