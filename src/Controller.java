import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Controller extends JFrame {
	private JPanel pane; // contentPane
	TimeLimitBar timeLimitBar; // 時間制限バー
	Model model; // データ保持変数
	View board; // 描画変数
	Dimension dim;

	// main

	/*
	 * public static void main(String arg[]) {
	 * Controller frame = new Controller();
	 * frame.setVisible(true);
	 * frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	 * frame.setSize(500, 520);
	 * frame.setDimension();
	 * }
	 *
	 */

	// main
	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			Controller controller = new Controller();
			controller.setSize(500, 535);
			controller.setResizable(false);
			controller.setVisible(true);
			// controller.setDimension();
		});
	}

	// コンストラクタ
	public Controller() {
		pane = (JPanel) getContentPane();
		pane.setLayout(new BorderLayout());
		pane.setBackground(Color.WHITE);
		// model = new model();
		// System.out.println("width =" + get + " height = " + pane.getHeight());

		// 制限時間バーの追加
		timeLimitBar = new TimeLimitBar();
		pane.add(timeLimitBar, BorderLayout.NORTH);

		// テスト
		// timeLimitBar.start();

		// ボードの追加
		initializeView();
		// board = new View();
		// pane.add(board, BorderLayout.CENTER);
	}

	private void initializeView() {
		board = new View(this); // Controllerのインスタンス（this）をViewに渡します
		setView(board); // boardをフレームに追加
	}

	public void setView(View view) {
		pane.add(view, BorderLayout.CENTER);
	}

	public void setViewSize(Dimension dim) {
		// Viewのサイズを更新するための処理
		board.setSize(dim);
	}

	public JPanel getPane() {
		return pane;
	}

	public void setDimension() {
		dim = getContentPane().getSize();
		System.out.println("width = " + dim.getWidth() + " height = " + dim.getHeight());
		setViewSize(dim);
	}

	public Dimension getDim() {
		return dim;
	}

	public void printXY(int x, int y) {
		SwingUtilities.invokeLater(() -> {
			System.out.println("X:" + x + ", Y:" + y);
		});
	}
}
