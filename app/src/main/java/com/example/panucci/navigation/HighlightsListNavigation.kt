package com.example.panucci.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.example.panucci.sampledata.sampleProducts
import com.example.panucci.ui.screens.HighlightsListScreen

internal const val highlightsListRoute = "highlight"

fun NavGraphBuilder.highlightsListScreen(navController: NavHostController) {
    composable(highlightsListRoute) {
        HighlightsListScreen(
            products = sampleProducts,
            onNavigateToDetails = { product ->
                navController.navigateToProductDetails(product.id)
            },
            onNavigateToCheckout = {
                navController.navigateToCheckout()
            },
        )
    }
}

fun NavController.navigateToHighlightsList(
    navOptions: NavOptions? = null
) {
    navigate(highlightsListRoute, navOptions)
}