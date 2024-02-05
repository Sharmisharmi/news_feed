package com.example.newsarticle

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.newsarticle.model.getArticleData
import com.squareup.picasso.Picasso

class ArticlesAdapter (context: Context, data: List<getArticleData>?) : RecyclerView.Adapter<ArticlesAdapter.ViewHolder>() {

    private var context : Context
    var data:List<getArticleData>


    init {
        this.context=context
        this.data= data!!

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticlesAdapter.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.articles_list,parent,false))
    }

    override fun onBindViewHolder(holder: ArticlesAdapter.ViewHolder, position: Int) {

        holder.newsTitle.text=data[position].title.toString()
        holder.news_desc.text=data[position].description.toString()
        holder.newsContent.text=data[position].content.toString()


        Picasso.get().load(data[position].urlToImage).into(holder.newsImage)

        holder.newsLL.setOnClickListener {
                        val openURL = Intent(Intent.ACTION_VIEW)
            openURL.data = Uri.parse(data[position].url)
            context.startActivity(openURL)
        }

        holder.newsContent.setSelected(true);
    }


    override fun getItemCount(): Int {
        return data.size
    }

    inner class ViewHolder (itemView: View): RecyclerView.ViewHolder(itemView){

        var news_desc : TextView = itemView.findViewById(R.id.news_desc)
        var newsContent : TextView = itemView.findViewById(R.id.newsContent)
        var newsTitle : TextView = itemView.findViewById(R.id.newsTitle)
        var newsImage : ImageView = itemView.findViewById(R.id.newsImage)
        var newsLL : LinearLayout = itemView.findViewById(R.id.newsLL)

    }


}