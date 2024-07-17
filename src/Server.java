import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Server {
    private static final int BLACK = 1;
    private static final int WHITE = 2;
    private static Model model;
    private static List<ClientHandler> clients = Collections.synchronizedList(new ArrayList<>());
    private static ServerSocket serverSocket = null;

    public static void main(String[] args) {
        model = new Model();
        model.getTurn();
        calcPositionable();

        try {
            serverSocket = new ServerSocket(12345);
            System.out.println("Server started on port 12345");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                ClientHandler clientHandler = new ClientHandler(clientSocket);
                clients.add(clientHandler);
                new Thread(clientHandler).start();
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static Model getModel() {
        return model;
    }

    // 置ける場所の計算
    public static void calcPositionable() {
        // 初めにすべてfalseに
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                model.setWhitePositionable(i, j, false);
                model.setBlackPositionable(i, j, false);
            }
        }
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                // 現在探索中の駒が空
                if (model.getBoard(i, j) != 0) {
                    continue;
                } else {
                    int next;
                    // 右方向の探索
                    if (i < 6) {
                        next = model.getBoard(i + 1, j);
                        if (next != 0) {
                            for (int m = 2; m <= 7 - i; m++) {
                                if (model.getBoard(i + m, j) == 0) {
                                    break;
                                } else if (model.getBoard(i + m, j) == next) {
                                    continue;
                                } else if (model.getBoard(i + m, j) != next) {
                                    if (next == 1) {
                                        model.setWhitePositionable(i, j, true);
                                    } else {
                                        model.setBlackPositionable(i, j, true);
                                    }
                                    break;
                                }
                            }
                        }
                    }

                    // 左方向の探索
                    if (i > 1) {
                        next = model.getBoard(i - 1, j);
                        if (next != 0) {
                            for (int m = 2; m <= i - 1; m++) {
                                if (model.getBoard(i - m, j) == 0) {
                                    break;
                                } else if (model.getBoard(i - m, j) == next) {
                                    continue;
                                } else if (model.getBoard(i - m, j) != next) {
                                    if (next == 1) {
                                        model.setWhitePositionable(i, j, true);
                                    } else {
                                        model.setBlackPositionable(i, j, true);
                                    }
                                    break;
                                }
                            }
                        }
                    }

                    // 上方向の探索(ok)
                    if (j > 1) {
                        next = model.getBoard(i, j - 1);
                        if (next != 0) {
                            for (int m = 2; m <= j - 1; m++) {
                                if (model.getBoard(i, j - m) == 0) {
                                    break;
                                } else if (model.getBoard(i, j - m) == next) {
                                    continue;
                                } else if (model.getBoard(i, j - m) != next) {
                                    if (next == 1) {
                                        model.setWhitePositionable(i, j, true);
                                    } else {
                                        model.setBlackPositionable(i, j, true);
                                    }
                                    break;
                                }
                            }
                        }
                    }

                    // 下方向の探索
                    if (j < 6) {
                        next = model.getBoard(i, j + 1);
                        if (next != 0) {
                            for (int m = 2; m <= 7 - j; m++) {
                                if (model.getBoard(i, j + m) == 0) {
                                    break;
                                } else if (model.getBoard(i, j + m) == next) {
                                    continue;
                                } else {
                                    if (next == 1) {
                                        model.setWhitePositionable(i, j, true);
                                    } else {
                                        model.setBlackPositionable(i, j, true);
                                    }
                                    break;
                                }
                            }
                        }
                    }

                    int len; // i,jの壁まで短いほうを採用する
                    // 右上方向の探索
                    if (i < 6 && j > 1) {
                        next = model.getBoard(i + 1, j - 1);
                        if (next != 0) {
                            len = 7 - i;
                            if (len > j) {
                                len = j;
                            }
                            for (int m = 2; m <= len; m++) {
                                if (model.getBoard(i + m, j - m) == 0) {
                                    break;
                                } else if (model.getBoard(i + m, j - m) == next) {
                                    continue;
                                } else if (model.getBoard(i + m, j - m) != next) {
                                    if (next == 1) {
                                        model.setWhitePositionable(i, j, true);
                                    } else {
                                        model.setBlackPositionable(i, j, true);
                                    }
                                    break;
                                }
                            }
                        }
                    }

                    // 右下(ok)
                    if (i < 6 && j < 6) {
                        next = model.getBoard(i + 1, j + 1);
                        if (next != 0) {
                            len = 7 - i;
                            if (i < j) {
                                len = 7 - j;
                            }
                            for (int m = 2; m <= len; m++) {
                                if (model.getBoard(i + m, j + m) == 0) {
                                    break;
                                } else if (model.getBoard(i + m, j + m) == next) {
                                    continue;
                                } else if (model.getBoard(i + m, j + m) != next) {
                                    if (next == 1) {
                                        model.setWhitePositionable(i, j, true);
                                    } else {
                                        model.setBlackPositionable(i, j, true);
                                    }
                                    break;
                                }
                            }
                        }
                    }

                    // 左上
                    if (i > 1 && j > 1) {
                        next = model.getBoard(i - 1, j - 1);
                        if (next != 0) {
                            len = i;
                            if (len > j) {
                                len = j;
                            }
                            for (int m = 2; m <= len; m++) {
                                if (model.getBoard(i - m, j - m) == 0) {
                                    break;
                                } else if (model.getBoard(i - m, j - m) == next) {
                                    continue;
                                } else if (model.getBoard(i - m, j - m) != next) {
                                    if (next == 1) {
                                        model.setWhitePositionable(i, j, true);
                                    } else {
                                        model.setBlackPositionable(i, j, true);
                                    }
                                    break;
                                }
                            }
                        }
                    }
                    // 左下(ok)
                    if (i > 1 && j < 6) {
                        next = model.getBoard(i - 1, j + 1);
                        if (next != 0) {
                            len = i;
                            if (7 - j < i) {
                                len = 7 - j;
                            }
                            for (int m = 2; m <= len; m++) {
                                if (model.getBoard(i - m, j + m) == 0) {
                                    break;
                                } else if (model.getBoard(i - m, j + m) == next) {
                                    continue;
                                } else if (model.getBoard(i - m, j + m) != next) {
                                    if (next == 1) {
                                        model.setWhitePositionable(i, j, true);
                                    } else {
                                        model.setBlackPositionable(i, j, true);
                                    }
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public static void changePiece(int index_x, int index_y) {
        int next;
        int now = model.getBoard(index_x, index_y);
        System.out.println("x = " + index_x + "y = " + index_y);
        // 右方向の探索(ok)
        if (index_x < 6) {
            next = model.getBoard(index_x + 1, index_y);
            if (next != now && next != 0) {
                for (int m = 2; m < 7 - index_x; m++) {
                    if (model.getBoard(index_x + m, index_y) == 0) {
                        break;
                    } else if (model.getBoard(index_x + m, index_y) == next) {
                        continue;
                    } else if (model.getBoard(index_x + m, index_y) != next) {
                        for (int k = 1; k <= m - 1; k++) {
                            if (next == BLACK) {
                                model.setBoard(index_x + k, index_y, WHITE);
                            } else {
                                model.setBoard(index_x + k, index_y, BLACK);
                            }
                        }
                        break;
                    }
                }
            }
        }

        // 左方向の探索 (ok)
        if (index_x > 1) {
            next = model.getBoard(index_x - 1, index_y);
            if (next != now && next != 0) {
                for (int m = 2; m <= index_x; m++) {
                    if (model.getBoard(index_x - m, index_y) == 0) {
                        break;
                    } else if (model.getBoard(index_x - m, index_y) == next) {
                        continue;
                    } else if (model.getBoard(index_x - m, index_y) != next) {
                        for (int k = 1; k <= m - 1; k++) {
                            if (next == BLACK) {
                                model.setBoard(index_x - k, index_y, WHITE);
                            } else {
                                model.setBoard(index_x - k, index_y, BLACK);
                            }
                        }
                        break;
                    }
                }
            }
        }

        // 上方向の探索
        if (index_y > 1) {
            next = model.getBoard(index_x, index_y - 1);
            if (next != now && next != 0) {
                for (int m = 2; m <= index_y; m++) {
                    if (model.getBoard(index_x, index_y - m) == 0) {
                        break;
                    } else if (model.getBoard(index_x, index_y - m) == next) {
                        continue;
                    } else if (model.getBoard(index_x, index_y - m) != next) {
                        for (int k = 1; k <= m - 1; k++) {
                            if (next == BLACK) {
                                model.setBoard(index_x, index_y - k, WHITE);
                            } else {
                                model.setBoard(index_x, index_y - k, BLACK);
                            }
                        }
                        break;
                    }
                }
            }
        }

        // 下方向の探索(ok)
        if (index_y < 6) {
            next = model.getBoard(index_x, index_y + 1);
            if (next != now && next != 0) {
                for (int m = 2; m <= 7 - index_y; m++) {
                    if (model.getBoard(index_x, index_y + m) == 0) {
                        break;
                    } else if (model.getBoard(index_x, index_y + m) == next) {
                        continue;
                    } else if (model.getBoard(index_x, index_y + m) != next) {
                        for (int k = 1; k <= m - 1; k++) {
                            if (next == BLACK) {
                                model.setBoard(index_x, index_y + k, WHITE);
                            } else {
                                model.setBoard(index_x, index_y + k, BLACK);
                            }
                        }
                        break;
                    }
                }
            }
        }

        int len; // index_x,jの壁まで短いほうを採用する
        // 右上方向の探索
        if (index_x < 6 && index_y > 1) {
            next = model.getBoard(index_x + 1, index_y - 1);
            if (next != now && next != 0) {
                len = 7 - index_x;
                if (len > index_y) {
                    len = index_y;
                }
                for (int m = 2; m <= len; m++) {
                    if (model.getBoard(index_x + m, index_y - m) == 0) {
                        break;
                    } else if (model.getBoard(index_x + m, index_y - m) == next) {
                        continue;
                    } else if (model.getBoard(index_x + m, index_y - m) != next) {
                        for (int k = 1; k <= m - 1; k++) {
                            if (next == BLACK) {
                                model.setBoard(index_x + k, index_y - k, WHITE);
                            } else {
                                model.setBoard(index_x + k, index_y - k, BLACK);
                            }
                        }
                        break;
                    }
                }
            }
        }

        // 右下(ok)
        if (index_x < 6 && index_y < 6) {
            next = model.getBoard(index_x + 1, index_y + 1);
            if (next != now && next != 0) {
                len = 7 - index_x;
                if (len < index_y) {
                    len = 7 - index_y;
                }
                for (int m = 2; m <= len; m++) {
                    if (model.getBoard(index_x + m, index_y + m) == 0) {
                        break;
                    } else if (model.getBoard(index_x + m, index_y + m) == next) {
                        continue;
                    } else if (model.getBoard(index_x + m, index_y + m) != next) {
                        for (int k = 1; k <= m - 1; k++) {
                            if (next == BLACK) {
                                model.setBoard(index_x + k, index_y + k, WHITE);
                            } else {
                                model.setBoard(index_x + k, index_y + k, BLACK);
                            }
                        }
                        break;
                    }
                }
            }
        }

        // 左上(ok)
        if (index_x > 1 && index_y > 1) {
            next = model.getBoard(index_x - 1, index_y - 1);
            if (next != now && next != 0) {
                len = index_x;
                if (len > index_y) {
                    len = index_y;
                }
                for (int m = 2; m <= len; m++) {
                    if (model.getBoard(index_x - m, index_y - m) == 0) {
                        break;
                    } else if (model.getBoard(index_x - m, index_y - m) == next) {
                        continue;
                    } else if (model.getBoard(index_x - m, index_y - m) != next) {
                        for (int k = 1; k <= m - 1; k++) {
                            if (next == BLACK) {
                                model.setBoard(index_x - k, index_y - k, WHITE);
                            } else {
                                model.setBoard(index_x - k, index_y - k, BLACK);
                            }
                        }
                        break;
                    }
                }
            }
        }

        // 左下(ok)
        if (index_x > 1 && index_y < 6) {
            next = model.getBoard(index_x - 1, index_y + 1);
            if (next != now && next != 0) {
                len = index_x;
                if (7 - index_y < index_x) {
                    len = 7 - index_y;
                }
                for (int m = 2; m <= len; m++) {
                    if (model.getBoard(index_x - m, index_y + m) == 0) {
                        break;
                    } else if (model.getBoard(index_x - m, index_y + m) == next) {
                        continue;
                    } else if (model.getBoard(index_x - m, index_y + m) != next) {
                        for (int k = 1; k <= m - 1; k++) {
                            if (next == BLACK) {
                                model.setBoard(index_x - k, index_y + k, WHITE);
                            } else {
                                model.setBoard(index_x - k, index_y + k, BLACK);
                            }
                        }
                        break;
                    }
                }
            }
        }
    }

    private static class ClientHandler implements Runnable {
        private Socket socket;
        private ObjectOutputStream out;
        private ObjectInputStream in;
        private char player;
        private boolean nowTurn;

        public ClientHandler(Socket socket) {
            this.socket = socket;
        }

        public void run() {
            try {
                out = new ObjectOutputStream(socket.getOutputStream());
                in = new ObjectInputStream(socket.getInputStream());

                synchronized (clients) {
                    player = clients.size() == 1 ? 'B' : 'W';
                }

                // Clientに返すモデル用
                Object receivedObject = in.readObject();

                if (receivedObject instanceof String) {
                    String input = (String) receivedObject;
                    System.out.println("got a code");
                    if (input.equals("getModel")) {
                        out.writeObject(model);
                        System.out.println("send a model(初回用)");
                    }
                }

                out.writeObject(new String("WELCOME " + player));

                while (true) {
                    if (in != null) {
                        nowTurn = model.getTurn();
                        receivedObject = in.readObject();
                        String input;
                        String regex = "\\d+,\\d+";
                        if (receivedObject instanceof String) {
                            input = (String) receivedObject;
                            System.out.println("got a code");
                            System.out.println(input);
                            if (input.matches(regex)) {
                                String coo[] = input.split(",");
                                int x = Integer.parseInt(coo[0]);
                                int y = Integer.parseInt(coo[1]);
                                int index_X = checkIndex(x);
                                int index_Y = checkIndex(y);
                                // 現在の駒が空
                                if (model.getBoard(index_X, index_Y) == 0) {
                                    // 黒が置ける
                                    if (nowTurn == true
                                            && model.getBlackPositionable(index_X, index_Y) == true) {
                                        model.setBoard(index_X, index_Y, BLACK);
                                        model.setTurn(!nowTurn);
                                        changePiece(index_X, index_Y);
                                        calcPositionable();
                                    }
                                    // 白の駒が置ける
                                    else if (nowTurn == false
                                            && model.getWhitePositionable(index_X, index_Y) == true) {
                                        model.setBoard(index_X, index_Y, WHITE);
                                        model.setTurn(true);
                                        changePiece(index_X, index_Y);
                                        calcPositionable();
                                    }
                                    // 何も置けない
                                    else {
                                        System.out.println("そこには置けません(mouseClicked())");
                                    }

                                    // ボード情報を更新
                                    System.out.println("board info updated");
                                    int[][] t = new int[8][8];
                                    for (int i = 0; i < 8; i++) {
                                        for (int j = 0; j < 8; j++) {
                                            t[i][j] = model.getBoard(i, j);
                                            System.out.print(t[j][i]);
                                        }
                                        System.out.println("");
                                    }
                                    out.writeObject(t);

                                    System.out.println("new blackPositonable");
                                    boolean[][] b_p = new boolean[8][8];
                                    for (int i = 0; i < 8; i++) {
                                        for (int j = 0; j < 8; j++) {
                                            b_p[i][j] = model.getBlackPositionable(i, j);
                                            if (b_p[i][j] == true) {
                                                System.out.print("T");
                                            } else {
                                                System.err.print("F");
                                            }
                                        }
                                        System.out.println("");
                                    }
                                    out.writeObject(b_p);

                                    System.out.println("new whitePositonable");
                                    boolean[][] w_p = new boolean[8][8];
                                    for (int i = 0; i < 8; i++) {
                                        for (int j = 0; j < 8; j++) {
                                            w_p[i][j] = model.getWhitePositionable(i, j);
                                            if (w_p[i][j] == true) {
                                                System.out.print("T");
                                            } else {
                                                System.err.print("F");
                                            }
                                        }
                                        System.out.println("");
                                    }
                                    out.writeObject(w_p);

                                    System.out.println("print turn");
                                    boolean turn = false;
                                    turn = model.getTurn();
                                    out.writeObject(turn);

                                    System.out.println("check finish");
                                    boolean isFinish = true;
                                    for (int i = 0; i < 8; i++) {
                                        for (int j = 0; j < 8; j++) {
                                            if (model.getBlackPositionable(i, j) == true
                                                    || model.getWhitePositionable(i, j) == true) {
                                                isFinish = false;
                                            }
                                        }
                                    }
                                    out.writeObject(isFinish);

                                    System.out.println("count Pieces");
                                    int blackCount = 0;
                                    int whiteCount = 0;
                                    for (int i = 0; i < 8; i++) {
                                        for (int j = 0; j < 8; j++) {
                                            int now = 0;
                                            now = model.getBoard(i, j);
                                            if (now == BLACK) {
                                                blackCount++;
                                            } else if (now == WHITE) {
                                                whiteCount++;
                                            } else {
                                                continue;
                                            }
                                        }
                                    }
                                    out.writeObject(blackCount);
                                    out.writeObject(whiteCount);

                                }
                            }
                            // 相手ターン中にマウスクリックしたら呼び出される（ビューの更新用）
                            else if (input.equals("forUpdate")) {
                                System.out.println("board info updated");
                                int[][] t = new int[8][8];
                                for (int i = 0; i < 8; i++) {
                                    for (int j = 0; j < 8; j++) {
                                        t[i][j] = model.getBoard(i, j);
                                        System.out.print(t[j][i]);
                                    }
                                    System.out.println("");
                                }
                                out.writeObject(t);

                                System.out.println("new blackPositonable");
                                boolean[][] b_p = new boolean[8][8];
                                for (int i = 0; i < 8; i++) {
                                    for (int j = 0; j < 8; j++) {
                                        b_p[i][j] = model.getBlackPositionable(i, j);
                                        if (b_p[i][j] == true) {
                                            System.out.print("T");
                                        } else {
                                            System.err.print("F");
                                        }
                                    }
                                    System.out.println("");
                                }
                                out.writeObject(b_p);

                                System.out.println("new whitePositonable");
                                boolean[][] w_p = new boolean[8][8];
                                for (int i = 0; i < 8; i++) {
                                    for (int j = 0; j < 8; j++) {
                                        w_p[i][j] = model.getWhitePositionable(i, j);
                                        if (w_p[i][j] == true) {
                                            System.out.print("T");
                                        } else {
                                            System.err.print("F");
                                        }
                                    }
                                    System.out.println("");
                                }
                                out.writeObject(w_p);

                                System.out.println("print turn");
                                boolean turn = false;
                                turn = model.getTurn();
                                out.writeObject(turn);

                                System.out.println("check finish");
                                boolean isFinish = true;
                                for (int i = 0; i < 8; i++) {
                                    for (int j = 0; j < 8; j++) {
                                        if (model.getBlackPositionable(i, j) == true
                                                || model.getWhitePositionable(i, j) == true) {
                                            isFinish = false;
                                        }
                                    }
                                }
                                out.writeObject(isFinish);

                                System.out.println("count Pieces");
                                int blackCount = 0;
                                int whiteCount = 0;
                                for (int i = 0; i < 8; i++) {
                                    for (int j = 0; j < 8; j++) {
                                        int now = 0;
                                        now = model.getBoard(i, j);
                                        if (now == BLACK) {
                                            blackCount++;
                                        } else if (now == WHITE) {
                                            whiteCount++;
                                        } else {
                                            continue;
                                        }
                                    }
                                }
                                out.writeObject(blackCount);
                                out.writeObject(whiteCount);
                            } else if (input.equals("MyTurnIsPass")) {
                                // ターンをスキップ
                                model.setTurn(!nowTurn);
                                boolean nextTurn = true;
                                nextTurn = model.getTurn();
                                out.writeObject(nextTurn);

                                // いつもの更新
                                System.out.println("board info updated");
                                int[][] t = new int[8][8];
                                for (int i = 0; i < 8; i++) {
                                    for (int j = 0; j < 8; j++) {
                                        t[i][j] = model.getBoard(i, j);
                                        System.out.print(t[j][i]);
                                    }
                                    System.out.println("");
                                }
                                out.writeObject(t);

                                System.out.println("new blackPositonable");
                                boolean[][] b_p = new boolean[8][8];
                                for (int i = 0; i < 8; i++) {
                                    for (int j = 0; j < 8; j++) {
                                        b_p[i][j] = model.getBlackPositionable(i, j);
                                        if (b_p[i][j] == true) {
                                            System.out.print("T");
                                        } else {
                                            System.err.print("F");
                                        }
                                    }
                                    System.out.println("");
                                }
                                out.writeObject(b_p);

                                System.out.println("new whitePositonable");
                                boolean[][] w_p = new boolean[8][8];
                                for (int i = 0; i < 8; i++) {
                                    for (int j = 0; j < 8; j++) {
                                        w_p[i][j] = model.getWhitePositionable(i, j);
                                        if (w_p[i][j] == true) {
                                            System.out.print("T");
                                        } else {
                                            System.err.print("F");
                                        }
                                    }
                                    System.out.println("");
                                }
                                out.writeObject(w_p);

                                System.out.println("print turn");
                                boolean turn = false;
                                turn = model.getTurn();
                                out.writeObject(turn);

                                System.out.println("check finish");
                                boolean isFinish = true;
                                for (int i = 0; i < 8; i++) {
                                    for (int j = 0; j < 8; j++) {
                                        if (model.getBlackPositionable(i, j) == true
                                                || model.getWhitePositionable(i, j) == true) {
                                            isFinish = false;
                                        }
                                    }
                                }
                                out.writeObject(isFinish);

                                System.out.println("count Pieces");
                                int blackCount = 0;
                                int whiteCount = 0;
                                for (int i = 0; i < 8; i++) {
                                    for (int j = 0; j < 8; j++) {
                                        int now = 0;
                                        now = model.getBoard(i, j);
                                        if (now == BLACK) {
                                            blackCount++;
                                        } else if (now == WHITE) {
                                            whiteCount++;
                                        } else {
                                            continue;
                                        }
                                    }
                                }
                                out.writeObject(blackCount);
                                out.writeObject(whiteCount);
                            }
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } finally {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        public int checkIndex(int n) {
            int index = 0;
            if (n >= 10) {
                index = (n - 10) / 60;
            }
            return index;
        }

    }
}
