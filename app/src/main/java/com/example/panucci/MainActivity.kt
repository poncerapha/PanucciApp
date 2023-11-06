package com.example.panucci

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PointOfSale
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.panucci.navigation.AppDestinations
import com.example.panucci.navigation.bottomAppBarItems
import com.example.panucci.sampledata.sampleProducts
import com.example.panucci.ui.components.BottomAppBarItem
import com.example.panucci.ui.components.PanucciBottomAppBar
import com.example.panucci.ui.screens.CheckoutScreen
import com.example.panucci.ui.screens.DrinksListScreen
import com.example.panucci.ui.screens.HighlightsListScreen
import com.example.panucci.ui.screens.MenuListScreen
import com.example.panucci.ui.screens.ProductDetailsScreen
import com.example.panucci.ui.theme.PanucciTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            val backStackEntryState by navController.currentBackStackEntryAsState()
            val currentDestination = backStackEntryState?.destination
            PanucciTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val selectedItem by remember(currentDestination) {
                        val item = currentDestination?.let { destination ->
                            bottomAppBarItems.find {
                                it.destination.route == destination.route
                            }
                        } ?: bottomAppBarItems.first()
                        mutableStateOf(item)
                    }
                    val containsInBottomAppBarItems = currentDestination?.let { destination ->
                        bottomAppBarItems.find {
                            it.destination.route == destination.route
                        }
                    } != null
                    val isShowFab = when (currentDestination?.route) {
                        AppDestinations.Menu.route,
                        AppDestinations.Drinks.route -> true
                        else -> false
                    }
                    PanucciApp(
                        bottomAppBarItemSelected = selectedItem,
                        onBottomAppBarItemSelectedChange = {
                            val route = it.destination.route
                            navController.navigate(route) {
                                launchSingleTop = true
                                popUpTo(route)
                            }
                        },
                        shouldShowTopBar = containsInBottomAppBarItems,
                        shouldShowBottomBar = containsInBottomAppBarItems,
                        shouldShowFab = isShowFab,
                        onFabClick = {
                            navController.navigate(
                                AppDestinations.Checkout.route
                            )
                        }) {
                        NavHost(
                            navController = navController,
                            startDestination = AppDestinations.Highlights.route
                        ) {
                            composable(AppDestinations.Highlights.route) {
                                HighlightsListScreen(
                                    products = sampleProducts,
                                    onProductClick = {
                                        navController.navigate(
                                            AppDestinations.ProductDetails.route
                                        )
                                    },
                                    onOrderClick = {
                                        navController.navigate(
                                            AppDestinations.Checkout.route
                                        )
                                    }
                                )
                            }
                            composable(AppDestinations.Menu.route) {
                                MenuListScreen(
                                    products = sampleProducts,
                                    onNavigationToProductDetails = {
                                        navController.navigate(
                                            AppDestinations.ProductDetails.route
                                        )
                                    }
                                )
                            }
                            composable(AppDestinations.Drinks.route) {
                                DrinksListScreen(
                                    products = sampleProducts,
                                    onNavigationToProductDetails = {
                                        navController.navigate(
                                            AppDestinations.ProductDetails.route
                                        )
                                    }
                                )
                            }
                            composable(AppDestinations.ProductDetails.route) {
                                ProductDetailsScreen(
                                    product = sampleProducts.random(),
                                    onNavigationToCheckout = {
                                        navController.navigate(
                                            AppDestinations.Checkout.route
                                        )
                                    }
                                )
                            }
                            composable(AppDestinations.Checkout.route) {
                                CheckoutScreen(products = sampleProducts)
                            }
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PanucciApp(
    bottomAppBarItemSelected: BottomAppBarItem = bottomAppBarItems.first(),
    onBottomAppBarItemSelectedChange: (BottomAppBarItem) -> Unit = {},
    onFabClick: () -> Unit = {},
    shouldShowTopBar: Boolean = false,
    shouldShowBottomBar: Boolean = false,
    shouldShowFab: Boolean = false,
    content: @Composable () -> Unit
) {
    Scaffold(
        topBar = {
            if (shouldShowTopBar) {
                CenterAlignedTopAppBar(
                    title = {
                        Text(text = "Ristorante Panucci")
                    },
                )
            }
        },
        bottomBar = {
            if (shouldShowBottomBar) {
                PanucciBottomAppBar(
                    item = bottomAppBarItemSelected,
                    items = bottomAppBarItems,
                    onItemChange = onBottomAppBarItemSelectedChange,
                )
            }
        },
        floatingActionButton = {
            if (shouldShowFab) {
                FloatingActionButton(
                    onClick = onFabClick
                ) {
                    Icon(
                        Icons.Filled.PointOfSale,
                        contentDescription = null
                    )
                }
            }
        }
    ) {
        Box(
            modifier = Modifier.padding(it)
        ) {
            content()
        }
    }
}

@Preview
@Composable
private fun PanucciAppPreview() {
    PanucciTheme {
        Surface {
            PanucciApp {}
        }
    }
}