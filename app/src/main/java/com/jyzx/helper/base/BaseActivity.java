package com.jyzx.helper.base;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;

import com.dylanc.loadinghelper.LoadingHelper;
import com.jyzx.helper.R;
import com.jyzx.helper.ui.activity.MainActivity;
import com.jyzx.helper.utils.ActivityControlUtil;
import com.tamsiree.rxkit.view.RxToast;

/**
 * activity基类
 */
public abstract  class BaseActivity extends AppCompatActivity {

    protected View emptyView,errorView;
    protected LoadingHelper loadingHelper;
    protected long lastTime = 0;
    protected RelativeLayout rlContent;
    private AppCompatTextView tvVoiceContent;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 无标题栏 继承AppcompatActivity
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        beforeView();
        // 设置竖屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_parent);
        addChildView();
        ActivityControlUtil.addActivity(this);
        obtainIntent();
        initNetView();
        initView(savedInstanceState);
        initListener();
        initData();
    }

    //setting title and so on
    protected abstract void beforeView();
    //get layout id
    protected abstract  int getLayoutId();
    //get intent
    protected abstract void obtainIntent();
    //get view
    protected abstract void initView(Bundle saveInstanceState);
    //set listener
    protected abstract void initListener();
    //set data
    protected abstract void initData();

    @Override
    protected void onResume() {
        super.onResume();
        //Eventbus 可封装在这
    }

    @Override
    protected void onStop() {
        super.onStop();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityControlUtil.removeActivity(this);
    }

    /**
     * 初始化网络加载界面
     */
    protected void initNetView(){
        emptyView = LayoutInflater.from(this).inflate(R.layout.layout_empty,null);
        errorView = LayoutInflater.from(this).inflate(R.layout.layout_error,null);
    }

    /**
     * 退出app
     */
    @Override
    public void onBackPressed() {
        if(ActivityControlUtil.getTopActivity() instanceof MainActivity){
            //如果顶部的activity是mainactivity 点击两次退出app
            long time = System.currentTimeMillis();
            long TIMEINNER = 2000;
            if(time - lastTime< TIMEINNER){
                //在两秒内退出app
                super.onBackPressed();
            }
            else{
                RxToast.showToast("再按一次退出app");
                lastTime = time;
            }
        }
        else
            super.onBackPressed();
    }

    private void addChildView() {
        //根据id获取view
        ViewGroup flParent = findViewById(R.id.flParent);
        rlContent = findViewById(R.id.rlContent);
        tvVoiceContent = findViewById(R.id.tvVoiceContent);
        View childView = LayoutInflater.from(this).inflate(getLayoutId(),flParent,false);
        flParent.addView(childView,0);
    }


    /**
     * 显示语音提示
     * @param content
     */
    public void showVoiceTip(String content){
        if(rlContent.getVisibility() != View.VISIBLE){
            rlContent.setVisibility(View.VISIBLE);
        }
        if(tvVoiceContent.getVisibility() != View.VISIBLE){
            tvVoiceContent.setVisibility(View.VISIBLE);
        }
        if(!TextUtils.isEmpty(content))
            tvVoiceContent.setText(content);
    }

    public void showVoiceTip(){
        showVoiceTip("");
    }

    /**
     * 隐藏语音提示
     */
    public void hideVoiceTip(){
        if (rlContent.getVisibility() == View.VISIBLE){
            rlContent.setVisibility(View.GONE);
        }
        if(tvVoiceContent.getVisibility() == View.VISIBLE){
            tvVoiceContent.setVisibility(View.GONE);
            tvVoiceContent.setText("");
        }
    }
}
