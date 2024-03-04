package com.example.shopper.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.shopper.R
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

        favoritesBack.setOnClickListener {
            findNavController().navigateUp()
        }

        favoritesRecyclerView.adapter = adapter

        adapter.setOnItemClickListener {
            val action =
                FavoritesFragmentDirections.actionFavoritesFragmentToProductDetailFragment(it)
            findNavController().navigate(action)
        }

        adapter.setOnItemDeleteListener {
            viewModel.deleteFavorites(it)
        }

        viewModel.getFavorites().observe(viewLifecycleOwner) { shopItems ->
            adapter.differ.submitList(shopItems)
        }

        binding.favoritesDelete.setOnClickListener {
            viewModel.clearFavorites()
        }
    }
}