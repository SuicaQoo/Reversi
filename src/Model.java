import java.io.Serializable;
import java.util.Arrays;

public class Model implements Serializable {
    private int[][] board; // ボード上の駒情報を管理(０：駒なし、１：黒、２：白)
    private boolean[][] blackPositionable; // 黒い駒が置ける場所を管理
    private boolean[][] whitePositionable; // 白い駒が置ける場所を管理
    private int countBlack; // 黒駒の数を管理
    private int countWhite; // 白駒の数を管理

    private static final int EMPTY = 0;
    private static final int BLACK = 1;
    private static final int WHITE = 2;

    private boolean turnIsBlack;

    public Model() {
        board = new int[8][8];
        blackPositionable = new boolean[8][8];
        whitePositionable = new boolean[8][8];
        // ボードの駒情報の初期化 すべて０にして駒がない状況に
        for (int i = 0; i < board.length; i++) {
            Arrays.fill(board[i], EMPTY);
            Arrays.fill(blackPositionable[i], false);
            Arrays.fill(whitePositionable[i], false);
        }
        boolean startTurn = true; // 黒(true)からスタート
        turnIsBlack = startTurn;

        // 初期配置
        board[3][3] = WHITE;
        board[4][4] = WHITE;
        board[3][4] = BLACK;
        board[4][3] = BLACK;

    }

    /* getter */
    // ボード全体の情報を取得
    public int[][] getBoard() {
        return board;
    }

    // ボードの情報を場所を指定して取得
    public int getBoard(int line, int row) {
        return board[line][row];
    }

    // 白駒が置ける場所の情報を取得
    public boolean[][] getWhitePositionable() {
        return whitePositionable;
    }

    // 白駒の置けるかを場所を指定して取得
    public boolean getWhitePositionable(int line, int row) {
        return whitePositionable[line][row];
    }

    // 黒駒が置ける場所の情報を取得
    public boolean[][] getBlackPositionable() {
        return blackPositionable;
    }

    // 黒駒の置けるかを場所を指定して取得
    public boolean getBlackPositionable(int line, int row) {
        return blackPositionable[line][row];
    }

    // 白駒の数を取得
    public int getCountWhite() {
        return countWhite;
    }

    // 黒駒の数を取得
    public int getCountBlack() {
        return countBlack;
    }

    // turnの取得
    public boolean getTurn() {
        return turnIsBlack;
    }

    /* setter */
    // ボード全体の情報を変更
    public void setBoard(int[][] board) {
        this.board = board;
        System.out.println("board updated");
    }

    // ボードの情報を場所を指定して変更
    public void setBoard(int line, int row, int info) {
        board[line][row] = info;
    }

    // 白駒が置ける場所の情報を変更
    public void setWhitePositionable(boolean[][] whitePositionable) {
        this.whitePositionable = whitePositionable;
    }

    // 白駒の置けるかを場所を指定して変更
    public void setWhitePositionable(int line, int row, boolean info) {
        whitePositionable[line][row] = info;
    }

    // 黒駒が置ける場所の情報を変更
    public void setBlackPositionable(boolean[][] blackPositionable) {
        this.blackPositionable = blackPositionable;
    }

    // 黒駒の置けるかを場所を指定して変更
    public void setBlackPositionable(int line, int row, boolean info) {
        blackPositionable[line][row] = info;
    }

    // 白駒の数を変更
    public void setCountWhite(int count) {
        countWhite = count;
    }

    // 黒駒の数を変更
    public void setCountBlack(int count) {
        countBlack = count;
    }

    // turnの更新
    public void setTurn(boolean turnIsBlack) {
        this.turnIsBlack = turnIsBlack;
    }
}