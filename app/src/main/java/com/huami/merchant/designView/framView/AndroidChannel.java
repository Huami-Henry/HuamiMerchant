package com.huami.merchant.designView.framView;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;

public class AndroidChannel {
    HandlerThread workerThread = null;
    Handler mainThreadHandler = null;
    Handler workerThreadHandler = null;
    UiCallback uiCallback = null;
    WorkerCallback workerCallback = null;
    boolean isChannelOpened = false;

    public AndroidChannel(UiCallback uiCallback, WorkerCallback workerCallback) {
        this.uiCallback = uiCallback;
        this.workerCallback = workerCallback;
        this.open();
    }

    public Handler toUI() {
        return this.mainThreadHandler;
    }

    public Handler toWorker() {
        return this.workerThreadHandler;
    }

    public boolean open() {
        if(this.isChannelOpened) {
            return true;
        } else if(this.uiCallback != null && this.workerCallback != null) {
            this.mainThreadHandler = new Handler(Looper.getMainLooper(), new Handler.Callback() {
                public boolean handleMessage(Message msg) {
                    return AndroidChannel.this.uiCallback.handleUiMessage(msg);
                }
            });
            this.workerThread = new HandlerThread("channel-worker-thread");
            this.workerThread.start();
            this.workerThreadHandler = new Handler(this.workerThread.getLooper(), new Handler.Callback() {
                public boolean handleMessage(Message msg) {
                    return AndroidChannel.this.workerCallback.handleWorkerMessage(msg);
                }
            });
            this.isChannelOpened = true;
            return true;
        } else {
            return false;
        }
    }

    public void close() {
        if(this.isChannelOpened) {
            this.mainThreadHandler.removeCallbacksAndMessages((Object)null);
            this.workerThreadHandler.removeCallbacksAndMessages((Object)null);
            this.workerThread.quit();
            this.workerThread = null;
            this.workerThreadHandler = null;
            this.mainThreadHandler = null;
            this.isChannelOpened = false;
        }
    }

    public interface WorkerCallback {
        boolean handleWorkerMessage(Message var1);
    }

    public interface UiCallback {
        boolean handleUiMessage(Message var1);
    }
}