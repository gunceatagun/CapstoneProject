package com.gunceatagun.capstoneprojesi.ui.basket

import androidx.lifecycle.ViewModel
import com.gunceatagun.capstoneprojesi.data.repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BasketViewModel @Inject constructor(private val productRepository: ProductRepository):ViewModel() {
}