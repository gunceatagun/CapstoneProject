package com.gunceatagun.capstoneprojesi.view.detail

import androidx.lifecycle.ViewModel
import com.gunceatagun.capstoneprojesi.data.repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val productRepository: ProductRepository):ViewModel() {
}