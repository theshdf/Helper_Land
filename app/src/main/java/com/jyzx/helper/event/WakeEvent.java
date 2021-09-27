package com.jyzx.helper.event;

import java.io.Serializable;

/**
 * user: Administrator
 * date:2021/8/2
 * EXP:
 */

public class WakeEvent implements Serializable {

    private boolean isWake;

    public WakeEvent(boolean isWake) {
        this.isWake = isWake;
    }

    public boolean isWake() {
        return isWake;
    }

    public void setWake(boolean wake) {
        isWake = wake;
    }
}
