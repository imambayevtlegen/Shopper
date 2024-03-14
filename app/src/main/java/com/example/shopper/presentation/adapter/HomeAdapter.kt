package com.example.shopper.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.shopper.data.model.ShopItem
import com.example.shopper.databinding.SingleItemBinding

class HomeAdapter: ListAdapter<ShopItem ,HomeAdapter.HomeViewHolder>(DiffUtilItemCallback) {

    object DiffUtilItemCallback : DiffUtil.ItemCallback<ShopItem>(){
        override fun areItemsTheSame(oldItem: ShopItem, newItem: ShopItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ShopItem, newItem: ShopItem): Boolean {
            return oldItem == newItem
        }
    }

    private var onItemClickListener: ((ShopItem) -> Unit) = {}

    fun setOnItemClickListener(listener: (ShopItem) -> Unit){
        onItemClickListener = listener
    }

    inner class HomeViewHolder(private val binding: SingleItemBinding): RecyclerView.ViewHolder(binding.root){

        fun bindData(shopItem: ShopItem) = with(binding){
            Glide.with(itemImage.context)
                .load(shopItem.image)
                .into(itemImage)

            itemTitle.text = shopItem.title
            itemPrice.text = "KZT ${shopItem.price}"
            itemRating.text = "${shopItem.rating.rate}"
            itemReview.text = "${shopItem.rating.count} Reviews"

            itemView.setOnClickListener{
                onItemClickListener(shopItem)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val binding = SingleItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return  HomeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        val shopItem = getItem(position)
        holder.bindData(shopItem)
    }

}