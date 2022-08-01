package com.azmiradi.news.presentation.fragment.home

import android.text.format.DateUtils
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.azmiradi.news.R
import com.azmiradi.news.data.model.Article
import com.azmiradi.news.databinding.LatestNewsBinding
import com.azmiradi.news.utils.convertDateFormate
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class LatestNewsAdapter : RecyclerView.Adapter<LatestNewsAdapter.NewsViewHolder>() {

    private val callback = object : DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this, callback)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val binding = LatestNewsBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return NewsViewHolder(binding)
    }


    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val article = differ.currentList[position]

        holder.bind(article)

    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    inner class NewsViewHolder(
        val binding: LatestNewsBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(article: Article) {
            val date = article.publishedAt.toString().convertDateFormate()
            binding.date.text=date
            binding.address.text = article.title
            Glide.with(binding.image.context).load(article.urlToImage)
                .placeholder(R.drawable.ic_loading).error(R.drawable.ic_no_image)
                .transform(CenterCrop(), RoundedCorners(15))
                .into(binding.image)
            binding.root.setOnClickListener {
                onItemClick?.let {
                    it(article)
                }
            }
        }


    }

    private var onItemClick: ((Article) -> Unit)? = null
    fun setOnItemClickListener(listener: (Article) -> Unit) {
        onItemClick = listener
    }
}