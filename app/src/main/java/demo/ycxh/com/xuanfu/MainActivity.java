package demo.ycxh.com.xuanfu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import demo.ycxh.com.xuanfu.next.NextActivity;
import demo.ycxh.com.xuanfu.next.NextActivity2;
import demo.ycxh.com.xuanfu.next3.XFActivity;

public class MainActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Button startFloatWindow = (Button) findViewById(R.id.start_float_window);
        Button mNext = (Button)findViewById(R.id.next);
        Button mNext2 = (Button)findViewById(R.id.next2);
        Button mNext3 = (Button)findViewById(R.id.next3);

		startFloatWindow.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(MainActivity.this, FloatWindowService.class);
				startService(intent);
				finish();
			}
		});

        mNext.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent (MainActivity.this,NextActivity.class));
            }
        });

        mNext2.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent (MainActivity.this,NextActivity2.class));
            }
        });
        mNext3.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent (MainActivity.this,XFActivity.class));
            }
        });
	}
}
