package com.jyzx.helper

/**
user: Administrator
date:2021/8/4
EXP:定义一个常量池
 */

open class Constants {

    companion object {
        // val HOME_TITLE = listOf("乡村振兴","生态文明","法制建设","经济建设","社会党建","数字建设")
        val HOME_TITLE = listOf(
            "党史学习", "乡村振兴", "基层治理", "数字变革",
            "生态文明", "法制建设","经济建设","党建引领","杨家岭","井冈山","抗大","南泥湾","七大","十四五")
        val voiceMap = mapOf<Int, String>(0 to "依法治国", 1 to "案例", 2 to "可以的")
        val voiceAnswer = mutableMapOf(
            "依法治国" to "好的，您是想要了解关于法治乡村建设的相关内容吗？",
            "案例" to "好的，那为您以黑龙江省哈尔滨市为例，讲述建设法治乡村的探索与实践，可以吗？",
            "可以的" to "open"
        )
        val TAG = "HelperLog"
        val VOICE_RESULT = 1001
        val VOICE_Partial = 1002
        val COUNTRYSIDEBUILD = "乡村振兴如何与青年返乡相结合？"
        val ECODEVLOP_QUESTION = "政府如何调控房地产发展，控制房产，让老百姓增强幸福感？"
        val DANGBUILD_QUESTION = "如何的科学信息化融入到红色教育资源中？"
        val NUMBERBUILD_QUESTION = "对标先进，整合资源，统筹推进，全面建设数字化政府和智慧城市建设？"
        val ECOCULTURE_QUESTION =  "生态文明如何与青年返乡相结合?"
        val LAWBUILD_QUESTION = "党的先进性如何在法制建设活动中的得到体现？"
        val PRIMARYLEVELGONVANCE_QUESTION = "新时期特色社会主义中如何探索养老服务？特别是医疗结合养老服务？"
        val DANGHIS_QUESTION = "如何用延安精神、井冈山精神及红船精神等鼓励党员干部不忘初心牢记使命，努力发扬自我革命精神？"

        // quality类型：0：正常、1：宽松、2：严格、3：自定义
        const val QUALITY_NORMAL = 0
        const val QUALITY_LOW = 1
        const val QUALITY_HIGH = 2
        const val QUALITY_CUSTOM = 3

        // 用于sharepreference的key
        const val KEY_QUALITY_LEVEL_SAVE = "quality_save"

        //语音唤醒、识别

        //设置默认的分页加载单页加载数量
        val PAGECOUNT: Int = 5

        const val VIDEOBEAN = "videoBean"
        const val USERINFO = "USERINFO"
        const val PASSWORD = "PASSWORD"

        //搜索关键字

        //操作关键字

    }
}