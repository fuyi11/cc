package demo.ycxh.com.xuanfu.next;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import demo.ycxh.com.xuanfu.R;

/**
 * Created by fuyi on 2017/6/8.
 */

public class NextActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_next2);
        //获取启动按钮
        Button start = (Button)findViewById(R.id.start_id);
        //获取移除按钮
        Button remove = (Button)findViewById(R.id.remove_id);
        //绑定监听
        start.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v)
            {
                // TODO Auto-generated method stub
                Intent intent = new Intent(NextActivity2.this, FxService.class);
                //启动FxService
                startService(intent);
//                finish();
            }
        });

        remove.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v)
            {
                //uninstallApp("com.phicomm.hu");
                Intent intent = new Intent(NextActivity2.this, FxService.class);
                //终止FxService
                stopService(intent);
            }
        });

    }
}
