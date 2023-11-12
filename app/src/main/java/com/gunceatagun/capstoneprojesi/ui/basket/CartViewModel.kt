package com.gunceatagun.capstoneprojesi.ui.basket

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
class CartViewModel @Inject constructor(private val productRepository: ProductRepository) :
    ViewModel() {
    private var _cartState = MutableLiveData<CartState>()
    val cartState: LiveData<CartState> get() = _cartState

    fun getCartProducts(userId:String) = viewModelScope.launch {
        _cartState.value = CartState.Loading

        _cartState.value = when(val result = productRepository.getCartProducts(userId)){
            is Resource.Success -> CartState.SuccessState(result.data)
            is Resource.Error -> CartState.EmptyScreen(result.errorMessage)
            is Resource.Fail -> CartState.ShowPopup(result.failMessage)
        }
    }
}

sealed interface CartState {
    object Loading : CartState
    data class SuccessState(val products: List<ProductUI>) : CartState
    data class EmptyScreen(val failMessage: String) : CartState
    data class ShowPopup(val errorMessage: String) : CartState
}