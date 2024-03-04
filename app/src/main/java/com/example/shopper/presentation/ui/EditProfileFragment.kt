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
import com.example.shopper.databinding.FragmentEditProfileBinding
import com.example.shopper.presentation.viewmodel.EditProfileViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import javax.inject.Inject

class EditProfileFragment: Fragment() {

    @Inject lateinit var viewModel: EditProfileViewModel

    private lateinit var binding: FragmentEditProfileBinding

    // TODO Lazy
    private lateinit var user: User

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_edit_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentEditProfileBinding.bind(view)
        user = EditProfileFragmentArgs.fromBundle(requireArguments()).user

        // TODO sepater method
        editProfileFirstName.setText(user.name.firstname)
        editProfileLastName.setText(user.name.lastname)
        editProfileEmailAddress.setText(user.email)
        editProfileUsername.setText(user.username)

        editProfileBack.setOnClickListener {
            findNavController().navigateUp()
        }

        editProfileButton.setOnClickListener {
            viewModel.updateUser(1, user.copy(
                id = 10,
                username = "${editProfileUsername.editableText ?: "new username"}"))
            viewModel.theUser.observe(viewLifecycleOwner){ result ->
                when(result){
                    is Outcome.Loading -> {
                        editProfileProgress.visibility = View.VISIBLE
                        editProfileButton.isEnabled = true
                        Log.i("EditProfileFragment", "Loading..")
                    }
                    is Outcome.Success -> {
                        editProfileProgress.visibility = View.VISIBLE
                        editProfileButton.isEnabled = true
                        Log.i("EditProfileFragment", "${result.data}")
                        MaterialAlertDialogBuilder(requireContext())
                            .setTitle("Updated successfully")
                            .setMessage("You have updated your profile successfully")
                            .setCancelable(false)
                            .setPositiveButton("Okay"){_,_, ->
                                findNavController().navigateUp()
                            }.show()
                    }
                    is Outcome.Error -> {
                        editProfileProgress.visibility = View.INVISIBLE
                        editProfileButton.isEnabled = true
                        Snackbar.make(editProfileButton, "Error ${result.message}", Snackbar.LENGTH_SHORT).show()
                        Log.i("EditProfileFragment", "Error ${result.message}")
                    }
                }
            }
        }
    }
}