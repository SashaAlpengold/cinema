package com.example.jopa

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CustomRecyclerAdapter(private val titles:List<String>, private val abouts:List<String>): RecyclerView.Adapter<CustomRecyclerAdapter.MyViewHolder>(){

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val titleTV:TextView = itemView.findViewById(R.id.tvTitle)
        val aboutTV:TextView = itemView.findViewById(R.id.tvAbout)
        val filmIconIV:ImageView = itemView.findViewById(R.id.ivFilmIcon)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.titleTV.text = titles[position]
        holder.aboutTV.text = abouts[position]
        holder.filmIconIV.setImageResource(R.drawable.film_icon)
    }

    override fun getItemCount(): Int {
        return titles.size
    }

}
