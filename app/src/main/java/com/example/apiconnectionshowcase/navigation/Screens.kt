package com.example.apiconnectionshowcase.navigation

sealed class Screens(val route: String) {
    object ProductsScreen: Screens("products")
    object ProductDetailsScreen: Screens("product_details")
    object CartScreen: Screens("cart")
    object FavoriteScreen: Screens("favorite")
}