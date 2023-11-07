package com.example.panucci.ui.uistate

import com.example.panucci.model.Product

sealed class ProductDetailsUiState {
    object Loading: ProductDetailsUiState()
    object Failure: ProductDetailsUiState()
    data class Success(val product: Product) : ProductDetailsUiState()
}

