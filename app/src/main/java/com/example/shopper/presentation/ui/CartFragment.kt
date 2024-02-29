package com.example.shopper.presentation.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.shopper.R
import com.example.shopper.data.util.Utils
import com.example.shopper.databinding.FragmentCartBinding
import com.example.shopper.presentation.adapter.CartAdapter
import com.example.shopper.presentation.viewmodel.CartViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CartFragment : Fragment() {

    private lateinit var binding: FragmentCartBinding

    @Inject
    lateinit var cartViewModel: CartViewModel

    @Inject
    lateinit var cartAdapter: CartAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_cart, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentCartBinding.bind(view)

        cartViewModel.getCartItems().observe(viewLifecycleOwner) {newList ->
            cartAdapter.submitList(newList)
        }

        cartAdapter.setOnRemoveClickListener {
            cartViewModel.deleteCart(it)
        }

        cartAdapter.incrementClickListener {
            Log.i("CartFragment", "Increment")
            cartViewModel.increment(it)
        }

        cartAdapter.decrementClickListener {
            Log.i("CartFragment", "Decrement")
            cartViewModel.decrement(it)
        }

        cartViewModel.totalItems.observe(viewLifecycleOwner) {
            cartItemsInfo.text = "Total $it Items"
        }

        cartViewModel.totalItemsPrice.observe(viewLifecycleOwner) {
            cartItemsPrice.text = "KZT ${Utils.formatPrice(it.toString())}"
        }

        cartRecyclerView.adapter = cartAdapter

        cartBack.setOnClickListener {
            findNavController().navigateUp()
        }

        cartClearAll.setOnClickListener {
            cartViewModel.clearCart()
        }
    }
}