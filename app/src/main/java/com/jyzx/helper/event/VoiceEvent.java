package com.jyzx.helper.event;

/**
 * user: Administrator
 * date:2021/8/2
 * EXP:
 */

public class VoiceEvent {

    private boolean isVoiceOpen;

    public VoiceEvent(boolean isVoiceOpen) {
        this.isVoiceOpen = isVoiceOpen;
    }

    public boolean isVoiceOpen() {
        return isVoiceOpen;
    }

    public void setVoiceOpen(boolean voiceOpen) {
        isVoiceOpen = voiceOpen;
    }
}
