import javax.swing.ImageIcon;

public abstract class Piece {
    private boolean color; // True: White, False: Brown
    private ImageIcon image;

    public Piece(boolean isWhite, ImageIcon image) {
        color = isWhite;
        this.image = image;
    }

    public boolean isWhite() { return color; }
    public ImageIcon getImage() { return image; }
}