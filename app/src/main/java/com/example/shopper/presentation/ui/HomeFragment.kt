package com.example.shopper.presentation.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.shopper.R
import com.example.shopper.data.model.Category2
import com.example.shopper.data.util.Outcome
import com.example.shopper.databinding.FragmentHomeBinding
import com.example.shopper.presentation.adapter.HomeAdapter
import com.example.shopper.presentation.viewmodel.HomeViewModel
import com.google.android.material.chip.Chip
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment: Fragment() {
    @Inject
    lateinit var viewModel: HomeViewModel

    @Inject
    lateinit var adapter: HomeAdapter
    private lateinit var binding: FragmentHomeBinding

    private var category2 = mutableListOf<Category2>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentHomeBinding.bind(view)

        viewModel.getAllCategories()

        viewModel.categories.observe(viewLifecycleOwner){response ->
            when(response){
                is Outcome.Success -> {
                    val categories = response.data
                    val chip = Chip(requireContext())
                    chip.text = "All"
                    chip.id = 0
                    chip.isChecked = true
                    category2.clear()
                    chipGroup.removeAllViews()
                    category2.add(Category2(0, "All"))
                    chipGroup.addView(chip)
                    categories?.forEachIndexed { index, category ->
                        val chip = Chip(requireContext())
                        chip.text = category
                        chip.id = index+1
                        category2.add(Category2(index, category))
                        chipGroup.addView(chip)
                    }
                }
                is Outcome.Loading -> {
                    Log.i("HomeFragment", "Loading..")
                }
                is Outcome.Error -> {
                    Log.i("HomeFragment", "${response.message}")
                }
            }
        }

        viewModel.getAllProducts()

        viewModel.products.observe(viewLifecycleOwner){result ->
            when(result){
                // TODO different livedata
                // TODO use kotlin result
                is Outcome.Success -> {
                    adapter.submitList(result.data)
                    Log.i("HomeFragment", "${result.data}")
                }
                is Outcome.Loading -> {
                    Log.i("HomeFragment", "Loading..")
                }
                is Outcome.Error -> {
                    Log.i("HomeFragment", "${result.message}")
                }
            }
        }

        adapter.setOnItemClickListener { homeProfile ->
            val bundle = Bundle().apply {
                putSerializable("homeProfile", homeProfile)
            }
            findNavController().navigate(R.id.productDetailFragment, bundle)
        }

        chipGroup.setOnCheckedStateChangeListener { group, checkedIds ->
            group.isSingleSelection = true
            val chipid = group.checkedChipId
            val category = category2[chipid].category
            val categoryList = viewModel.products.value?.data?.filter {
                it.category == category
            }
            viewModel.getCategoryProducts(category)
        }


    }
}