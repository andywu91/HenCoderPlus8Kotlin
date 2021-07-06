package com.example.core.http

import com.google.gson.Gson
import okhttp3.*
import java.io.IOException
import java.lang.reflect.Type

/**
 * Created by wuliang on 2021/7/4 19:29
 */
object HttpClient:OkHttpClient() {

    private val gson = Gson()

    private fun <T> convert(json:String?,type: Type?):T{
        return gson.fromJson(json,type)
    }

    fun <T> get(path:String?,type:Type?,entityCallback: EntityCallback<T>?){
        val request = Request.Builder()
                .url("https://api.hencoder.com/$path")
                .build()

        val call = this.newCall(request)

        call.enqueue(object :Callback{
            override fun onFailure(call: Call, e: IOException) {
                entityCallback?.onFailure("网络异常")
            }

            override fun onResponse(call: Call, response: Response) {
                when (response.code()) {
                    in 200 until  300 -> {
                        val body = response.body()
                        var json:String? = null
                        try {
                            json = body?.string()
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                        entityCallback?.onSuccess(convert(json,type) as T)
                    }
                    in 400 until  500 -> {
                        entityCallback?.onFailure("客户端错误")
                    }
                    in 501 until  600 -> {
                        entityCallback?.onFailure("服务器错误")
                    }
                    else -> {
                        entityCallback?.onFailure("未知错误")
                    }
                }

            }
        })

    }

}