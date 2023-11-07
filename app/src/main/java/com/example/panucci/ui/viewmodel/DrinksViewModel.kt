package com.example.panucci.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.panucci.dao.ProductDao
import com.example.panucci.ui.uistate.DrinksUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DrinksViewModel(
    private val dao: ProductDao = ProductDao()
) : ViewModel() {
    private val _uIState = MutableStateFlow(DrinksUiState())
    val uIState get() = _uIState.asStateFlow()

    init {
        viewModelScope.launch {
            dao.products.collect { products ->
                _uIState.update {
                    it.copy(products = products)
                }
            }
        }
    }
}