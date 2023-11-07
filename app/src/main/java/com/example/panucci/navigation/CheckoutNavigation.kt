package com.example.panucci.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.panucci.sampledata.sampleProducts
import com.example.panucci.ui.screens.CheckoutScreen

private const val CHECKOUT_ROUTE = "checkout"

fun NavGraphBuilder.checkoutScreen(navController: NavHostController) {
    composable(AppDestinations.Checkout.route) {
        CheckoutScreen(
            products = sampleProducts,
            onPopBackStack = {
                navController.navigateUp()
            },
        )
    }
}

fun NavController.navigateToCheckout() {
    navigate(CHECKOUT_ROUTE)
}