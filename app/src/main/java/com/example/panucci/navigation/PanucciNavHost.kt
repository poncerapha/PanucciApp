package com.example.panucci.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.panucci.sampledata.sampleProducts
import com.example.panucci.ui.screens.CheckoutScreen
import com.example.panucci.ui.screens.DrinksListScreen
import com.example.panucci.ui.screens.HighlightsListScreen
import com.example.panucci.ui.screens.MenuListScreen
import com.example.panucci.ui.screens.ProductDetailsScreen

@Composable
fun PanucciNavHost(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = AppDestinations.Highlights.route
    ) {
        highlightsListScreen(navController)
        menuScreen(navController)
        drinksScreen(navController)
        productDetailsScreen(navController)
        checkoutScreen(navController)
    }
}