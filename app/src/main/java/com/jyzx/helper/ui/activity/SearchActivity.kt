package com.jyzx.helper.ui.activity

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import cc.taylorzhang.singleclick.determineTriggerSingleClick
import cc.taylorzhang.singleclick.onSingleClick
import cn.jzvd.JzvdStd
import com.dylanc.loadinghelper.LoadingHelper
import com.jyzx.helper.Constants
import com.jyzx.helper.R
import com.jyzx.helper.base.BaseActivity
import com.jyzx.helper.bean.*
import com.jyzx.helper.event.CommonEvent
import com.jyzx.helper.event.VoiceResult
import com.jyzx.helper.http.Apiservice
import com.jyzx.helper.services.VoiceService
import com.jyzx.helper.ui.adapter.*
import com.jyzx.helper.utils.FakeDataUtil
import com.jyzx.helper.utils.StatusUtils
import com.jyzx.helper.utils.StringUtils
import com.jyzx.helper.view.FlowLayoutManager
import com.jyzx.helper.view.MyJzvdStdNoTitleNoClarity
import com.jyzx.helper.view.SpaceItemDecoration
import com.tamsiree.rxkit.view.RxToast
import kotlinx.android.synthetic.main.activity_search.*
import kotlinx.android.synthetic.main.activity_search.etSearch
import kotlinx.android.synthetic.main.fragment_countryside.*
import kotlinx.coroutines.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import rxhttp.awaitResult
import rxhttp.wrapper.param.RxHttp
import rxhttp.wrapper.param.toResponse

/**
user: Administrator
date:2021/8/9
EXP:搜索页面
 */

class SearchActivity : BaseActivity() {

    private lateinit var classLayoutManager: LinearLayoutManager
    private lateinit var teacherLayoutManager: LinearLayoutManager
    private lateinit var bookLayoutManager: LinearLayoutManager
    private lateinit var classAdapter: ClassAdapter
    private lateinit var teacherAdapter: TeacherAdapter
    private lateinit var bookAdapter: BookAdapter
    private lateinit var classData: ArrayList<Course>
    private lateinit var teacherData: ArrayList<TeacherBean>
    private lateinit var bookData: ArrayList<BookBean>
    private lateinit var hisData: ArrayList<String>
    private lateinit var hisLayoutManager: FlowLayoutManager
    private lateinit var searchHisAdapter: SearchHisAdapter
    private lateinit var searchResult: String

    override fun beforeView() {
        StatusUtils.updateHomeStatus(this)
        loadingHelper = LoadingHelper(this)

    }

    override fun getLayoutId(): Int {
        return R.layout.activity_search
    }

    override fun obtainIntent() {
        searchResult = intent.getStringExtra("result") as String
    }

    override fun initView(saveInstanceState: Bundle?) {
        searchRefresh.setColorSchemeColors(Color.parseColor("#1AA77A"))
        //loadingHelper = LoadingHelper(content)
        classData = ArrayList()
        teacherData = ArrayList()
        bookData = ArrayList()
        hisData = ArrayList()
        classAdapter = ClassAdapter(R.layout.class_item, classData, this)
        teacherAdapter = TeacherAdapter(R.layout.teacher_item, teacherData, this)
        bookAdapter = BookAdapter(R.layout.book_item, bookData, this)
        classLayoutManager = LinearLayoutManager(this)
        classLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
        teacherLayoutManager = LinearLayoutManager(this)
        teacherLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
        bookLayoutManager = LinearLayoutManager(this)
        bookLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
        hisLayoutManager = FlowLayoutManager()
        searchHisAdapter = SearchHisAdapter(R.layout.his_item, hisData)
        rvRelaClass.addItemDecoration(SpaceItemDecoration(20, 24))
        rvRelaTeacher.addItemDecoration(SpaceItemDecoration(20, 22))
        rvRelaBook.addItemDecoration(SpaceItemDecoration(20,22))
        rvSearchHis.adapter = searchHisAdapter
        rvSearchHis.layoutManager = hisLayoutManager
        rvRelaClass.adapter = classAdapter
        rvRelaClass.layoutManager = classLayoutManager
        rvRelaTeacher.adapter = teacherAdapter
        rvRelaTeacher.layoutManager = teacherLayoutManager
        rvRelaBook.adapter = bookAdapter
        rvRelaBook.layoutManager = bookLayoutManager
        hisData = FakeDataUtil.getSearchHis()
        etSearch.setText(searchResult)
        etSearch.setOnClickListener {
            it.onSingleClick {
                var voiceIntent = Intent(this, VoiceService::class.java)
                startService(voiceIntent)
            }
        }
        getSearchList(searchResult)
    }

