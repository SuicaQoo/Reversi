import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;



public class Controller extends JFrame implements MouseListener{
    JPanel pane; // contentPane
    JPanel board; // ボード
    JProgressBar timeLimitBar; // 時間制限バー
    Model model; // データ保持変数
	

    // main
    public static void main(String arg[]){
        JFrame frame = new Controller();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 520);
    }

    // コンストラクタ
    public Controller(){
        pane = (JPanel)getContentPane();
        pane.setLayout(new BorderLayout());
        pane.setBackground(Color.WHITE);
        //model = new model();
        timeLimitBar = new TimeLimitBar();
        add(timeLimitBar);

		// 制限時間バーの追加
		timeLimitBar = new TimeLimitBar();
		pane.add(timeLimitBar, BorderLayout.NORTH);

		// ボードの追加
        JPanel board = new View();
		pane.add(board, BorderLayout.CENTER);
    }

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'mouseClicked'");
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