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
import javax.inject.Inject

class SignUpFragment: Fragment() {

    @Inject
    lateinit var viewModel : RegisterViewModel

    private lateinit var binding: FragmentSignupBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_signup, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentSignupBinding.bind(view)

        binding.registerButton.setOnClickListener {
            val username = binding.registerUsername.editableText.toString()
            val password = binding.registerPassword.editableText.toString()

            val result = validateLoginRequest(username, password)

            if(result.successful){
                binding.registerProgress.visibility = View.VISIBLE
                binding.registerButton.isEnabled = false

                viewModel.successful.observe(viewLifecycleOwner){succesful ->
                    if(succesful == true ){
                        binding.registerProgress.visibility = View.INVISIBLE
                        binding.registerButton.isEnabled = true
                        findNavController().navigate(R.id.action_signUpFragment_to_homeFragment)
                        viewModel.navigated()
                    } else if(succesful == false){
                        binding.registerProgress.visibility = View.INVISIBLE
                        binding.registerButton.isEnabled = true
                        Snackbar.make(binding.registerButton, "${viewModel.error.value}", Snackbar.LENGTH_SHORT).show()
                        viewModel.navigated()
                    }
                }
            } else{
                Snackbar.make(binding.registerButton, "${result.error}", Snackbar.LENGTH_SHORT).show()
            }
        }

        binding.registerSignin.setOnClickListener {
            findNavController().navigate(R.id.action_signUpFragment_to_homeFragment)
        }
    }

}