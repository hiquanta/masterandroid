package com.hiquanta.masterandroidcode;

import android.animation.ObjectAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
//    private static final String TAG = "MainActivity";
//    private ImageView iv;
    private PieView pieView;
    private CheckView checkView;
    private boolean ischecked=false;
    // 数据
    private ArrayList<PieData> mData=new ArrayList<>();
    private ArrayList<PieData> mData2=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        iv = (ImageView) findViewById(R.id.iv);
//        iv.setOnClickListener(this);
//        initData();
//        pieView= (PieView) findViewById(R.id.pieView);
//        pieView.setData(mData);
        checkView= (CheckView) findViewById(R.id.checkview);
        checkView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ischecked){
                    checkView.unCheck();
                    ischecked=false;
                }else{
                    checkView.check();
                    ischecked=true;
               }
            }
        });
    }
    private void initData(){
        for (int i=0;i<5;i++){
            PieData pieData=new PieData("teas"+i,50.0f);
            mData.add(pieData);
        }
    }

    @Override
    public void onClick(View v) {
//        Log.d(TAG,"translationX:"+iv.getTranslationX()+",x:"+iv.getX());
//        ObjectAnimator.ofFloat(iv,"translationX",-30f).setDuration(1000).start();

    }
}
