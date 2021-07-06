package com.example.lesson

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.core.BaseViewHolder
import com.example.lesson.entity.Lesson

/**
 * Created by wuliang on 2021/7/4 22:12
 */
class LessonAdapter: RecyclerView.Adapter<LessonAdapter.LessonViewHolder>() {

    private var list:List<Lesson> = ArrayList<Lesson>()

    fun updateAndNotify(list:List<Lesson>){
        this.list = list
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LessonViewHolder {
        return LessonViewHolder.onCreate(parent)
    }

    override fun onBindViewHolder(holder: LessonViewHolder, position: Int) {
        holder.onBind(list[position])
    }

    class LessonViewHolder(itemView: View):BaseViewHolder(itemView){

        companion object{

            fun onCreate(parent:ViewGroup):LessonViewHolder{
                return LessonViewHolder(LayoutInflater
                        .from(parent.context)
                        .inflate(R.layout.item_lesson,parent,false))

            }

        }

        fun onBind(lesson: Lesson){
            val date = lesson.date?:"日期待定"

            setText(R.id.tv_date,date)

            setText(R.id.tv_content,lesson.content)

            val state = lesson.state

            state?.let {
                setText(R.id.tv_state,it.stateName())
                val colorRes = when(it){
                    Lesson.State.PLAYBACK->R.color.playback
                    Lesson.State.LIVE->R.color.live
                    Lesson.State.WAIT->R.color.wait
                }
                (getView(R.id.tv_state) as View).setBackgroundColor(itemView.context.getColor(colorRes))
            }

        }

    }

}

