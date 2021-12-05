package com.example.core

import android.app.Application
import android.content.Context

/**
 * Created by wuliang on 2021/7/4 19:50
 */
class BaseApplication :Application(){

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        currentApplication = this
    }

    companion object{
        @JvmStatic
        @get:JvmName("currentApplication")
        lateinit var currentApplication: Context
        private set

    }


}