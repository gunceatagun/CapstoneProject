package com.gunceatagun.capstoneprojesi.ui.home

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
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

    private fun observeData() = with(binding) {
        viewModel.homeState.observe(viewLifecycleOwner) { state ->
            when (state) {
                is HomeState.SuccessState -> {
                    progressBar.gone()
                    productsAdapter.submitList(state.product)
                }

                is HomeState.EmptyScreen -> {
                    progressBar.gone()
                    productListRecycler.gone()
                    noDataText.visible()
                    noDataText.text = state.failMessage
                }

                is HomeState.ShowPopup -> {
                    binding.progressBar.gone()
                    //Snackbar.make(requireView(), it.errorMessage, 1000).show()
                    AlertDialog.Builder(context)
                        .setTitle("Hata!")
                        .setMessage("Bir şeyler ters gitti, geri giderek tekrar deneyin")
                        .setPositiveButton("Tamam") { dialog, _ -> dialog.dismiss() }
                        .setNegativeButton("İptal") { dialog, _ -> dialog.dismiss() }
                        .create()
                        .show()
                }

                HomeState.Loading -> binding.progressBar.visible()
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