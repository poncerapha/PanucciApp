package com.example.panucci.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.panucci.sampledata.sampleProducts
import com.example.panucci.ui.screens.DrinksListScreen

private const val DRINKS_ROUTE = "drinks"

fun NavGraphBuilder.drinksScreen(navController: NavHostController) {
    composable(AppDestinations.Drinks.route) {
        DrinksListScreen(
            products = sampleProducts,
            onNavigateToDetails = { product ->
                navController.navigateToProductDetails(product.id)
            },
        )
    }
}

fun NavController.navigateToDrinks() {
    navigate(DRINKS_ROUTE)
}