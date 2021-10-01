package com.jyzx.helper.ui.activity

import android.content.Intent
import android.content.pm.ActivityInfo
import android.graphics.Color
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.text.TextUtils
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import cc.taylorzhang.singleclick.determineTriggerSingleClick
import cc.taylorzhang.singleclick.onSingleClick
import cn.jzvd.JzvdStd
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.listener.OnItemClickListener
import com.jyzx.helper.Constants
import com.jyzx.helper.R
import com.jyzx.helper.base.BaseActivity
import com.jyzx.helper.bean.BookBean
import com.jyzx.helper.bean.VideoBean
import com.jyzx.helper.event.CommonEvent
import com.jyzx.helper.event.VoiceResult
import com.jyzx.helper.services.VoiceService
import com.jyzx.helper.ui.adapter.*
import com.jyzx.helper.utils.*
import com.jyzx.helper.utils.MediaPlay.Companion.playMedia
import com.jyzx.helper.utils.MediaPlay.Companion.relesaeMedia
import com.jyzx.helper.utils.StatusUtils
import com.jyzx.helper.view.FlowLayoutManager
import com.jyzx.helper.view.MyJzvdStdNoTitleNoClarity
import com.jyzx.helper.view.SpaceItemDecoration
import com.tamsiree.rxkit.view.RxToast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_search.*
import kotlinx.android.synthetic.main.activity_search.etSearch
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

/**
user: Administrator
date:2021/8/9
EXP:搜索页面： 搜索，回到首页，播放第一个视频（播放视频）
 */

class SearchFakeActivity : BaseActivity() {

    private lateinit var classLayoutManager: LinearLayoutManager
    private lateinit var teacherLayoutManager: LinearLayoutManager
    private lateinit var bookLayoutManager: LinearLayoutManager
    private lateinit var classAdapter: ClassFakeAdapter
    private lateinit var teacherAdapter: TeacherFakeAdapter
    private lateinit var booklAdapter: BookAdapter
    private lateinit var classData: ArrayList<VideoBean>
    private lateinit var teacherData: ArrayList<VideoBean>
    private lateinit var bookData: ArrayList<BookBean>
    private lateinit var hisData: ArrayList<String>
    private lateinit var hisLayoutManager: FlowLayoutManager
    private lateinit var searchHisAdapter: SearchHisAdapter

    override fun beforeView() {
        StatusUtils.updateHomeStatus(this)
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_search
    }

    override fun obtainIntent() {
    }

    override fun initView(saveInstanceState: Bundle?) {
        classData = ArrayList()
        teacherData = ArrayList()
        bookData = ArrayList()
        hisData = ArrayList()
        initdata()
        classAdapter = ClassFakeAdapter(R.layout.class_item, classData, this)
        teacherAdapter = TeacherFakeAdapter(R.layout.teacher_item, teacherData, this)
        booklAdapter = BookAdapter(R.layout.book_item, bookData, this)
        classLayoutManager = LinearLayoutManager(this)
        classLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
        teacherLayoutManager = LinearLayoutManager(this)
        teacherLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
        bookLayoutManager = LinearLayoutManager(this)
        bookLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
        hisLayoutManager = FlowLayoutManager()
        searchHisAdapter = SearchHisAdapter(R.layout.his_item, hisData)
        rvRelaClass.addItemDecoration(SpaceItemDecoration(20,24))
        rvRelaTeacher.addItemDecoration(SpaceItemDecoration(20,22))
        rvRelaBook.addItemDecoration(SpaceItemDecoration(60))
        rvSearchHis.adapter = searchHisAdapter
        rvSearchHis.layoutManager = hisLayoutManager
        rvRelaClass.adapter = classAdapter
        rvRelaClass.layoutManager = classLayoutManager
        rvRelaTeacher.adapter = teacherAdapter
        rvRelaTeacher.layoutManager = teacherLayoutManager
        rvRelaBook.adapter = booklAdapter
        rvRelaBook.layoutManager = bookLayoutManager
        playAudio()
    }

