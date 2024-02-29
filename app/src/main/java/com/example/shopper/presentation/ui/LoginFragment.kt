package com.example.shopper.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.shopper.R
import com.example.shopper.databinding.FragmentLoginBinding
import com.example.shopper.presentation.viewmodel.LoginViewModel
import com.example.shopper.data.util.Utils.validateLoginRequest
import com.google.android.material.snackbar.Snackbar
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class LoginFragment : Fragment() {
    @Inject
    lateinit var viewModel: LoginViewModel
    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentLoginBinding.bind(view)

        loginUsername.setText("tlegen")
        loginPassword.setText("ygTr3c3")

        loginButton.setOnClickListener {

            val username = loginUsername.editableText.toString()
            val password = loginPassword.editableText.toString()

            val result = validateLoginRequest(username, password)

            if (result.successful) {
                loginProgress.visibility = View.VISIBLE
                loginButton.isEnabled = false

                viewModel.loginUser(username, password)

                viewModel.successful.observe(viewLifecycleOwner) { successful ->
                    if (successful == true) {
                        loginProgress.visibility = View.INVISIBLE
                        loginButton.isEnabled = true
                        findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
                        viewModel.navigated()
                    } else if (successful == false) {
                        loginProgress.visibility = View.INVISIBLE
                        loginButton.isEnabled = true
                        Snackbar.make(
                            loginButton,
                            "${viewModel.error.value}",
                            Snackbar.LENGTH_SHORT
                        ).show()
                        viewModel.navigated()
                    }
                }
            } else {
                Snackbar.make(loginButton, "${result.error}", Snackbar.LENGTH_SHORT).show()
            }
        }

        loginSignup.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_signUpFragment)
        }

    }
}