package com.gunceatagun.capstoneprojesi.view.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gunceatagun.capstoneprojesi.data.model.Product
import com.gunceatagun.capstoneprojesi.data.repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel

class ProductListViewModel @Inject constructor(private val productRepository: ProductRepository) : ViewModel() {
    private var _productsLiveData = MutableLiveData<List<Product>>()
    val productsLiveData: LiveData<List<Product>> get() = _productsLiveData
    init {
        _productsLiveData = productRepository.productsLiveData
    }
    fun getProducts(){
        productRepository.getProducts()
    }

}