package com.jyzx.helper.view;

import android.content.Context;
import android.util.AttributeSet;

import com.jyzx.helper.R;

import cn.jzvd.JzvdStd;

public class MyJzvdStdNoTitleNoClarity extends JzvdStd {

    public MyJzvdStdNoTitleNoClarity(Context context) {
        super(context);
    }

    public MyJzvdStdNoTitleNoClarity(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public int getLayoutId() {
        return R.layout.layout_jzstd_notitle;
    }


}
