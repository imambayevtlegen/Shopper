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
import com.example.shopper.data.util.Outcome
import com.example.shopper.databinding.FragmentProfileBinding
import com.example.shopper.presentation.viewmodel.ProfileViewModel
import com.google.android.material.snackbar.Snackbar
import javax.inject.Inject

class ProfileFragment : Fragment() {

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentProfileBinding.bind(view)

        viewModel.getUser(1)

        viewModel.user.observe(viewLifecycleOwner) { result ->
            when (result) {
                is Outcome.Loading -> {
                    Log.i("ProfileFragment", "Loading..")
                }

                is Outcome.Success -> {
                    user = result.data
                    profileName.text =
                        "${result.data?.name?.firstname} ${result.data?.name?.lastname}"
                    profileEmail.text = "${result.data?.email}"
                }

                is Outcome.Error -> {
                    Log.i("ProfileFragment", "Error ${result.message}")
                }
            }
        }

        profileBack.setOnClickListener {
            findNavController().navigateUp()
        }

        profileEdit.setOnClickListener {
            val action = user?.let { user1 ->
                ProfileFragmentDirections.actionProfileFragmentToEditProfileFragment(user1)
            }
            if (action != null) findNavController().navigate(action)
        }

        profileNotifications.setOnClickListener {
            Snackbar.make(profileNotifications, "Coming soon..", Snackbar.LENGTH_SHORT).show()
        }

        profileFavorites.setOnClickListener {
            findNavController().navigate(R.id.action_profileFragment_to_favoritesFragment)
        }

        profileTerms.setOnClickListener {
            Snackbar.make(profileNotifications, "Coming soon..", Snackbar.LENGTH_SHORT).show()
        }

        profilePrivacy.setOnClickListener {
            Snackbar.make(profileNotifications, "Coming soon..", Snackbar.LENGTH_SHORT).show()
        }

        profileReportBug.setOnClickListener {
            Snackbar.make(profileNotifications, "Coming soon...", Snackbar.LENGTH_SHORT).show()
        }

        profileLogout.setOnClickListener {
            viewModel.logoutUser()
            findNavController().navigate(R.id.action_profileFragment_to_splashFragment)
        }

    }
}