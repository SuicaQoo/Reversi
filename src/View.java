import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.swing.JPanel;

public class View extends JPanel {
    private ObjectOutputStream out;
    // private Controller controller; // 非静的フィールドに変更
    private Model model;

    private static final int BOARD_SIZE = 8; // 縦横何マスか
    private static final int SQUARE_SIZE = 60; // マス目のサイズ
    private static final int CIRCLE_SIZE = SQUARE_SIZE - 2;

    private static final int BLACK = 1;
    private static final int WHITE = 2;

    public View(Model model, ObjectOutputStream out) {
        setBackground(Color.GREEN); // 背景色を緑に設定
        this.model = model;
        this.out = out;
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    int x = e.getX();
                    int y = e.getY();
                    out.writeObject(x + "," + y);
                    // SwingUtilities.invokeLater(() -> repaint());
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;

        drawBoard(g2d);
        drawPiece(g2d);
        drawPositionable(g2d);
    }

    public void updateBoard() {
        paintComponent(getGraphics());
    }

    private void drawBoard(Graphics2D g2d) {
        Color color = new Color(0, 0, 0); // 黒色の線を描画

        // マス目の線を描く
        for (int i = 0; i <= BOARD_SIZE; i++) {
            g2d.setColor(color);
            g2d.drawLine(i * SQUARE_SIZE + 10, 10, i * SQUARE_SIZE + 10, BOARD_SIZE * SQUARE_SIZE + 10);
            g2d.drawLine(10, i * SQUARE_SIZE + 10, BOARD_SIZE * SQUARE_SIZE + 10, i * SQUARE_SIZE + 10);
        }
    }

    private void drawPiece(Graphics2D g2d) {
        int[][] board = model.getBoard();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[i][j] != 0) {
                    if (board[i][j] == 1) {
                        // 黒円
                        Color color = new Color(0, 0, 0);
                        g2d.setColor(color);
                        g2d.fillOval(i * SQUARE_SIZE + 10 + 1, j * SQUARE_SIZE + 10 + 1, CIRCLE_SIZE, CIRCLE_SIZE);
                    } else {
                        // 白円
                        Color color = new Color(255, 255, 255);
                        g2d.setColor(color);
                        g2d.fillOval(i * SQUARE_SIZE + 10 + 1, j * SQUARE_SIZE + 10 + 1, CIRCLE_SIZE, CIRCLE_SIZE);
                    }
                }
            }
        }
    }

    private void drawPositionable(Graphics2D g2d) {
        boolean[][] whitePositionable = model.getWhitePositionable();
        boolean[][] blackPositionable = model.getBlackPositionable();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (model.getTurn() == WHITE && whitePositionable[i][j] == true) {
                    // 白
                    Color color = new Color(0, 0, 0);
                    g2d.setColor(color);
                    g2d.drawString("白", i * SQUARE_SIZE + 10 + SQUARE_SIZE / 2, j * SQUARE_SIZE + 10 + SQUARE_SIZE / 2);
                } else if (model.getTurn() == BLACK && blackPositionable[i][j] == true) {
                    // 黒
                    Color color = new Color(0, 0, 0);
                    g2d.setColor(color);
                    g2d.drawString("黒", i * SQUARE_SIZE + 10 + SQUARE_SIZE / 2, j * SQUARE_SIZE + 10 + SQUARE_SIZE / 2);
                }
            }
        }
    }

    public void setModel(Model model) {
        this.model = model;
    }
}
