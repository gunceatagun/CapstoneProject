package com.gunceatagun.capstoneprojesi.view.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gunceatagun.capstoneprojesi.common.Resource
import com.gunceatagun.capstoneprojesi.data.model.Product
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
        //loading
        _homeState.value = HomeState.Loading
        _homeState.value = when (val result = productRepository.getProducts()) {
            is Resource.Success -> HomeState.SuccessState(result.data)
            is Resource.Error -> HomeState.EmptyScreen(result.errorMessage)
            is Resource.Fail -> HomeState.ShowPopup(result.failMessage)
        }
    }

}

sealed interface HomeState {
    object Loading : HomeState
    data class SuccessState(val product: List<Product>) : HomeState
    data class EmptyScreen(val failMessage: String) : HomeState
    data class ShowPopup(val errorMessage: String) : HomeState


}