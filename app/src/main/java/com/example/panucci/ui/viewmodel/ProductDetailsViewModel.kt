package com.example.panucci.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.panucci.dao.ProductDao
import com.example.panucci.ui.uistate.ProductDetailsUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ProductDetailsViewModel(
    private val dao: ProductDao = ProductDao()
) : ViewModel() {
    private val _uIState = MutableStateFlow(ProductDetailsUiState())
    val uIState get() = _uIState.asStateFlow()

    fun findProductById(id: String) {
        dao.findById(id)?.let { product ->
            _uIState.update {
                it.copy(product = product)
            }
        }
    }
}