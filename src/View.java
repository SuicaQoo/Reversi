import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JPanel;

public class View extends JPanel {
    private Controller controller; // 非静的フィールドに変更
    // static JPanel pane = controller.getPane();
    // static final int width = pane.getSize().width;
    // static final int height = pane.getSize().height;

    private static final int BOARD_SIZE = 8; // 縦横何マスか
    private static final int SQUARE_SIZE = 58; // マス目のサイズ
    private static int width;
    private static int height;
    private int SQUARE_SIZE_WIDTH; // マス目のサイズ
    private int SQUARE_SIZE_HEIGHT; // マス目のサイズ

    public View(Controller controller) {
        setBackground(Color.GREEN); // 背景色を緑に設定
        this.controller = controller;
        setBackground(Color.GREEN);
        setBackground(Color.GREEN);
        // setSize(controller.getWidth(), controller.getHeight());
        // サイズ変更時のイベントを定義
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                if (controller != null) {
                    Dimension dim = controller.getDim();
                    width = (int) dim.getWidth();
                    height = (int) dim.getHeight();
                    SQUARE_SIZE_WIDTH = width / BOARD_SIZE;
                    SQUARE_SIZE_HEIGHT = height / BOARD_SIZE;
                    controller.setViewSize(dim); // ControllerのsetViewSizeメソッドを安全に呼び出します
                }
            }
        });
    }

    public void updateBoard() {
        // TODO
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        Color color = new Color(0, 0, 0); // 黙色に変更

        // マス目の線を描く
        for (int i = 0; i <= BOARD_SIZE; i++) {
            g2d.setColor(color);
            g2d.drawLine(i * SQUARE_SIZE + 10, 10, i * SQUARE_SIZE + 10, BOARD_SIZE * SQUARE_SIZE + 10);
            g2d.drawLine(10, i * SQUARE_SIZE + 10, BOARD_SIZE * SQUARE_SIZE + 10, i * SQUARE_SIZE + 10);
        }
    }
    /*
     * public static void main(String[] args) {
     * // GUIを表示するためのフレームを作成
     * JFrame frame = new JFrame("Reversi Board");
     * frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     * frame.setSize(480, 520); // フレームのサイズ
     * 
     * // ReversiBoardのインスタンスを作成し、フレームに追加
     * View board = new View();
     * frame.add(board);
     * 
     * frame.setVisible(true);
     * }
     */
}
