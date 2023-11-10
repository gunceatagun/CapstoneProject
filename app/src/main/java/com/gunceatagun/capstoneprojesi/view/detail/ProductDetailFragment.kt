package com.gunceatagun.capstoneprojesi.view.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.gunceatagun.capstoneprojesi.databinding.FragmentProductDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductDetailFragment : Fragment() {
    private lateinit var binding: FragmentProductDetailBinding
    private val viewModel by viewModels<DetailViewModel>()
    private var productId = 0
    private val args by navArgs<ProductDetailFragmentArgs>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProductDetailBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            productId = ProductDetailFragmentArgs.fromBundle(it).productId
            viewModel.getProductDetail(productId)
            // args.productId
        }
        observeData()
    }

    private fun observeData() = with(binding) {
        viewModel.productDetailLiveData.observe(viewLifecycleOwner) { product ->
            Glide.with(productImage).load(product.imageOne).into(productImage)
            productName.text = product.title
            productDescription.text = product.description
            productPrize.text = "${product.price} ₺"
            productPrizeSale.text = product.salePrice.toString()
        }
        viewModel.errorDataLiveData.observe(viewLifecycleOwner) { errorMessage ->
            Snackbar.make(requireView(), errorMessage, 1000).show()
            detailView.visibility = View.GONE
            noDataText.visibility = View.VISIBLE
            noDataText.text = "Data Bulunamadı"
        }
        viewModel.loadingLiveData.observe(viewLifecycleOwner) { isLoading ->
            progressBar.isVisible = isLoading
        }
    }
}