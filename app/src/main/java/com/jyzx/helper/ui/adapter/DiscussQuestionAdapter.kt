package com.jyzx.helper.ui.adapter

import android.content.Context
import android.content.Intent
import android.widget.Button
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.jyzx.helper.R
import com.jyzx.helper.bean.BookBean
import com.jyzx.helper.bean.QuestionBean
import com.jyzx.helper.bean.VideoBean
import com.jyzx.helper.ui.activity.AnswerActivity
import kotlinx.android.synthetic.main.discuss_question_item.*

/**
 * 课程相关的适配器
 */
class DiscussQuestionAdapter(resId: Int, data: ArrayList<QuestionBean>, context: Context?) : BaseQuickAdapter<QuestionBean,BaseViewHolder>(resId,data){

    override fun convert(holder: BaseViewHolder, item: QuestionBean) {
        holder.setText(R.id.tvUserName,item.userName)//用户名字
        holder.setText(R.id.tvAnsTime,item.time)//回复时间
        holder.setText(R.id.tvQuestion,item.questionName)//问题
        holder.setText(R.id.tvAnsCount,item.ansCount.toString()  + "个回答  .")
        holder.setText(R.id.tvGlanceCount,"  "+item.glanceCount+"次浏览")//回复时间
        var btnAnswer = holder.getView<Button>(R.id.btnAnswer)

        var ivUserHead = holder.getView<ImageView>(R.id.ivUserHead)
        val option = RequestOptions().error(R.mipmap.ic_launcher)
            .placeholder(R.mipmap.ic_launcher)
            .circleCrop()
        Glide.with(context).load(item.head).apply(option).into(ivUserHead)
        btnAnswer.setOnClickListener{
            //点击按钮
            var intent = Intent(context, AnswerActivity::class.java)
            context.startActivity(intent)
        }
    }
}