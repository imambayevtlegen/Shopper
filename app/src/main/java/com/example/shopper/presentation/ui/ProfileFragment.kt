package com.example.shopper.presentation.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.shopper.R
import com.example.shopper.data.model.User
import com.example.shopper.data.util.Resource
import com.example.shopper.databinding.FragmentProfileBinding
import com.example.shopper.presentation.viewmodel.ProfileViewModel
import com.google.android.material.snackbar.Snackbar
import javax.inject.Inject

class ProfileFragment: Fragment() {

    @Inject
    lateinit var viewModel: ProfileViewModel

    private lateinit var binding: FragmentProfileBinding

    private var user: User? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentProfileBinding.bind(view)

        viewModel.getUser(1)

        viewModel.user.observe(viewLifecycleOwner){ result ->
            when(result){
                is Resource.Loading -> {
                    Log.i("ProfileFragment", "Loading..")
                }
                is Resource.Success -> {
                    user = result.data
                    binding.profileName.text = "${result.data?.name?.firstname} ${result.data?.name?.lastname}"
                    binding.profileEmail.text = "${result.data?.email}"
                }
                is Resource.Error -> {
                    Log.i("ProfileFragment", "Error ${result.message}")
                }
            }
        }

        binding.profileBack.setOnClickListener {
            findNavController().navigateUp()
        }

        binding.profileEdit.setOnClickListener {
            val action = user?.let { user1 ->
                ProfileFragmentDirections.actionProfileFragmentToEditProfileFragment(user1)
            }
            if (action != null) findNavController().navigate(action)
        }

        binding.profileNotifications.setOnClickListener {
            Snackbar.make(binding.profileNotifications, "Coming soon..", Snackbar.LENGTH_SHORT).show()
        }

        binding.profileFavorites.setOnClickListener {
            findNavController().navigate(R.id.action_profileFragment_to_favoritesFragment)
        }

        binding.profileTerms.setOnClickListener {
            Snackbar.make(binding.profileNotifications, "Coming soon..", Snackbar.LENGTH_SHORT).show()
        }

        binding.profilePrivacy.setOnClickListener {
            Snackbar.make(binding.profileNotifications, "Coming soon..", Snackbar.LENGTH_SHORT).show()
        }

        binding.profileReportBug.setOnClickListener {
            Snackbar.make(binding.profileNotifications,"Coming soon...",Snackbar.LENGTH_SHORT).show()
        }

        binding.profileLogout.setOnClickListener {
            viewModel.logoutUser()
            findNavController().navigate(R.id.action_profileFragment_to_splashFragment)
        }

    }
}