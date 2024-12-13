public abstract class Piece {
    private boolean color; // True: White, False: Brown

    public Piece(boolean isWhite) {
        color = isWhite;
    }

    public boolean getColor() { return color; }

    public Piece(Piece p) {
        color = p.color;
    }
}