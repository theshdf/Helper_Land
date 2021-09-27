package com.jyzx.helper.services;

import android.os.Handler;
import android.os.Message;
import android.util.EventLog;
import android.util.Log;

import com.baidu.aip.asrwakeup3.core.recog.RecogResult;
import com.baidu.aip.asrwakeup3.core.recog.listener.MessageStatusRecogListener;
import com.jyzx.helper.Constants;
import com.jyzx.helper.event.CommonEvent;

import org.greenrobot.eventbus.EventBus;

/**
 * user: Administrator
 * date:2021/8/2
 * EXP:
 */

class HelperMessageStatusRecogListener extends MessageStatusRecogListener {

    private Handler handler;

    public HelperMessageStatusRecogListener(Handler handler) {
        super(handler);
        this.handler = handler;
    }

    /**
     * 最终的语音结果
     * @param results
     * @param recogResult
     */
    @Override
    public void onAsrFinalResult(String[] results, RecogResult recogResult) {
        String message = results[0] ;
        Message msg = new Message();
        msg.obj = message;
        msg.what = 1001;
        handler.sendMessage(msg);
    }

    @Override
    public void onAsrPartialResult(String[] results, RecogResult recogResult) {
     //   super.onAsrPartialResult(results, recogResult);
        Log.d(Constants.Companion.getTAG(),results[0]);
        String message = results[0] ;
        Message msg = new Message();
        msg.obj = message;
        msg.what = 1002;
        handler.sendMessage(msg);
    }

    @Override
    public void onAsrEnd() {
        super.onAsrEnd();
        Log.d(Constants.Companion.getTAG(),"onAsrEnd");
    }

    @Override
    public void onAsrBegin() {
        super.onAsrBegin();
        Log.d(Constants.Companion.getTAG(),"onAsrBegin");
    }

    @Override
    public void onAsrExit() {
        super.onAsrExit();
        Log.d(Constants.Companion.getTAG(),"onAsrExit");
        EventBus.getDefault().post(new CommonEvent("onAsrExit"));
    }

    /**
     * 语音识别准备就绪，可以开始说话
     */
    @Override
    public void onAsrReady() {
        super.onAsrReady();
        Log.d(Constants.Companion.getTAG(),"onAsrReady");
        EventBus.getDefault().post(new CommonEvent("onAsrReady"));
    }

    @Override
    public void onAsrFinishError(int errorCode, int subErrorCode, String descMessage, RecogResult recogResult) {
        super.onAsrFinishError(errorCode, subErrorCode, descMessage, recogResult);
        Log.d(Constants.Companion.getTAG(),"onAsrFinishError");
    }

    @Override
    public void onAsrFinish(RecogResult recogResult) {
        super.onAsrFinish(recogResult);
        Log.d(Constants.Companion.getTAG(),"onAsrFinish");
    }
}
