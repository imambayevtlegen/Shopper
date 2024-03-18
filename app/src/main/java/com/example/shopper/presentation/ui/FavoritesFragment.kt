package com.example.shopper.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.shopper.R
import com.example.shopper.data.model.ShopItem
import com.example.shopper.databinding.FragmentFavoritesBinding
import com.example.shopper.presentation.adapter.FavoritesAdapter
import com.example.shopper.presentation.viewmodel.FavoritesViewModel
import javax.inject.Inject

// TODO BaseFragment with viewModel, and binding
// TODO less lateinit use lazy
class FavoritesFragment : Fragment() {

    private lateinit var binding: FragmentFavoritesBinding

    @Inject
    lateinit var viewModel: FavoritesViewModel

    private lateinit var adapter: FavoritesAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_favorites, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {
        super.onViewCreated(view, savedInstanceState)

        // TODO sepate methods
        adapter = FavoritesAdapter()

        binding = FragmentFavoritesBinding.bind(view)

        favoritesBack.setOnClickListener {
            findNavController().navigateUp()
        }
        favoritesRecyclerView.adapter = adapter

        adapter.setOnItemClickListener { productId ->
            val bundle = Bundle().apply {
                putSerializable("productId", productId)
            }
            findNavController().navigate(R.id.productDetailFragment, bundle)
        }

        adapter.setOnItemDeleteListener {
            viewModel.deleteFavorites(it)
        }

        viewModel.getFavorites().observe(viewLifecycleOwner) { newList: List<ShopItem> ->
            adapter.submitList(newList)
        }

        binding.favoritesDelete.setOnClickListener {
            viewModel.clearFavorites()
        }
    }
}