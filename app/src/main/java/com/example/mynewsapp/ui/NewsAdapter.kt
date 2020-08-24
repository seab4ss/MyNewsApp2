package com.example.mynewsapp.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.mynewsapp.R
import com.example.mynewsapp.Utils
import com.example.mynewsapp.model.Article
import com.example.mynewsapp.model.MediaItem


/**
 * Created by Yury on 8/24/2020
 */

class NewsAdapter(private val items: MutableList<Article>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return NewsItemViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.news_item, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as NewsItemViewHolder).bind(items[position])
    }

}

open class NewsItemViewHolder(private val v: View) : RecyclerView.ViewHolder(v) {
    protected val mContext: Context = v.context;
    private val mImage: ImageView = v.findViewById(R.id.image)
    private val mTitle: TextView = v.findViewById(R.id.title)
    private val mCornerRadius =
        v.context.resources.getDimensionPixelSize(R.dimen.media_item_image_corner_radius)

    open fun bind(item: Article) {
        mTitle.text = item.title
        Glide.with(v.context).load(item.imageUrl).transform(RoundedCorners(mCornerRadius))
            .placeholder(R.drawable.media_image_placeholder)
            .error(R.drawable.error_image_placeholder).into(mImage)

        v.setOnClickListener { onItemClicked(item) }
    }

    private fun onItemClicked(item: Article) {
        Utils.openWebPage(mContext, item.url)
    }
}