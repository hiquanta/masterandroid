package com.hiquanta.customview.coordinate;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * meizu3 1800x1080
 * Created by hiquanta on 2016/7/18.
 */
public class CoordinateView extends View {
    //设置view的默认宽和高，在重写onMeasure中用到
    //注意哦  我们view的宽高设置的都是 30px
    private int defaultWidth = 100;
    private int defaultHeight = 100;
    Paint p = new Paint();

    public CoordinateView(Context context) {
        super(context);
    }

    public CoordinateView(Context context, AttributeSet attrs) {
        super(context, attrs);
        logCoordinate("CoordinateView+attrs");
    }

    public CoordinateView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        logCoordinate("CoordinateView+attrs+defStyleAttr");

    }

    private void logCoordinate(String method) {
        Log.e(method, "start");
        //因为不是ViewGroup,所以默认为0
        Log.e("getLeft", getLeft() + "");
        Log.e("getTop", getTop() + "");

        Log.e("getBottom", getBottom() + "");
        Log.e("getRight", getRight() + "");

        Log.e("getWidth", getWidth() + "");
        Log.e("getHeight", getHeight() + "");
        Log.e(method, "end");
    }

    private void logLocation() {
        int[] locations = new int[2];
        getLocationInWindow(locations);
        Log.e("LocationInWindowX", locations[0] + "");
        Log.e("LocationInWindowY", locations[1] + "");
        getLocationOnScreen(locations);
        Log.e("LocationOnScreenX", locations[0] + "");
        Log.e("LocationOnScreenY", locations[1] + "");
    }

    /**
     * 重写onDraw方法
     * 以控件中心点为中心，宽度的一半为半径画一个红色的圆
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        p.setColor(Color.BLUE);
        int x = this.getLeft() + this.getWidth() / 2;
        int y = this.getTop() + this.getHeight() / 2;
        canvas.drawCircle(this.getWidth() / 2, this.getHeight() / 2, this.getWidth() / 2, p);
        logCoordinate("onDraw");
        logLocation();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.e("MotionEvent.getX()", "" + event.getX());
                Log.e("MotionEvent.getY()", "" + event.getY());
                Log.e("MotionEvent.getRawX()", "" + event.getRawX());
                Log.e("MotionEvent.getRawY()", "" + event.getRawY());
                logLocation();
                break;
        }
        return true;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthSpecMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSpecSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSpecMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSpecSize = MeasureSpec.getSize(heightMeasureSpec);
        if (widthSpecMode == MeasureSpec.AT_MOST && heightSpecMode == MeasureSpec.AT_MOST) {
            //设置宽高的方法
            setMeasuredDimension(defaultWidth, defaultHeight);
        } else if (widthSpecMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(defaultWidth, heightSpecSize);
        } else if (heightSpecMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(widthSpecSize, defaultHeight);
        }

    }
}
