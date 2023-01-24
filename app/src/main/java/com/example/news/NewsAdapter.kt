package com.example.news

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide

class NewsAdapter(private  var items:ArrayList<News>,private var listner:Help) : RecyclerView.Adapter<Viewholder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Viewholder {
        val view=Viewholder(LayoutInflater.from(parent.context).inflate(R.layout.news,parent,false))
      view.image.setOnClickListener {
          listner.onclick(items[view.adapterPosition])
      }
        view.itemView.setOnClickListener{
            listner.onclick(items[view.adapterPosition])
        }
        return view
    }

    override fun onBindViewHolder(holder: Viewholder, position: Int) {
      holder.final.text=items[position].title
        Glide.with(holder.itemView.context).load(items[position].imageurl).into(holder.image)
    }

    override fun getItemCount(): Int {
        return items.size
    }
    fun update(ite:ArrayList<News>){
   items.clear()
        items.addAll(ite)
        notifyDataSetChanged()
    }
}
class Viewholder(itemView: View) : ViewHolder(itemView){
val final:TextView=itemView.findViewById(R.id.textView)
    val image:ImageView=itemView.findViewById(R.id.imageView)
}
interface Help{
    fun onclick(item:News)
}