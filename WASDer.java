public class WASDer extends Piece implements WASDing {
    public WASDer(boolean isWhite) {
        super(isWhite);
    }

    @Override
    public boolean move(int[] startPos, int[] endPos) {
        return false;
    }
}