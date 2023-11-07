package com.example.panucci.navigation

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.example.panucci.model.Product
import com.example.panucci.ui.screens.HighlightsListScreen
import com.example.panucci.ui.viewmodel.HighlightListViewModel

internal const val highlightsListRoute = "highlight"

fun NavGraphBuilder.highlightsListScreen(
    onNavigateToCheckout: () -> Unit,
    onNavigateToProductDetails: (Product) -> Unit
) {
    composable(highlightsListRoute) {
        val viewModel = viewModel<HighlightListViewModel>()
        val uiState by viewModel.uiState.collectAsState()
        HighlightsListScreen(
            uiState = uiState,
            onProductClick = onNavigateToProductDetails,
            onOrderClick = onNavigateToCheckout,
        )
    }
}

fun NavController.navigateToHighlightsList(
    navOptions: NavOptions? = null
) {
    navigate(highlightsListRoute, navOptions)
}