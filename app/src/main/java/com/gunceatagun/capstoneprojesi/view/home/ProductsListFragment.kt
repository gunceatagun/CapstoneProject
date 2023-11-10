package com.gunceatagun.capstoneprojesi.view.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.gunceatagun.capstoneprojesi.databinding.FragmentProductsListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductsListFragment : Fragment() {
    private lateinit var binding: FragmentProductsListBinding
    private val viewModel by viewModels<ProductListViewModel>()
    private val productsAdapter = ProductsAdapter(onProductClick = ::onProductClick)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProductsListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getProducts()

        with(binding) {
            productListRecycler.adapter = productsAdapter
        }

        observeData()
    }

    private fun observeData() {
        viewModel.productsLiveData.observe(viewLifecycleOwner) { productList ->
            productsAdapter.submitList(productList)
            binding.productListRecycler.visibility = View.VISIBLE
            binding.noDataText.visibility = View.GONE
        }
        viewModel.errorDataLiveData.observe(viewLifecycleOwner) { errorMessage ->
            Snackbar.make(requireView(),errorMessage,1000).show()
            binding.productListRecycler.visibility = View.GONE
            binding.noDataText.visibility = View.VISIBLE
            binding.noDataText.text = "Data Bulunamadı"
        }
        viewModel.loadingLiveData.observe(viewLifecycleOwner) { isLoading ->
            binding.progressBar.isVisible = isLoading
        }
    }

    private fun onProductClick(id: Int) {
        findNavController().navigate(
            ProductsListFragmentDirections.actionProductsListFragmentToProductDetailFragment(
                productId = id
            )
        )
    }
}