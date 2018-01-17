package com.huami.merchant.designView.framView;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

import java.util.ArrayList;

public class FramView extends AppCompatImageView {
    private static final int DEFAULT_INTERVAL = 1000;
    Timer timer;
    int interval;
    public ArrayList<Drawable> drawableList=new ArrayList<>();
    int currentFrameIndex;
    boolean loop;
    int animationRepeatCount;
    boolean restoreFirstFrameWhenFinishAnimation;

    public FramView(Context context) {
        this(context, (AttributeSet)null);
    }

    public FramView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FramView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.interval = 1000;
        this.currentFrameIndex = 0;
        this.loop = false;
        this.animationRepeatCount = 1;
        this.restoreFirstFrameWhenFinishAnimation = true;
    }

    public void setInterval(int interval) {
        this.interval = interval;
    }

    public void addImageFrame(int resId) {
        if(this.drawableList == null) {
            this.drawableList = new ArrayList();
        }
        this.drawableList.add(this.getContext().getResources().getDrawable(resId));
    }

    /**
     * 移除数据源
     */
    public void removeImageFrame() {
        reset();
    }
    public void addImageFrame(Drawable d) {
        if(this.drawableList == null) {
            this.drawableList = new ArrayList();
        }
        this.drawableList.add(d);
    }

    public void startAnimation() {
        if(this.drawableList != null && this.drawableList.size() != 0) {
            this.currentFrameIndex = 0;
            this.setImageDrawable(this.drawableList.get(this.currentFrameIndex));
            if(this.timer == null) {
                this.timer = new Timer(this.interval, new Timer.OnTimer() {
                    public void onTime(Timer timer) {
                        ++FramView.this.currentFrameIndex;
                        if(FramView.this.currentFrameIndex == FramView.this.drawableList.size()) {
                            if(FramView.this.loop) {
                                FramView.this.currentFrameIndex = 0;
                            } else {
                                --FramView.this.animationRepeatCount;
                                if(FramView.this.animationRepeatCount == 0) {
                                    if(FramView.this.restoreFirstFrameWhenFinishAnimation) {
                                        FramView.this.currentFrameIndex = 0;
                                    } else {
                                        FramView.this.currentFrameIndex = FramView.this.drawableList.size() - 1;
                                    }

                                    FramView.this.stopAnimaion();
                                } else {
                                    FramView.this.currentFrameIndex = 0;
                                }
                            }
                        }

                        FramView.this.setImageDrawable((Drawable)FramView.this.drawableList.get(FramView.this.currentFrameIndex));
                    }
                });
                this.timer.stop();
            }

            if(!this.timer.isAlive()) {
                this.timer.start();
            }

        } else {
            throw new IllegalStateException("You shoud add frame at least one frame");
        }
    }

    public void stopAnimaion() {
        if(this.timer != null && this.timer.isAlive()) {
            this.timer.stop();
        }

        this.timer = null;
    }

    public void setLoop(boolean loop) {
        this.loop = loop;
    }

    public void setRestoreFirstFrameWhenFinishAnimation(boolean restore) {
        this.restoreFirstFrameWhenFinishAnimation = restore;
    }

    public void setAnimationRepeatCount(int animationRepeatCount) {
        this.animationRepeatCount = animationRepeatCount;
    }

    public void reset() {
        this.stopAnimaion();
        this.drawableList.clear();
        this.drawableList = null;
        timer = null;
    }
}