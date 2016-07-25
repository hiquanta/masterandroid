package com.hiquanta.customview.scrollertest;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;

import com.hiquanta.customview.R;

/**
 * Created by hiquanta on 2016/7/25.
 */
public class ScrollerMultiScreenActivity extends Activity implements View.OnClickListener {
    private Button bt_scrollLeft;
    private Button bt_scrollRight;
    private ScrollerMultiViewGroup mulTiViewGroup  ;

    public static int screenWidth  ;  // 屏幕宽度
    public static int scrrenHeight ;  //屏幕高度
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //获得屏幕分辨率大小
        DisplayMetrics metric = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metric);
        screenWidth = metric.widthPixels ;
        scrrenHeight = metric.heightPixels;
        System.out.println("screenWidth * scrrenHeight --->" + screenWidth + " * " +scrrenHeight);

        setContentView(R.layout.scroller_multiview);

        //获取自定义视图的空间引用
        mulTiViewGroup = (ScrollerMultiViewGroup)findViewById(R.id.scroolermultiViewGroup);

        bt_scrollLeft = (Button) findViewById(R.id.bt_scrollLeft);
        bt_scrollRight = (Button) findViewById(R.id.bt_scrollRight);

        bt_scrollLeft.setOnClickListener(this);
        bt_scrollRight.setOnClickListener(this);
    }
    private int curscreen = 0;   // 当前位于第几屏幕  ，共3个"屏幕"， 3个LinearLayout
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_scrollLeft:
                mulTiViewGroup.startMove() ; //下一屏
                break;
            case R.id.bt_scrollRight:
                mulTiViewGroup.stopMove() ; //停止滑动
                break;
        }
    }
}
