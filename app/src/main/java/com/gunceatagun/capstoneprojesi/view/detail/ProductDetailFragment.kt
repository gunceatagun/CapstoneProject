package com.gunceatagun.capstoneprojesi.view.detail

import android.app.AlertDialog
import android.graphics.Paint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.gunceatagun.capstoneprojesi.common.gone
import com.gunceatagun.capstoneprojesi.common.visible
import com.gunceatagun.capstoneprojesi.databinding.FragmentProductDetailBinding
import com.gunceatagun.capstoneprojesi.view.home.HomeState
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
        }
        observeData()
    }

    private fun observeData() = with(binding) {
        viewModel.detailState.observe(viewLifecycleOwner) { state ->
            when (state) {
                is DetailState.SuccessState -> {
                    progressBar.gone()
                    Glide.with(productImage).load(state.product.imageOne).into(productImage)
                    productName.text = state.product.title
                    productDescription.text = state.product.description
                    if (state.product.saleState == true) {
                        productPrize.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
                        productPrizeSaleRate.text = "%${state.product.rate} indirim "
                        productPrize.text = "${state.product.price} ₺"
                        productPrizeSale.text = "${state.product.salePrice} ₺"
                    } else {
                        productPrizeSale.gone()
                        productPrizeSaleRate.gone()
                        productPrize.text = "${state.product.price} ₺"
                    }
                }

                is DetailState.EmptyScreen -> {
                    progressBar.gone()
                    noDataText.visible()
                    noDataText.text = state.failMessage
                }

                is DetailState.ShowPopup -> {
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
                DetailState.Loading -> binding.progressBar.visible()
            }
        }
    }
}