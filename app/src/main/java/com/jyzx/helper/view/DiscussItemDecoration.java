package com.jyzx.helper.view;

import android.graphics.Rect;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by xiangcheng on 17/8/22.
 */

public class DiscussItemDecoration extends RecyclerView.ItemDecoration {

    private int space = 0;
    private int spaceAdd = 0;

    public DiscussItemDecoration(int space) {
        this.space = space;
    }
    public DiscussItemDecoration(int space, int spaceAdd) {
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) { if(parent.getChildPosition(view) ==parent.getAdapter().getItemCount()-1){
            //如果是最后一个
            outRect.set(0,0,0,0);
        }
        else{
             outRect.set(0,0,0,1);
        }
    }
}