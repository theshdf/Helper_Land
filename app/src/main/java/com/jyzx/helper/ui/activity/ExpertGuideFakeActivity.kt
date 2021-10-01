package com.jyzx.helper.ui.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import cc.taylorzhang.singleclick.determineTriggerSingleClick
import cc.taylorzhang.singleclick.onSingleClick
import cn.jzvd.JZUtils
import cn.jzvd.Jzvd
import cn.jzvd.JzvdStd
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.listener.OnItemClickListener
import com.jyzx.helper.Constants
import com.jyzx.helper.R
import com.jyzx.helper.base.BaseActivity
import com.jyzx.helper.bean.VideoBean
import com.jyzx.helper.event.CommonEvent
import com.jyzx.helper.event.VoiceResult
import com.jyzx.helper.ui.adapter.ExperRelaAdapter
import com.jyzx.helper.ui.adapter.ExperRelaFakeAdapter
import com.jyzx.helper.utils.ActivityControlUtil
import com.jyzx.helper.utils.FakeDataUtil
import com.jyzx.helper.utils.StatusUtils
import com.jyzx.helper.utils.StringUtils
import com.jyzx.helper.view.MyJzvdStdNoTitleNoClarity
import com.jyzx.helper.view.SpaceItemDecoration
import com.orhanobut.logger.Logger
import com.tamsiree.rxkit.view.RxToast
import kotlinx.android.synthetic.main.activity_expertguide.*
import kotlinx.android.synthetic.main.activity_main.*
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

/**
 * 用户名: zcm
 * date: 2021/8/25
 * des: 专家指导页面
 **/
class ExpertGuideFakeActivity : BaseActivity(){

    private lateinit var experRelaAdapter: ExperRelaFakeAdapter
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var datas: ArrayList<VideoBean>

    override fun beforeView() {
        StatusUtils.updateActivityStatusByColor(this)
    }

    override fun getLayoutId(): Int {
      return R.layout.activity_expertguide
    }

    override fun obtainIntent() {

    }

    override fun initView(saveInstanceState: Bundle?) {
        var expertObj =  intent.getSerializableExtra("expertObj") as VideoBean
        var videoType = expertObj.videoType
        var author = expertObj.author
        when(videoType){
            Constants.HOME_TITLE[0]->{
                datas = FakeDataUtil.getLearnGangHis()
            }
            Constants.HOME_TITLE[1]->{
                datas = FakeDataUtil.getCountryVideo()
            }
            Constants.HOME_TITLE[2]->{
                datas = FakeDataUtil.getPrimaryLevelGovernance()
            }
            Constants.HOME_TITLE[3]->{
                datas = FakeDataUtil.getNumberBuild()
            }
            Constants.HOME_TITLE[4]->{
                datas = FakeDataUtil.getEcoCult()
            }
            Constants.HOME_TITLE[5]->{
                datas = FakeDataUtil.getLawCountry()
            }
            Constants.HOME_TITLE[6]->{
                datas = FakeDataUtil.getEcoBuild()
            }
            Constants.HOME_TITLE[7]->{
                datas = FakeDataUtil.getDangBuild()
            }
            Constants.HOME_TITLE[8]->{
            datas = FakeDataUtil.getYangJiaLing()
            }
            Constants.HOME_TITLE[9]->{
                datas = FakeDataUtil.getJingGangShan()
            }
            Constants.HOME_TITLE[10]->{
                datas = FakeDataUtil.getKangDa()
            }
            Constants.HOME_TITLE[11]->{
                datas = FakeDataUtil.getNanNiWan()
            }
            Constants.HOME_TITLE[12]->{
                datas = FakeDataUtil.getQiDa()
            }
            Constants.HOME_TITLE[13]->{
                datas = FakeDataUtil.getShiSiWu()
            }
            else->{
                datas = FakeDataUtil.getDangBuild()
            }
        }
        experRelaAdapter = ExperRelaFakeAdapter(R.layout.exper_class_item,datas,this)
        linearLayoutManager = LinearLayoutManager(this)
        linearLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
        rvVideo.layoutManager = linearLayoutManager
        rvVideo.adapter = experRelaAdapter
        tvName.text = author
        tvRank.text = expertObj.des
        tvExpertDetail.text =  FakeDataUtil.getExpertData()[author]
        jzVideo.setUp(expertObj.videoUrl, expertObj.title, Jzvd.SCREEN_NORMAL)
        jzVideo.posterImageView.setImageResource(expertObj.videoCover)
        Glide.with(this).load(expertObj.authorHead).apply(RequestOptions().circleCrop()) .into(ivExpertHead)
    }

    override fun initListener() {
        ivBack.setOnClickListener{
            finish()
        }
        btnOrder.setOnClickListener{
            it.onSingleClick{
                RxToast.info("暂无开放该功能")
            }
        }
        experRelaAdapter.setOnItemClickListener { adapter, view, position ->
            view?.determineTriggerSingleClick{
              /*  MyJzvdStdNoTitleNoClarity.startFullscreenDirectly(
                    this,
                    JzvdStd::class.java,
                    datas[position].videoUrl,
                    datas[position].title
                )*/
                var intent = Intent(this,PlayActivity::class.java)
                intent.putExtra(Constants.VIDEOBEAN,datas[position])
                startActivity(intent)
            }
        }
    }
    override fun initData() {

    }

    override fun onResume() {
        super.onResume()
        Jzvd.goOnPlayOnResume()
    }

    override fun onPause() {
        super.onPause()
        //清空缓存
        JZUtils.clearSavedProgress(applicationContext,null)
        Jzvd.releaseAllVideos()
    }

    /**
     * 处理语音识别出来的中间文字和最终结果
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    fun handlerVoiceResult(result: VoiceResult) {
        var restultStr = StringUtils.getAllWord(result.result)
        var type = result.type
        showVoiceTip(restultStr)
        if (type == Constants.VOICE_RESULT) {
            Logger.d(Constants.TAG,restultStr)
            Handler().postDelayed({
                //拿到最终结果
                handleKey(restultStr)
            }, 0)
        } else {
            Logger.d(Constants.TAG, "inn$restultStr")
        }
    }

    /**
     * 控制语音的开始和关闭的ui
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    override fun handleVoice(event: CommonEvent) {
        when (event.type) {
            "onAsrExit" -> {
                Handler().postDelayed({
                    hideVoiceTip()
                },1000)
                Logger.d(Constants.TAG,"onAsrExit")
            }
            "onAsrReady" -> {
                showVoiceTip("可以说话了")
                Logger.d(Constants.TAG,"onAsrReady")
            }
        }
    }

    private fun handleKey(result:String) {
            if(result.contains("登录") ) {
                startActivity(Intent(this, LoginActivity::class.java))
            }
        else if(result.contains("首页")){
                var activity: Activity? = ActivityControlUtil.getTopActivity() ?: return;
                if(activity is MainActivity){
                }
                else {
                    ActivityControlUtil.removeActivityexcepeMain();
                }
        }
    }
}