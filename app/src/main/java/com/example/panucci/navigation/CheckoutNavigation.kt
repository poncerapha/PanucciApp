package com.example.panucci.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.panucci.sampledata.sampleProducts
import com.example.panucci.ui.screens.CheckoutScreen

private const val checkoutRoute = "checkout"

fun NavGraphBuilder.checkoutScreen(navController: NavHostController) {
    composable(checkoutRoute) {
        CheckoutScreen(
            products = sampleProducts,
            onPopBackStack = {
                navController.navigateUp()
            },
        )
    }
}

fun NavController.navigateToCheckout(){
    navigate(checkoutRoute)
}