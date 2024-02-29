package com.example.shopper.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.shopper.R
import com.example.shopper.data.model.CartItem2
import com.example.shopper.data.model.ShopItem
import com.example.shopper.data.util.Utils
import com.example.shopper.databinding.FragmentProductDetailBinding
import com.example.shopper.presentation.viewmodel.ProductDetailViewModel
import com.google.android.material.snackbar.Snackbar
import javax.inject.Inject

class ProductDetailFragment: Fragment() {

    private lateinit var binding: FragmentProductDetailBinding

    @Inject
    lateinit var viewModel: ProductDetailViewModel

    private lateinit var shopItem: ShopItem

    private var like = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        shopItem = ProductDetailFragmentArgs.fromBundle(requireArguments()).shopItem
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_product_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentProductDetailBinding.bind(view)

        updateViews()

        productDetailBack.setOnClickListener {
            findNavController().navigateUp()
        }

        productDetailAddToCart.setOnClickListener {
            val cartItem = CartItem2(shopItem.id, shopItem.image, Utils.formatPrice(shopItem.price.toString()), shopItem.title, 1, shopItem.price)
            viewModel.saveToCart(cartItem)
            Snackbar.make(productDetailAddToCart, "Added to Cart Successfully", Snackbar.LENGTH_SHORT).show()
        }

        productDetailCart.setOnClickListener {
            findNavController().navigate(R.id.action_productDetailFragment_to_cartFragment)
        }

        productDetailLike.setOnClickListener {
            if(like){
                like = false
                viewModel.removeFromFavorites(shopItem)
                productDetailLike.setImageResource(R.drawable.ic_not_favorite)
                Snackbar.make(productDetailAddToCart, "Removed from favorites", Snackbar.LENGTH_SHORT).show()
            } else{
                like = true
                viewModel.addToFavorites(shopItem)
                productDetailLike.setImageResource(R.drawable.ic_favorite)
                Snackbar.make(productDetailAddToCart, "Added to favorites", Snackbar.LENGTH_SHORT).show()
            }
        }
    }

    private fun updateViews() = with(binding){

        productDetailPrice.text = "KZT ${shopItem.price}"
        productDetailTitle.text = shopItem.title
        productDetailDescription.text = shopItem.description

        Glide.with(productDetailImage)
            .load(shopItem.image)
            .into(productDetailImage)

        productDetailRating.text = "${shopItem.rating.rate}"
        productDetailReviews.text = "${shopItem.rating.count} Reviews"
    }
}