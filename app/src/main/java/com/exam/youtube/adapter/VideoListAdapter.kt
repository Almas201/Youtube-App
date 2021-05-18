package com.exam.youtube.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.exam.youtube.R
import com.exam.youtube.model.Item


class VideoListAdapter(private val mContext: Context, private val onItemClickListener: OnItemClickListener) :
    RecyclerView.Adapter<VideoListAdapter.ViewHolder>() {
    private var mVideoList: List<Item> = ArrayList()

    interface OnItemClickListener {
        fun onItemClicked(position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(mContext).inflate(R.layout.video_list_item, parent, false)
        return ViewHolder(view, onItemClickListener)
    }

    override fun getItemCount(): Int {
        return mVideoList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(mVideoList[position],mContext)
    }

    fun setItems(items: List<Item>) {
        mVideoList = items
    }

    class ViewHolder(itemView: View, private val onItemClickListener: OnItemClickListener) :
        RecyclerView.ViewHolder(itemView), View.OnClickListener {

        private val mTitleTv = itemView.findViewById<TextView>(R.id.tv_title)
        private val mImageThumb = itemView.findViewById<ImageView>(R.id.iv_thumb)
        init {
             itemView.setOnClickListener(this)
        }

        fun bind(item: Item, mContext: Context) {
            mTitleTv.text = item.video.title
            Glide.with(mContext).load(item.video.thumbnails.standard.url).into(mImageThumb)
        }

        override fun onClick(v: View?) {
            onItemClickListener.onItemClicked(adapterPosition)
        }

    }
}