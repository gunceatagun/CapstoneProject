package com.gunceatagun.capstoneprojesi

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.gunceatagun.capstoneprojesi.databinding.FragmentProductDetailBinding

class ProductDetailFragment : Fragment() {
    private lateinit var binding: FragmentProductDetailBinding
    private var productId = 0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProductDetailBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        arguments?.let {
//            productId = ProductDetailFragmentArgs.fromBundle(it).productId
//            println(productId)
//        }
//        binding.listeyeGitButton.setOnClickListener {
//            val action = ProductDetailFragmentDirections.actionProductDetailFragmentToProductsListFragment()
//            Navigation.findNavController(it).navigate(action)
//        }
    }
}