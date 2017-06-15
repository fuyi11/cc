package demo.ycxh.com.xuanfu.next;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import demo.ycxh.com.xuanfu.MainActivity;
import demo.ycxh.com.xuanfu.R;

/**
 * Created by fuyi on 2017/6/8.
 */

public class FloatingWindowService extends Service {
    private static final String TAG="OnTouchListener";
    private static View mView = null;
    private static WindowManager mWindowManager = null;
    private static Context mContext = null;
    public static Boolean isShown = false;
    public WindowManager.LayoutParams params = null;
    private int pixel;
    private int TheOffset;
    @Override
    public void onCreate() {
        super.onCreate();
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        pixel = intent.getIntExtra("pixel",1);
        showPopupWindow(this);
        return super.onStartCommand(intent, flags, startId);
    }
    /**
     * 显示弹出框
     *
     * @param context
     *
     */
    private void showPopupWindow(final Context context) {
        if (isShown) {
            return;
        }
        isShown = true;
        // 获取应用的Context
        mContext = context.getApplicationContext();
        // 获取WindowManager
        mWindowManager = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        params = new WindowManager.LayoutParams();
        mView = setUpView(context);
        // 类型
        params.type = WindowManager.LayoutParams.TYPE_SYSTEM_ALERT;
        int flags=WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
        params.flags = flags;
        params.format = PixelFormat.TRANSLUCENT;
        params.width = WindowManager.LayoutParams.WRAP_CONTENT;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        params.gravity = Gravity.CENTER;
        mWindowManager.addView(mView, params);
    }
    /**
     * 隐藏弹出框
     */
    private static void hidePopupWindow() {
        if (isShown && null != mView) {
            mWindowManager.removeView(mView);
            isShown = false;
        }
    }
    private  int x=0;
    private  int y=0;
    private  int startX=0;
    private  int startY=0;
    private View setUpView(final Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.popupwindow,
                null);
        TextView tv= (TextView) view.findViewById(R.id.title);
        int w = View.MeasureSpec.makeMeasureSpec(0,View.MeasureSpec.UNSPECIFIED);
        int h = View.MeasureSpec.makeMeasureSpec(0,View.MeasureSpec.UNSPECIFIED);
        tv.measure(w, h);
        TheOffset=(pixel-tv.getMeasuredWidth())/2-50;
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(context,MainActivity.class);
                context.startActivity(intent);
                // Toast.makeText(context,"点击事件",Toast.LENGTH_LONG).show();
            }
        });
        tv.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()){
                    case MotionEvent.ACTION_MOVE:
                        int newX= (int) (event.getRawX()-x);
                        int newY= (int) (event.getRawY()-y);
                        params.x=newX+startX;
                        params.y=newY+startY;
                        mWindowManager.updateViewLayout(mView,params);
                        break;
                    case MotionEvent.ACTION_DOWN:
                        x= (int) event.getRawX();
                        y= (int) event.getRawY();
                        break;
                    case MotionEvent.ACTION_UP:
                        if(params.x>=0){
                            params.x=TheOffset;
                            mWindowManager.updateViewLayout(mView,params);
                        }
                        if(params.x<=-0){
                            params.x=-TheOffset;
                            mWindowManager.updateViewLayout(mView,params);
                        }
                        Log.i(TAG,params.x+"");
                        Log.i(TAG,params.y+"");
                        //判断 从按住到抬起时候的移动距离， 如果如果移动距离大于20 那么就拦截事件，否则就不拦截事件，主要是处理点击事件的冲突
                        if(Math.abs(startX-params.x)>20 ||Math.abs(startY-params.y)>20 ){
                            //记录上一次的偏移量
                            startX=params.x;
                            startY=params.y;
                            return true;
                        }else {
                            startX=params.x;
                            startY=params.y;
                            return false;
                        }
                }
                return false;
            }
        });
        return view;
    }
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mView != null) {
            isShown=false;
            mWindowManager.removeView(mView);
        }
    }
}
