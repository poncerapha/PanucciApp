package com.example.panucci.navigation

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.example.panucci.ui.screens.DrinksListScreen
import com.example.panucci.ui.viewmodel.DrinksViewModel

internal const val drinksRoute = "drinks"

fun NavGraphBuilder.drinksScreen(navController: NavHostController) {
    composable(drinksRoute) {
        val viewModel = viewModel<DrinksViewModel>()
        val uiState by viewModel.uiState.collectAsState()
        DrinksListScreen(
            uiState = uiState,
            onNavigateToDetails = { product ->
                navController.navigateToProductDetails(product.price.toPlainString())
            },
        )
    }
}

fun NavController.navigateToDrinks(
    navOptions: NavOptions? = null
){
    navigate(drinksRoute, navOptions)
}