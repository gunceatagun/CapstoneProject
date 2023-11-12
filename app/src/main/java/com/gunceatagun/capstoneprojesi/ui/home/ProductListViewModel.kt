package com.gunceatagun.capstoneprojesi.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gunceatagun.capstoneprojesi.common.Resource
import com.gunceatagun.capstoneprojesi.data.model.response.ProductListUI
import com.gunceatagun.capstoneprojesi.data.repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel

class ProductListViewModel @Inject constructor(private val productRepository: ProductRepository) :
    ViewModel() {
    private var _homeState = MutableLiveData<HomeState>()
    val homeState: LiveData<HomeState> get() = _homeState

    fun getProducts() = viewModelScope.launch {
        _homeState.value = HomeState.Loading
        _homeState.value = when (val productResult = productRepository.getProducts()) {
            is Resource.Success -> HomeState.SuccessProductState(productResult.data)
            is Resource.Error -> HomeState.EmptyScreen(productResult.errorMessage)
            is Resource.Fail -> HomeState.ShowPopup(productResult.failMessage)
        }
    }

    fun getSaleProducts() = viewModelScope.launch {
        _homeState.value = HomeState.Loading
        _homeState.value = when (val saleProductResult = productRepository.getSaleProducts()) {
            is Resource.Success -> HomeState.SuccessSaleProductState(saleProductResult.data)
            is Resource.Error -> HomeState.EmptyScreen(saleProductResult.errorMessage)
            is Resource.Fail -> HomeState.ShowPopup(saleProductResult.failMessage)
        }
    }

    fun addToFavorites(product: ProductListUI) = viewModelScope.launch {
        productRepository.addToFavorites(product)
    }
}

sealed interface HomeState {
    object Loading : HomeState
    data class SuccessProductState(val products: List<ProductListUI>) : HomeState
    data class SuccessSaleProductState(val saleProduct: List<ProductListUI>) : HomeState

    data class EmptyScreen(val failMessage: String) : HomeState
    data class ShowPopup(val errorMessage: String) : HomeState


}