package com.jyzx.helper.services;

import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.baidu.aip.asrwakeup3.core.mini.AutoCheck;
import com.baidu.aip.asrwakeup3.core.recog.MyRecognizer;
import com.baidu.aip.asrwakeup3.core.recog.listener.IRecogListener;
import com.baidu.aip.asrwakeup3.core.recog.listener.MessageStatusRecogListener;
import com.baidu.aip.asrwakeup3.uiasr.params.CommonRecogParams;
import com.baidu.aip.asrwakeup3.uiasr.params.OfflineRecogParams;
import com.baidu.aip.asrwakeup3.uiasr.params.OnlineRecogParams;
import com.google.gson.Gson;
import com.jyzx.helper.Constants;
import com.jyzx.helper.HelperApplication;
import com.jyzx.helper.R;
import com.jyzx.helper.event.VoiceEvent;
import com.jyzx.helper.event.VoiceResult;
import com.jyzx.helper.ui.fragment.childFragent.CountrySideFragment;
import com.jyzx.helper.utils.MediaPlay;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.Map;

import retrofit2.http.POST;

/**
 * user: Administrator
 * date:2021/7/30
 * EXP:
 */

public class VoiceService extends Service {

    private MyRecognizer myRecognizer;
    private Handler handler;
    private boolean enableOffLine = true;
    /*
     * Api的参数类，仅仅用于生成调用START的json字符串，本身与SDK的调用无关
     */
    private  CommonRecogParams apiParams;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        EventBus.getDefault().register(this);
        apiParams = new OnlineRecogParams();
        apiParams.initSamplePath(this);
        handler = new Handler(){
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                if(msg.obj!=null){
                      //唤醒成功 执行唤醒成功的后续操作
                    Log.d("appapp","语音"+msg.obj+"");
                    //解析数据
                    if(msg.what == Constants.Companion.getVOICE_RESULT()){
                      String result = (String)  msg.obj;
                      //  Toast.makeText(VoiceService.this,result,Toast.LENGTH_SHORT).show();
                        //识别结束后 立马开启语音
                        EventBus.getDefault().post(new VoiceResult(result,Constants.Companion.getVOICE_RESULT()));
                       // playAudio();
                      //  start();
                    }
                    else if(msg.what == Constants.Companion.getVOICE_Partial()){
                        String result = (String)  msg.obj;
                        //  Toast.makeText(VoiceService.this,result,Toast.LENGTH_SHORT).show();
                        //识别结束后 立马开启语音
                        EventBus.getDefault().post(new VoiceResult(result,Constants.Companion.getVOICE_Partial()));
                        // playAudio();
                        //  start();
                    }
                    else if(msg.what == 1000){
                        //识别错误，重新启动语音识别
                      //  start();
                    }
                }
            }
        };
        IRecogListener iRecogListener = new HelperMessageStatusRecogListener(handler);
        myRecognizer = new MyRecognizer(this,iRecogListener);
        if(enableOffLine){
            //允许使用离线模式
            Map<String,Object> params = OfflineRecogParams.fetchOfflineParams();
            myRecognizer.loadOfflineEngine(params);
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if(HelperApplication.Companion.getStart() == 1){
            //app在前台时可以唤醒
          start();
        }
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        release();
        EventBus.getDefault().unregister(this);
    }

    public void start(){
        // DEMO集成步骤2.1 拼接识别参数： 此处params可以打印出来，直接写到你的代码里去，最终的json一致即可。
        final Map<String, Object> params = fetchParams();
        // params 也可以根据文档此处手动修改，参数会以json的格式在界面和logcat日志中打印
        // 复制此段可以自动检测常规错误
        (new AutoCheck(getApplicationContext(), new Handler() {
            public void handleMessage(Message msg) {
                if (msg.what == 100) {
                    AutoCheck autoCheck = (AutoCheck) msg.obj;
                    synchronized (autoCheck) {
                        String message = autoCheck.obtainErrorMessage(); // autoCheck.obtainAllMessage();
                        ; // 可以用下面一行替代，在logcat中查看代码
                        // Log.w("AutoCheckMessage", message);
                    }
                }
            }
        }, enableOffLine)).checkAsr(params);

        // 这里打印出params， 填写至您自己的app中，直接调用下面这行代码即可。
        // DEMO集成步骤2.2 开始识别
        myRecognizer.start(params);
    }

    public void stop(){
        myRecognizer.stop();
    }

    public void release(){
        myRecognizer.release();
    }

    public void cancle(){
        myRecognizer.cancel();
    }

    protected Map<String, Object> fetchParams() {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        //  上面的获取是为了生成下面的Map， 自己集成时可以忽略
        Map<String, Object> params = apiParams.fetch(sp);
        //  集成时不需要上面的代码，只需要params参数。
        return params;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getPost(VoiceEvent voiceEvent){
        boolean isVoiceOpen = voiceEvent.isVoiceOpen();
        if(isVoiceOpen){
            start();
        }
        else{
            stop();
        }
    }
}
