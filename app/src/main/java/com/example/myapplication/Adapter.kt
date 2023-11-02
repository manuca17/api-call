package com.example.myapplication

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class Adapter(val context: Context, val userList: List<PostsItem>) : RecyclerView.Adapter<Adapter.ViewHolder>() {
    class  ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewId: TextView
        val textViewTitle: TextView

        init {
            textViewId = itemView.findViewById(R.id.textViewId)
            textViewTitle = itemView.findViewById(R.id.textViewTitle)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var itemView = LayoutInflater.from(context).inflate(R.layout.card_view, parent,  false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textViewId.text = userList[position].userId.toString()
        holder.textViewTitle.text = userList[position].title.toString()
    }
}