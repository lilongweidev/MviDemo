package com.llw.mvidemo.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.llw.mvidemo.data.model.Vertical
import com.llw.mvidemo.databinding.ItemWallpaperRvBinding

/**
 * 壁纸适配器
 */
class WallpaperAdapter(private val verticals: ArrayList<Vertical>) :
    RecyclerView.Adapter<WallpaperAdapter.ViewHolder>() {

    fun addData(data: List<Vertical>) {
        verticals.addAll(data)
    }

    class ViewHolder(itemWallPaperRvBinding: ItemWallpaperRvBinding) :
        RecyclerView.ViewHolder(itemWallPaperRvBinding.root) {

        var binding: ItemWallpaperRvBinding

        init {
            binding = itemWallPaperRvBinding
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(ItemWallpaperRvBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun getItemCount() = verticals.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //加载图片
        verticals[position].img.let {
            Glide.with(holder.itemView.context).load(it).into(holder.binding.ivWallPaper)
        }
    }
}