// import java.awt.BorderLayout;
// import java.awt.Color;
// import java.awt.Dimension;
// import java.awt.event.MouseEvent;
// import java.awt.event.MouseListener;

// import javax.swing.JFrame;
// import javax.swing.JPanel;
// import javax.swing.SwingUtilities;

<<<<<<< HEAD
// public class Controller extends JFrame implements MouseListener {
// private JPanel pane; // contentPane
// TimeLimitBar timeLimitBar; // 時間制限バー
// Model model; // データ保持変数
// View board; // 描画変数
// Player player_b; // 黒色のプレイヤー
// Player player_w; // 白色のプレイヤー
// Dimension dim;
=======
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Controller extends JFrame implements MouseListener{
	private JPanel pane; // contentPane
	TimeLimitBar timeLimitBar; // 時間制限バー
	Model model; // データ保持変数
	View board; // 描画変数
	Dimension dim;
>>>>>>> 110ebc744e396a07fa71ba8250264fb46e45aa6c

// // 一時的にプレイヤー変数
// boolean nowPlayer = true; // true: black false: white

// // 色用の定数
// private static final int BLACK = 1;
// private static final int WHITE = 2;

// // main

// /*
// * public static void main(String arg[]) {
// * Controller frame = new Controller();
// * frame.setVisible(true);
// * frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
// * frame.setSize(500, 520);
// * frame.setDimension();
// * }
// *
// */

// // main
// public static void main(String[] args) {
// SwingUtilities.invokeLater(() -> {
// Controller controller = new Controller();
// controller.setSize(520, 550);
// controller.setResizable(false);
// controller.setVisible(true);
// // controller.setDimension();
// });
// }

// // コンストラクタ
// public Controller() {
// // ウィンドウの用意
// pane = (JPanel) getContentPane();
// pane.setLayout(new BorderLayout());
// pane.setBackground(Color.WHITE);

// // プレイヤーの初期化
// player_b = new Player(BLACK);
// player_w = new Player(WHITE);

// // モデルの初期化
// model = new Model();
// calcPositionable();
// for (int i = 0; i < 8; i++) {
// for (int j = 0; j < 8; j++) {
// System.out.print(model.getWhitePositionable(j, i) + ",");
// }
// System.out.println();
// }

// // 制限時間バーの追加
// timeLimitBar = new TimeLimitBar();
// pane.add(timeLimitBar, BorderLayout.NORTH);

// // ボードの追加
// initializeView();

// // テスト
// timeLimitBar.start(BLACK);
// }

// private void initializeView() {
// board = new View(this); // Controllerのインスタンス（this）をViewに渡します
// board.addMouseListener(this);
// setView(board); // boardをフレームに追加
// }

// public void setView(View view) {
// pane.add(view, BorderLayout.CENTER);
// }

// public void setViewSize(Dimension dim) {
// // Viewのサイズを更新するための処理
// board.setSize(dim);
// }

// public JPanel getPane() {
// return pane;
// }

<<<<<<< HEAD
// public void setDimension() {
// dim = getContentPane().getSize();
// System.out.println("width = " + dim.getWidth() + " height = " +
// dim.getHeight());
// setViewSize(dim);
// }

// public Dimension getDim() {
// return dim;
// }

// public Model getModel() {
// return model;
// }

// public void printXY(int x, int y) {
// SwingUtilities.invokeLater(() -> {
// System.out.println("X:" + x + ", Y:" + y);
// });
// }

// @Override
// public void mouseClicked(MouseEvent e) {
// if (model.getTurn() == player_b.getColor()) {
// int x = e.getX();
// int y = e.getY();
// int index_X;
// int index_Y;
// System.out.println("x= " + x + "y = " + y);

// if (10 <= x && x <= 490 && 10 <= y && y <= 490) {
// index_X = checkIndex(x);
// index_Y = checkIndex(y);
// if (model.getBoard(index_X, index_Y) == 0) {
// if (model.getTurn() == BLACK && model.getBlackPositionable(index_X, index_Y)
// == true) {
// model.setBoard(index_X, index_Y, BLACK);
// changePiece(index_X, index_X);
// model.setTurn(WHITE);
// } else if (model.getTurn() == WHITE && model.getWhitePositionable(index_X,
// index_Y) == true) {
// model.setBoard(index_X, index_Y, WHITE);
// changePiece(index_X, index_Y);
// model.setTurn(BLACK);
// } else {
// System.out.println("そこには置けません(mouseClicked())");
// }
// calcPositionable();
// board.repaint();
// }
// }
// } else if (model.getTurn() == player_w.getColor()) { // テスト用
// int x = e.getX();
// int y = e.getY();
// int index_X;
// int index_Y;
// System.out.println("x= " + x + "y = " + y);

// if (10 <= x && x <= 490 && 10 <= y && y <= 490) {
// index_X = checkIndex(x);
// index_Y = checkIndex(y);
// if (model.getBoard(index_X, index_Y) == 0) {
// if (model.getTurn() == BLACK && model.getBlackPositionable(index_X, index_Y)
// == true) {
// model.setBoard(index_X, index_Y, BLACK);
// changePiece(index_X, index_Y);
// model.setTurn(WHITE);
// } else if (model.getTurn() == WHITE && model.getWhitePositionable(index_X,
// index_Y) == true) {
// model.setBoard(index_X, index_Y, WHITE);
// changePiece(index_X, index_Y);
// model.setTurn(BLACK);
// } else {
// System.out.println("そこには置けません(mouseClicked())");
// }
// calcPositionable();
// board.repaint();
// }
// }
// }
// }

// @Override
// public void mousePressed(MouseEvent e) {
// // TODO Auto-generated method stub

// }

// @Override
// public void mouseReleased(MouseEvent e) {
// // TODO Auto-generated method stub

// }

// @Override
// public void mouseEntered(MouseEvent e) {
// // TODO Auto-generated method stub

// }

// @Override
// public void mouseExited(MouseEvent e) {
// // TODO Auto-generated method stub

// }

// //
// public int checkIndex(int n) {
// int index = 0;
// if (n >= 10) {
// index = (n - 10) / 60;
// }
// return index;
// }

// // 置ける場所の計算
// public void calcPositionable() {
// // 初めにすべてfalseに
// for (int i = 0; i < 8; i++) {
// for (int j = 0; j < 8; j++) {
// model.setWhitePositionable(i, j, false);
// model.setBlackPositionable(i, j, false);
// }
// }
// for (int i = 0; i < 8; i++) {
// for (int j = 0; j < 8; j++) {
// // 現在探索中の駒が空
// if (model.getBoard(i, j) != 0) {
// continue;
// } else {
// int next;
// // 右方向の探索
// if (i < 6) {
// next = model.getBoard(i + 1, j);
// if (next != 0) {
// for (int m = 2; m < 8 - i; m++) {
// if (model.getBoard(i + m, j) == 0) {
// break;
// } else if (model.getBoard(i + m, j) == next) {
// continue;
// } else if (model.getBoard(i + m, j) != next) {
// if (next == 1) {
// model.setWhitePositionable(i, j, true);
// } else {
// model.setBlackPositionable(i, j, true);
// }
// break;
// }
// }
// }
// }

// // 左方向の探索
// if (i > 1) {
// next = model.getBoard(i - 1, j);
// if (next != 0) {
// for (int m = 2; m <= i - 1; m++) {
// if (model.getBoard(i - m, j) == 0) {
// break;
// } else if (model.getBoard(i - m, j) == next) {
// continue;
// } else if (model.getBoard(i - m, j) != next) {
// if (next == 1) {
// model.setWhitePositionable(i, j, true);
// } else {
// model.setBlackPositionable(i, j, true);
// }
// break;
// }
// }
// }
// }

// // 上方向の探索(ok)
// if (j > 1) {
// next = model.getBoard(i, j - 1);
// if (next != 0) {
// for (int m = 2; m <= j - 1; m++) {
// if (model.getBoard(i, j - m) == 0) {
// break;
// } else if (model.getBoard(i, j - m) == next) {
// continue;
// } else if (model.getBoard(i, j - m) != next) {
// if (next == 1) {
// model.setWhitePositionable(i, j, true);
// } else {
// model.setBlackPositionable(i, j, true);
// }
// break;
// }
// }
// }
// }

// // 下方向の探索
// if (j < 6) {
// next = model.getBoard(i, j + 1);
// if (next != 0) {
// for (int m = 2; m <= 8 - j; m++) {
// if (model.getBoard(i, j + m) == 0) {
// break;
// } else if (model.getBoard(i, j + m) == next) {
// continue;
// } else {
// if (next == 1) {
// model.setWhitePositionable(i, j, true);
// } else {
// model.setBlackPositionable(i, j, true);
// }
// break;
// }
// }
// }
// }

// int len; // i,jの壁まで短いほうを採用する
// // 右上方向の探索
// if (i < 6 && j > 1) {
// next = model.getBoard(i + 1, j - 1);
// if (next != 0) {
// len = 7 - i;
// if (len > j) {
// len = j;
// }
// for (int m = 2; m <= len; m++) {
// if (model.getBoard(i + m, j - m) == 0) {
// break;
// } else if (model.getBoard(i + m, j - m) == next) {
// continue;
// } else if (model.getBoard(i + m, j - m) != next) {
// if (next == 1) {
// model.setWhitePositionable(i, j, true);
// } else {
// model.setBlackPositionable(i, j, true);
// }
// break;
// }
// }
// }
// }

// // 右下(ok)
// if (i < 6 && j < 7) {
// next = model.getBoard(i + 1, j + 1);
// if (next != 0) {
// len = 7 - i;
// if (len < j) {
// len = 7 - j;
// }
// for (int m = 2; m <= len; m++) {
// if (model.getBoard(i + m, j + m) == 0) {
// break;
// } else if (model.getBoard(i + m, j + m) == next) {
// continue;
// } else if (model.getBoard(i + m, j + m) != next) {
// if (next == 1) {
// model.setWhitePositionable(i, j, true);
// } else {
// model.setBlackPositionable(i, j, true);
// }
// break;
// }
// }
// }
// }
// // 左上
// if (i > 1 && j > 1) {
// next = model.getBoard(i - 1, j - 1);
// if (next != 0) {
// len = i;
// if (len > j) {
// len = j;
// }
// for (int m = 2; m <= len; m++) {
// if (model.getBoard(i - m, j - m) == 0) {
// break;
// } else if (model.getBoard(i - m, j - m) == next) {
// continue;
// } else if (model.getBoard(i - m, j - m) != next) {
// if (next == 1) {
// model.setWhitePositionable(i, j, true);
// } else {
// model.setBlackPositionable(i, j, true);
// }
// break;
// }
// }
// }
// }
// // 左下(ok)
// if (i > 1 && j < 6) {
// next = model.getBoard(i - 1, j + 1);
// if (next != 0) {
// len = i;
// if (7 - j < i) {
// len = 7 - j;
// }
// for (int m = 2; m <= len; m++) {
// if (model.getBoard(i - m, j + m) == 0) {
// break;
// } else if (model.getBoard(i - m, j + m) == next) {
// continue;
// } else if (model.getBoard(i - m, j + m) != next) {
// if (next == 1) {
// model.setWhitePositionable(i, j, true);
// } else {
// model.setBlackPositionable(i, j, true);
// }
// break;
// }
// }
// }
// }
// }
// }
// }
// }

// public void changePiece(int index_X, int index_Y) {
// int[][] tentative = new int[8][8];
// for (int i = 0; i < 8; i++) {
// for (int j = 0; j < 8; j++) {
// tentative[i][j] = model.getBoard(i, j);
// }
// }
// int i = index_X;
// int j = index_Y;
// int next;
// int now = model.getBoard(index_X, index_Y);
// // 右方向の探索(ok)
// if (index_X < 6) {
// next = model.getBoard(index_X + 1, j);
// if (next != now && next != 0) {
// for (int m = 2; m < 8 - index_X; m++) {
// if (model.getBoard(index_X + m, j) == 0) {
// break;
// } else if (model.getBoard(i + m, j) == next) {
// continue;
// } else if (model.getBoard(i + m, j) != next) {
// for (int k = 1; k <= m - 1; k++) {
// if (next == BLACK) {
// tentative[i + k][j] = WHITE;
// } else {
// tentative[i + k][j] = BLACK;
// }
// }
// break;
// }
// }
// }
// }

// // 左方向の探索 (ok)
// if (i > 1) {
// next = model.getBoard(i - 1, j);
// if (next != now && next != 0) {
// for (int m = 2; m <= i - 1; m++) {
// if (model.getBoard(i - m, j) == 0) {
// break;
// } else if (model.getBoard(i - m, j) == next) {
// continue;
// } else if (model.getBoard(i - m, j) != next) {
// for (int k = 1; k <= m - 1; k++) {
// if (next == BLACK) {
// tentative[i - k][j] = WHITE;
// } else {
// tentative[i - k][j] = BLACK;
// }
// }
// break;
// }
// }
// }
// }

// // 上方向の探索
// if (j > 1) {
// next = model.getBoard(i, j - 1);
// if (next != now && next != 0) {
// for (int m = 2; m <= j - 1; m++) {
// if (model.getBoard(i, j - m) == 0) {
// break;
// } else if (model.getBoard(i, j - m) == next) {
// continue;
// } else if (model.getBoard(i, j - m) != next) {
// for (int k = 1; k <= m - 1; k++) {
// if (next == BLACK) {
// tentative[i][j - k] = WHITE;
// } else {
// tentative[i][j - k] = BLACK;
// }
// }
// break;
// }
// }
// }

// // 下方向の探索(ok)
// if (j < 6) {
// next = model.getBoard(i, j + 1);
// if (next != now && next != 0) {
// for (int m = 2; m <= 8 - j; m++) {
// if (model.getBoard(i, j + m) == 0) {
// break;
// } else if (model.getBoard(i, j + m) == next) {
// continue;
// } else if (model.getBoard(i, j + m) != next) {
// for (int k = 1; k <= m - 1; k++) {
// if (next == BLACK) {
// tentative[i][j + k] = WHITE;
// } else {
// tentative[i][j + k] = BLACK;
// }
// }
// break;
// }
// }
// }
// }

// int len; // i,jの壁まで短いほうを採用する
// // 右上方向の探索
// if (i < 6 && j > 1) {
// next = model.getBoard(i + 1, j - 1);
// if (next != now && next != 0) {
// len = 7 - i;
// if (len > j) {
// len = j;
// }
// for (int m = 2; m <= len; m++) {
// if (model.getBoard(i + m, j - m) == 0) {
// break;
// } else if (model.getBoard(i + m, j - m) == next) {
// continue;
// } else if (model.getBoard(i + m, j - m) != next) {
// for (int k = 1; k <= m - 1; k++) {
// if (next == BLACK) {
// tentative[i + k][j - k] = WHITE;
// } else {
// tentative[i + k][j - k] = BLACK;
// }
// }
// break;
// }
// }
// }
// }

// // 右下(ok)
// if (i < 6 && j < 7) {
// next = model.getBoard(i + 1, j + 1);
// if (next != now && next != 0) {
// len = 7 - i;
// if (len < j) {
// len = 7 - j;
// }
// for (int m = 2; m <= len; m++) {
// if (model.getBoard(i + m, j + m) == 0) {
// break;
// } else if (model.getBoard(i + m, j + m) == next) {
// continue;
// } else if (model.getBoard(i + m, j + m) != next) {
// for (int k = 1; k <= m - 1; k++) {
// if (next == BLACK) {
// tentative[i + k][j + k] = WHITE;
// } else {
// tentative[i + k][j + k] = BLACK;
// }
// }
// break;
// }
// }
// }
// }
// // 左上(ok)
// if (i > 1 && j > 1) {
// next = model.getBoard(i - 1, j - 1);
// if (next != now && next != 0) {
// len = i;
// if (len > j) {
// len = j;
// }
// for (int m = 2; m <= len; m++) {
// if (model.getBoard(i - m, j - m) == 0) {
// break;
// } else if (model.getBoard(i - m, j - m) == next) {
// continue;
// } else if (model.getBoard(i - m, j - m) != next) {
// for (int k = 1; k <= m - 1; k++) {
// if (next == BLACK) {
// tentative[i - k][j - k] = WHITE;
// } else {
// tentative[i - k][j - k] = BLACK;
// }
// }
// break;
// }
// }
// }
// }
// // 左下(ok)
// if (i > 1 && j < 6) {
// next = model.getBoard(i - 1, j + 1);
// if (next != now && next != 0) {
// len = i;
// if (7 - j < i) {
// len = 7 - j;
// }
// for (int m = 2; m <= len; m++) {
// if (model.getBoard(i - m, j + m) == 0) {
// break;
// } else if (model.getBoard(i - m, j + m) == next) {
// continue;
// } else if (model.getBoard(i - m, j + m) != next) {
// for (int k = 1; k <= m - 1; k++) {
// if (next == BLACK) {
// tentative[i - k][j + k] = WHITE;
// } else {
// tentative[i - k][j + k] = BLACK;
// }
// }
// break;
// }
// }
// }
// }
// }
// for (int m = 0; i < 8; i++) {
// for (int n = 0; j < 8; j++) {
// model.setBoard(m, n, tentative[i][j]);
// }
// }
// test(tentative);
// }

// private void test(int[][] t) {
// for (int i = 0; i < 8; i++) {
// for (int j = 0; j < 8; j++) {
// System.out.print(t[j][i] + ",");
// }
// System.out.println();
// }
// }
// }
=======
	public void printXY(int x, int y) {
		SwingUtilities.invokeLater(() -> {
			System.out.println("X:" + x + ", Y:" + y);
		});
	}
	
	@Override
    public void mouseClicked(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        controller.printXY(x, y);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mousePressed'");
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mouseReleased'");
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mouseEntered'");
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mouseExited'");
    }
}
>>>>>>> 110ebc744e396a07fa71ba8250264fb46e45aa6c
