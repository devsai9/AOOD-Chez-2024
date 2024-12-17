import javax.swing.*;

public class WASDer extends Piece implements WASDing {
    public WASDer(boolean isWhite) {
        super(isWhite, new ImageIcon(isWhite ? "assets/WASDer_White.png" : "assets/WASDer_Black.png"));
    }

    @Override
    public boolean move(int[] startPos, int[] endPos) {
        return false;
    }
}