    override fun initListener() {
        rlBack.setOnClickListener { finish() }
        ivClean.setOnClickListener {
            RxToast.info("清除搜索历史记录")
        }
        searchHisAdapter.setOnItemClickListener { adapter, view, position ->
            view?.determineTriggerSingleClick {
                var intent = Intent()
                intent.putExtra("result", hisData[position])
                intent.setClass(this, PlayWithRelaActivity::class.java)
                startActivity(intent)
            }
        }
        //点击课程
        classAdapter.setOnItemClickListener { adapter, view, position ->
            view?.determineTriggerSingleClick {
                var intent = intent
                intent.setClass(this, PlayWithRelaActivity::class.java)
                startActivity(intent)
            }
        }
        //点击老师师资
        teacherAdapter.setOnItemClickListener { adapter, view, position ->
            view?.determineTriggerSingleClick {
                /*  var intent = intent
                  intent.setClass(this, PlayWithRelaActivity::class.java)
                  startActivity(intent)*/
                var intent = Intent(this, ExpertGuideActivity::class.java)
                intent.putExtra("id", teacherData[position].id)
                startActivity(intent)
            }
        }
        //点击图书
        bookAdapter.setOnItemClickListener { adapter, view, position ->
            view?.determineTriggerSingleClick {
                MyJzvdStdNoTitleNoClarity.startFullscreenDirectly(
                    this,
                    JzvdStd::class.java,
                    "http://jykt.jy365.net/lessionnew/mp4/DSP21090301.mp4",
                    "延安精神永放光芒"
                )
            }
        }
        searchRefresh.setOnRefreshListener {
            getSearchList(searchResult)
        }
    }

    override fun initData() {
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun handlerVoiceResult(result: VoiceResult) {
        var type = result.type
        if (type == Constants.VOICE_RESULT) {
            //只搜索最终结果
            searchResult = StringUtils.getAllWord(result.result)
            etSearch.setText(searchResult)
            //重置搜索关键词
            var intent = intent
            intent.putExtra("result", searchResult)
            //根据关键字进行搜索
            getSearchList(searchResult)
        }
    }

    override fun onResume() {
        super.onResume()
        if (!EventBus.getDefault().isRegistered(this))
            EventBus.getDefault().register(this)

    }

    override fun onStop() {
        super.onStop()
        if (EventBus.getDefault().isRegistered(this))
            EventBus.getDefault().unregister(this)
    }

    fun startPlayVideo() {
        var intent = intent
        var res = intent.getStringExtra("result")
        intent.setClass(this, PlayWithRelaActivity::class.java)
        startActivity(intent)
    }

    /**
     * 控制语音的开始和关闭的ui
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    fun handleVoice(event: CommonEvent) {
        when (event.type) {
            "onAsrExit" -> {
                Handler().postDelayed({
                    /* tvVoiceContent.visibility = View.GONE
                     ivVoice.visibility = View.GONE*/
                }, 1000)
            }
            "onAsrReady" -> {
                /* tvVoiceContent.visibility = View.VISIBLE
                 tvVoiceContent.text = "可以说话了"
                 ivVoice.visibility = View.VISIBLE*/
                RxToast.info("开始说话", 1)
            }
        }
    }

    /**
     * 根据关键字获取搜索结果
     */
    private fun  getSearchList(keyWord: String?){
        loadingHelper.showLoadingView()
        lifecycleScope.launch {
         RxHttp.postForm(Apiservice.SEARCH_LIST)
             .add("page",1)
             .add("limit",10)
           //  .add("keyword",searchResult.subSequence(0,searchResult.length-1))
             .add("keyword",searchResult)
             .toResponse<SearchBean>()
             .awaitResult{
                 //请求数据成功
                 refresh(it)
                 //取消刷新
                 searchRefresh.isRefreshing = false
             }.onFailure {
                 searchRefresh.isRefreshing = false
                 loadingHelper.showErrorView()
             }
     }
    }

    /**
     * 刷新后台返回的数据
     */
    fun refresh(it: SearchBean){
        if((it.bookList!=null&& it.bookList.size==0)&&
            (it.teacherList!=null&& it.teacherList.size==0)&&
            (it.courseList!=null&& it.courseList.size==0)){
            //如果获取的数据都没有，那么显示空页面
            loadingHelper.showEmptyView()
            return
        }
        loadingHelper.showContentView()
        if(it.bookList!=null&&it.bookList.size>0){
            bookData.clear()
            bookData.addAll(it.bookList)
            bookAdapter.notifyDataSetChanged()
        }
        else{
            rvRelaBook.visibility = View.GONE
        }
        if(it.teacherList!=null&&it.teacherList.size>0){
            teacherData.clear()
            teacherData.addAll(it.teacherList)
            teacherAdapter.notifyDataSetChanged()
        }
        else{
            rvRelaTeacher.visibility = View.GONE
        }
        if(it.courseList!=null&&it.courseList.size>0){
            classData.clear()
            classData.addAll(it.courseList)
            classAdapter.notifyDataSetChanged()
        }
        else{
            rvRelaBook.visibility = View.GONE
        }
    }

    /**
     * 添加头部
     */
    private fun addHeader(){
        var classHead = LayoutInflater.from(this).inflate(R.layout.search_common_head,rvRelaClass,false)
/*        var teacherHead = LayoutInflater.from(this).inflate(R.layout.search_common_head,rvRelaTeacher,false)
        var bookHead = LayoutInflater.from(this).inflate(R.layout.search_common_head,rvRelaBook,false)
        var classText = classHead.findViewById<TextView>(R.id.tvCommon)
        var teacherText = teacherHead.findViewById<TextView>(R.id.tvCommon)
        var bookText = bookHead.findViewById<TextView>(R.id.tvCommon)
        classText.text = "相关课程"
        teacherText.text = "相关师资"
        bookText.text = "相关图书"
        teacherAdapter.addHeaderView(teacherHead)
        bookAdapter.addHeaderView(bookHead)*/
        classHead = LayoutInflater.from(this).inflate(R.layout.guide_head_view,rvRelaClass,false)
        classAdapter.addHeaderView(classHead)
    }
}