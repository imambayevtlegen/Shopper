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
import com.example.shopper.databinding.FragmentEditProfileBinding
import com.example.shopper.presentation.viewmodel.EditProfileViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import javax.inject.Inject

class EditProfileFragment: Fragment() {

    private lateinit var binding: FragmentEditProfileBinding

    private lateinit var user: User

    @Inject
    lateinit var viewModel: EditProfileViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_edit_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentEditProfileBinding.bind(view)
        user = EditProfileFragmentArgs.fromBundle(requireArguments()).user

        binding.editProfileFirstName.setText(user.name.firstname)
        binding.editProfileLastName.setText(user.name.lastname)
        binding.editProfileEmailAddress.setText(user.email)
        binding.editProfileUsername.setText(user.username)

        binding.editProfileBack.setOnClickListener {
            findNavController().navigateUp()
        }

        binding.editProfileButton.setOnClickListener {
            viewModel.updateUser(1, user.copy(
                id = 10,
                username = "${binding.editProfileUsername.editableText ?: "new username"}"))
            viewModel.theUser.observe(viewLifecycleOwner){ result ->
                when(result){
                    is Resource.Loading -> {
                        binding.editProfileProgress.visibility = View.VISIBLE
                        binding.editProfileButton.isEnabled = true
                        Log.i("EditProfileFragment", "Loading..")
                    }
                    is Resource.Success -> {
                        binding.editProfileProgress.visibility = View.VISIBLE
                        binding.editProfileButton.isEnabled = true
                        Log.i("EditProfileFragment", "${result.data}")
                        MaterialAlertDialogBuilder(requireContext())
                            .setTitle("Updated successfully")
                            .setMessage("You have updated your profile successfully")
                            .setCancelable(false)
                            .setPositiveButton("Okay"){_,_, ->
                                findNavController().navigateUp()
                            }.show()
                    }
                    is Resource.Error -> {
                        binding.editProfileProgress.visibility = View.INVISIBLE
                        binding.editProfileButton.isEnabled = true
                        Snackbar.make(binding.editProfileButton, "Error ${result.message}", Snackbar.LENGTH_SHORT).show()
                        Log.i("EditProfileFragment", "Error ${result.message}")
                    }
                }
            }
        }
    }
}