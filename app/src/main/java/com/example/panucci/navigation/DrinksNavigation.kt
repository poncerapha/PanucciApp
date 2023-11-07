package com.example.panucci.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.panucci.sampledata.sampleProducts
import com.example.panucci.ui.screens.DrinksListScreen

fun NavGraphBuilder.drinksScreen(navController: NavHostController) {
    composable(AppDestinations.Drinks.route) {
        DrinksListScreen(
            products = sampleProducts,
            onNavigateToDetails = { product ->
                navController.navigate(
                    "${AppDestinations.ProductDetails.route}/${product.id}"
                )
            },
        )
    }
}