package com.hiquanta.customview.scrollertest;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.hiquanta.customview.R;

/**
 * Created by hiquanta on 2016/7/26.
 */
public class DelayScrollActivity extends Activity implements View.OnClickListener {
    private static final String TAG = "DelayScrollActivity";
    private static final int MESSAGE_SCROLL_TO = 1;
    private static final int FRAME_COUNT = 30;
    private static final int DELAYED_TIME = 33;
    Button button;
    private int mCount = 0;
    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MESSAGE_SCROLL_TO: {
                    mCount++;
                    if (mCount <= FRAME_COUNT) {
                        float fraction = mCount / (float) FRAME_COUNT;
                        int scrollX = (int) (fraction * 100);
                        button.scrollTo(scrollX, 0);
                        mHandler.sendEmptyMessageDelayed(MESSAGE_SCROLL_TO, DELAYED_TIME);
                    }
                    break;
                }

                default:
                    break;
            }
        };
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delay_scroll);
        button= (Button) findViewById(R.id.button1);
        button.setOnClickListener(this);

    }
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            Log.d(TAG, "button1.left=" + button.getLeft());
            Log.d(TAG, "button1.x=" + button.getX());
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button1:
                mHandler.sendEmptyMessageDelayed(MESSAGE_SCROLL_TO, DELAYED_TIME);
                break;
        }
    }
}
