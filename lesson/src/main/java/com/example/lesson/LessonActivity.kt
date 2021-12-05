package com.example.lesson

import android.os.Bundle
import android.view.MenuItem
import android.widget.LinearLayout
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.core.BaseView
import com.example.core.utils.CacheUtils
import com.example.lesson.entity.Lesson
import kotlin.reflect.KProperty

/**
 * Created by wuliang on 2021/7/4 20:41
 */
class LessonActivity:AppCompatActivity(),BaseView<LessonPresenter>,Toolbar.OnMenuItemClickListener {

    override val presenter: LessonPresenter by lazy { LessonPresenter(this) }

    var token:String by Saver("token")
    var token2:String by Saver("token2")

    class Saver(val s: String) {
        operator fun getValue(lessonActivity: LessonActivity,property: KProperty<*>):String{
            return CacheUtils.get(s)!!
        }

        operator fun setValue(lessonActivity: LessonActivity,property: KProperty<*>,value: String){
            CacheUtils.save(s,value)
        }

    }

    private val lessonAdapter = LessonAdapter()

    private lateinit var refreshLayout: SwipeRefreshLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_lesson)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        toolbar.inflateMenu(R.menu.menu_lesson)
        toolbar.setOnMenuItemClickListener(this)

        findViewById<RecyclerView>(R.id.list).run {
            layoutManager = LinearLayoutManager(this@LessonActivity)
            adapter = lessonAdapter
            addItemDecoration(DividerItemDecoration(this@LessonActivity,LinearLayout.VERTICAL))
        }


        findViewById<SwipeRefreshLayout>(R.id.swipe_refresh_layout).run{
            refreshLayout = this
            setOnRefreshListener {
                presenter.fetchData()
            }
            isRefreshing = true
        }

        presenter.fetchData()

    }

    fun showResult(lessons:List<Lesson>){
        lessonAdapter.updateAndNotify(lessons)
        refreshLayout.isRefreshing = false
    }

    override fun onMenuItemClick(p0: MenuItem?): Boolean {
        presenter.showPlayback()
        return false
    }


}