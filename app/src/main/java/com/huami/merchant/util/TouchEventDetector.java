package com.huami.merchant.util;

import android.graphics.PointF;
import android.view.MotionEvent;

/**
 * Created by Administrator on 2017/9/5.
 */

public class TouchEventDetector {
    private static final float DETECT_THRESHOLD = 0.05F;
    private PointF mPoint = new PointF(0.0F, 0.0F);
    private TouchEventDetector.TouchEventListener mTouchEventListener;
    private boolean mIsDetectStarted = false;

    public TouchEventDetector() {
    }

    public void setTouchEventListener(TouchEventDetector.TouchEventListener listener) {
        this.mTouchEventListener = listener;
    }

    public boolean onTouchEvent(MotionEvent event) {
        if(this.mTouchEventListener != null && event.getPointerCount() == 1) {
            int action = event.getAction() & 255;
            if(action == 0) {
                this.mTouchEventListener.onTouchDown(event.getX(), event.getY());
                this.mIsDetectStarted = true;
            } else if(action == 1) {
                this.mTouchEventListener.onTouchUp(event.getX(), event.getY());
                this.mIsDetectStarted = false;
            } else if(this.mIsDetectStarted && action == 2 && (Math.abs(this.mPoint.x - event.getX()) > 0.05F || Math.abs(this.mPoint.y - event.getY()) > 0.05F)) {
                this.mTouchEventListener.onTouchMoved(this.mPoint.x, this.mPoint.y, event.getX() - this.mPoint.x, event.getY() - this.mPoint.y);
            }

            this.mPoint.set(event.getX(), event.getY());
            return true;
        } else {
            this.mIsDetectStarted = false;
            return false;
        }
    }

    public interface TouchEventListener {
        void onTouchDown(float var1, float var2);

        void onTouchUp(float var1, float var2);

        void onTouchMoved(float var1, float var2, float var3, float var4);
    }
}
