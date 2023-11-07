package com.example.panucci.navigation

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.example.panucci.model.Product
import com.example.panucci.ui.screens.MenuListScreen
import com.example.panucci.ui.viewmodel.MenuViewModel

internal const val menuRoute = "menu"

fun NavGraphBuilder.menuScreen(
    onNavigateToProductDetails: (Product) -> Unit
) {
    composable(menuRoute) {
        val viewModel = viewModel<MenuViewModel>()
        val uiState by viewModel.uiState.collectAsState()
        MenuListScreen(
            uiState = uiState,
            onProductClick = onNavigateToProductDetails,
        )
    }
}

fun NavController.navigateToMenu(
    navOptions: NavOptions? = null
) {
    navigate(menuRoute, navOptions)
}