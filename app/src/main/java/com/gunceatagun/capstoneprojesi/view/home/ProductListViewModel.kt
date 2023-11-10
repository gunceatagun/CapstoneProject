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
    private var _productsLiveData = MutableLiveData<Resource<List<Product>>>()
    val productsLiveData: LiveData<Resource<List<Product>>> get() = _productsLiveData

    init {
        _productsLiveData = productRepository.products
    }

    fun getProducts() = viewModelScope.launch {
        productRepository.getProducts()
    }

}