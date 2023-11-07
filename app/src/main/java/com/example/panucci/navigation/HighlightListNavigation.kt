package com.example.panucci.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.panucci.sampledata.sampleProducts
import com.example.panucci.ui.screens.HighlightsListScreen

const val HIGHLIGHTS_LIST_ROUTE = "highlights"

fun NavGraphBuilder.highlightsListScreen(navController: NavHostController) {
    composable(AppDestinations.Highlights.route) {
        HighlightsListScreen(
            products = sampleProducts,
            onNavigateToDetails = { product ->
                navController.navigateToProductDetails(product.id)
            },
            onNavigateToCheckout = {
                navController.navigate(AppDestinations.Checkout.route)
            },
        )
    }
}

fun NavController.navigateToHighlightList() {
    navigate(HIGHLIGHTS_LIST_ROUTE)
}