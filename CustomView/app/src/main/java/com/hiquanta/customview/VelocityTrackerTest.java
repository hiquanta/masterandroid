package com.hiquanta.customview;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.ViewConfiguration;
import android.widget.TextView;
import android.view.ViewGroup.LayoutParams;
/**
 * Created by hiquanta on 2016/7/21.
 */
public class VelocityTrackerTest extends AppCompatActivity {
    private TextView mInfo;
    private VelocityTracker mVelocityTracker;
    private int mMaxVelocity;
    private int mPointerId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mInfo = new TextView(this);
        mInfo.setLines(4);
        mInfo.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        mInfo.setTextColor(Color.BLUE);

        setContentView(mInfo);
       // mMaxVelocity = ViewConfiguration.get(this).getMaximumFlingVelocity();
      //  mMaxVelocity = ViewConfiguration.get(this).getScaledMaximumFlingVelocity();
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        final int action = event.getAction();
        acquireVelocityTracker(event);
        final VelocityTracker verTracker = mVelocityTracker;
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                //求第一个触点的id， 此时可能有多个触点，但至少一个
                mPointerId = event.getPointerId(0);
                break;

            case MotionEvent.ACTION_MOVE:
                //求伪瞬时速度
              //  verTracker.computeCurrentVelocity(1000, mMaxVelocity);
                verTracker.computeCurrentVelocity(1000);
                final float velocityX = verTracker.getXVelocity(mPointerId);
                final float velocityY = verTracker.getYVelocity(mPointerId);
                recodeInfo(velocityX, velocityY);
                break;

            case MotionEvent.ACTION_UP:
                releaseVelocityTracker();
                break;

            case MotionEvent.ACTION_CANCEL:
                releaseVelocityTracker();
                break;

            default:
                break;
        }
        return super.onTouchEvent(event);
    }

    /**
     *
     * @param event 向VelocityTracker添加MotionEvent
     *
     * @see android.view.VelocityTracker#obtain()
     * @see android.view.VelocityTracker#addMovement(MotionEvent)
     */
    private void acquireVelocityTracker(final MotionEvent event) {
        if(null == mVelocityTracker) {
            mVelocityTracker = VelocityTracker.obtain();
        }
        mVelocityTracker.addMovement(event);
    }

    /**
     * 释放VelocityTracker
     *
     * @see android.view.VelocityTracker#clear()
     * @see android.view.VelocityTracker#recycle()
    */
    private void releaseVelocityTracker() {
        if(null != mVelocityTracker) {
            mVelocityTracker.clear();
            mVelocityTracker.recycle();
            mVelocityTracker = null;
        }
    }

    private static final String sFormatStr = "velocityX=%f\nvelocityY=%f";

    /**
     * 记录当前速度
     *
     * @param velocityX x轴速度
     * @param velocityY y轴速度
     */
    private void recodeInfo(final float velocityX, final float velocityY) {
        final String info = String.format(sFormatStr, velocityX, velocityY);
        mInfo.setText(info);
    }
}
