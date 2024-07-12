import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class ReversiBoard extends JPanel {

    private static final int BOARD_SIZE = 8;
    private static final int SQUARE_SIZE = 58; // マス目のサイズ

    public ReversiBoard() {
        setSize(480,480);
        setBackground(Color.GREEN); // 背景色を緑に設定
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        Color color = new Color(0, 0, 0); // 黙色に変更

        // マス目の線を描く
        for (int i = 0; i <= BOARD_SIZE; i++) {
            g2d.setColor(color);
            g2d.drawLine(i * SQUARE_SIZE, 0, i * SQUARE_SIZE, BOARD_SIZE * SQUARE_SIZE);
            g2d.drawLine(0, i * SQUARE_SIZE, BOARD_SIZE * SQUARE_SIZE, i * SQUARE_SIZE);
        }
    }

    public static void main(String[] args) {
        // GUIを表示するためのフレームを作成
        JFrame frame = new JFrame("Reversi Board");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(480, 520); // フレームのサイズ

        // ReversiBoardのインスタンスを作成し、フレームに追加
        ReversiBoard board = new ReversiBoard();
        frame.add(board);

        frame.setVisible(true);
    }
}
