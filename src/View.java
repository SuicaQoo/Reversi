import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;


import javax.swing.JPanel;

public class View extends JPanel {
    private Controller controller; // 非静的フィールドに変更

    private static final int BOARD_SIZE = 8; // 縦横何マスか
    private static final int SQUARE_SIZE = 58; // マス目のサイズ

    public View(Controller controller) {
        setBackground(Color.GREEN); // 背景色を緑に設定
        this.controller = controller;
        setBackground(Color.GREEN);
        setBackground(Color.GREEN);
    }

    public void updateBoard() {
        // TODO
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        drawBoard(g2d);
    }

    private void drawBoard(Graphics2D g2d) {
        Color color = new Color(0, 0, 0); // 黙色に変更

        // マス目の線を描く
        for (int i = 0; i <= BOARD_SIZE; i++) {
            g2d.setColor(color);
            g2d.drawLine(i * SQUARE_SIZE + 10, 10, i * SQUARE_SIZE + 10, BOARD_SIZE * SQUARE_SIZE + 10);
            g2d.drawLine(10, i * SQUARE_SIZE + 10, BOARD_SIZE * SQUARE_SIZE + 10, i * SQUARE_SIZE + 10);
        }
    }

    
}
