package com.example.shopper.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.shopper.data.model.CartItem2
import com.example.shopper.data.util.Utils
import com.example.shopper.databinding.CartItemBinding


// TODO ListAdapter
class CartAdapter: ListAdapter<CartItem2, CartAdapter.CartViewHolder>(DiffUtilItemCallback) {

    // TODO inner classes, interfaces and so on, at the bottom of the class
    object DiffUtilItemCallback : DiffUtil.ItemCallback<CartItem2>(){
        override fun areItemsTheSame(oldItem: CartItem2, newItem: CartItem2): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: CartItem2, newItem: CartItem2): Boolean {
            return oldItem == newItem
        }
    }

    // TODO pass interface with all these function
    private var incrementListener : ((CartItem2) -> Unit) = {}

    private var decrementListener : ((CartItem2) -> Unit) = {}

    private var removeListener : ((CartItem2) -> Unit) = {}

    fun setOnRemoveClickListener(listener: (CartItem2) -> Unit){
        removeListener = listener
    }

    fun incrementClickListener(listener : (CartItem2) -> Unit){
        incrementListener = listener
    }

    fun decrementClickListener(listener: (CartItem2) -> Unit){
        decrementListener = listener
    }

    inner class CartViewHolder(private val binding: CartItemBinding) : RecyclerView.ViewHolder(binding.root){

        // TODO use with(binding)
        fun bindData(cartItem: CartItem2) = with(binding){

            cartItemName.text = cartItem.title
            cartItemPrice.text = "KZT ${Utils.formatPrice(cartItem.price)}"
            cartItemQuantity.text = "${cartItem.quantity}"

            Glide.with(cartItemImage.context)
                .load(cartItem.image)
                .into(cartItemImage)

            cartItemDelete.setOnClickListener {
                removeListener(cartItem)
            }

            cartItemPlus.setOnClickListener {
                incrementListener(cartItem)
            }

            cartItemMinus.setOnClickListener {
                decrementListener(cartItem)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val binding = CartItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CartViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val cartItem = getItem(position)
        holder.bindData(cartItem)
    }
}