    private fun initdata() {
        hisData = FakeDataUtil.getSearchHis()
        val result = intent.getStringExtra("result")
        etSearch.setText(result)
        var keyword = handleSearchWord(result)
        bookData = FakeDataUtil.initBookByType(keyword)
        classData = FakeDataUtil.initTeacherOrClassByType(keyword)
        teacherData = hanadleRepeat(FakeDataUtil.initTeacherOrClassByType(keyword))
        if(!TextUtils.isEmpty(keyword)&&keyword!!.contains("党建引领")){
            //如果使党建 过滤掉重复的人
            teacherData.removeAt(0)
            teacherData.removeAt(2)
        }
        etSearch.setOnClickListener {
            it.onSingleClick{
                var voiceIntent = Intent(this, VoiceService::class.java)
                startService(voiceIntent)
            }
        }
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
                var intent = Intent(this,ExpertGuideFakeActivity::class.java)
                intent.putExtra("expertObj",teacherData[position])
                startActivity(intent)
            }
        }
        //点击图书
        booklAdapter.setOnItemClickListener { adapter, view, position ->
            view?.determineTriggerSingleClick{
             /*   MyJzvdStdNoTitleNoClarity.startFullscreenDirectly(
                    this,
                    JzvdStd::class.java,
                    "http://jykt.jy365.net/lessionnew/mp4/DSP21090301.mp4",
                    "延安精神永放光芒"
                )*/
                var intent = Intent(this,PlayActivity::class.java)
                //默认播放第一个视频
                intent.putExtra(Constants.VIDEOBEAN,classData[0])
                startActivity(intent)
            }
        }
    }

    override fun initData() {
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun handlerVoiceResult(result: VoiceResult) {
        var type = result.type
        if(type == Constants.VOICE_RESULT){
            executeFun(result.result)
        }
    }

    private fun refreshData(result: String){
        bookData.clear()
        teacherData.clear()
        classData.clear()
        bookData.addAll(FakeDataUtil.initBookByType(result))
        classData.addAll(FakeDataUtil.initTeacherOrClassByType(result))
        teacherData.addAll(hanadleRepeat(FakeDataUtil.initTeacherOrClassByType(result)))
        booklAdapter.notifyDataSetChanged()
        classAdapter.notifyDataSetChanged()
        teacherAdapter.notifyDataSetChanged()
        playAudio()
    }

    override fun onResume() {
        super.onResume()
        if(!EventBus.getDefault().isRegistered(this))
            EventBus.getDefault().register(this)

    }

    override fun onStop() {
        super.onStop()
        if(EventBus.getDefault().isRegistered(this))
            EventBus.getDefault().unregister(this)
    }

    fun startPlayVideo(){
        var intent = intent
        var res = intent.getStringExtra("result")
        intent.setClass(this, PlayWithRelaActivity::class.java)
        startActivity(intent)
    }

    /**
     * 控制语音的开始和关闭的ui
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    override fun handleVoice(event: CommonEvent) {
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
     * 执行对应的功能
     */
    private fun executeFun(keyword: String){
        var newkeyword = handleSearchWord(keyword)
        when(StringUtils.getAllWord(newkeyword)){
            "播放"->{
                var intent = intent
                intent.setClass(this, PlayWithRelaActivity::class.java)
                startActivity(intent)
            }
            "首页"->{
                finish()
            }
            "上一页"->{
                finish()
            }
            "登录"->{
                startActivity(Intent(this, LoginActivity::class.java))
            }
            "好"->{
                var intent = intent
                intent.setClass(this, PlayWithRelaActivity::class.java)
                startActivity(intent)
            }
            "是"->{
                var intent = intent
                intent.setClass(this, PlayWithRelaActivity::class.java)
                startActivity(intent)
            }
            else->{
                //执行搜索
                //只搜索最终结果
                etSearch.setText(StringUtils.getAllWord(keyword))
                //重置搜索关键词
                var intent = intent
                intent.putExtra("result",StringUtils.getAllWord(keyword))
                refreshData(newkeyword)
            }
        }
    }

    private fun playAudio() {
        val url = Uri.parse("android.resource://" + packageName + "/" + R.raw.searchtip)
        playMedia(this, R.raw.searchtip, {
            //打开语音
            val voiceIntent = Intent(
                this,
                VoiceService::class.java
            )
            startService(voiceIntent)
            relesaeMedia()
        }) {

        }
    }

    override fun onDestroy() {
        super.onDestroy()
      //  MediaPlay.relesaeMedia()
    }


    /**
     * 处理重复数据
     */
    private fun hanadleRepeat(data: ArrayList<VideoBean>): ArrayList<VideoBean>{
        var newData = ArrayList<VideoBean>()
        for(i in 0 until data.size){
            var has = false
            for (j in 0 until newData.size){
                if(data[i].author == newData[j].author){
                    has = true
                }
            }
            if(!has){
                newData.add(data[i])
            }
        }
        return newData
    }
}