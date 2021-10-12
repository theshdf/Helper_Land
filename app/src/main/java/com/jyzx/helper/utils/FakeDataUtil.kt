package com.jyzx.helper.utils

import android.bluetooth.BluetoothClass
import com.jyzx.helper.Constants
import com.jyzx.helper.R
import com.jyzx.helper.bean.BookBean
import com.jyzx.helper.bean.QuestionBean
import com.jyzx.helper.bean.VideoBean
import com.tamsiree.rxkit.RxTool

/**
 * 制作假数据的工具类
 */
class FakeDataUtil  {
    companion object{
        /**
         * 获取乡村振兴的数据
         */
        fun  getCountryVideo(): ArrayList<VideoBean>{
            var videoList = ArrayList<VideoBean>()
            var bean = VideoBean(
                "2021-8-1",
                100,
                100,
                "http://jykt.jy365.net/lessionnew/mp4/GC13N1818075_1807.mp4",
                "郑风田",
                "实施乡村振兴战略，着力推进“五个振兴”",
                "中国人民大学农业与农村发展学院教授、博士生导师",
                R.mipmap.zft_cover,
                R.mipmap.country1,
                R.mipmap.zft_cover,
                videoType = Constants.HOME_TITLE[1]
            )
            var bean2 = VideoBean(
                "2021-8-1",
                100,
                100,
                "http://jykt.jy365.net/lessionnew/mp4/GC13I2918055_1806.mp4",
                "李军鹏",
                "大力实施乡村振兴战略，壮大乡村发展新动能",
                "国家行政学院教授、博士生导师",
                R.mipmap.ljp_head,
                R.mipmap.country2,
                R.mipmap.ljp_head,
                videoType = Constants.HOME_TITLE[1]
            )
            var bean3 = VideoBean(
                "2021-8-1",
                100,
                100,
                "http://jykt.jy365.net/lessionnew/mp4/GC13A2519045_1905.mp4",
                "王曙光",
                "乡村振兴中的文化与伦理重建",
                "中国人民大学农业与农村发展学院教授、博士生导师",
                R.mipmap.wsg_head,
                R.mipmap.country3,
                R.mipmap.wsg_head,
                videoType = Constants.HOME_TITLE[1])
            var bean4 = VideoBean(
                "2021-8-1",
                100,
                100,
                "http://jykt.jy365.net/lessionnew/mp4/GC13A2819015_1902.mp4",
                "朱旭华",
                "智慧农业在助推乡村振兴中的模式探索”",
                "浙江托普云农股份有限公司副总经理",
                R.mipmap.zxh_cover,
                R.mipmap.country4,
                R.mipmap.zxh_cover,
                videoType = Constants.HOME_TITLE[1]
            )
            var bean5 = VideoBean(
                "2021-8-1",
                100,
                100,
                "http://jykt.jy365.net/lessionnew/mp4/NGC2021092901.mp4",
                "朱旭华",
                "探路乡村振兴“浙江模式”先行",
                "精英在线",
                R.mipmap.zxh_cover,
                R.mipmap.country5,
                R.mipmap.zxh_cover,
                videoType = Constants.HOME_TITLE[1]
            )
            var bean6 = VideoBean(
                "2021-8-1",
                100,
                100,
                "http://jykt.jy365.net/lessionnew/mp4/NGC2021092902.mp4",
                "朱旭华",
                "乡村振兴的江苏特色路径——让乡村振兴成为“强富美高”新江苏的鲜明底色",
                "精英在线",
                R.mipmap.zxh_cover,
                R.mipmap.country6,
                R.mipmap.zxh_cover,
                videoType = Constants.HOME_TITLE[1]
            )

            var bean7 = VideoBean(
                "2021-8-1",
                100,
                100,
                "http://jykt.jy365.net/lessionnew/mp4/NGC2021092903.mp4",
                "朱旭华",
                "以“基层治理”带动产业兴旺与乡村振兴——安吉县高禹村的乡村治理 “密码”",
                "精英在线",
                R.mipmap.zxh_cover,
                R.mipmap.country7,
                R.mipmap.zxh_cover,
                videoType = Constants.HOME_TITLE[1]
            )

            var bean8 = VideoBean(
                "2021-8-1",
                100,
                100,
                "http://jykt.jy365.net/lessionnew/mp4/NGC2021092904.mp4",
                "朱旭华",
                "建设田园综合体 振兴乡村产业——田园鲁家从“美丽乡村”走向“美丽经济",
                "精英在线",
                R.mipmap.zxh_cover,
                R.mipmap.country8,
                R.mipmap.zxh_cover,
                videoType = Constants.HOME_TITLE[1]
            )

            var bean9 = VideoBean(
                "2021-8-1",
                100,
                100,
                "http://jykt.jy365.net/lessionnew/mp4/NGC2021092905.mp4",
                "朱旭华",
                "创新党建凝聚民心促进乡村振兴——幸福刘家塘村的探索",
                "精英在线",
                R.mipmap.zxh_cover,
                R.mipmap.country9,
                R.mipmap.zxh_cover,
                videoType = Constants.HOME_TITLE[1]
            )

            var bean10 = VideoBean(
                "2021-8-1",
                100,
                100,
                "http://jykt.jy365.net/lessionnew/mp4/NGC2021092906.mp4",
                "朱旭华",
                "画外桐坞:中国版的“枫丹白露”——文化振兴和人才振兴的典范",
                "精英在线",
                R.mipmap.zxh_cover,
                R.mipmap.country10,
                R.mipmap.zxh_cover,
                videoType = Constants.HOME_TITLE[1]
            )
            var bean11 = VideoBean(
                "2021-8-1",
                100,
                100,
                "http://jykt.jy365.net/lessionnew/mp4/NGC2021092907.mp4",
                "朱旭华",
                "发展农业生态旅游 实现共同富裕——奉化滕头村发展经验",
                "精英在线",
                R.mipmap.zxh_cover,
                R.mipmap.country11,
                R.mipmap.zxh_cover,
                videoType = Constants.HOME_TITLE[1]
            )

            var bean12 = VideoBean(
                "2021-8-1",
                100,
                100,
                "http://jykt.jy365.net/lessionnew/mp4/NGC2021092908.mp4",
                "朱旭华",
                "“浙东红村”横坎头：以“红色文化”引领乡村振兴",
                "精英在线",
                R.mipmap.zxh_cover,
                R.mipmap.country12,
                R.mipmap.zxh_cover,
                videoType = Constants.HOME_TITLE[1]
            )

            var bean13 = VideoBean(
                "2021-8-1",
                100,
                100,
                "http://jykt.jy365.net/lessionnew/mp4/NGC2021092909.mp4",
                "朱旭华",
                "党建引领乡村旅游 加快生态振兴——苏州旺山村的华丽蜕变史",
                "精英在线",
                R.mipmap.zxh_cover,
                R.mipmap.country13,
                R.mipmap.zxh_cover,
                videoType = Constants.HOME_TITLE[1]
            )
            var bean14 = VideoBean(
                "2021-8-1",
                100,
                100,
                "http://jykt.jy365.net/lessionnew/mp4/NGC2021092910.mp4",
                "朱旭华",
                "产业迭代造就乡村振兴新样板——标杆蒋巷，在人间天堂里实现“田园梦",
                "精英在线",
                R.mipmap.zxh_cover,
                R.mipmap.country14,
                R.mipmap.zxh_cover,
                videoType = Constants.HOME_TITLE[1]
            )


            videoList.add(bean)
            videoList.add(bean2)
            videoList.add(bean3)
            videoList.add(bean4)
            videoList.add(bean5)
            videoList.add(bean6)
            videoList.add(bean7)
            videoList.add(bean8)
            videoList.add(bean9)
            videoList.add(bean10)
            videoList.add(bean11)
            videoList.add(bean12)
            videoList.add(bean13)
            videoList.add(bean14)
            return videoList
        }


        /**
         *生态文明
         */
        fun getEcoCult(): ArrayList<VideoBean>{
            var ecoCul = ArrayList<VideoBean>()
            var bean = VideoBean(
                "2021-8-1",
                100,
                100,
                "http://jykt.jy365.net/lessionnew/mp4/GC00I0118115_1811.mp4",
                "郭兆晖",
                "生态文明建设的共同富裕观和人类福祉观",
                "中央党校（国家行政学院）经济学教研部副教授",
                R.mipmap.gzh_head,
                R.mipmap.culture1,
                R.mipmap.gzh_head,
                videoType = Constants.HOME_TITLE[4]
            )

            var bean2 = VideoBean(
                "2021-8-1",
                100,
                100,
                "http://jykt.jy365.net/lessionnew/mp4/HGC00A0121031_2104.mp4",
                "陈瑛",
                "“无废城市”试点初步进展",
                "生态环境部固废与化学品管理中心固废管理技术部主任",
                R.mipmap.cy_head,
                R.mipmap.culture2,
                R.mipmap.cy_head,
                videoType = Constants.HOME_TITLE[4]
            )
            var bean3 = VideoBean(
                "2021-8-1",
                100,
                100,
                "http://jykt.jy365.net/lessionnew/mp4/HGC00I0120115_2012.mp4",
                "王小广",
                "生态文明建设重点与绿色发展机会（上）",
                "中央党校（国家行政学院）经济学部副主任、教授",
                R.mipmap.wxg_head,
                R.mipmap.culture4,
                R.mipmap.wxg_head,
                videoType = Constants.HOME_TITLE[4]
            )
            var bean4 = VideoBean(
                "2021-8-1",
                100,
                100,
                "http://jykt.jy365.net/lessionnew/mp4/HGC00I0220115_2012.mp4",
                "王小广",
                "生态文明建设重点与绿色发展机会（下）",
                "中央党校（国家行政学院）经济学部副主任、教授",
                R.mipmap.wxg_head,
                R.mipmap.culture5,
                R.mipmap.wxg_head,
                videoType = Constants.HOME_TITLE[4]
            )
            var bean5 = VideoBean(
                "2021-8-1",
                100,
                100,
                "http://jykt.jy365.net/lessionnew/mp4/NGCRK2021090873.mp4",
                "朱於",
                "护美山水助发展 安吉余村“两山”路 第一集 走进浙江安吉，追寻“两山”理念的思想起源",
                "浙江生态文明干部学院教师",
                R.mipmap.zhuyu_head,
                R.mipmap.culture6,
                R.mipmap.zhuyu_head,
                videoTime = "25",
                videoType = Constants.HOME_TITLE[4]
            )
            var bean6 = VideoBean(
                "2021-8-1",
                100,
                100,
                "http://jykt.jy365.net/lessionnew/mp4/NGCRK2021090874.mp4",
                "朱於",
                "护美山水助发展 安吉余村“两山”路 第二集 走进“两山理论”发源地余村，感悟“两山”理念的生动实践",
                "浙江生态文明干部学院教师",
                R.mipmap.zhuyu_head,
                R.mipmap.culture7,
                R.mipmap.zhuyu_head,
                videoTime = "25",
                videoType = Constants.HOME_TITLE[4]
            )
            var bean7 = VideoBean(
                "2021-8-1",
                100,
                100,
                "http://jykt.jy365.net/lessionnew/mp4/NGCRK2021090875.mp4",
                "朱於",
                "护美山水助发展 安吉余村“两山”路 第三集 走进黄杜村、鲁家村，共话“两山”理念的继往开来",
                "浙江生态文明干部学院教师",
                R.mipmap.zhuyu_head,
                R.mipmap.culture8,
                R.mipmap.zhuyu_head,
                videoTime = "25",
                videoType = Constants.HOME_TITLE[4]
            )
            ecoCul.add(bean)
            ecoCul.add(bean2)
            ecoCul.add(bean3)
            ecoCul.add(bean4)
            ecoCul.add(bean5)
            ecoCul.add(bean6)
            ecoCul.add(bean7)
            return ecoCul
        }

        /**
         * 获取法制乡村
         */
        fun getLawCountry(): ArrayList<VideoBean>{
            var ecoCoun = ArrayList<VideoBean>()
            var bean = VideoBean(
                "2021-8-1",
                100,
                100,
                "http://jykt.jy365.net/lessionnew/mp4/GC03I1816015_1602.mp4",
                "李军鹏",
                "农村法治建设的目标与对策",
                "国家行政学院公共管理教研部教授",
                R.mipmap.ljp_head,
                R.mipmap.law1,
                R.mipmap.ljp_head,
                videoType = Constants.HOME_TITLE[5]
            )

            var bean2 = VideoBean(
                "2021-8-1",
                100,
                100,
                "http://jykt.jy365.net/lessionnew/mp4/NC06T0520125_2101.mp4",
                "张璇孟",
                "法治建设护航乡村振兴的“余村样板",
                "浙江生态文明干部学院生态文明教研室主任",
                R.mipmap.zxm_head,
                R.mipmap.law2,
                R.mipmap.zxm_head,
                videoType = Constants.HOME_TITLE[5]
            )

            ecoCoun.add(bean)
            ecoCoun.add(bean2)
           // ecoCoun.add(bean3)
           // ecoCoun.add(bean4)
            return ecoCoun
        }

        /**
         * 获取经济建设的数据
         */
        fun getEcoBuild(): ArrayList<VideoBean>{
            var ecoCoun = ArrayList<VideoBean>()
            var bean = VideoBean(
                "2021-8-1",
                100,
                100,
                "http://jykt.jy365.net/lessionnew/mp4/GC03C0816125_1702.mp4",
                "贾康",
                "推进与房地产相关的制度改革",
                "华夏新供给经济学研究院院长",
                R.mipmap.jk_head,
                R.mipmap.eco1,
                R.mipmap.jk_head,
                videoTime = "25",
                videoType = Constants.HOME_TITLE[6]
            )

            var bean2 = VideoBean(
                "2021-8-1",
                100,
                100,
                "http://jykt.jy365.net/lessionnew/mp4/GC03C0217015_1705.mp4",
                "马光远",
                "中国房地产的问题与未来走势",
                "民建中央经济委员会副主任",
                R.mipmap.mgy_head,
                R.mipmap.eco2,
                R.mipmap.mgy_head,
                videoTime = "30",
                videoType = Constants.HOME_TITLE[6]
            )
            var bean3 = VideoBean(
                "2021-8-1",
                100,
                100,
                "http://jykt.jy365.net/lessionnew/mp4/GC03I0417055_1706.mp4",
                "王小广",
                "房地产健康发展的长效机制建设（上）",
                "国家行政学院决策咨询部副主任、研究员",
                R.mipmap.wxg_head,
                R.mipmap.eco3,
                R.mipmap.wxg_head,
                videoTime = "45",
                videoType = Constants.HOME_TITLE[6]
            )
            var bean4 = VideoBean(
                "2021-8-1",
                100,
                100,
                "http://jykt.jy365.net/lessionnew/mp4/GC03I0517055_1706.mp4",
                "王小广",
                "房地产健康发展的长效机制建设（下）",
                "国家行政学院决策咨询部副主任、研究员",
                R.mipmap.wxg_head,
                R.mipmap.eco4,
                R.mipmap.wxg_head,
                videoTime = "50",
                videoType = Constants.HOME_TITLE[6]
            )
            ecoCoun.add(bean)
            ecoCoun.add(bean2)
            ecoCoun.add(bean3)
            ecoCoun.add(bean4)
            return ecoCoun
        }
        /**
         * 获取数字党建相关的内容
         */
        fun getDangBuild(): ArrayList<VideoBean>{
            var ecoCoun = ArrayList<VideoBean>()
            var bean = VideoBean(
                "2021-8-1",
                100,
                100,
                "http://jykt.jy365.net/lessionnew/mp4/HGC89I2821035_2104.mp4",
                "祝彦",
                "重温红色历史，弘扬长征精神（上）",
                "中央党校（国家行政学院）党史教研部教授、毛泽东思想教研室主任",
                R.mipmap.zy_head,
                R.mipmap.dang1,
                R.mipmap.zy_head,
                videoTime = "35",
                videoType = Constants.HOME_TITLE[7]
            )

            var bean2 = VideoBean(
                "2021-8-1",
                100,
                100,
                "http://jykt.jy365.net/lessionnew/mp4/HGC89I2921035_2104.mp4",
                "祝彦",
                "重温红色历史，弘扬长征精神（下）",
                "中央党校（国家行政学院）党史教研部教授、毛泽东思想教研室主任",
                R.mipmap.zy_head,
                R.mipmap.dang2,
                R.mipmap.zy_head,
                videoTime = "26",
                videoType = Constants.HOME_TITLE[7])
            var bean3 = VideoBean(
                "2021-8-1",
                100,
                100,
                "http://jykt.jy365.net/lessionnew/mp4/HGC89I3021045_2104.mp4",
                "王峰",
                "红色基因，精神永存——在学习党史中汲取前行力量（上）",
                "北京师范大学马克思主义学院副教授",
                R.mipmap.wf_head,
                R.mipmap.dang3,
                R.mipmap.wf_head,
                videoTime = "29",
                videoType = Constants.HOME_TITLE[7]
            )
            var bean4 = VideoBean(
                "2021-8-1",
                100,
                100,
                "http://jykt.jy365.net/lessionnew/mp4/HGC89I3121045_2104.mp4",
                "王峰",
                "红色基因，精神永存——在学习党史中汲取前行力量（下）",
                "北京师范大学马克思主义学院副教授",
                R.mipmap.wf_head,
                R.mipmap.dang4,
                R.mipmap.wf_head,
                videoTime = "29",
                videoType = Constants.HOME_TITLE[7]
            )
            ecoCoun.add(bean)
            ecoCoun.add(bean2)
            ecoCoun.add(bean3)
            ecoCoun.add(bean4)
            return ecoCoun
        }

        /**
         * 获取数字建设相关的内容
         */
        fun getNumberBuild(): ArrayList<VideoBean>{
            var ecoCoun = ArrayList<VideoBean>()
            var bean = VideoBean(
                "2021-8-1",
                100,
                100,
                "http://jykt.jy365.net/lessionnew/mp4/GC03A0117095_1711.mp4",
                "袁晓庆",
                "推动制造企业数字化转型的五个着力点",
                "赛迪智库信息化研究中心博士",
                R.mipmap.yxq_head,
                R.mipmap.number1,
                R.mipmap.yxq_head,
                videoTime = "20",
                videoType = Constants.HOME_TITLE[3]
            )

            var bean2 = VideoBean(
                "2021-8-1",
                100,
                100,
                "http://jykt.jy365.net/lessionnew/mp4/GC03N0918065_1806.mp4",
                "李勇坚",
                "推动数字产业化与产业数字化——学习习近平总书记全国网信工作会议重要讲话",
                "中国社会科学院财经战略研究院研究员",
                R.mipmap.lyj_head,
                R.mipmap.number2,
                R.mipmap.lyj_head,
                videoTime = "50",
                videoType = Constants.HOME_TITLE[3]
            )
            ecoCoun.add(bean)
            ecoCoun.add(bean2)
            return ecoCoun
        }

        /**
         * 获取党史学习相关的内容
         */
        fun getLearnGangHis(): ArrayList<VideoBean>{
            var ecoCoun = ArrayList<VideoBean>()
            var bean = VideoBean(
                "2021-8-1",
                100,
                100,
                "http://jykt.jy365.net/lessionnew/mp4/DSP21090301.mp4",
                "安振华",
                "延安精神永放光芒（上）",
                "延安市委理论讲师团团长",
                R.mipmap.azh_head,
                R.mipmap.dagnhis1,
                R.mipmap.azh_head,
                videoTime = "18",
                videoType = Constants.HOME_TITLE[0]
            )

            var bean2 = VideoBean(
                "2021-8-1",
                100,
                100,
                "http://jykt.jy365.net/lessionnew/mp4/DSP21090303.mp4",
                "杨延虎",
                "艰苦奋斗是延安时期共产党人的政治本色",
                "延安大学教授，中国延安干部学院返聘教授，西北大学外聘博士生导师",
                R.mipmap.yyh_head,
                R.mipmap.dagnhis2,
                R.mipmap.yyh_head,
                videoTime = "27",
                videoType = Constants.HOME_TITLE[0]
            )

            var bean3 = VideoBean(
                "2021-8-1",
                100,
                100,
                "http://jykt.jy365.net/lessionnew/mp4/DSP21090302.mp4",
                "李益模",
                "延安精神永放光芒（下）",
                "延安市委理论讲师团团长",
                R.mipmap.lym_head,
                R.mipmap.dagnhis3,
                R.mipmap.lym_head,
                videoTime = "20",
                videoType = Constants.HOME_TITLE[0]
            )
            var bean4 = VideoBean(
                "2021-8-1",
                100,
                100,
                "http://jykt.jy365.net/lessionnew/mp4/DSP21090304.mp4",
                "惠小峰",
                "身临延安革命旧址 坚持和践行群众路线",
                "延安大学教授",
                R.mipmap.hxf_head,
                R.mipmap.yabts,
                R.mipmap.hxf_head,
                videoTime = "24",
                videoType = Constants.HOME_TITLE[0]
            )
            ecoCoun.add(bean)
            ecoCoun.add(bean2)
            ecoCoun.add(bean3)
            ecoCoun.add(bean4)
            return ecoCoun
        }

        /**
         * 获取基层治理相关的内容
         */
        fun getPrimaryLevelGovernance(): ArrayList<VideoBean>{
            var ecoCoun = ArrayList<VideoBean>()
            var bean1 = VideoBean(
                "2018-8",
                100,
                100,
                "http://jykt.jy365.net/lessionnew/mp4/GC03A0618085_1808.mp4",
                "黄石松",
                "养老服务新格局下的新动力",
                "中国人民大学国家发展与战略研究院研究员",
                R.mipmap.hss_head,
                R.mipmap.primary1,
                R.mipmap.hss_head,
                videoTime = "20",
                videoType = Constants.HOME_TITLE[2]
            )

            var bean2 = VideoBean(
                "2015-3",
                100,
                100,
                "http://jykt.jy365.net/lessionn" +
                        "ew/mp4/GC26I1115035_1504.mp4",
                "王道勇",
                "养老制度改革：积极应对“国未富民先老”局面",
                "中央党校社会学教研室副教授、副主任",
                R.mipmap.wdy_head,
                R.mipmap.primary2,
                R.mipmap.wdy_head,
                videoTime = "60",
                videoType = Constants.HOME_TITLE[2]
            )
            var bean3 = VideoBean(
                "2021-4",
                100,
                100,
                "http://jykt.jy365.net/lessionnew/mp4/NGC85A2021041_2105.mp4",
                "杜鹏",
                "实施积极应对人口老龄化国家战略，健全基本养老服务体系上",
                "中国人民大学副校长、老年学研究所所长",
                R.mipmap.dp_head,
                R.mipmap.primary3,
                R.mipmap.dp_head,
                videoTime = "33",
                videoType = Constants.HOME_TITLE[2]
            )
            var bean4 = VideoBean(
                "2017-2",
                100,
                100,
                "http://jykt.jy365.net/lessionnew/mp4/GC03N0617025_1703.mp4",
                "郑秉文",
                "推进养老保险制度改革",
                "中国社会科学院研究员",
                R.mipmap.zbw_head,
                R.mipmap.primary4,
                R.mipmap.zbw_head,
                videoTime = "30",
                videoType = Constants.HOME_TITLE[2]
            )
            var bean5 = VideoBean(
                "2017-2",
                100,
                100,
                "http://jykt.jy365.net/lessionnew/mp4/NGCRK2021090867.mp4",
                "张璐",
                "改革开放中“闯”出来的“昆山之路” 第一集",
                "中共昆山市委党校老师",
                R.mipmap.zl_head,
                R.mipmap.primary5,
                R.mipmap.zl_head,
                videoTime = "30",
                videoType = Constants.HOME_TITLE[2]
            )
            var bean6 = VideoBean(
                "2017-2",
                100,
                100,
                "http://jykt.jy365.net/lessionnew/mp4/NGCRK2021090868.mp4",
                "周宇阳",
                "改革开放中“闯”出来的“昆山之路” 第二集",
                "中共昆山市委党校老师",
                R.mipmap.zyy_head,
                R.mipmap.primary6,
                R.mipmap.zyy_head,
                videoTime = "30",
                videoType = Constants.HOME_TITLE[2]
            )
            var bean7 = VideoBean(
                "2017-2",
                100,
                100,
                "http://jykt.jy365.net/lessionnew/mp4/NGCRK2021090869.mp4",
                "吴艳萍",
                "改革开放中“闯”出来的“昆山之路” 第三集",
                "中共昆山市委党校老师",
                R.mipmap.wyp_head,
                R.mipmap.primary7,
                R.mipmap.wyp_head,
                videoTime = "30",
                videoType = Constants.HOME_TITLE[2]
            )
            var bean8 = VideoBean(
                "2021-8-1",
                100,
                100,
                "http://jykt.jy365.net/lessionnew/mp4/NGC2021092911.mp4",
                "张璇孟",
                "法治建设护航乡村振兴的“余村样本”第一集",
                "浙江生态文明干部学院生态文明教研室主任",
                R.mipmap.zxm_head,
                R.mipmap.law3,
                R.mipmap.zxm_head,
                videoType = Constants.HOME_TITLE[5]
            )
            var bean9 = VideoBean(
                "2021-8-1",
                100,
                100,
                "http://jykt.jy365.net/lessionnew/mp4/NGC2021092912.mp4",
                "张璇孟",
                "法治建设护航乡村振兴的“余村样本”第二集",
                "浙江生态文明干部学院生态文明教研室主任",
                R.mipmap.zxm_head,
                R.mipmap.law4,
                R.mipmap.zxm_head,
                videoType = Constants.HOME_TITLE[5]
            )
            var bean10 = VideoBean(
                "2021-8-1",
                100,
                100,
                "http://jykt.jy365.net/lessionnew/mp4/NGC2021092913.mp4",
                "张璇孟",
                "三治融合“桐乡经验”持续创新",
                "浙江生态文明干部学院生态文明教研室主任",
                R.mipmap.zxm_head,
                R.mipmap.primary10,
                R.mipmap.zxm_head,
                videoType = Constants.HOME_TITLE[5]
            )
            ecoCoun.add(bean10)
            ecoCoun.add(bean8)
            ecoCoun.add(bean9)
            ecoCoun.add(bean1)
            ecoCoun.add(bean2)
            ecoCoun.add(bean3)
            ecoCoun.add(bean4)
            ecoCoun.add(bean5)
            ecoCoun.add(bean6)
            ecoCoun.add(bean7)
            return ecoCoun
        }

        /**
         * 获取杨家岭相关的内容
         */
        fun getYangJiaLing(): ArrayList<VideoBean>{
            var ecoCoun = ArrayList<VideoBean>()
            var bean = VideoBean(
                "2021-8-1",
                100,
                100,
                "http://jykt.jy365.net/lessionnew/mp4/NGCRK2021090807.mp4",
                "张小兵",
                "艰难曲折话七大（上）身临杨家岭 艰难曲折话七大",
                "延安大学教授",
                R.mipmap.yxq_head,
                R.mipmap.yangjialing1,
                R.mipmap.yxq_head,
                videoTime = "33",
                videoType = Constants.HOME_TITLE[8]
            )

            var bean2 = VideoBean(
                "2021-8-1",
                100,
                100,
                "http://jykt.jy365.net/lessionnew/mp4/NGCRK2021090808.mp4",
                "张小兵",
                "艰难曲折话七大（下）亲临延安“七大”会场 重温“七大”光辉历程",
                "延安大学教授",
                R.mipmap.lyj_head,
                R.mipmap.yangjialing2,
                R.mipmap.lyj_head,
                videoTime = "26",
                videoType = Constants.HOME_TITLE[8]
            )
            var bean3 = VideoBean(
                "2021-8-1",
                100,
                100,
                "http://jykt.jy365.net/lessionnew/mp4/NGCRK2021090810.mp4",
                "杨延虎",
                "延安整风与毛泽东核心地位的巩固 第一集 走进杨家岭，讲讲延安整风运动",
                "延安大学教授，中国延安干部学院返聘教授，西北大学外聘博士生导师",
                R.mipmap.lyj_head,
                R.mipmap.yangjialing3,
                R.mipmap.lyj_head,
                videoTime = "26",
                videoType = Constants.HOME_TITLE[8]
            )
            var bean4 = VideoBean(
                "2021-8-1",
                100,
                100,
                "http://jykt.jy365.net/lessionnew/mp4/NGCRK2021090914.mp4",
                "刘茜",
                "杨家岭讲解",
                "延安南泥湾学院讲师",
                R.mipmap.lyj_head,
                R.mipmap.yangjialing4,
                R.mipmap.lyj_head,
                videoTime = "22",
                videoType = Constants.HOME_TITLE[8]
            )
            ecoCoun.add(bean)
            ecoCoun.add(bean2)
            ecoCoun.add(bean3)
            ecoCoun.add(bean4)
            return ecoCoun
        }

        /**
         * 获取七大相关的内容
         */
        fun getQiDa(): ArrayList<VideoBean>{
            var ecoCoun = ArrayList<VideoBean>()
            var bean = VideoBean(
                "2021-8-1",
                100,
                100,
                "http://jykt.jy365.net/lessionnew/mp4/NGCRK2021090807.mp4",
                "张小兵",
                "艰难曲折话七大（上）身临杨家岭 艰难曲折话七大",
                "延安大学教授",
                R.mipmap.yxq_head,
                R.mipmap.yangjialing1,
                R.mipmap.yxq_head,
                videoTime = "33",
                videoType = Constants.HOME_TITLE[12],
                startPosition = (2*60+45)*1000
            )

            var bean2 = VideoBean(
                "2021-8-1",
                100,
                100,
                "http://jykt.jy365.net/lessionnew/mp4/NGCRK2021090808.mp4",
                "张小兵",
                "艰难曲折话七大（下）亲临延安“七大”会场 重温“七大”光辉历程",
                "延安大学教授",
                R.mipmap.lyj_head,
                R.mipmap.yangjialing2,
                R.mipmap.lyj_head,
                videoTime = "26",
                videoType = Constants.HOME_TITLE[12],
                startPosition = (20)*1000
            )
            var bean3 = VideoBean(
                "2021-8-1",
                100,
                100,
                "http://jykt.jy365.net/lessionnew/mp4/NGCRK2021090808.mp4",
                "张小兵",
                "艰难曲折话七大（下）亲临延安“七大”会场 重温“七大”光辉历程",
                "延安大学教授",
                R.mipmap.lyj_head,
                R.mipmap.yangjialing2,
                R.mipmap.lyj_head,
                videoTime = "26",
                videoType = Constants.HOME_TITLE[12],
                startPosition = (22*60+19)*1000
            )
            var bean4 = VideoBean(
                "2021-8-1",
                100,
                100,
                "http://jykt.jy365.net/lessionnew/mp4/NGCRK2021090914.mp4",
                "刘茜",
                "杨家岭讲解",
                "延安南泥湾学院讲师",
                R.mipmap.lyj_head,
                R.mipmap.yangjialing4,
                R.mipmap.lyj_head,
                videoTime = "22",
                videoType = Constants.HOME_TITLE[12],
                startPosition = (2*60+10)*1000
            )
            ecoCoun.add(bean)
            ecoCoun.add(bean2)
            ecoCoun.add(bean3)
            ecoCoun.add(bean4)
            return ecoCoun
        }

        /**
         * 获取十四五相关的内容
         */
        fun getShiSiWu(): ArrayList<VideoBean>{
            var ecoCoun = ArrayList<VideoBean>()
            var bean = VideoBean(
                "2021-8-1",
                100,
                100,
                "http://jykt.jy365.net/lessionnew/mp4/HGC72N4220115_2012.mp4",
                "赵英",
                "“十四五”规划《建议》的重大意义与经济社会发展基本原则（上）",
                "中国社会科学院工业经济研究所研究员、国家经济发展与经济风险研究中心主任",
                R.mipmap.zhaoying_head,
                R.mipmap.shisiwu1,
                R.mipmap.zhaoying_head,
                videoTime = "34",
                videoType = Constants.HOME_TITLE[13],
                startPosition = (21*60+54)*1000
            )

            var bean2 = VideoBean(
                "2021-8-1",
                100,
                100,
                "http://jykt.jy365.net/lessionnew/mp4/HGC90I4021035_2103.mp4",
                "王小广",
                "“十四五”规划和2035年远景目标纲要的主要亮点（上）",
                "中共中央党校（国家行政学院）经济学部副主任、教授",
                R.mipmap.wxg_head,
                R.mipmap.shisiwu2,
                R.mipmap.wxg_head,
                videoTime = "33",
                videoType = Constants.HOME_TITLE[13],
                startPosition = (6*60+40)*1000
            )
            var bean3 = VideoBean(
                "2021-8-1",
                100,
                100,
                "http://jykt.jy365.net/lessionnew/mp4/HGC72I3620115_2012.mp4",
                "黄相怀",
                "“十四五”规划与现代化国家的战略谋划（下）",
                "中共中央党校（国家行政学院）国家高端智库学术委员会秘书长",
                R.mipmap.huangnxianghuai_head,
                R.mipmap.shisiwu3,
                R.mipmap.huangnxianghuai_head,
                videoTime = "30",
                videoType = Constants.HOME_TITLE[13],
                startPosition = (20*60+33)*1000
            )
            var bean4 = VideoBean(
                "2021-8-1",
                100,
                100,
                "http://jykt.jy365.net/lessionnew/mp4/HGC90I3621035_2104.mp4",
                "王小广",
                "“四个全面”新布局与“十四五”及2021年主要举措（上）",
                "中央党校（国家行政学院）经济学部副主任、教授、博导",
                R.mipmap.wxg_head,
                R.mipmap.shisiwu4,
                R.mipmap.wxg_head,
                videoTime = "32",
                videoType = Constants.HOME_TITLE[13],
                startPosition = (5*60+39)*1000
            )
            var bean5 = VideoBean(
                "2021-8-1",
                100,
                100,
                "http://jykt.jy365.net/lessionnew/mp4/HGC85I3521015_2102.mp4",
                "张燕生",
                "乘势而上为“十四五”开新局、起好步（下）",
                "中国国际经济交流中心首席研究员、原国家发改委学术委员会秘书长",
                R.mipmap.zhangyansheng_head,
                R.mipmap.shisiwu5,
                R.mipmap.zhangyansheng_head,
                videoTime = "32",
                videoType = Constants.HOME_TITLE[13],
                startPosition = (15*60+20)*1000
            )
            var bean6 = VideoBean(
                "2021-8-1",
                100,
                100,
                "http://jykt.jy365.net/lessionnew/mp4/HC85N1820125_2101.mp4",
                "赵英",
                "以“双循环”新发展格局推动“十四五”时期经济高质量发展（下）",
                "中国社会科学院工业经济研究所研究员、国家经济发展与经济风险研究中心主任",
                R.mipmap.zhaoying_head,
                R.mipmap.shisiwu6,
                R.mipmap.zhaoying_head,
                videoTime = "32",
                videoType = Constants.HOME_TITLE[13],
                startPosition = (26*60+19)*1000
            )
            ecoCoun.add(bean)
            ecoCoun.add(bean2)
            ecoCoun.add(bean3)
            ecoCoun.add(bean4)
            ecoCoun.add(bean5)
            ecoCoun.add(bean6)
            return ecoCoun
        }
        /**
         * 三治融合演示假数据
         */
        private fun getSanzhi(): ArrayList<VideoBean>{
            var ecoCoun = ArrayList<VideoBean>()
            var bean = VideoBean(
                "2021-8-1",
                100,
                100,
                "http://jykt.jy365.net/lessionnew/mp4/NGC2021092913.mp4",
                "赵英",
                "打造“三治融合”新格局",
                "中国社会科学院工业经济研究所研究员、国家经济发展与经济风险研究中心主任",
                R.mipmap.zhaoying_head,
                R.mipmap.sanzhi1,
                R.mipmap.zhaoying_head,
                videoTime = "34",
                videoType = Constants.HOME_TITLE[13],
                startPosition = 13*1000
            )

            var bean2 = VideoBean(
                "2021-8-1",
                100,
                100,
                "http://jykt.jy365.net/lessionnew/mp4/HGC90I4021035_2103.mp4",
                "王小广",
                "党建引领 三治融合",
                "中共中央党校（国家行政学院）经济学部副主任、教授",
                R.mipmap.wxg_head,
                R.mipmap.sanzhi2,
                R.mipmap.wxg_head,
                videoTime = "33",
                videoType = Constants.HOME_TITLE[13],
                startPosition = (6*60+40)*1000
            )
            var bean3 = VideoBean(
                "2021-8-1",
                100,
                100,
                "http://jykt.jy365.net/lessionnew/mp4/HGC72I3620115_2012.mp4",
                "黄相怀",
                "三治融合关键在融",
                "中共中央党校（国家行政学院）国家高端智库学术委员会秘书长",
                R.mipmap.huangnxianghuai_head,
                R.mipmap.sanzhi3,
                R.mipmap.huangnxianghuai_head,
                videoTime = "30",
                videoType = Constants.HOME_TITLE[13],
                startPosition = (20*60+33)*1000
            )
            var bean4 = VideoBean(
                "2021-8-1",
                100,
                100,
                "http://jykt.jy365.net/lessionnew/mp4/HGC90I3621035_2104.mp4",
                "王小广",
                "走好“三治融合”的新路子",
                "中央党校（国家行政学院）经济学部副主任、教授、博导",
                R.mipmap.wxg_head,
                R.mipmap.sanzhi4,
                R.mipmap.wxg_head,
                videoTime = "32",
                videoType = Constants.HOME_TITLE[13],
                startPosition = (5*60+39)*1000
            )
            ecoCoun.add(bean)
            ecoCoun.add(bean2)
            ecoCoun.add(bean3)
            ecoCoun.add(bean4)
            return ecoCoun
        }
        /**
         * 获取抗大相关的内容
         */
        fun getKangDa(): ArrayList<VideoBean>{
            var ecoCoun = ArrayList<VideoBean>()
            var bean = VideoBean(
                "2021-8-1",
                100,
                100,
                "http://jykt.jy365.net/lessionnew/mp4/NGCRK2021090815.mp4",
                "张志强",
                "身临抗大革命纪念馆 回忆抗战时期爱国青年的朝圣之路",
                "延安大学马克思主义学院副教授",
                R.mipmap.yxq_head,
                R.mipmap.number1,
                R.mipmap.yxq_head,
                videoTime = "18",
                videoType = Constants.HOME_TITLE[10]
            )

            var bean2 = VideoBean(
                "2021-8-1",
                100,
                100,
                "http://jykt.jy365.net/lessionnew/mp4/NGCRK2021090904.mp4",
                "侯雯",
                "抗大艰苦奋斗的优良作风",
                "延安南泥湾学院讲师",
                R.mipmap.lyj_head,
                R.mipmap.number2,
                R.mipmap.lyj_head,
                videoTime = "26",
                videoType = Constants.HOME_TITLE[10]
            )
            var bean3 = VideoBean(
                "2021-8-1",
                100,
                100,
                "http://jykt.jy365.net/lessionnew/mp4/NGCRK2021090915.mp4",
                "张苗苗",
                "抗大纪念馆讲解",
                "延安南泥湾学院讲师",
                R.mipmap.lyj_head,
                R.mipmap.number2,
                R.mipmap.lyj_head,
                videoTime = "21",
                videoType = Constants.HOME_TITLE[10]
            )
            ecoCoun.add(bean)
            ecoCoun.add(bean2)
            ecoCoun.add(bean3)
            return ecoCoun
        }

        /**
         * 获取南泥湾相关的内容
         */
        fun getNanNiWan(): ArrayList<VideoBean>{
            var ecoCoun = ArrayList<VideoBean>()
            var bean = VideoBean(
                "2021-8-1",
                100,
                100,
                "http://jykt.jy365.net/lessionnew/mp4/NGCRK2021090826.mp4",
                "郝琦",
                "身临南泥湾革命旧址 体会三五九旅开发南泥湾的精神内核",
                "延安大学马克思主义学院院委书记、教授",
                R.mipmap.yxq_head,
                R.mipmap.number1,
                R.mipmap.yxq_head,
                videoTime = "18",
                videoType = Constants.HOME_TITLE[11]
            )

            var bean2 = VideoBean(
                "2021-8-1",
                100,
                100,
                "http://jykt.jy365.net/lessionnew/mp4/NGCRK2021090882.mp4",
                "冯静",
                "毛泽东视察南泥湾",
                "延安南泥湾学院讲师",
                R.mipmap.lyj_head,
                R.mipmap.number2,
                R.mipmap.lyj_head,
                videoTime = "10",
                videoType = Constants.HOME_TITLE[11]
            )
            var bean3 = VideoBean(
                "2021-8-1",
                100,
                100,
                "http://jykt.jy365.net/lessionnew/mp4/NGCRK2021090898.mp4",
                "侯雯",
                "什么是南泥湾精神",
                "延安南泥湾学院讲师",
                R.mipmap.lyj_head,
                R.mipmap.number2,
                R.mipmap.lyj_head,
                videoTime = "21",
                videoType = Constants.HOME_TITLE[11]
            )
            var bean4 = VideoBean(
                "2021-8-1",
                100,
                100,
                "http://jykt.jy365.net/lessionnew/mp4/NGCRK2021090913.mp4",
                "侯雯",
                "南泥湾大生产展览馆讲解",
                "延安南泥湾学院讲师",
                R.mipmap.lyj_head,
                R.mipmap.number2,
                R.mipmap.lyj_head,
                videoTime = "29",
                videoType = Constants.HOME_TITLE[11]
            )
            ecoCoun.add(bean)
            ecoCoun.add(bean2)
            ecoCoun.add(bean3)
            return ecoCoun
        }

        /**
         * 获取井冈山相关的内容
         */
        fun getJingGangShan(): ArrayList<VideoBean>{
            var ecoCoun = ArrayList<VideoBean>()
            var bean = VideoBean(
                "2021-8-1",
                100,
                100,
                "http://jykt.jy365.net/lessionnew/mp4/NGCRK2021090844.mp4",
                "李忠",
                "井冈山_三湾改编：新型人民军队的诞生",
                "井冈山大学马克思主义学院教授、硕士研究生导师",
                R.mipmap.yxq_head,
                R.mipmap.jinggangshan1,
                R.mipmap.yxq_head,
                videoTime = "10",
                videoType = Constants.HOME_TITLE[9]
            )

            var bean2 = VideoBean(
                "2021-8-1",
                100,
                100,
                "http://jykt.jy365.net/lessionnew/mp4/NGCRK2021090845.mp4",
                "肖邮华",
                "井冈山_光辉的井冈山道路",
                "井冈山革命博物馆原馆长",
                R.mipmap.lyj_head,
                R.mipmap.jinggangshan2,
                R.mipmap.lyj_head,
                videoTime = "16",
                videoType = Constants.HOME_TITLE[9]
            )
            var bean3 = VideoBean(
                "2021-8-1",
                100,
                100,
                "http://jykt.jy365.net/lessionnew/mp4/NGCRK2021090848.mp4",
                "李忠",
                "井冈山_军政院校的摇篮——龙江书院  探访朱毛第一次会师地",
                "井冈山大学马克思主义学院教授、研究生导师",
                R.mipmap.lyj_head,
                R.mipmap.jinggangshan3,
                R.mipmap.lyj_head,
                videoTime = "12",
                videoType = Constants.HOME_TITLE[9]
            )
            var bean4 = VideoBean(
                "2021-8-1",
                100,
                100,
                "http://jykt.jy365.net/lessionnew/mp4/NGCRK2021090849.mp4",
                "肖邮华",
                "井冈山_伟大的井冈山精神",
                "井冈山革命博物馆原馆长",
                R.mipmap.lyj_head,
                R.mipmap.jinggangshan4,
                R.mipmap.lyj_head,
                videoTime = "19",
                videoType = Constants.HOME_TITLE[9]
            )
            var bean5 = VideoBean(
                "2021-8-1",
                100,
                100,
                "http://jykt.jy365.net/lessionnew/mp4/NGCRK2021090850.mp4",
                "李忠",
                "井冈山_黄洋界保卫战",
                "井冈山大学马克思主义学院教授、硕士研究生导师",
                R.mipmap.lyj_head,
                R.mipmap.jinggangshan5,
                R.mipmap.lyj_head,
                videoTime = "9",
                videoType = Constants.HOME_TITLE[9]
            )
            var bean6 = VideoBean(
                "2021-8-1",
                100,
                100,
                "http://jykt.jy365.net/lessionnew/mp4/NGCRK2021090851.mp4",
                "肖邮华",
                "井冈山_毛泽东同志为什么选择在井冈山建立农村革命根据地",
                "井冈山革命博物馆原馆长",
                R.mipmap.lyj_head,
                R.mipmap.jinggangshan6,
                R.mipmap.lyj_head,
                videoTime = "19",
                videoType = Constants.HOME_TITLE[9]
            )
            ecoCoun.add(bean)
            ecoCoun.add(bean2)
            ecoCoun.add(bean3)
            ecoCoun.add(bean4)
            ecoCoun.add(bean5)
            ecoCoun.add(bean6)
            return ecoCoun
        }

        fun getSearchHis(): ArrayList<String>{
            var hisData = ArrayList<String>()
            hisData.add("党史学习")
            hisData.add("乡村振兴")
            hisData.add("基层治理")
            hisData.add("数字变革")
            hisData.add("生态文明")
            hisData.add("法制建设")
            hisData.add("经济建设")
            hisData.add("党建引领")
            return hisData
        }

        /**
         * 获取乡村建设的书籍
         */
        fun getCountryBook(): ArrayList<BookBean>{
            var books = ArrayList<BookBean>()
            var book1 = BookBean("城镇化建设","李军鹏","",R.mipmap.czhjs)
            var book2 = BookBean("公共服务型政府","李军鹏","",R.mipmap.ggfwxzf)
            books.add(book1)
            books.add(book2)
            return books
        }

        /**
         * 获取生态文明的书籍
         */
        fun getEcocultBook(): ArrayList<BookBean>{
            var books = ArrayList<BookBean>()
            var book1 = BookBean("生态文明体制改革初论","郭兆晖","",R.mipmap.stwmtzggcl)
            var book2 = BookBean("碳市场原理与实践研究","郭兆晖","",R.mipmap.cscyzysjyj)
            var book3 = BookBean("我国生态文明制度体系研究","郭兆晖","",R.mipmap.wgstwmzdtxyj)
            books.add(book1)
            books.add(book2)
            books.add(book3)
            return books
        }

        /**
         * 获取法治相关书籍
         */
        fun getLawBuildBook(): ArrayList<BookBean>{
            var books = ArrayList<BookBean>()
            var book1 = BookBean("公共管理学","李军鹏","",R.mipmap.ggglx)
            var book2 = BookBean("现代政府建设","李军鹏","",R.mipmap.xdzfjs)
            books.add(book1)
            books.add(book2)
            return books
        }

        /**
         * 获取经济建设书籍
         */
        fun getEcoBuildBook(): ArrayList<BookBean>{
            var books = ArrayList<BookBean>()
            var book1 = BookBean("供给侧改革","贾康","",R.mipmap.gjcgg)
            var book2 = BookBean("中国的坎-如何跨越中等收入陷阱","贾康","",R.mipmap.zgdk)
            var book3 = BookBean("中国财政制度史","贾康","",R.mipmap.zgczzd)
            books.add(book1)
            books.add(book2)
            books.add(book3)
            return books
        }

        /**
         * 获取社会党建书籍
         */
        fun getDangBuildBook(): ArrayList<BookBean>{
            var books = ArrayList<BookBean>()
            var book1 = BookBean("《论共产党员的修养》导读","祝彦","",R.mipmap.lgcdydxy)
            var book2 = BookBean("大革命后的陈独秀","祝彦","",R.mipmap.dgmhdcdx)
            books.add(book1)
            books.add(book2)
            return books
        }

        /**
         * 获取数字党建书籍
         */
        fun getNumberBuildBook(): ArrayList<BookBean>{
            var books = ArrayList<BookBean>()
            var book1 = BookBean("从产品经济到服务经济","李勇坚","",R.mipmap.ccpjjdfwjj)
            var book2 = BookBean("服务业的增长","李勇坚","",R.mipmap.fwydzz)
            var book3 = BookBean("服务业改革的中国实践与探索","李勇坚","",R.mipmap.fwyggdzgsjyts)
            books.add(book1)
            books.add(book2)
            books.add(book3)
            return books
        }

        /**
         * 获取党史学习书籍
         */
        fun getLearnDangHisBook(): ArrayList<BookBean>{
            var books = ArrayList<BookBean>()
            var book1 = BookBean("延安时期与延安精神研究","安振华","",R.mipmap.yasqyyajsyj)
            var book2 = BookBean("井冈山精神","肖邮华","",R.mipmap.jgsjs)
            books.add(book1)
            books.add(book2)
            return books
        }

        /**
         * 获取基层治理书籍
         */
        fun getPrimaryLevelGovernanceBook(): ArrayList<BookBean>{
            var books = ArrayList<BookBean>()
            var book1 = BookBean("中国人口老龄化过程研究","杜鹏","",R.mipmap.zgrkllhgc)
            var book2 = BookBean("回顾与展望—中国老人养老方式研究","杜鹏","",R.mipmap.zglrylfsyj)
            var book3 = BookBean("房价之谜","黄石松","",R.mipmap.fjzm)
            books.add(book1)
            books.add(book2)
            books.add(book3)
            return books
        }


        fun initBookByType(result: String?): ArrayList<BookBean>{
            result?.let {
                return when {
                    result.contains("乡村振兴") -> {
                        getCountryBook()
                    }
                    result.contains("生态文明") -> {
                        getEcocultBook()
                    }
                    result.contains("法制建设")-> {
                        getLawBuildBook()
                    }
                    result.contains("经济建设") -> {
                        getEcoBuildBook()
                    }
                    result.contains("党建引领") -> {
                        getDangBuildBook()
                    }
                    result.contains("数字变革") -> {
                        getNumberBuildBook()
                    }
                    result.contains("党史学习") -> {
                        getLearnDangHisBook()
                    }
                    result.contains("基层治理") -> {
                        getPrimaryLevelGovernanceBook()
                    }
                    else->{
                        getCountryBook()
                    }
                }
            }
            return  getCountryBook()
        }

        fun initTeacherOrClassByType(result: String?): ArrayList<VideoBean>{
            result?.let {
                return when {
                    result.contains("乡村振兴") -> {
                        getCountryVideo()
                    }
                    result.contains("生态文明") -> {
                        getEcoCult()
                    }
                    result.contains("法制建设")  -> {
                        getLawCountry()
                    }
                    result.contains("经济建设") -> {
                        getEcoBuild()
                    }
                    result.contains("党建引领") -> {
                        getDangBuild()
                    }
                    result.contains("数字变革") -> {
                        getNumberBuild()
                    }
                    result.contains("党史学习")  -> {
                        getLearnGangHis()
                    }
                    result.contains("基层治理")  -> {
                        getPrimaryLevelGovernance()
                    }
                    result.contains("杨家岭") -> {
                        getYangJiaLing()
                    }
                    result.contains("井冈山") -> {
                        getJingGangShan()
                    }
                    result.contains("七大")->{
                        getQiDa()
                    }
                    result.contains("十四五")->{
                        getShiSiWu()
                    }
                    //
                    result.contains("融合")->{
                        getSanzhi()
                    }
                    else->{
                        getCountryVideo()
                    }
                }
            }
            return  getCountryVideo()
        }

        fun getDiscussData(): ArrayList<QuestionBean>{
            var questions = ArrayList<QuestionBean>()
            var bean1 = QuestionBean(R.mipmap.zft_head,"郑风田","08.22 09:22", Constants.COUNTRYSIDEBUILD,200,100)
            var bean2 = QuestionBean(R.mipmap.zy_head,"祝彦","08.22 09:22",Constants.DANGBUILD_QUESTION,20,10)
            var bean3 = QuestionBean(R.mipmap.jk_head,"贾康","08.22 09:22", Constants.ECODEVLOP_QUESTION,100,400)
            var bean4 = QuestionBean(R.mipmap.gzh_head,"郭兆辉","08.22 09:22",Constants.ECOCULTURE_QUESTION,500,100)
            var bean5 = QuestionBean(R.mipmap.ljp_head,"李军鹏","08.22 09:22",Constants.LAWBUILD_QUESTION,60,100)
            var bean6 = QuestionBean(R.mipmap.yxq_head,"袁晓庆","08.22 09:22",Constants.NUMBERBUILD_QUESTION,700,100)
            questions.add(bean1)
            questions.add(bean2)
            questions.add(bean3)
            questions.add(bean4)
            questions.add(bean5)
            questions.add(bean6)
            return  questions
        }

        /**
         * 获取专家的简介
         */
        fun getExpertData(): HashMap<String,String>{
            var map = HashMap<String,String>()
            map["安振华"] = "    安振华，男，现任延安市委理论讲师团团长，中国延安干部学院兼职教授，陕西省委讲师团特聘教授，西安陆军学院、陕西省社会主义学院、延安干部培训学院客座教授。从事理论宣讲工作三十年，多次被确定为重大政治活动省市委宣讲团主要成员。著有《延安时期与延安精神研究》等专著。"
            map["李益模"] = "    李益模，男，硕士研究生学历，现为浙江红船干部学院、中共嘉兴市委党校讲师，嘉兴红船宣讲团成员。主要从事红船精神、党史党建等方面的教学与研究。曾应邀赴北京、新疆、上海复旦大学等地和高校宣讲红船精神，曾获嘉兴市党校系统现场教学演示竞赛一等奖、嘉兴市微型党课大赛优秀奖等荣誉称号。近年来在《河北省社会主义学院学报》、《甘肃理论学刊》等国内公开期刊、杂志上刊发学术论文多篇，主持省、市级课题多项。"
            map["肖邮华"] = "    肖邮华，男，汉族，1965年7月生于江西万安，中共党员，大学学历，副研究员，曾任教师、团委书记、乡人民政府乡长、井冈山革命博物馆常务副馆长、井冈山市旅游局局长、井冈山革命博物馆馆长兼书记等职务，现为井冈山革命博物馆副县级调研员，井冈山市红色记忆干部培训中心特聘教授，井冈山干部学院、江西经济管理干部学院、北京警察学院、上海武警政治学院等院校客座教授。曾荣获吉安市文化奖章、全国杰出社科专家、中国红色旅游十大人物等称号。"
            map["李军鹏"] = "    李军鹏，男，汉族，1966年11月生于湖北云梦。1992年于中国人民大学劳动人事学院获法学硕士学位；1998年于北京大学政府管理学院获法学博士学位。现为国家行政学院公共管理教研部副教授。李军鹏的研究领域是公共管理与公共行政、公共服务与社会管理、责任政府与行政伦理、人力资源开发与管理、社会危机管理、行政执行力。其讲授的专题包括了行政体制改革与服务型政府建设、转变地方政府职能加强社会管理与公共服务、责任政府与行政问责制、人才强国战略与人力资源开发等。李军鹏出版过多部著作，其专著主要有《公共行政学》、《公共服务型政府》、《公共管理学》、《公共服务型政府建设指南》、《公共服务学》等，合著及参与编写著作有 《中国政府改革的方向》、《公共行政概论》、《中国公共行政案例教程》等10余部。而他曾主持与参与国家与省部级课题10余项，主持完成 国家社会科学基金“十五”规划课题1项，主持完成省部级课题2项。2004年，他的《中国政府公共产品供给的国际比较研究》一文还获第三届行政管理科学优秀论文论著评选论文一等奖。"
            map["张璇孟"] = "    张璇孟，女，1974年出生，法学硕士，副教授，具有法律职业资格，现任生态文明教研室主任。中国社会科学院法学研究所访问学者，湖州市政府咨询委员会特约研究员，湖州市社科理论专家库成员，湖州市社科院客座研究员，湖州市第三批宣传文化“五个一批”优秀人才，湖州市直属单位“三育人”先进个人。主要从事法治政府和生态文明建设方面的教学与研究。先后主持和参与省市级课题10余项、公开发表学术论文10余篇，多次荣获湖州市组织系统、湖州市党校系统精品课，主讲课程《践行“绿水青山就是金山银山”理念 彰显党员干部绿色担当》荣获中共中央组织部党性教育网络精品课程。"
            map["陈瑛"] = "    陈瑛，女，生态环境部固废与化学品管理中心固废管理技术部主任部、土壤环境管理技术部副主任，致力于生态环境保护、固废处理以及无废城市方面的研究，曾在“变废危机，共拓危废蓝海”的热点论坛上，提出“创建‘无废社会’任重道远，政策上鼓励循环利用”的重要观点。"
            map["郭兆晖"] = "    郭兆晖，男，中共中央党校（国家行政学院）社会和生态文明部教研室副主任、副教授，中国人民大学经济学院经济学博士毕业，美国密歇根州立大学访问学者，兼任中国人民大学风险资本与网络经济研究中心副主任。承担中央党校省部班、厅局班、县委书记班等各类班次课程主讲工作，新华社党的十九大、全国两会直播点评嘉宾，多次为国家各部委、各级政府、企业高校等单位讲课，讲题包括：习近平新时代中国特色社会主义思想、习近平新时代中国特色社会主义经济思想、习近平生态文明思想、党的十九大精神解读、两会政府工作报告解读、改革开放40周年新改革与新战略、生态文明建设与转变经济发展方式、建设现代化经济体系、创新驱动发展战略、乡村振兴战略、全面深化改革、供给侧结构性改革等。"
            map["侯立安"] = "    侯立安，1957年8月24日出生江苏省徐州市，环境工程专家，中国工程院院士，文职少将，中国人民解放军火箭军工程大学教授、博士生导师，火箭军后勤科学技术研究所所长。1995年进入中国人民解放军第二炮兵工程设计研究院工作，先后担任副主任、六室主任、副总工程师；2001年获得中国科协求是杰出青年奖；2006年获得中国人民解放军防化研究院环境工程专业博士学位；2005年获得发明创业奖；2009年当选为中国中国工程院院士；2010年获得全国科普工作先进工作者称号；2013年受聘浙江大学求是讲座教授，长期致力于环境工程领域的科学研究、工程设计和技术管理工作。"
            map["王小广"] = "    王小广，男，1963年出生，国籍：中国，籍贯：安徽，1995年于中国社会科学院研究生院获经济学博士学位，同年就职于国家计委（现国家发展和改革委员会）经济研究所，1997年晋升为副研究员，2003年晋升为研究员，2001－2007年为该所经济形势研究室主任，自2000年以来一直担任国家发改委宏观经济研究院重点课题《宏观经济形势跟踪、预测和对策》主持人，还主持和参与过十多项其他部委委托和地方规划课题，2009年调入国家行政学院决策咨询部，任国家行政学院决策咨询部研究员。"
            map["王曙光"] = "    王曙光，男，北京大学经济学院副院长，金融系教授、博士生导师。北京大学中国金融研究中心秘书长，北京大学金融与产业发展研究中心农村金融部部长。现任北京大学 中国金融研究中心秘书长，北京大学 金融与产业发展研究中心农村金融部部长。1971年生于山东 莱州。1990年考入北京大学。先后获北京大学经济学学士、硕士和博士学位。1998年留校任教，2001~2002年赴美国 明尼苏达大学访问研究。已出版专著《经济转轨中的金融制度演进》（2007）、《农村金融与新农村建设》（2006）、《金融自由化与经济发展》（2004）、《理性与信仰——经济学反思札记》（2001）及译著多种，在《北京大学学报》、《金融研究》等核心学术期刊上发表经济学论文30余篇，主持国家社会科学基金等国家级科研项目，数次获得北京大学社会科学优秀成果奖和北京大学教学优秀奖。"
            map["郑风田"] = "    郑风田，男，经济学博士，中国人民大学农业与农村发展学院教授，博士生导师，副院长。1994年毕业于中国人民大学与McGill University联合培养博士项目，1996-1997年在荷兰商学院（The Netherlands Business School）进修，2000-2001年赴加拿大The University ofWestern Ontario经济系任客座教授。2005年入选教育部新世纪人才计划。主要社会兼职包括：国家自然科学基金、国家社科基金同行评审专家，财政部、教育部、科技部、商务部、农业部、北京市农委、科委等部委项目评审专家。曾任德国GTZ等国际机构咨询顾问，农业部环境污染普查专家组成员。主要研究领域为：\"三农问题\",食品安全，农村产业集群与创业、企业与环境关系等。近年来主持国家社科基金3项，国家自然基金3项，主持教育部人文社科基金、福特基金会、国家留学基金委课题、中国发展研究基金会、北京市科委、北京市自然科学基金、北京市教育科学基金等10多项，主持财政部、国务院发展研究中心、科技部、农业部、国家能源办、国家发改委、国家食药局等部委合作课题10多项，地方合作课题20多项。近年出版研究专著2部，主编5部，参编4部，主译校2部。主编教材4部，发表核心论文50多篇，其他非核心论文40多篇。"
            map["黄石松"] = "    黄石松，男，汉族，1972年9月生，湖北大冶人。曾任北京天恒置业集团总经济师，北京天恒房地产股份有限公司副董事长、总经理，北京市人大常委会办公厅综合处处长，市人大常委会副秘书长、北京市人大常委会研究室主任，民建北京市委常委、经济委员会主任，首都经济贸易大学城市学院兼职教授。现任中国民主建国会北京市第十一届委员会副主任委员。北京市第十五届人民代表大会代表。"
            map["王道勇"] = "    王道勇，男，现任中共中央党校科社部教授社会学室教授、副主任、博士生导师。兼中央党校习近平新时代中国特色社会主义思想研究中心研究员。 《科学社会主义》和《社会学评论》杂志编委，北京市委讲师团网络“百名社科专家”成员，多所高校和党校兼职教授。王教授每年均参与中央全会和全国“两会”精神的宣讲，并在中央党校省部级干部进修班、地厅级干部进修班、新疆地厅班等班次授课，获2014年度中国版协图书奖一等奖、2015年度中央党校科研优秀奖，获2012年、2017年度中央党校教学优秀奖一等奖。2017年所讲授课程为评为中央党校首批两门“用学术讲政治”样板课程之一。"
            map["祝彦"] = "    祝彦，男，硕士研究生学历，现为浙江红船干部学院、中共嘉兴市委党校讲师，嘉兴红船宣讲团成员。主要从事红船精神、党史党建等方面的教学与研究。曾应邀赴北京、新疆、上海复旦大学等地和高校宣讲红船精神，曾获嘉兴市党校系统现场教学演示竞赛一等奖、嘉兴市微型党课大赛优秀奖等荣誉称号。近年来在《河北省社会主义学院学报》、《甘肃理论学刊》等国内公开期刊、杂志上刊发学术论文多篇，主持省、市级课题多项。"
            map["贾康"] = "    贾康，男，硕士研究生学历，现为浙江红船干部学院、中共嘉兴市委党校讲师，嘉兴红船宣讲团成员。主要从事红船精神、党史党建等方面的教学与研究。曾应邀赴北京、新疆、上海复旦大学等地和高校宣讲红船精神，曾获嘉兴市党校系统现场教学演示竞赛一等奖、嘉兴市微型党课大赛优秀奖等荣誉称号。近年来在《河北省社会主义学院学报》、《甘肃理论学刊》等国内公开期刊、杂志上刊发学术论文多篇，主持省、市级课题多项。"
            map["马光远"] = "    马光远，男，硕士研究生学历，现为浙江红船干部学院、中共嘉兴市委党校讲师，嘉兴红船宣讲团成员。主要从事红船精神、党史党建等方面的教学与研究。曾应邀赴北京、新疆、上海复旦大学等地和高校宣讲红船精神，曾获嘉兴市党校系统现场教学演示竞赛一等奖、嘉兴市微型党课大赛优秀奖等荣誉称号。近年来在《河北省社会主义学院学报》、《甘肃理论学刊》等国内公开期刊、杂志上刊发学术论文多篇，主持省、市级课题多项。"
            map["袁晓庆"] = "    袁晓庆，男，硕士研究生学历，现为浙江红船干部学院、中共嘉兴市委党校讲师，嘉兴红船宣讲团成员。主要从事红船精神、党史党建等方面的教学与研究。曾应邀赴北京、新疆、上海复旦大学等地和高校宣讲红船精神，曾获嘉兴市党校系统现场教学演示竞赛一等奖、嘉兴市微型党课大赛优秀奖等荣誉称号。近年来在《河北省社会主义学院学报》、《甘肃理论学刊》等国内公开期刊、杂志上刊发学术论文多篇，主持省、市级课题多项。"
            map["李勇坚"] = "    李勇坚，男，硕士研究生学历，现为浙江红船干部学院、中共嘉兴市委党校讲师，嘉兴红船宣讲团成员。主要从事红船精神、党史党建等方面的教学与研究。曾应邀赴北京、新疆、上海复旦大学等地和高校宣讲红船精神，曾获嘉兴市党校系统现场教学演示竞赛一等奖、嘉兴市微型党课大赛优秀奖等荣誉称号。近年来在《河北省社会主义学院学报》、《甘肃理论学刊》等国内公开期刊、杂志上刊发学术论文多篇，主持省、市级课题多项。"
            map["杨延虎"] = "    杨延虎，男，延安大学教授、中国延安干部学院返聘教授、西北大学外聘博士生导师，从1940年开始国民党对陕甘宁边区实行了严密的军事包围和经济封锁，面对缺少衣食物质的困难，边区政府作出了自力更生自给自足的决策，制定了一系列经济建设的方针经过几年大生产运动，边区财政困难得到了缓解。艰苦奋斗、自力更生是一种艰苦朴素、勤俭节约的生活作风；是一种艰苦奋斗、奋发图强的创业精神；是一种生气勃勃、乐观向上的精神状态；更是一种强烈的忧患意识和担当精神。艰苦奋斗是中国共产党人的政治本色，我们要将这种精神永远传承下去。"
            map["惠小峰"] = "    惠小峰，男，延安大学教授。本课从四个方面来讲述延安时期中国共产党坚持群众路线的光辉实践。一、一切依靠群众，坚信人民是推动历史发展的根本动力；二、一切为了群众，坚持全心全意为人民服务的根本宗旨；三、时刻把群众安危冷暖放在心上，保持党同人民群众的血肉联系；四、先做群众的学生，后做群众的先生，从群众中来到群众中去。"
            map["张小兵"] = "    张小兵，男，延安大学教授，群众路线是毛泽东思想活的灵魂，是延安精神的重要内容。本课从四个方面来讲述延安时期中国共产党坚持群众路线的光辉实践。一、一切依靠群众，坚信人民是推动历史发展的根本动力；二、一切为了群众，坚持全心全意为人民服务的根本宗旨；三、时刻把群众安危冷暖放在心上，保持党同人民群众的血肉联系；四、先做群众的学生，后做群众的先生，从群众中来到群众中去。"
            map["刘茜"] = "     刘茜，女，延安大学教授，群众路线是毛泽东思想活的灵魂，是延安精神的重要内容。本课从四个方面来讲述延安时期中国共产党坚持群众路线的光辉实践。一、一切依靠群众，坚信人民是推动历史发展的根本动力；二、一切为了群众，坚持全心全意为人民服务的根本宗旨；三、时刻把群众安危冷暖放在心上，保持党同人民群众的血肉联系；四、先做群众的学生，后做群众的先生，从群众中来到群众中去。"
            map["朱於"] = "     朱於，女，浙江生态文明干部学院教师，护美山水助发展 安吉余村“两山”路 第二集 走进“两山理论”发源地余村，感悟“两山”理念的生动实践。本课从四个方面来讲述延安时期中国共产党坚持群众路线的光辉实践。一、一切依靠群众，坚信人民是推动历史发展的根本动力；二、一切为了群众，坚持全心全意为人民服务的根本宗旨；三、时刻把群众安危冷暖放在心上，保持党同人民群众的血肉联系；四、先做群众的学生，后做群众的先生，从群众中来到群众中去。"
            map["吴艳萍"] = "    吴艳萍，女，中共昆山市委党校老师，改革开放中“闯”出来的“昆山之路”，是基层建设的重要内容。本课从四个方面来讲述延安时期中国共产党坚持群众路线的光辉实践。一、一切依靠群众，坚信人民是推动历史发展的根本动力；二、一切为了群众，坚持全心全意为人民服务的根本宗旨；三、时刻把群众安危冷暖放在心上，保持党同人民群众的血肉联系；四、先做群众的学生，后做群众的先生，从群众中来到群众中去。"
            map["周宇阳"] = "    周宇阳，男，中共昆山市委党校老师，改革开放中“闯”出来的“昆山之路”，是基层建设的重要内容。本课从四个方面来讲述延安时期中国共产党坚持群众路线的光辉实践。一、一切依靠群众，坚信人民是推动历史发展的根本动力；二、一切为了群众，坚持全心全意为人民服务的根本宗旨；三、时刻把群众安危冷暖放在心上，保持党同人民群众的血肉联系；四、先做群众的学生，后做群众的先生，从群众中来到群众中去。"
            map["李忠"] = "    李忠，男，井冈山大学马克思主义学院教授、硕士研究生导师李忠主讲，改革开放中“闯”出来的“昆山之路”，是基层建设的重要内容。本课从四个方面来讲述延安时期中国共产党坚持群众路线的光辉实践。一、一切依靠群众，坚信人民是推动历史发展的根本动力；二、一切为了群众，坚持全心全意为人民服务的根本宗旨；三、时刻把群众安危冷暖放在心上，保持党同人民群众的血肉联系；四、先做群众的学生，后做群众的先生，从群众中来到群众中去。"
            map["张燕生"] = "    张燕生，女，1982年四川师范大学政教系毕业，获得法学学士，1984年华中科技大学经济学硕士毕业，曾任国家发展和改革委员会对外经济研究所所长、研究员，现任国家发展和改革委员会学术委员会秘书长，享受国务院颁发的政府特殊津贴的专家。1984年至1996年，中央财经大学任教，国际金融专业硕士导师；1986年至1988年，先后在美国科罗拉多大学、加拿大多伦多大学、世界银行EDI宏观处进修和工作。北京大学、清华大学、华中科技大学EMBA特聘教授。"
            map["赵英"] = "     赵英，男，1952年9月17日出生，辽宁沈阳市人，1982年毕业于北京广播学院中国社会科学院工业经济研究所工业发展室主任、研究员、研究生院教授，研究生院博士生导师，国家经济发展与经济风险研究中心主任。主要研究领域为产业发展、企业战略管理与规划。在国家安全战略研究、产业经济研究、汽车工业等方面均有多项学术成果。近年来，对文化产业的发展进行了深入的理论与政策研究，对文化创意企业的经营战略进行了深入研究与咨询。负责制定、参与了许多国有大企业集团及民营企业的发展战略规划，并且经常为企业提供咨询意见。"
            map["黄相怀"] = "    黄相怀，中共中央党校（国家行政学院）习近平新时代中国特色社会主义思想研究中心办公室主任、研究员。中央党校国家高端智库学术委员会秘书长、中央党校科研部协作处处长、政治学博士、副研究员。长期专注于中国特色社会主义重大理论和实践问题的研究工作，主要围绕党建、政治理论等展开。在《人民日报》《光明日报》《求是》等报刊发表理论文章百余篇。2020年5月14日，入选“第三届全国基层政权建设和社区治理专家委员会成员”。"
            return  map
        }
    }
}