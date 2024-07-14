import java.awt.BorderLayout;
import java.awt.Color;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

//import OthelloClient.BoardPanel;

public class Client {
    private static JFrame frame;
    private static JPanel pane;
    private static View boardPanel;
    private static TimeLimitBar timeLimitBar; // 時間制限バー
    private static Model model = null;
    private static ObjectOutputStream out = null;
    private static ObjectInputStream in = null;

    private static int myColor;

    private static final int BLACK = 1;
    private static final int WHITE = 2;

    public static void main(String[] args) {
        String serverAddress = "localhost";
        int port = 12345;
        Object obj = null;
        String response = "";
        int nowTurn;

        try (Socket socket = new Socket(serverAddress, port);) {
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());

            System.out.println("connected");

            String code = "getModel";
            out.writeObject(code);
            obj = in.readObject();
            if (obj instanceof Model) {
                model = (Model) obj;
                System.out.println("got model");
            }

            initializeGUI();

            obj = in.readObject();
            if (obj instanceof String) {
                response = (String) obj;
                if (response.startsWith("WELCOME")) {
                    if (response.split(" ")[1].equals("B")) {
                        myColor = BLACK;
                    } else {
                        myColor = WHITE;
                    }
                    nowTurn = Integer.parseInt(response.split(" ")[2]);
                    System.out.println(myColor);
                    System.out.println(nowTurn);
                }
            }

            // 自分の駒の色をViewに持たせる
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    boardPanel.setColor(myColor);
                }
            });

            while (true) {

                try {
                    SwingUtilities.invokeLater(() -> repaint());
                    obj = in.readObject(); // モデルを受信
                    if (obj instanceof Model) {
                        model = (Model) obj;
                        SwingUtilities.invokeLater(() -> {
                            boardPanel.setModel(model);
                            boardPanel.repaint(); // UIスレッドで再描画
                        });
                    }
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void initializeGUI() {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                frame = new JFrame("Client");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(520, 550);
                frame.setResizable(false);

                pane = new JPanel(new BorderLayout());
                pane.setBackground(Color.WHITE);
                frame.add(pane);

                timeLimitBar = new TimeLimitBar(); // 時間制限バーの初期化
                pane.add(timeLimitBar, BorderLayout.NORTH);

                boardPanel = new View(model, out); // View クラスを利用した表示パネルの初期化
                pane.add(boardPanel, BorderLayout.CENTER);
                boardPanel.setModel(model);

                frame.setVisible(true); // モデルを受信した後にフレームを表示
            }
        });

    }

    private static void repaint() {
        boardPanel.repaint();
    }
}
