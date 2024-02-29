package com.example.shopper.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.shopper.R
import com.example.shopper.data.util.Utils.validateLoginRequest
import com.example.shopper.databinding.FragmentSignupBinding
import com.example.shopper.presentation.viewmodel.RegisterViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SignUpFragment : Fragment() {
    @Inject
    lateinit var viewModel: RegisterViewModel
    private lateinit var binding: FragmentSignupBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_signup, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentSignupBinding.bind(view)

        registerButton.setOnClickListener {
            val username = registerUsername.editableText.toString()
            val password = registerPassword.editableText.toString()

            val result = validateLoginRequest(username, password)

            if (result.successful) {
                registerProgress.visibility = View.VISIBLE
                registerButton.isEnabled = false

                viewModel.registerUser(username, password)

                viewModel.successful.observe(viewLifecycleOwner) { successful ->
                    if (successful == true) {
                        registerProgress.visibility = View.INVISIBLE
                        registerButton.isEnabled = true
                        findNavController().navigate(R.id.action_signUpFragment_to_homeFragment)
                        viewModel.navigated()
                    } else if (successful == false) {
                        registerProgress.visibility = View.INVISIBLE
                        registerButton.isEnabled = true
                        Snackbar.make(
                            registerButton,
                            "${viewModel.error.value}",
                            Snackbar.LENGTH_SHORT
                        ).show()
                        viewModel.navigated()
                    }
                }
            } else {
                Snackbar.make(registerButton, "${result.error}", Snackbar.LENGTH_SHORT).show()
            }
        }

        registerSignin.setOnClickListener {
            findNavController().navigate(R.id.action_signUpFragment_to_loginFragment)
        }
    }

}