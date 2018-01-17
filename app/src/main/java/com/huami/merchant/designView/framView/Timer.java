package com.huami.merchant.designView.framView;
import android.os.Message;

public class Timer {
    private final int START_TIMER = 0;
    private final int STOP_TIMER = 1;
    private AndroidChannel androidChannel;
    private int interval = 0;
    private OnTimer onTimer;
    volatile boolean loop = false;

    public Timer(int interval, OnTimer onTimer) {
        this.interval = interval < 0?interval * -1:interval;
        this.onTimer = onTimer;
        this.androidChannel = new AndroidChannel(new AndroidChannel.UiCallback() {
            public boolean handleUiMessage(Message msg) {
                Timer.this.onTimer.onTime(Timer.this);
                return false;
            }
        }, new AndroidChannel.WorkerCallback() {
            Thread jobThread = null;

            public boolean handleWorkerMessage(Message msg) {
                switch(msg.what) {
                case 0:
                    Timer.this.loop = true;
                    break;
                case 1:
                    Timer.this.loop = false;
                    this.jobThread = null;
                }

                if(msg.what == 0 && this.jobThread == null) {
                    this.jobThread = new Thread(new Runnable() {
                        public void run() {
                            while(Timer.this.loop) {
                                try {
                                    Message e = Timer.this.androidChannel.toUI().obtainMessage();
                                    Timer.this.androidChannel.toUI().sendMessage(e);
                                    Thread.sleep((long)Timer.this.interval);
                                } catch (InterruptedException var2) {
                                    ;
                                }
                            }

                        }
                    });
                }

                switch(msg.what) {
                case 0:
                    Timer.this.loop = true;
                    this.jobThread.start();
                    break;
                case 1:
                    Timer.this.loop = false;
                    this.jobThread = null;
                }

                return false;
            }
        });
    }

    public void start() {
        this.androidChannel.toWorker().sendEmptyMessage(0);
    }

    public void stop() {
        this.androidChannel.toWorker().sendEmptyMessage(1);
    }

    public void resetInterval(int interval) {
        this.interval = interval;
    }

    public int getInterval() {
        return this.interval;
    }

    public boolean isAlive() {
        return this.loop;
    }

    public interface OnTimer {
        void onTime(Timer var1);
    }
}