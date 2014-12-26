package com.zyh.baidu;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import com.nineoldandroids.view.ViewHelper;
import com.zyh.baiduapp.R;

public class MainActivity extends Activity {

	protected static float DIS = 0;
	protected static float F = 0.5f;
	private ScrollView scrollview;
	private View title_bg;
	private View wifi;
	private RelativeLayout rl_et;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		init();
	}

	private void init() {
		scrollview = (ScrollView) findViewById(R.id.scrollview);
		wifi = findViewById(R.id.wifi);
		rl_et = (RelativeLayout) findViewById(R.id.rl_et);
		title_bg = findViewById(R.id.title_bg);
		scrollview.setOnTouchListener(new OnTouchListener() {
			private int left;
			private int top;
			private int right;

			@Override
			public boolean onTouch(View v, MotionEvent event) {

				if (DIS == 0) {
					DIS = rl_et.getTop();
					left = rl_et.getPaddingLeft();
					right = rl_et.getPaddingRight();
					top = rl_et.getPaddingTop();
				}
				int y = scrollview.getScrollY();
				Log.d("", "getTop=" + rl_et.getTop());
				Log.d("", "" + y);
				if (y < DIS && rl_et.getPaddingLeft()>=left && rl_et.getPaddingRight()>=right) {
					ViewHelper.setAlpha(title_bg, y / DIS);
					ViewHelper.setAlpha(wifi, 1 - y / DIS);
					rl_et.setPadding(left+(int) (y * F), 8, right+(int) (y * F), 0);
					rl_et.setTranslationY(-y);
				}
				return false;
			}
		});
	}
}
