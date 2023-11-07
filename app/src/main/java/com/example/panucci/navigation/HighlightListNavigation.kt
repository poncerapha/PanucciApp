package com.example.panucci.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.panucci.sampledata.sampleProducts
import com.example.panucci.ui.screens.HighlightsListScreen

fun NavGraphBuilder.highlightsListScreen(navController: NavHostController) {
    composable(AppDestinations.Highlights.route) {
        HighlightsListScreen(
            products = sampleProducts,
            onNavigateToDetails = { product ->
                navController.navigate(
                    "${AppDestinations.ProductDetails.route}/${product.id}"
                )
            },
            onNavigateToCheckout = {
                navController.navigate(AppDestinations.Checkout.route)
            },
        )
    }
}