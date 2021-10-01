package com.jyzx.helper

import android.app.Activity
import android.app.Application
import android.content.Context
import android.os.Bundle
import android.util.Log
import com.baidu.idl.face.platform.FaceEnvironment
import com.baidu.idl.face.platform.FaceSDKManager
import com.baidu.idl.face.platform.LivenessTypeEnum
import com.baidu.idl.face.platform.listener.IInitCallback
import com.dylanc.loadinghelper.LoadingHelper
import com.dylanc.loadinghelper.ViewType
import com.jyzx.helper.bean.UserInfo
import com.jyzx.helper.http.RxHttpManager
import com.jyzx.helper.ui.activity.face.QualityConfigManager
import com.jyzx.helper.ui.adapter.loading.EmptyAdapter
import com.jyzx.helper.ui.adapter.loading.ErrorAdapter
import com.jyzx.helper.ui.adapter.loading.LoadingAdapter
import com.jyzx.helper.utils.DynamicTimeFormat
import com.jyzx.helper.utils.SharedPreferencesUtil
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import com.orhanobut.logger.PrettyFormatStrategy
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import com.scwang.smartrefresh.layout.api.DefaultRefreshFooterCreator
import com.scwang.smartrefresh.layout.api.DefaultRefreshInitializer
import com.scwang.smartrefresh.layout.api.RefreshFooter
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.footer.ClassicsFooter
import com.scwang.smartrefresh.layout.header.ClassicsHeader
import com.shuyu.gsyvideoplayer.player.PlayerFactory
import com.tamsiree.rxkit.RxTool
import com.tamsiree.rxkit.view.RxToast.showToast
import com.tencent.mmkv.MMKV
import tv.danmaku.ijk.media.exo2.Exo2PlayerManager
import java.util.*
import kotlin.collections.ArrayList


/**
Created by shdf on 2021/7/28.
wechat：zcm656025633
exp：
 **/
class HelperApplication : Application() {

    private var mIsInitSuccess = false


    companion object{
        var isLogin = false
        lateinit var currentUser: UserInfo
        lateinit var instance: HelperApplication
        var start: Int = 0
        lateinit var keyWord: ArrayList<String>
        //初始化关键字
        lateinit var orderKeyword: ArrayList<String>

        // 动作活体条目集合
        var livenessList: List<LivenessTypeEnum> = ArrayList()

        // 活体随机开关
        var isLivenessRandom = true

        // 语音播报开关
        var isOpenSound = true

        // 活体检测开关
        var isActionLive = true

        // 质量等级（0：正常、1：宽松、2：严格、3：自定义）
        var qualityLevel: Int = Constants.QUALITY_NORMAL
        private val destroyMap = mutableMapOf<String,Activity>()

        //初始化smartrefresh刷新
        init {
            SmartRefreshLayout.setDefaultRefreshInitializer { _, layout ->
                //全局设置（优先级最低）
                layout.setEnableAutoLoadMore(true)
                layout.setEnableOverScrollDrag(false)
                layout.setEnableOverScrollBounce(true)
                layout.setEnableLoadMoreWhenContentNotFull(true)
                layout.setEnableScrollContentWhenRefreshed(true)
                layout.setPrimaryColorsId(R.color.textgreen_1aa, android.R.color.white)
                layout.setFooterMaxDragRate(4.0f)
                layout.setFooterHeight(45f)
            }
            SmartRefreshLayout.setDefaultRefreshHeaderCreator { context, layout ->
                //全局设置主题颜色（优先级第二低，可以覆盖 DefaultRefreshInitializer 的配置，与下面的ClassicsHeader绑定）
                layout!!.setEnableHeaderTranslationContent(true)
                ClassicsHeader(context).setTimeFormat(DynamicTimeFormat("更新于 %s"))
            }
            SmartRefreshLayout.setDefaultRefreshFooterCreator { context, _ ->
                ClassicsFooter(
                    context
                )
            }
        }

        /**
         * 添加到销毁队列
         * @param activity 要销毁的activity
         */
        fun addDestroyActivity(activity: Activity, activityName: String) {
            destroyMap[activityName] = activity
        }

        /**
         * 销毁指定Activity
         */
        fun destroyActivity(activityName: String?) {
            val keySet: Set<String> = destroyMap.keys
            for (key in keySet) {
               destroyMap[key]?.finish()
            }
        }
    }

