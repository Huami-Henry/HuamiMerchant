package com.huami.merchant.designView;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

import com.huami.merchant.util.DisplayUtil;

public class TextMoveLayout extends AppCompatTextView {
    private Paint paint;
    public TextMoveLayout(Context context) {
        super(context);
        initPaint();
    }
    public TextMoveLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initPaint();
    }
    private void initPaint() {
        paint = new Paint();
        paint.setColor(Color.BLACK);
        int dp2px = DisplayUtil.dp2px(getContext(), 14);
        paint.setTextSize(dp2px);
    }

    public TextMoveLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    private int getProgress_intwidth;
    private int textcolor;
    private int progress_int;
    public void setProgress(int progress_int){
        this.progress_int = progress_int;
        invalidate();
    }
    public void setProgressColor(int textcolor){
        this.textcolor = textcolor;
        paint.setColor(textcolor);
    }
    public void setProgressWidth(int getProgress_intwidth){
        this.getProgress_intwidth = getProgress_intwidth;
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawText(progress_int+"", (float) (progress_int*1.0/100*getProgress_intwidth),50,paint);
    }
}