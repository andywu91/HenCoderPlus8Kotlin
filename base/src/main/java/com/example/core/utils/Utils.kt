
@file:JvmName("Utils")

package com.example.core.utils

import android.content.res.Resources
import android.util.TypedValue
import android.widget.Toast
import com.example.core.BaseApplication


/**
 * Created by wuliang on 2021/7/4 19:45
 */

private val displayMetrics = Resources.getSystem().displayMetrics

fun dp2px(dp:Float):Float{
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,dp, displayMetrics)
}

fun toast(string:String?,duration:Int = Toast.LENGTH_SHORT){
    Toast.makeText(BaseApplication.currentApplication,string,duration).show()
}

