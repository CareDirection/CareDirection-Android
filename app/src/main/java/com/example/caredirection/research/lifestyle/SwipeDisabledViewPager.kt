package com.example.caredirection.research.lifestyle

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.viewpager.widget.ViewPager
import java.util.jar.Attributes

class SwipeDisabledViewPager(context : Context, attr : AttributeSet) : ViewPager(context, attr) {


    override fun onInterceptHoverEvent(event: MotionEvent?): Boolean = false


    override fun onTouchEvent(ev: MotionEvent?): Boolean = false

}