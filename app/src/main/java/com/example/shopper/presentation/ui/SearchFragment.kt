package com.example.shopper.presentation.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.shopper.R
import com.example.shopper.data.model.ShopItem
import com.example.shopper.data.util.Resource
import com.example.shopper.databinding.FragmentSearchBinding
import com.example.shopper.presentation.adapter.SearchAdapter
import com.example.shopper.presentation.viewmodel.HomeViewModel
import javax.inject.Inject

class SearchFragment: Fragment() {

    @Inject
    lateinit var viewModel: HomeViewModel

    @Inject
    lateinit var adapter: SearchAdapter

    private lateinit var binding: FragmentSearchBinding

    private var productsList = listOf<ShopItem>()
    private var productsList2 = listOf<ShopItem>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding){
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentSearchBinding.bind(view)

        viewModel.getAllProducts()

        viewModel.products.observe(viewLifecycleOwner){ result ->
            when(result){
                is Resource.Success -> {
                    Log.i("SearchFragment", "${result.data}")
                    productsList = result.data!!
                    adapter.differ.submitList(result.data)
                }
                is Resource.Loading -> {
                    Log.i("SearchFragment", "Loading..")
                }
                is Resource.Error -> {
                    Log.i("SearchFragment", "${result.message}")
                }
            }
        }
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                productsList2 = productsList.filter { it.title.contains("$query",ignoreCase = true)}
                adapter.differ.submitList(productsList2)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                productsList2 = productsList.filter { it.title.contains("$newText",ignoreCase = true)}
                adapter.differ.submitList(productsList2)
                return true
            }

        })

        searchView.setOnCloseListener {
            adapter.differ.submitList(productsList)
            true
        }

        searchBack.setOnClickListener {
            findNavController().navigateUp()
        }

        searchCart.setOnClickListener {
            findNavController().navigate(R.id.action_searchFragment_to_cartFragment)
        }

        searchRecyclerView.adapter = adapter

        adapter.setOnItemClickListener {
            val action = SearchFragmentDirections.actionSearchFragmentToProductDetailFragment(it)
            findNavController().navigate(action)
        }
    }
}