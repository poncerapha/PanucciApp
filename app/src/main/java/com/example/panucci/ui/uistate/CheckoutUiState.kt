package com.example.panucci.ui.uistate

import com.example.panucci.model.Product

data class CheckoutUiState(
    val products: List<Product> = emptyList()
)