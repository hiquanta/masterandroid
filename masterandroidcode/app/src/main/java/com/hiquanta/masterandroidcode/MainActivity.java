package com.hiquanta.masterandroidcode;

import android.animation.ObjectAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "MainActivity";
    private ImageView iv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iv = (ImageView) findViewById(R.id.iv);
        iv.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Log.d(TAG,"translationX:"+iv.getTranslationX()+",x:"+iv.getX());
        ObjectAnimator.ofFloat(iv,"translationX",-30f).setDuration(1000).start();
    }
}
