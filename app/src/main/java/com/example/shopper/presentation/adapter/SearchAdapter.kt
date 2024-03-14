package com.example.shopper.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.shopper.data.model.ShopItem
import com.example.shopper.databinding.SearchItemBinding

class SearchAdapter: ListAdapter<ShopItem ,SearchAdapter.SearchViewHolder>(DiffUtilItemCallback) {

    object DiffUtilItemCallback : DiffUtil.ItemCallback<ShopItem>(){
        override fun areItemsTheSame(oldItem: ShopItem, newItem: ShopItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ShopItem, newItem: ShopItem): Boolean {
            return oldItem == newItem
        }
    }

    private var onItemClickListener : ((ShopItem) -> Unit) = {}

    fun setOnItemClickListener(listener: (ShopItem) -> Unit){
        onItemClickListener = listener
    }

    inner class SearchViewHolder(
        private val binding: SearchItemBinding
    ): RecyclerView.ViewHolder(binding.root){

        fun bindData(shopItem: ShopItem) = with(binding){

            Glide.with(searchItemImage.context)
                .load(shopItem.image)
                .into(searchItemImage)

            searchItemTitle.text = shopItem.title
            searchItemPrice.text = "KZT ${shopItem.price}"
            searchItemRating.text = "${shopItem.rating.rate}"
            searchItemReview.text = "${shopItem.rating.count} Reviews"

            searchItemView.setOnClickListener {
                onItemClickListener(shopItem)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val binding = SearchItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SearchViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        val shopItem = getItem(position)
        holder.bindData(shopItem)
    }
}