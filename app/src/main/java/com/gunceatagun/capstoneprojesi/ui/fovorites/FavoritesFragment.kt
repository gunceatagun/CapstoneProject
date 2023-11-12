package com.gunceatagun.capstoneprojesi.ui.fovorites

import android.annotation.SuppressLint
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
import com.gunceatagun.capstoneprojesi.data.model.response.ProductListUI
import com.gunceatagun.capstoneprojesi.databinding.FragmentFavoritesBinding
import dagger.hilt.android.AndroidEntryPoint
import okhttp3.internal.notifyAll

@AndroidEntryPoint
class FavoritesFragment : Fragment() {
    private lateinit var binding: FragmentFavoritesBinding
    private val viewModel by viewModels<FavoitesViewModel>()
    private val favProductsAdapter = FavoritesAdapter(onProductClick = ::onProductClick, onDeleteClick = ::onDeleteClick)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getFavorites()

        with(binding) {
            favProductListRecycler.adapter = favProductsAdapter
        }

        observeData()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun observeData() = with(binding) {
        viewModel.favoritesState.observe(viewLifecycleOwner) { state ->
            when (state) {
                is FavoritesState.SuccessProductState -> {
                    progressBar.gone()
                    favProductsAdapter.submitList(state.products)
                }
                is FavoritesState.SuccessSaleProductState -> {
                    progressBar.gone()
                    //saleProductsAdapter.submitList(state.saleProducts)
                }
                is FavoritesState.EmptyScreen -> {
                    progressBar.gone()
                    favProductListRecycler.gone()
                    noDataText.visible()
                    noDataImage.visible()
                    noDataText.text = state.failMessage
                }

                is FavoritesState.ShowPopup -> {
                    binding.progressBar.gone()
                    AlertDialog.Builder(context)
                        .setTitle("Uyarı!")
                        .setMessage("Favori listeniz boş. Anasayfaya dönüp favoriye ürün eklemek ister misiniz?")
                        .setPositiveButton("Tamam") { dialog, _ -> dialog.dismiss()
                            findNavController().popBackStack()
                        }
                        .setNegativeButton("İptal") { dialog, _ -> dialog.dismiss()
                            favProductListRecycler.gone()
                            noDataText.visible()
                            noDataImage.visible()
                            noDataText.text = state.errorMessage
                        }
                        .create()
                        .show()
                }

                FavoritesState.Loading -> binding.progressBar.visible()
            }

            binding.favProductListRecycler.visible()
            binding.noDataText.gone()
        }
    }

    private fun onProductClick(id: Int) {
        findNavController().navigate(
            FavoritesFragmentDirections.actionFavoritesFragmentToProductDetailFragment(productId = id)
        )
    }
    private fun onDeleteClick(product: ProductListUI) {
        viewModel.deleteFormFavorites(product)
    }
}