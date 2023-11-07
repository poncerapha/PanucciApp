package com.example.panucci.ui.uistate

import com.example.panucci.model.Product

data class MenuUiState(
    val products: List<Product> = emptyList()
)
