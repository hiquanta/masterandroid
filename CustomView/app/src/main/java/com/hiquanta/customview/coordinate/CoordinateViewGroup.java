package com.hiquanta.customview.coordinate;


import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

/**
 * Created by hiquanta on 2016/7/18.
 */
public class CoordinateViewGroup extends LinearLayout {
    //声明一个我们自定义的 MyView
    private CoordinateView coordinateView = null;

    //通过LayoutParams来设置MyView在该ViewGroup中的布局
    private LayoutParams layoutParams = null;


    public CoordinateViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
        coordinateView = new CoordinateView(context);
        //设置myView的宽高均为wrap_content
        layoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT);
        //设置myView的布局位置为在ViewGroup的左上角，方便我们比对坐标
//        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT, TRUE);
//        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_TOP, TRUE);
        //设置myView的背景为蓝色，方便观察myView的真正范围
      //  coordinateView.setBackgroundColor(Color.BLUE);

        // 把这个myView以layoutPrams添加到MyViewGroup中
        addView(coordinateView, layoutParams);
        //我们直接在构造方法里面打印可能因为这时候布局还没有生成，所以打印出来都是0
        //display();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                //打印触摸点的位置
                Log.e("MotionEvent.getX()", "" + event.getX());
                Log.e("MotionEvent.getY()", "" + event.getY());
                Log.e("MotionEvent.getRawX()", "" + event.getRawX());
                Log.e("MotionEvent.getRawY()", "" + event.getRawY());
                //打印myView的位置
                display();
                break;
        }
        return true;
    }

    private void display() {

        //打印myView在viewGroup中的位置
        Log.e("myView.getLeft()", "" + coordinateView.getLeft());
        Log.e("myView.getRight()", "" + coordinateView.getRight());
        Log.e("myView.getTop()", "" + coordinateView.getTop());
        Log.e("myView.getBottom", "" + coordinateView.getBottom());

        //打印myView在window中的位置
        int[] location1 = new int[2];
        coordinateView.getLocationInWindow(location1);
        int viewX1 = location1[0];
        int viewY1 = location1[1];
        Log.e("myView.getLocationInWindow", "InWindow x坐标" + viewX1);
        Log.e("myView.getLocationInWindow", "InWindow y坐标" + viewY1);

        //打印myView在screen中的位置
        int[] location2 = new int[2];
        coordinateView.getLocationOnScreen(location2);
        int viewX2 = location2[0];
        int viewY2 = location2[1];
        Log.e("myView.getLocationOnScreen", "OnScreen x坐标" + viewX2);
        Log.e("myView.getLocationOnScreen", "OnScreen y坐标" + viewY2);
    }

}
