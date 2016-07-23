package com.hiquanta.customview.scrollto_by;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

//自定义ViewGroup ， 包含了三个LinearLayout控件，存放在不同的布局位置，通过scrollBy或者scrollTo方法切换
public class MultiViewGroup extends ViewGroup {

	private Context mContext;

	private static String TAG = "MultiViewGroup";

	public MultiViewGroup(Context context) {
		super(context);
		mContext = context;
		init();
	}

	public MultiViewGroup(Context context, AttributeSet attrs) {
		super(context, attrs);
		mContext = context;
		init();
	}

	private void init() {
		// 初始化3个 LinearLayout控件
		LinearLayout oneLL = new LinearLayout(mContext);
		oneLL.setBackgroundColor(Color.RED);
		addView(oneLL);

		LinearLayout twoLL = new LinearLayout(mContext);
		twoLL.setBackgroundColor(Color.YELLOW);
		addView(twoLL);

		LinearLayout threeLL = new LinearLayout(mContext);
		threeLL.setBackgroundColor(Color.BLUE);
		addView(threeLL);
	}

	// measure过程
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

		Log.i(TAG, "--- start onMeasure --");

		// 设置该ViewGroup的大小
		int width = MeasureSpec.getSize(widthMeasureSpec);
		int height = MeasureSpec.getSize(heightMeasureSpec);
		setMeasuredDimension(width, height);

		int childCount = getChildCount();
		Log.i(TAG, "--- onMeasure childCount is -->" + childCount);
		for (int i = 0; i < childCount; i++) {
			View child = getChildAt(i);
			// 设置每个子视图的大小 ， 即全屏
			child.measure(Scrollto_byMultiScreenActivity.screenWidth, Scrollto_byMultiScreenActivity.scrrenHeight);
		}
	}

	// layout过程
	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		// TODO Auto-generated method stub
		Log.i(TAG, "--- start onLayout --");
		int startLeft = 0; // 每个子视图的起始布局坐标
		int startTop = 10; // 间距设置为10px 相当于 android：marginTop= "10px"
		int childCount = getChildCount();
		Log.i(TAG, "--- onLayout childCount is -->" + childCount);
		for (int i = 0; i < childCount; i++) {
			View child = getChildAt(i);
			child.layout(startLeft, startTop,
					startLeft + Scrollto_byMultiScreenActivity.screenWidth,
					startTop + Scrollto_byMultiScreenActivity.scrrenHeight);
			startLeft = startLeft + Scrollto_byMultiScreenActivity.screenWidth ; //校准每个子View的起始布局位置
			//三个子视图的在屏幕中的分布如下 [0 , 320] / [320,640] / [640,960]
		}
	}

}

