package com.example.panucci.ui.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.panucci.dao.ProductDao
import com.example.panucci.navigation.productIdArgument
import com.example.panucci.navigation.promoCodeArgument
import com.example.panucci.ui.uistate.ProductDetailsUiState
import java.math.BigDecimal
import kotlin.random.Random
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ProductDetailsViewModel(
    private val dao: ProductDao,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _uiState = MutableStateFlow<ProductDetailsUiState>(
        ProductDetailsUiState.Loading
    )
    val uiState = _uiState.asStateFlow()
    private var discount = BigDecimal.ZERO

    init {
        viewModelScope.launch {
            val promoCode = savedStateHandle.get<String?>(promoCodeArgument)
            discount = when(promoCode) {
                "PANUCCI10" -> BigDecimal("0.1")
                else -> BigDecimal.ZERO
            }
            savedStateHandle
                .getStateFlow<String?>(productIdArgument, null)
                .filterNotNull()
                .collect { id ->
                    findProductById(id)
                }
        }
    }

    fun findProductById(id: String) {
        _uiState.update { ProductDetailsUiState.Loading }
        viewModelScope.launch {
            val timemillis = Random.nextLong(500, 2000)
            delay(timemillis)
            val dataState = dao.findById(id)?.let { product ->
                val priceWithDiscount = product.price - (product.price * discount)
                ProductDetailsUiState.Success(product = product.copy(price = priceWithDiscount))
            } ?: ProductDetailsUiState.Failure
            _uiState.update { dataState }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                ProductDetailsViewModel(
                    dao = ProductDao(),
                    savedStateHandle = createSavedStateHandle()
                )
            }
        }
    }
}