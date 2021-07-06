package com.example.core

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by wuliang on 2021/7/4 19:55
 */
abstract class BaseViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {

    private val viewHashMap = HashMap<Int,View>()

    @Suppress("UNCHECKED_CAST")
    protected fun <T:View> getView(id:Int):T{
        var view = viewHashMap[id]
        if(view == null){
            view = itemView.findViewById(id)
            viewHashMap[id] = view
        }
        return view as T
    }

    protected fun setText(id:Int,text:String?){
        (getView(id) as TextView).text = text
    }

}