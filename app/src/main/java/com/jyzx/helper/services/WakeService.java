package com.jyzx.helper.services;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import com.baidu.aip.asrwakeup3.core.wakeup.MyWakeup;
import com.baidu.aip.asrwakeup3.core.wakeup.listener.IWakeupListener;
import com.baidu.speech.asr.SpeechConstant;
import com.jyzx.helper.HelperApplication;
import com.jyzx.helper.R;
import com.jyzx.helper.ui.activity.LoginActivity;
import com.jyzx.helper.ui.activity.MainActivity;
import com.jyzx.helper.event.WakeEvent;
import com.jyzx.helper.ui.activity.SearchActivity;
import com.jyzx.helper.utils.ActivityControlUtil;
import com.jyzx.helper.utils.MediaPlay;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import java.util.HashMap;
import java.util.List;

/**
 * 语音唤醒服务  启动app时要先授权  当app在前台时不需要唤醒  当app在后台时可以唤醒
 */
public class WakeService extends Service {

    protected MyWakeup myWakeup;
    protected  Handler handler;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        EventBus.getDefault().register(this);
        //初始化 启动唤醒功能
        handler = new Handler(){
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                if(msg.obj!=null){
                  // Toast.makeText(WakeService.this,"唤醒："+msg.obj,Toast.LENGTH_SHORT).show();
                    //唤醒成功 执行唤醒成功的后续操作
                  if("小助小助".equals(msg.obj)){
                        if(HelperApplication.Companion.getStart() == 0){
                            setTopApp(WakeService.this);
                        }
                        else{
                            //启动语音功能
                            playAudio();
                        }
                    }
                    else if("回到首页".equals(msg.obj)){
                      Activity activity = ActivityControlUtil.getTopActivity();
                      if(activity == null)
                          return;
                      if(activity instanceof MainActivity){
                      }
                      else {
                        ActivityControlUtil.removeActivityexcepeMain();
                      }
                    }
                    else if("人脸登录".equals(msg.obj)){
                      Activity activity = ActivityControlUtil.getTopActivity();
                      if(activity == null)
                          return;
                      //在当前页面打开人脸
                     // activity.startActivity(new Intent(activity, FaceDetectExpActivity.class));
                      activity.startActivity(new Intent(activity, LoginActivity.class));
                  }
                    else if("播放".equals(msg.obj)){
                      Activity activity = ActivityControlUtil.getTopActivity();
                      if(activity == null)
                          return;
                      if(activity instanceof SearchActivity){
                          // 播放视频  跳转到 视频播放页
                          ((SearchActivity)((SearchActivity) activity)).startPlayVideo();
                      }
                  }
                  }
            }
        };
        IWakeupListener listener = new HelperRecogWakeupListener(handler);
        myWakeup = new MyWakeup(this, listener);
        //适配android 8.o以上的系统
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        // 从Android 8.0开始，需要注册通知通道
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel("test",
                    "Service notification channel", NotificationManager.IMPORTANCE_DEFAULT);
            notificationManager.createNotificationChannel(notificationChannel);
        }
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "test")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("唤醒小助");
        // 注意第一个参数不能为0
        startForeground(666, builder.build());
    }

    @Override
    public void onDestroy() {
        //关闭唤醒功能
        myWakeup.release();
        stopSelf();
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //每次服务被杀死 都打开
       /* if(HelperApplication.Companion.getStart() == 0){
            //app在后台时可以唤醒
            startWake();
        }*/
        startWake();
        return START_STICKY;
    }

    /**
     * 启动唤醒功能
     */
    private void startWake(){
        HashMap<String,Object> params = new HashMap<>();
        params.put(SpeechConstant.WP_WORDS_FILE,"assets:///WakeUp.bin");
        myWakeup.start(params);
    }

    /**
     *  关闭唤醒功能
     */
    private void stopWake(){
        myWakeup.stop();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getPost(WakeEvent wakeEvent){
        if(wakeEvent.isWake()){
            //打开唤醒功能 需要语音权限
            startWake();
        }
        else{
            //关闭唤醒功能
            stopWake();
        }
    }
    public static boolean isRunningForeground(Context context) {
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> appProcessInfoList = activityManager.getRunningAppProcesses();
        /**枚举进程*/
        for (ActivityManager.RunningAppProcessInfo appProcessInfo : appProcessInfoList) {
            if (appProcessInfo.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
                if (appProcessInfo.processName.equals(context.getApplicationInfo().processName)) {
                    return true;
                }
            }
        }
        return false;
    }
    public void setTopApp(Context context) {
        if (!isRunningForeground(context)) {
            /**获取ActivityManager*/
            ActivityManager activityManager = (ActivityManager) context.getSystemService(ACTIVITY_SERVICE);
            /**获得当前运行的task(任务)*/
            List<ActivityManager.RunningTaskInfo> taskInfoList = activityManager.getRunningTasks(100);
            for (ActivityManager.RunningTaskInfo taskInfo : taskInfoList) {
                /**找到本应用的 task，并将它切换到前台*/
                if (taskInfo.topActivity.getPackageName().equals(context.getPackageName())) {
                    //  Toast.makeText(ActivityCommon.this,"test",Toast.LENGTH_SHORT);
                    //activityManager.moveTaskToFront(taskInfo.id, 0);
                    Intent intent = new Intent(WakeService.this, MainActivity.class);
                    intent.addCategory(Intent.CATEGORY_LAUNCHER);
                    intent.setAction(Intent.ACTION_MAIN);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
                    startActivity(intent);
                    //EventBus.getDefault().post(new PlayEvent());
                    break;
                }
            }
        }
    }

    private void playAudio(){
        Uri url = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.test3);
        MediaPlay.Companion.playMedia(this, R.raw.playhello, mediaPlayer -> {
            //打开语音
            Intent voiceIntent = new Intent(WakeService.this,VoiceService.class);
            startService(voiceIntent);
            MediaPlay.Companion.relesaeMedia();
        }, mediaPlayer -> {

        });
    }
}
