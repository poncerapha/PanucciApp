package com.example.panucci.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.example.panucci.sampledata.sampleProducts
import com.example.panucci.ui.screens.MenuListScreen

internal const val menuRoute = "menu"

fun NavGraphBuilder.menuScreen(navController: NavHostController) {
    composable(menuRoute) {
        MenuListScreen(
            products = sampleProducts,
            onNavigateToDetails = { product ->
                navController.navigateToProductDetails(product.id)
            },
        )
    }
}

fun NavController.navigateToMenu(
    navOptions: NavOptions? = null
){
    navigate(menuRoute, navOptions)
}