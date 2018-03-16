package com.huami.merchant.activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
/**
 * Created by huami-lxx on 2018/3/15.
 */
public class ExhibitionImageView extends android.support.v7.widget.AppCompatImageView {
    private int[] images;
    private int index;
    private MyTask task;
    private boolean isCircle;

    private static final long AUTO_SWITCH_TIME = 1500;

    public ExhibitionImageView(Context context) {
        super(context, null);

    }

    public ExhibitionImageView(Context context, AttributeSet attrs) {
        super(context, attrs, 0);
    }

    public ExhibitionImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    public class MyTask implements Runnable {


        @Override
        public void run() {
            if (index == images.length - 1 && !isCircle) {
                removeCallbacks(this);
                return;
            }
            switchWithAnim(this);
        }

        public void start() {
            postDelayed(this, AUTO_SWITCH_TIME);
        }
    }

    /**
     * 执行动画实现图片的切换
     * @param task
     */
    private void switchWithAnim(final MyTask task) {
        AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f,0.2f);
        alphaAnimation.setFillAfter(true);
        alphaAnimation.setDuration(1500);
        startAnimation(alphaAnimation);
        alphaAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (index == images.length - 1 && isCircle) {
                    index = -1;
                }
                AlphaAnimation anim = new AlphaAnimation(0.1f, 1);
                anim.setDuration(2000);
                anim.setFillAfter(true);
                startAnimation(anim);
                setImageResource(images[++index]);
                anim.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {

                        postDelayed(task, AUTO_SWITCH_TIME);

                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    /**
     * 设置图片数据
     * @param images
     * @return
     */
    public ExhibitionImageView setImages(int[] images) {
        this.images = images;
        if (images != null && images.length > 0) {
            setImageResource(images[index]);
        }
        return this;
    }

    /**
     * 启动线程，执行任务
     */
    public void startTask() {
        if (images == null || images.length == 0) {
            return;
        }
        if (task != null) {
            removeCallbacks(task);
            task = null;
        }
        task = new MyTask();
        task.start();
    }

    /**
     * 设置是否循环切换图片
     * @param isCircle
     * @return
     */
    public ExhibitionImageView setIsCircle(boolean isCircle) {
        this.isCircle = isCircle;
        return this;
    }
}