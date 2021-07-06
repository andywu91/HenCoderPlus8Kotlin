package com.example.core.http

/**
 * Created by wuliang on 2021/7/1 22:59
 */
interface EntityCallback<T> {
    fun onSuccess(entity:T)
    fun onFailure(message:String?)
}