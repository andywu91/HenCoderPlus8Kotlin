package com.example.app.entity

/**
 * Created by wuliang on 2021/7/1 22:42
 */
data class User @JvmOverloads constructor(var userName: String? = null,
                                          var password: String? = null,
                                          var code: String? = null)