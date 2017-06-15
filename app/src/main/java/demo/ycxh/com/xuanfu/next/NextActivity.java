package demo.ycxh.com.xuanfu.next;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import demo.ycxh.com.xuanfu.R;

/**
 * Created by fuyi on 2017/6/8.
 */

public class NextActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next);
        Button open = (Button) findViewById(R.id.open);
        Button close = (Button) findViewById(R.id.close2);
        open.setOnClickListener(this);
        close.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.open:
                //判断是否拥有悬浮权限
                //op 的值是 0 ~ 47，其中0代表粗略定位权限，1代表精确定位权限，24代表悬浮窗权限。（具体可以看看Android源码在android.app下就有个AppOpsManager类）
                if (utils.checkOp(this, 24) == 0) {
                    Intent intent = new Intent(NextActivity.this, FloatingWindowService.class);
                    intent.putExtra("pixel", utils.pixel(this)[0]);
                    startService(intent);
                } else {
                    //引导用户进入悬浮权限设置界面
                    Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                            Uri.parse("package:" + getPackageName()));
                    startActivityForResult(intent, 200);
                }
                break;
            case R.id.close2:
                stopService(new Intent(NextActivity.this, FloatingWindowService.class));
                break;

        }

    }

}
