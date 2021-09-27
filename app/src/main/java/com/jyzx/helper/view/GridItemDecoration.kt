package com.jyzx.helper.view

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 * 用户名: zcm
 * date: 2021/9/5
 * des:
 **/
class GridItemDecoration(space: Int) : RecyclerView.ItemDecoration() {
    private var space:Int = 0
    init {
        this.space = space
    }
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        outRect.left = space
        outRect.bottom = space
       if(parent.getChildPosition(view)%3 == 2){
           outRect.right = space
       }
    }
}