package com.gunceatagun.capstoneprojesi.view.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ProductListViewModel:ViewModel() {
    private var _productsLiveData = MutableLiveData<Int>()
    val productsLiveData :LiveData<Int> get() = _productsLiveData

}