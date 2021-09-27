package com.jyzx.helper.utils

import android.content.Intent
import android.graphics.Color
import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.style.ClickableSpan
import android.text.style.ForegroundColorSpan
import android.view.View

/**
 * 用户名: zcm
 * date: 2021/9/17
 * des: 处理字符串
 **/
class StringUtils {

    companion object{
        var  strReg = "。"
        fun getAllWord(str: String): String{
           return str.replace(strReg,"")
        }
        fun richText(s: String,onRichListener: onRichListener): SpannableString? {
            val spannableString = SpannableString(s)
            val clickableSpan: ClickableSpan = object : ClickableSpan() {
                override fun onClick(widget: View) {
                    onRichListener.onRichBack()
                }
                override fun updateDrawState(ds: TextPaint) {
                    super.updateDrawState(ds)
                    ds.color = Color.TRANSPARENT
                    ds.isUnderlineText = false
                }
            }
            spannableString.setSpan(clickableSpan, 7, s.length, Spanned.SPAN_INCLUSIVE_EXCLUSIVE)
            val colorSpan = ForegroundColorSpan(Color.BLACK)
            spannableString.setSpan(colorSpan, 7, s.length, Spanned.SPAN_INCLUSIVE_EXCLUSIVE)
            return spannableString
        }
    }

    public interface onRichListener{
        fun onRichBack()
    }
}