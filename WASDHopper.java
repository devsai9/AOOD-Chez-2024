public class WASDHopper extends Piece implements WASDing, Hopping {
    public WASDHopper(boolean isWhite) {
        super(isWhite);
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