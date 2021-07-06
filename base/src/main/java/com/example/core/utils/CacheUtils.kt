package com.example.core.utils

import android.annotation.SuppressLint
import android.content.Context
import com.example.core.BaseApplication
import com.example.core.R

/**
 * Created by wuliang on 2021/7/4 19:42
 */
class CacheUtils {

    companion object{

        @SuppressLint("StaticFieldLeak")
        @JvmStatic
        val context = BaseApplication.currentApplication

        val SP = context?.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE)

        fun save(key:String,value:String){
            SP?.edit()?.putString(key,value)?.apply()
        }

        fun get(key:String):String?{
            return SP?.getString(key,null)
        }

    }

}