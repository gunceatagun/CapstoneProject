package com.gunceatagun.capstoneprojesi

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gunceatagun.capstoneprojesi.databinding.FragmentProductDetailBinding

class ProductDetailFragment : Fragment() {
    private lateinit var binding: FragmentProductDetailBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProductDetailBinding.inflate(inflater, container, false)
        return binding.root

    }

}