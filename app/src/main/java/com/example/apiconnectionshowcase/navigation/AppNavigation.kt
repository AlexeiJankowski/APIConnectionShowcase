package com.example.apiconnectionshowcase.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.apiconnectionshowcase.viewmodel.ProductViewModel
import com.example.apiconnectionshowcase.views.Cart
import com.example.apiconnectionshowcase.views.FavoriteList
import com.example.apiconnectionshowcase.views.ProductDetails
import com.example.apiconnectionshowcase.views.ProductList

@Composable
fun AppNavigation(
    productViewModel: ProductViewModel,
    navHostController: NavHostController = rememberNavController(),
    modifier: Modifier
) {
    NavHost(
        navController = navHostController,
        startDestination = Screens.ProductsScreen.route
    ) {
        composable(Screens.ProductDetailsScreen.route) {
            ProductDetails(
                modifier = modifier,
                productViewModel
            )
        }
        composable(Screens.ProductsScreen.route) {
            ProductList(
                modifier = modifier,
                productViewModel = productViewModel,
                onProductClicked = {
                    navHostController.navigate(Screens.ProductDetailsScreen.route)
                }
            )
        }
        composable(Screens.FavoriteScreen.route) {
            FavoriteList(
                modifier = modifier,
                productViewModel = productViewModel,
                onProductClicked = {
                    navHostController.navigate(Screens.ProductDetailsScreen.route)
                }
            )
        }
        composable(Screens.CartScreen.route) {
            Cart(
                modifier = modifier,
                productViewModel = productViewModel,
                onProductClicked = {
                    navHostController.navigate(Screens.ProductDetailsScreen.route)
                }
            )
        }
    }
}