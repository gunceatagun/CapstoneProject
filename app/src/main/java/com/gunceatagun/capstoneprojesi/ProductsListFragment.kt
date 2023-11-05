package com.gunceatagun.capstoneprojesi

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.gunceatagun.capstoneprojesi.databinding.FragmentProductsListBinding

class ProductsListFragment : Fragment() {
    private lateinit var binding: FragmentProductsListBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProductsListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        binding.detayaGitButton.setOnClickListener {
//            val action = ProductsListFragmentDirections.actionProductsListFragmentToProductDetailFragment(3)
//            Navigation.findNavController(it).navigate(action)
//        }
    }
}