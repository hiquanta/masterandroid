package com.hiquanta.animation;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import android.widget.TextView;

/**
 * Created by hiquanta on 2016/7/26.
 */
public class MoveButton extends TextView {
    private static final String TAG = "MoveButton";
    private int mScaledTouchSlop;
    // 分别记录上次滑动的坐标
    private int mLastX = 0;
    private int mLastY = 0;
    public MoveButton(Context context) {
        this(context, null);
        init();
    }

    public MoveButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MoveButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }
    private void init() {
        mScaledTouchSlop = ViewConfiguration.get(getContext())
                .getScaledTouchSlop();
        Log.d(TAG, "sts:" + mScaledTouchSlop);
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getRawX();
        int y = (int) event.getRawY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: {
                break;
            }
            case MotionEvent.ACTION_MOVE: {
                int deltaX = x - mLastX;
                int deltaY = y - mLastY;
                Log.d(TAG, "move, deltaX:" + deltaX + " deltaY:" + deltaY);
                int translationX = (int)getTranslationX() + deltaX;
                int translationY = (int)getTranslationY() + deltaY;
                setTranslationX( translationX);
                setTranslationY( translationY);
                break;
            }
            case MotionEvent.ACTION_UP: {
                break;
            }
            default:
                break;
        }

        mLastX = x;
        mLastY = y;
        return true;
    }
}
