import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JProgressBar;
import javax.swing.Timer;

public class TimeLimitBar extends JProgressBar {
    private Timer timer;
    private int timeAllowed;
    private boolean isTimeLimit;
    private boolean isMyTurn;

    TimeLimitBar() {
        super(0, 100);
        setTimeAllowed(100);
        isTimeLimit = false;
        isMyTurn = true;
    }
    
    private void setTimeAllowed(int time) {
        timeAllowed = time;
    }
    public boolean checkTimeLimit() {
        return this.isTimeLimit;
    }

    public void Update() {
        
        if (timer == null) {
            timer = new Timer(100, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    timeAllowed--;
                    setValue(timeAllowed);
                    if (timeAllowed < 0) {
                        changeTurn();
                        reset();
                    }
                }
            });
        }
        timer.start();
    }

    public void reset() {
        timer.stop();
        timer = null;
        timeAllowed = 100;
        isTimeLimit = true;
    }
    
    // 自分のターンかつタイマーが動いていないとき反応する。
    // プログレスバーの開始ボタン。Update関数を動かす
    public void start() {
        if (isMyTurn == true) {
            Update();
        }
    }
    
    // 置く場所選ぶと制限時間切れ待たずに、相手ターンにする。
    // 自分のターンかつタイマーが動いているときに限り反応する。
    public void select() {
        if (timer != null && isMyTurn == true) {
            reset();
            changeTurn();
        }
    }
    
    private void changeTurn() {
        isMyTurn = !isMyTurn;
    }
    
    public void showMyTurn() {
        System.out.println("MyTurn:" + isMyTurn);
    }
}