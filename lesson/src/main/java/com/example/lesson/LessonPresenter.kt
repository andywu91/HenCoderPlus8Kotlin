package com.example.lesson

import com.example.core.http.EntityCallback
import com.example.core.http.HttpClient
import com.example.core.utils.toast
import com.example.lesson.entity.Lesson
import com.google.gson.reflect.TypeToken
import java.util.*


/**
 * Created by wuliang on 2021/7/4 22:04
 */
class LessonPresenter(val activity: LessonActivity) {

    private val LESSON_PATH = "lessons"

    private var lessons = ArrayList<Lesson>()

    private val type = object : TypeToken<List<Lesson>>(){}.rawType

    internal fun fetchData(){
        HttpClient.get(LESSON_PATH,type,object :EntityCallback<ArrayList<Lesson>>{
            override fun onFailure(message: String?) {
                activity.runOnUiThread {
                    toast(message)
                }
            }

            override fun onSuccess(entity: ArrayList<Lesson>) {
                this@LessonPresenter.lessons = entity
                activity.runOnUiThread {
                    activity.showResult(lessons)
                }
            }


        })
    }

    internal fun showPlayback() {
        val playbackLessons: MutableList<Lesson> = ArrayList()
        for (lesson in lessons) {
            if (lesson.state === Lesson.State.PLAYBACK) {
                playbackLessons.add(lesson)
            }
        }
        activity.showResult(playbackLessons)
    }

}