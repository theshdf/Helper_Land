package com.jyzx.helper.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.jyzx.helper.R
import com.jyzx.helper.base.BaseFragment
import com.jyzx.helper.base.BaseListFragment
import com.jyzx.helper.bean.QuestionBean
import com.jyzx.helper.ui.activity.AnswerActivity
import com.jyzx.helper.ui.adapter.DiscussQuestionAdapter
import com.jyzx.helper.utils.FakeDataUtil
import com.jyzx.helper.view.DiscussItemDecoration
import kotlinx.android.synthetic.main.discuss_question_item.*
import kotlinx.android.synthetic.main.fragment_discuss.*

/**
user: Administrator
date:2021/8/3
EXP: 研讨
 */

class DiscussFragment : BaseListFragment() {
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var discussQuestionAdapter: DiscussQuestionAdapter
    private lateinit var questions: ArrayList<QuestionBean>


    override fun getContentLayoutId(): Int {
        return R.layout.fragment_discuss
    }

    override fun initView(view: View?) {

    }

    override fun initListener(view: View?) {

    }

    override fun initData() {

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        questions = FakeDataUtil.getDiscussData()
        linearLayoutManager = LinearLayoutManager(activity)
        discussQuestionAdapter = DiscussQuestionAdapter(R.layout.discuss_question_item,questions,activity)
        rvDiscuss.adapter = discussQuestionAdapter
        rvDiscuss.layoutManager = linearLayoutManager
        rvDiscuss.addItemDecoration(DiscussItemDecoration(1,1))
    }
}