import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class View extends JPanel {
    private ObjectOutputStream out;
    private ObjectInputStream in;
    private Model model; // 非静的フィールドに変更
    private boolean isBlack;
    private boolean canTouch;

    private static final int BOARD_SIZE = 8; // 縦横何マスか
    private static final int SQUARE_SIZE = 60; // マス目のサイズ
    private static final int CIRCLE_SIZE = SQUARE_SIZE - 2;

    private static final int BLACK = 1;
    private static final int WHITE = 2;

    public View(Model model, ObjectOutputStream out, ObjectInputStream in, int myColor) {
        setBackground(Color.GREEN); // 背景色を緑に設定
        this.model = model;
        this.out = out;
        this.in = in;
        if (myColor == BLACK) {
            isBlack = true;
        } else if (myColor == WHITE) {
            isBlack = false;
        }
        this.canTouch = true;
        addMouseListener(new CustomMouseListener());

    }

    public boolean getMyColor() {
        return isBlack;
    }

    public void setMyColor(int color) {
        if (color == BLACK) {
            isBlack = true;
        } else {
            isBlack = false;
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;

        drawBackground(g2d);
        drawBoard(g2d);
        drawPiece(g2d);
        drawPositionable(g2d);
        System.out.println("repainted");
    }

    private void drawBackground(Graphics2D g2d) {
        Color color = new Color(0, 255, 0);
        g2d.setColor(color);
        g2d.setBackground(color);
        setBackground(color);
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
                Color color = new Color(0, 0, 0);
                if (blackPositionable[i][j] == true) {
                    g2d.setColor(color);
                    g2d.drawString("黒", i * SQUARE_SIZE + 10 + SQUARE_SIZE / 2, j * SQUARE_SIZE + 10 + SQUARE_SIZE / 3);
                }
                if (whitePositionable[i][j] == true) {
                    g2d.setColor(color);
                    g2d.drawString("白", i * SQUARE_SIZE + 10 + SQUARE_SIZE / 2,
                            j * SQUARE_SIZE + 10 + SQUARE_SIZE / 3 * 2);
                }
            }
        }
    }

    private class CustomMouseListener extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            if (canTouch == true) {
                try {
                    int x = e.getX();
                    int y = e.getY();
                    if (getMyColor() == model.getTurn()) {
                        // 黒なら
                        boolean[][] temp_Positionable;
                        if (getMyColor() == true) {
                            temp_Positionable = model.getBlackPositionable();
                            boolean isPass = true;
                            for (int i = 0; i < 8; i++) {
                                for (int j = 0; j < 8; j++) {
                                    if (temp_Positionable[i][j] == true) {
                                        isPass = false;
                                        break;
                                    }
                                }
                                if (isPass == false) {
                                    break;
                                }
                            }
                            if (isPass == true) {
                                out.writeObject("MyTurnIsPass");
                                boolean nextTurn = (boolean) in.readObject();
                                System.out.println("スキップされました");
                                model.setTurn(nextTurn);
                            } else {
                                out.writeObject(x + "," + y);
                            }
                        } else {
                            temp_Positionable = model.getWhitePositionable();
                            boolean isPass = true;
                            for (int i = 0; i < 8; i++) {
                                for (int j = 0; j < 8; j++) {
                                    if (temp_Positionable[i][j] == true) {
                                        isPass = false;
                                        break;
                                    }
                                }
                                if (isPass == false) {
                                    break;
                                }
                            }
                            if (isPass == true) {
                                out.writeObject("MyTurnIsPass");
                                boolean nextTurn = (boolean) in.readObject();
                                System.out.println("スキップされました");
                                model.setTurn(nextTurn);
                            } else {
                                out.writeObject(x + "," + y);
                            }
                        }
                    } else {
                        out.writeObject("forUpdate");
                    }

                    int[][] t = (int[][]) in.readObject();
                    for (int i = 0; i < 8; i++) {
                        for (int j = 0; j < 8; j++) {
                            System.out.print(t[i][j]);
                        }
                        System.out.println();
                    }
                    System.out.println("got a new board");
                    model.setBoard(t);
                    boolean[][] b_p = (boolean[][]) in.readObject();
                    for (int i = 0; i < 8; i++) {
                        for (int j = 0; j < 8; j++) {
                            if (b_p[i][j] == true) {
                                System.out.print("T");
                            } else {
                                System.out.print("F");
                            }
                        }
                        System.out.println();
                    }
                    System.out.println("got a new blackPositionable");
                    model.setBlackPositionable(b_p);
                    boolean[][] w_p = (boolean[][]) in.readObject();
                    for (int i = 0; i < 8; i++) {
                        for (int j = 0; j < 8; j++) {
                            if (b_p[i][j] == true) {
                                System.out.print("T");
                            } else {
                                System.out.print("F");
                            }
                        }
                        System.out.println();
                    }
                    System.out.println("got a new whitePositionable");
                    model.setWhitePositionable(w_p);

                    System.out.println("turn set");
                    boolean turnIsBlack = true;
                    turnIsBlack = (boolean) in.readObject();
                    model.setTurn(turnIsBlack);

                    System.out.println("check finish");
                    boolean isFinish = (boolean) in.readObject();

                    System.out.println("count pieces");
                    int blackCount = (int) in.readObject();
                    int whiteCount = (int) in.readObject();
                    model.setCountBlack(blackCount);
                    model.setCountWhite(whiteCount);
                    System.out.println("b:" + model.getCountBlack() + " w:" + model.getCountWhite());

                    if (isFinish == true) {
                        int totalBlack = model.getCountBlack();
                        int totalWhite = model.getCountWhite();
                        System.out.println("ゲーム終了です");
                        if (totalBlack > totalWhite) {
                            System.out.println(totalBlack + "対" + totalWhite + "で黒の勝ち");
                        } else if (totalWhite > totalBlack) {
                            System.out.println(totalBlack + "対" + totalWhite + "で白の勝ち");
                        } else {
                            System.out.println(totalBlack + "対" + totalWhite + "で引き分け");
                        }
                        canTouch = false;
                        SwingUtilities.invokeLater(() -> repaint());
                    } else {
                        SwingUtilities.invokeLater(() -> repaint());
                    }
                    // if (obj instanceof Model) {
                    // model = (Model)obj;
                    // System.out.println("got a new model");
                    // repaint();
                    // }
                } catch (IOException | ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
}
