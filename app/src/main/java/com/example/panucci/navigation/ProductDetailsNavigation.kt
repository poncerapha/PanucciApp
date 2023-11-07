package com.example.panucci.navigation

import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.panucci.sampledata.sampleProducts
import com.example.panucci.ui.screens.ProductDetailsScreen

private const val PRODUCT_DETAILS_ROUTE = "productDetails"
private const val PRODUCT_ID_ARGUMENT = "productId"

fun NavGraphBuilder.productDetailsScreen(navController: NavHostController) {
    composable(
        "$PRODUCT_DETAILS_ROUTE/{$PRODUCT_ID_ARGUMENT}"
    ) { backStackEntry ->
        val id = backStackEntry.arguments?.getString(PRODUCT_ID_ARGUMENT)
        sampleProducts.find {
            it.id == id
        }?.let { product ->
            ProductDetailsScreen(
                product = product,
                onNavigateToCheckout = {
                    navController.navigateToCheckout()
                },
            )
        } ?: LaunchedEffect(Unit) {
            navController.navigateUp()
        }
    }
}

fun NavController.navigateToProductDetails(id: String){
    navigate("$PRODUCT_DETAILS_ROUTE/$id")
}