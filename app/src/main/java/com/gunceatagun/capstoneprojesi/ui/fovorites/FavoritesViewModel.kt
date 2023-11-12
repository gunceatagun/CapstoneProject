package com.gunceatagun.capstoneprojesi.ui.fovorites

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gunceatagun.capstoneprojesi.common.Resource
import com.gunceatagun.capstoneprojesi.data.model.response.ProductUI
import com.gunceatagun.capstoneprojesi.data.repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel

class FavoitesViewModel @Inject constructor(private val productRepository: ProductRepository) :
    ViewModel() {
    private var _favoritesState = MutableLiveData<FavoritesState>()
    val favoritesState: LiveData<FavoritesState> get() = _favoritesState

    fun getFavorites() = viewModelScope.launch {
        _favoritesState.value = FavoritesState.Loading
        _favoritesState.value = when (val productResult = productRepository.getFavorites()) {
            is Resource.Success -> FavoritesState.SuccessProductState(productResult.data)
            is Resource.Error -> FavoritesState.EmptyScreen(productResult.errorMessage)
            is Resource.Fail -> FavoritesState.ShowPopup(productResult.failMessage)
        }
    }

    fun deleteFormFavorites(product: ProductUI) = viewModelScope.launch {
        productRepository.deleteFromFavorites(product)
        getFavorites()

    }
    /* fun getSaleProducts() = viewModelScope.launch {
         _favoritesState.value = FavoritesState.Loading
         _favoritesState.value = when (val saleProductResult = productRepository.getSaleProducts()) {
             is Resource.Success -> FavoritesState.SuccessSaleProductState(saleProductResult.data)
             is Resource.Error -> FavoritesState.EmptyScreen(saleProductResult.errorMessage)
             is Resource.Fail -> FavoritesState.ShowPopup(saleProductResult.failMessage)
         }
     }*/

}

sealed interface FavoritesState {
    object Loading : FavoritesState
    data class SuccessProductState(val products: List<ProductUI>) : FavoritesState
    data class SuccessSaleProductState(val saleProduct: List<ProductUI>) : FavoritesState
    data class EmptyScreen(val failMessage: String) : FavoritesState
    data class ShowPopup(val errorMessage: String) : FavoritesState


}