package com.example.memo

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.memo.room.MemoEntity
import kotlinx.android.synthetic.main.item_memo.view.*

/**
 * @author Taeeun Kim
 * @email xodms8713@gmail.com
 * @created 2021-03-06
 */
class MyAdapter(val context: Context,
                val list: List<MemoEntity>,
                var onDeleteListener: OnDeleteListener)
    : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val memo = itemView.textview_memo
        val root = itemView.root
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(context)
            .inflate(R.layout.item_memo, parent, false)

        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        //list = 1, 2, 3
        val memo = list[position]

        holder.memo.text = memo.memo
        holder.root.setOnLongClickListener(object: View.OnLongClickListener{
            override fun onLongClick(v: View?): Boolean {
                onDeleteListener.onDeleteListener(memo)
                return true
            }

        })
    }
}