    override fun onCreate() {
        super.onCreate()
        //初始化App数据
        RxTool.init(this)
        //初始化网络请求
        RxHttpManager.init(this)
        MMKV.initialize(this)
        PlayerFactory.setPlayManager(Exo2PlayerManager::class.java)
        //日志打印
        var format = PrettyFormatStrategy.newBuilder()
            .showThreadInfo(false)
            .tag(Constants.TAG)
            .methodCount(0)
            .methodOffset(7)
            .build()
        Logger.addLogAdapter(AndroidLogAdapter(format))
        initLoading()
        initLicense()
        instance = this
        registerActivityLifecycleCallbacks(object :ActivityLifecycleCallbacks{
            override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
            }
            override fun onActivityStarted(activity: Activity) {
                start++
                if(start == 1){
                    Log.d("appapp","font")
                    //app在前台  关闭唤醒 打开语音
                   // EventBus.getDefault().post(WakeEvent(false))
                }
            }

            override fun onActivityResumed(activity: Activity) {

            }

            override fun onActivityPaused(activity: Activity) {

            }

            override fun onActivityStopped(activity: Activity) {
                start--
                if(start == 0){
                    //app退到后台 唤醒打开 关闭语音
                   // EventBus.getDefault().post(WakeEvent(true))
                }
            }

            override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
            }

            override fun onActivityDestroyed(activity: Activity) {
            }
        })
    }


    /**
     * 初始化界面加载
     */
    fun initLoading(){
        LoadingHelper.setDefaultAdapterPool {
            this.register(ViewType.LOADING,LoadingAdapter())
            this.register(ViewType.ERROR,ErrorAdapter())
            this.register(ViewType.EMPTY,EmptyAdapter())
        }
    }

    /**
     * 参数配置方法
     */
    private fun setFaceConfig(): Boolean {
        val config = FaceSDKManager.getInstance().faceConfig
        // SDK初始化已经设置完默认参数（推荐参数），也可以根据实际需求进行数值调整
        // 质量等级（0：正常、1：宽松、2：严格、3：自定义）
        // 获取保存的质量等级
        val util = SharedPreferencesUtil(this)
        var qualityLevel = util.getSharedPreference(Constants.KEY_QUALITY_LEVEL_SAVE, -1) as Int
        if (qualityLevel == -1) {
            qualityLevel = Companion.qualityLevel
        }
        // 根据质量等级获取相应的质量值（注：第二个参数要与质量等级的set方法参数一致）
        val manager = QualityConfigManager.getInstance()
        manager.readQualityFile(this, qualityLevel)
        val qualityConfig = manager.config ?: return false
        // 设置模糊度阈值
        config.blurnessValue = qualityConfig.blur
        // 设置最小光照阈值（范围0-255）
        config.brightnessValue = qualityConfig.minIllum
        // 设置最大光照阈值（范围0-255）
        config.brightnessMaxValue = qualityConfig.maxIllum
        // 设置左眼遮挡阈值
        config.occlusionLeftEyeValue = qualityConfig.leftEyeOcclusion
        // 设置右眼遮挡阈值
        config.occlusionRightEyeValue = qualityConfig.rightEyeOcclusion
        // 设置鼻子遮挡阈值
        config.occlusionNoseValue = qualityConfig.noseOcclusion
        // 设置嘴巴遮挡阈值
        config.occlusionMouthValue = qualityConfig.mouseOcclusion
        // 设置左脸颊遮挡阈值
        config.occlusionLeftContourValue = qualityConfig.leftContourOcclusion
        // 设置右脸颊遮挡阈值
        config.occlusionRightContourValue = qualityConfig.rightContourOcclusion
        // 设置下巴遮挡阈值
        config.occlusionChinValue = qualityConfig.chinOcclusion
        // 设置人脸姿态角阈值
        config.headPitchValue = qualityConfig.pitch
        config.headYawValue = qualityConfig.yaw
        config.headRollValue = qualityConfig.roll
        // 设置可检测的最小人脸阈值
        config.minFaceSize = FaceEnvironment.VALUE_MIN_FACE_SIZE
        // 设置可检测到人脸的阈值
        config.notFaceValue = FaceEnvironment.VALUE_NOT_FACE_THRESHOLD
        // 设置闭眼阈值
        config.eyeClosedValue = FaceEnvironment.VALUE_CLOSE_EYES
        // 设置图片缓存数量
        config.cacheImageNum = FaceEnvironment.VALUE_CACHE_IMAGE_NUM
        // 设置活体动作，通过设置list，LivenessTypeEunm.Eye, LivenessTypeEunm.Mouth,
        // LivenessTypeEunm.HeadUp, LivenessTypeEunm.HeadDown, LivenessTypeEunm.HeadLeft,
        // LivenessTypeEunm.HeadRight
        config.livenessTypeList = livenessList
        // 设置动作活体是否随机
        config.isLivenessRandom = isLivenessRandom
        // 设置开启提示音
        config.isSound = !isOpenSound
        // 原图缩放系数
        config.scale = FaceEnvironment.VALUE_SCALE
        // 抠图宽高的设定，为了保证好的抠图效果，建议高宽比是4：3
        config.cropHeight = FaceEnvironment.VALUE_CROP_HEIGHT
        config.cropWidth = FaceEnvironment.VALUE_CROP_WIDTH
        // 抠图人脸框与背景比例
        config.enlargeRatio = FaceEnvironment.VALUE_CROP_ENLARGERATIO
        // 加密类型，0：Base64加密，上传时image_sec传false；1：百度加密文件加密，上传时image_sec传true
        config.secType = FaceEnvironment.VALUE_SEC_TYPE
        // 检测超时设置
        config.timeDetectModule = FaceEnvironment.TIME_DETECT_MODULE
        // 检测框远近比率
        config.faceFarRatio = FaceEnvironment.VALUE_FAR_RATIO
        config.faceClosedRatio = FaceEnvironment.VALUE_CLOSED_RATIO
        FaceSDKManager.getInstance().faceConfig = config
        return true
    }

    private fun initLicense() {
        val success = setFaceConfig()
        if (!success) {
            showToast("初始化失败 = json配置文件解析出错")
            return
        }
        // 为了android和ios 区分授权，appId=appname_face_android ,其中appname为申请sdk时的应用名
        // 应用上下文
        // 申请License取得的APPID
        // assets目录下License文件名
        FaceSDKManager.getInstance().initialize(this, "helper-face-android-face-android",
            "idl-license.faceexample-face-android-1", object : IInitCallback {
                override fun initSuccess() {
                    mIsInitSuccess = true
                    Log.e(Constants.TAG, "face初始化成功")
                }

                override fun initFailure(errCode: Int, errMsg: String) {
                    mIsInitSuccess = false
                    Log.e(Constants.TAG, "face初始化失败")
                }
            })
    }
}