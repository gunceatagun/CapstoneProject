package com.gunceatagun.capstoneprojesi.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.gunceatagun.capstoneprojesi.R
import com.gunceatagun.capstoneprojesi.databinding.FragmentSearchBinding
import com.gunceatagun.capstoneprojesi.databinding.FragmentSplashBinding
import com.gunceatagun.capstoneprojesi.ui.splash.SplashState
import com.gunceatagun.capstoneprojesi.ui.splash.SplashViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : Fragment() {

    private lateinit var binding: FragmentSearchBinding
    private val viewModel by viewModels<SearchViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
        observeData()
    }

    private fun observeData() = with(binding) {
        viewModel.searchState.observe(viewLifecycleOwner) { state ->


        }

    }
}