package demo.ycxh.com.xuanfu.next3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import demo.ycxh.com.xuanfu.R;

public class DesktopLayout extends LinearLayout {

	public DesktopLayout(Context context) {
		super(context);
		setOrientation(LinearLayout.VERTICAL);// 水平排列

		//设置宽高
		this.setLayoutParams( new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT));
		
		View view = LayoutInflater.from(context).inflate(  
                R.layout.desklayout, null);
		this.addView(view);
	}

}
