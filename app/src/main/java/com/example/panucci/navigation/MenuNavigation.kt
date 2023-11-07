package com.example.panucci.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.panucci.sampledata.sampleProducts
import com.example.panucci.ui.screens.MenuListScreen

private const val MENU_ROUTE = "menu"

fun NavGraphBuilder.menuScreen(navController: NavHostController) {
    composable(AppDestinations.Menu.route) {
        MenuListScreen(
            products = sampleProducts,
            onNavigateToDetails = { product ->
                navController.navigateToProductDetails(product.id)
            },
        )
    }
}

fun NavController.navigateToMenu() {
    navigate(MENU_ROUTE)
}