package com.jyzx.helper.view;

import android.graphics.Rect;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by xiangcheng on 17/8/22.
 */

public class SpaceItemDecoration extends RecyclerView.ItemDecoration {

    private int space = 0;
    private int spaceAdd = 0;

    public SpaceItemDecoration(int space) {
        this.space = space;
    }
    public SpaceItemDecoration(int space,int spaceAdd) {
        this.space = space;
        this.spaceAdd = spaceAdd;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        if(parent.getChildPosition(view) == 0){
            outRect.left = space+spaceAdd;
        }
        else if(parent.getChildPosition(view) ==parent.getAdapter().getItemCount()-1){
            //如果是最后一个
            outRect.right = space+spaceAdd;
            outRect.left = space;
        }
        else{
            outRect.left = space;
        }
    }
}