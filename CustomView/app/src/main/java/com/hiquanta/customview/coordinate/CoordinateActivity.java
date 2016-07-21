package com.hiquanta.customview.coordinate;

import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.Window;
import android.view.WindowManager;

import com.hiquanta.customview.R;

import java.lang.reflect.Field;

/**
 * 状态栏的高度就等于区域一的高度减去区域二的高度，标题栏的高度就等于区域二的高度减去区域三的高度！
 * Created by hiquanta on 2016/7/18.
 */
public class CoordinateActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //取消状态栏
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_coordinate);
        ScreenHeighAndWidth();
        ScreenHeighAndWidth2();


    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if(hasFocus){
            getStateBarHight2();
            getAppHeight();
            ContentViewHeight();
            getActionBarHeight();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    /**
     * view绘制区域获取
     */
    private void ContentViewHeight(){
        Rect rect = new Rect();
        getWindow().findViewById(Window.ID_ANDROID_CONTENT).getDrawingRect(rect);
        Log.e("布局的高度：",rect.height()+"");
        Log.e("布局的宽度：",rect.width()+"");
    }
    /**
     *
     */
    private void getStateBarHight2(){
        Rect frame = new Rect();
        getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
        int statusBarHeight = frame.top;
        Log.e("状态栏的高度2：",statusBarHeight+"");
    }

    /**
     *屏幕区域的获取
     */
    private void ScreenHeighAndWidth(){
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int widthPixels = metrics.widthPixels;
        int heightPixels = metrics.heightPixels;
        Log.e("屏幕的高度：",heightPixels+"");
        Log.e("屏幕的宽度：",widthPixels+"");
    }
    /**
     *屏幕区域的获取
     */
    private void ScreenHeighAndWidth2(){
        WindowManager windowManager = getWindowManager();

        Display display = windowManager.getDefaultDisplay();

      int  screenWidth = display.getWidth();

     int   screenHeight = display.getHeight();
        Log.e("屏幕的高度2：",screenHeight+"");
        Log.e("屏幕的宽度2：",screenWidth+"");
    }

    /**
     * 应用区域的获取
     */
    private void getAppHeight(){
        Rect rect = new Rect();
        getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
        int heightPixels=rect.height();
        int widthPixels=rect.width();
        Log.e("App的高度：",heightPixels+"");
        Log.e("App的宽度：",widthPixels+"");
    }
    private void getActionBarHeight(){
        Rect frame = new Rect();
        getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
        int statusBarHeight = frame.top;
        int contentTop = getWindow().findViewById(Window.ID_ANDROID_CONTENT).getTop();
        int titleBarHeight = contentTop - statusBarHeight;
        Log.e("ActionBar的宽度：",titleBarHeight+"");
    }
    /**
     *
     */
    private void getStateBarHight(){

        Class<?> c = null;
        Object obj = null;
        Field field = null;
        int x = 0, sbar = 0;
        try {
            c = Class.forName("com.android.internal.R$dimen");
            obj = c.newInstance();
            field = c.getField("status_bar_height");
            x = Integer.parseInt(field.get(obj).toString());
            sbar = getResources().getDimensionPixelSize(x);
            Log.e("状态栏的高度：",sbar+"");
        } catch(Exception e1) {
            Log.e("getStateBarHight","get status bar height fail");
            e1.printStackTrace();
        }
    }
}
