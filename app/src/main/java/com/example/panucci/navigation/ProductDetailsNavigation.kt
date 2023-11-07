package com.example.panucci.navigation

import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.panucci.sampledata.sampleProducts
import com.example.panucci.ui.screens.ProductDetailsScreen

fun NavGraphBuilder.productDetailsScreen(navController: NavHostController) {
    composable(
        "${AppDestinations.ProductDetails.route}/{productId}"
    ) { backStackEntry ->
        val id = backStackEntry.arguments?.getString("productId")
        sampleProducts.find {
            it.id == id
        }?.let { product ->
            ProductDetailsScreen(
                product = product,
                onNavigationToCheckout = {
                    navController.navigate(AppDestinations.Checkout.route)
                },
            )
        } ?: LaunchedEffect(Unit) {
            navController.navigateUp()
        }
    }
}