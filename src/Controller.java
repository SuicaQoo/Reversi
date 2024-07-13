import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

<<<<<<< HEAD
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Controller extends JFrame implements MouseListener{
=======
public class Controller extends JFrame implements MouseListener {
>>>>>>> 54b304d3e1123d8b55a13996cf52555b88bfec40
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
			controller.getTimeLimitBar().start();

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

	public TimeLimitBar getTimeLimitBar() {
		return timeLimitBar;
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
<<<<<<< HEAD
	
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
=======

	@Override
	public void mouseClicked(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		printXY(x, y);
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
>>>>>>> 54b304d3e1123d8b55a13996cf52555b88bfec40
}
