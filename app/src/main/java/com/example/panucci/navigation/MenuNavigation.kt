package com.example.panucci.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.panucci.sampledata.sampleProducts
import com.example.panucci.ui.screens.MenuListScreen

fun NavGraphBuilder.menuScreen(navController: NavHostController) {
    composable(AppDestinations.Menu.route) {
        MenuListScreen(
            products = sampleProducts,
            onNavigateToDetails = { product ->
                navController.navigate(
                    "${AppDestinations.ProductDetails.route}/${product.id}"
                )
            },
        )
    }
}