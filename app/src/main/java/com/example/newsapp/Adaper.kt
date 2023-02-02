package com.example.newsapp

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item.view.*
import java.time.Instant

class Adaper(val context: Context, val articles: List<Artical>) :
    RecyclerView.Adapter<Adaper.viewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item, parent, false)
        return viewHolder(view)
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        val articles = articles[position]
        holder.newsTitle.text = articles.title
        holder.newsDiscreption.text = articles.description
        Glide.with(context)
            .load(articles.urlToImage)
            .into(holder.imageView)
        holder.imageView.setOnClickListener {
            val intent = Intent(context,DetailVeiw::class.java)
            intent.putExtra("Url",articles.url)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return articles.size
    }

    class viewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imageView = itemView.findViewById<ImageView>(R.id.imageView)
        var newsTitle = itemView.findViewById<TextView>(R.id.newsTitle)
        var newsDiscreption = itemView.findViewById<TextView>(R.id.newsDiscreption)
    }
}
