package com.example.apiconnectionshowcase.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.apiconnectionshowcase.viewmodel.UserViewModel
import com.example.apiconnectionshowcase.viewmodel.ProductViewModel
import com.example.apiconnectionshowcase.views.Cart
import com.example.apiconnectionshowcase.views.FavoriteList
import com.example.apiconnectionshowcase.views.Login
import com.example.apiconnectionshowcase.views.ProductDetails
import com.example.apiconnectionshowcase.views.ProductList

@Composable
fun AppNavigation(
    userViewModel: UserViewModel,
    productViewModel: ProductViewModel,
    navHostController: NavHostController = rememberNavController(),
    modifier: Modifier
) {
    val context = LocalContext.current

    NavHost(
        navController = navHostController,
        startDestination = Screens.LoginScreen.route
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
                userViewModel = userViewModel,
                onProductClicked = {
                    navHostController.navigate(Screens.ProductDetailsScreen.route)
                }
            )
        }
        composable(Screens.LoginScreen.route) {
            val loginResult by userViewModel.loginResult

            Login(
                onLoginClick = { username, password ->
                    userViewModel.login(username, password)
                },
                loginResult = loginResult,
                onLoginResultHandled = { userViewModel.resetLoginResult() },
                navHostController = navHostController,
                modifier = modifier
            )
        }
    }
}