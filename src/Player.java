public class Player {
    private static final int BLACK = 1;
    private static final int WHITE = 2;
    private boolean isMyTurn;
    private int myColor; // 1:黒, 2:白

    Player(int color) {
        myColor = color;
        if (myColor == BLACK) {
            isMyTurn = true;
        } else {
            isMyTurn = false;
        }
    }

    public void setTurn() {
        isMyTurn = !isMyTurn;
    }

    public boolean getTurn() {
        return isMyTurn;
    }

    public int getColor() {
        if (myColor == BLACK) {
            return BLACK;
        } else {
            return WHITE;
        }
    }
}
