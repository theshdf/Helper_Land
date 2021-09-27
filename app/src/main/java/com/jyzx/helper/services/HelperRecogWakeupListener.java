package com.jyzx.helper.services;
import android.os.Handler;
import android.os.Message;
import com.baidu.aip.asrwakeup3.core.wakeup.WakeUpResult;
import com.baidu.aip.asrwakeup3.core.wakeup.listener.RecogWakeupListener;

/**
 * user: Administrator
 * date:2021/7/30
 * EXP: 语音唤醒监听器
 */

public class HelperRecogWakeupListener extends RecogWakeupListener {

    private Handler handler;

    public HelperRecogWakeupListener(Handler handler) {
        super(handler);
        this.handler = handler;
    }

    @Override
    public void onSuccess(String word, WakeUpResult result) {
        //super.onSuccess(word, result);
        Message msg = new Message();
        msg.obj = word;
        handler.sendMessage(msg);
    }
}
