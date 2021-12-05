package com.example.app

import com.example.app.entity.User

/**
 * Created by wuliang on 2021/12/5 21:33
 */
fun main():Unit{
    val user = User()
    val copy = user.copy()
    println(user)
    println(copy)
    println(user == copy)
    println(user === copy)

    repeat(100){
        println(it)
    }

}