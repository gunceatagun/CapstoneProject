package com.gunceatagun.capstoneprojesi.view.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gunceatagun.capstoneprojesi.data.model.Product
import com.gunceatagun.capstoneprojesi.data.repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val productRepository: ProductRepository) :
    ViewModel() {
    private var _productDetailLiveData = MutableLiveData<Product>()
    val productDetailLiveData: LiveData<Product> get() = _productDetailLiveData

    private var _errorDataLiveData = MutableLiveData<String>()
    val errorDataLiveData: LiveData<String> get() = _errorDataLiveData

    private var _loadingLiveData = MutableLiveData<Boolean>()
    val loadingLiveData: LiveData<Boolean> get() = _loadingLiveData

    init {
        _productDetailLiveData = productRepository.productDetailLiveData
        _errorDataLiveData = productRepository.errorDataLiveData
        _loadingLiveData = productRepository.loadingLiveData
    }

    fun getProductDetail(id: Int) {
        productRepository.getProductDetail(id)
    }
}