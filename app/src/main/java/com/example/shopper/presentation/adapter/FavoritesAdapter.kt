package com.example.shopper.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.shopper.data.model.ShopItem
import com.example.shopper.databinding.SingleFavoriteBinding

class FavoritesAdapter: RecyclerView.Adapter<FavoritesAdapter.FavoritesViewHolder>() {

    private val callback = object : DiffUtil.ItemCallback<ShopItem>(){
        override fun areItemsTheSame(oldItem: ShopItem, newItem: ShopItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ShopItem, newItem: ShopItem): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, callback)

    private var onItemClickListener : ((ShopItem) -> Unit) = {}

    private var onItemDeleteListener : ((ShopItem) -> Unit) = {}

    fun setOnItemClickListener(listener: (ShopItem) -> Unit){
        onItemClickListener = listener
    }

    fun setOnItemDeleteListener(listener: (ShopItem) -> Unit){
        onItemDeleteListener = listener
    }

    inner class FavoritesViewHolder(
        private val binding: SingleFavoriteBinding
    ): RecyclerView.ViewHolder(binding.root){

        fun bindData(shopItem: ShopItem) = with(binding){

            Glide.with(itemImage.context)
                .load(shopItem.image)
                .into(itemImage)

            itemTitle.text = shopItem.title
            itemPrice.text = "KZT ${shopItem.price}"
            itemRating.text = "${shopItem.rating.rate}"
            itemReview.text = "${shopItem.rating.count} Reviews"

            itemView.setOnClickListener {
                onItemClickListener(shopItem)
            }

            itemDelete.setOnClickListener {
                onItemDeleteListener(shopItem)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritesViewHolder{
        val binding = SingleFavoriteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavoritesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FavoritesViewHolder, position: Int) {
        val shopItem = differ.currentList[position]
        holder.bindData(shopItem)
    }

    override fun getItemCount() = differ.currentList.size
}