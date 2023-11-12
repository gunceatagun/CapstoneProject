package com.gunceatagun.capstoneprojesi.ui.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.gunceatagun.capstoneprojesi.R
import com.gunceatagun.capstoneprojesi.databinding.FragmentSearchBinding
import com.gunceatagun.capstoneprojesi.databinding.FragmentSplashBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashFragment : Fragment(R.layout.fragment_splash) {

    private lateinit var binding: FragmentSplashBinding
    private val viewModel by viewModels<SplashViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSplashBinding.inflate(inflater, container, false)
        return binding.root
        observeData()
    }

    private fun observeData() = with(binding) {
        viewModel.splashState.observe(viewLifecycleOwner) { state ->
            when (state) {
                SplashState.GoToHome -> {

                }

                SplashState.GoToSignIn -> {

                }
            }

        }

    }


}