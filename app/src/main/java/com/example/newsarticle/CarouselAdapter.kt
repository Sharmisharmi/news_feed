package com.example.newsarticle

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class CarouselAdapter (
    context: Context,
    list: ArrayList<String>,
    text1: ArrayList<String>,
    text2: ArrayList<String>
) : RecyclerView.Adapter<CarouselAdapter.ViewHolder>() {

    private var context : Context

    var data:List<String>
    var list1:List<String>
    var list2:List<String>




    init {
        this.context=context
        this.data = list
        this.list1 = text1
        this.list2 = text2


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.onboarding_list,parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        Picasso.get().load(data[position]).into(holder.image)
        holder.text1.text = list1[position]
        holder.text2.text = list2[position]


    }


    override fun getItemCount(): Int {
        return list1.size
    }

    inner class ViewHolder (itemView: View): RecyclerView.ViewHolder(itemView){

        var image: ImageView = itemView.findViewById(R.id.image)
        var text1: TextView = itemView.findViewById(R.id.text1)
        var text2: TextView = itemView.findViewById(R.id.text2)

    }


}