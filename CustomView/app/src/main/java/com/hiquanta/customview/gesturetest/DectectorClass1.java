package com.hiquanta.customview.gesturetest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.hiquanta.customview.R;

/**
 * Created by hiquanta on 2016/7/22.
 */
public class DectectorClass1  extends AppCompatActivity implements View.OnTouchListener {

    private GestureDetector mGestureDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gesture);

        mGestureDetector = new GestureDetector(new simpleGestureListener());

        TextView tv = (TextView)findViewById(R.id.tv);
        tv.setOnTouchListener(this);
        tv.setFocusable(true);
        tv.setClickable(true);
        tv.setLongClickable(true);
    }

    public boolean onTouch(View v, MotionEvent event) {
        // TODO Auto-generated method stub
        return mGestureDetector.onTouchEvent(event);
    }

    private class simpleGestureListener extends
            GestureDetector.SimpleOnGestureListener {

        /*****OnGestureListener的函数*****/
        public boolean onDown(MotionEvent e) {
            Log.i("MyGesture", "onDown");
            Toast.makeText(DectectorClass1.this, "onDown", Toast.LENGTH_SHORT)
                    .show();
            return false;
        }

        public void onShowPress(MotionEvent e) {
            Log.i("MyGesture", "onShowPress");
            Toast.makeText(DectectorClass1.this, "onShowPress", Toast.LENGTH_SHORT)
                    .show();
        }

        public boolean onSingleTapUp(MotionEvent e) {
            Log.i("MyGesture", "onSingleTapUp");
            Toast.makeText(DectectorClass1.this, "onSingleTapUp",
                    Toast.LENGTH_SHORT).show();
            return true;
        }

        public boolean onScroll(MotionEvent e1, MotionEvent e2,
                                float distanceX, float distanceY) {
            Log.i("MyGesture", "onScroll:" + (e2.getX() - e1.getX()) + "   "
                    + distanceX);
            Toast.makeText(DectectorClass1.this, "onScroll", Toast.LENGTH_LONG)
                    .show();

            return true;
        }

        public void onLongPress(MotionEvent e) {
            Log.i("MyGesture", "onLongPress");
            Toast.makeText(DectectorClass1.this, "onLongPress", Toast.LENGTH_LONG)
                    .show();
        }

        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
                               float velocityY) {
            Log.i("MyGesture", "onFling");
            Toast.makeText(DectectorClass1.this, "onFling", Toast.LENGTH_LONG)
                    .show();
            return true;
        }

        /*****OnDoubleTapListener的函数*****/
        public boolean onSingleTapConfirmed(MotionEvent e) {
            Log.i("MyGesture", "onSingleTapConfirmed");
            Toast.makeText(DectectorClass1.this, "onSingleTapConfirmed",
                    Toast.LENGTH_LONG).show();
            return true;
        }

        public boolean onDoubleTap(MotionEvent e) {
            Log.i("MyGesture", "onDoubleTap");
            Toast.makeText(DectectorClass1.this, "onDoubleTap", Toast.LENGTH_LONG)
                    .show();
            return true;
        }

        public boolean onDoubleTapEvent(MotionEvent e) {
            Log.i("MyGesture", "onDoubleTapEvent");
            Toast.makeText(DectectorClass1.this, "onDoubleTapEvent",
                    Toast.LENGTH_LONG).show();
            return true;
        }

    }
}