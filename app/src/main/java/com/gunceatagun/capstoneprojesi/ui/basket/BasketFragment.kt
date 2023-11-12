package com.gunceatagun.capstoneprojesi.ui.basket

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.gunceatagun.capstoneprojesi.R
import com.gunceatagun.capstoneprojesi.databinding.FragmentBasketBinding
import com.gunceatagun.capstoneprojesi.databinding.FragmentSearchBinding
import com.gunceatagun.capstoneprojesi.ui.search.SearchViewModel
import dagger.hilt.android.AndroidEntryPoint

/**
 * A simple [Fragment] subclass.
 * Use the [BasketFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class BasketFragment : Fragment() {

    private lateinit var binding: FragmentBasketBinding
    private val viewModel by viewModels<BasketViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBasketBinding.inflate(inflater, container, false)
        return binding.root
        observeData()
    }
    private fun observeData() = with(binding) {
//        viewModel.searchState.observe(viewLifecycleOwner) { state ->
//
//
//        }
    }
}