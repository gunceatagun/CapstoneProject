package com.gunceatagun.capstoneprojesi.view.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.gunceatagun.capstoneprojesi.common.Resource
import com.gunceatagun.capstoneprojesi.common.gone
import com.gunceatagun.capstoneprojesi.common.visible
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
        viewModel.productsLiveData.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Success -> {
                    binding.progressBar.gone()
                    productsAdapter.submitList(it.data)
                }

                is Resource.Error -> {
                    binding.progressBar.gone()
                    Snackbar.make(requireView(), it.errorMessage, 1000).show()
                    binding.productListRecycler.visibility = View.GONE
                    binding.noDataText.visibility = View.VISIBLE
                    binding.noDataText.text = "Data Bulunamadı"
                }

                is Resource.Fail -> {
                    binding.progressBar.gone()
                    Snackbar.make(requireView(), it.failMessage, 1000).show()
                }

                Resource.Loading -> binding.progressBar.visible()
            }

            binding.productListRecycler.visibility = View.VISIBLE
            binding.noDataText.visibility = View.GONE
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