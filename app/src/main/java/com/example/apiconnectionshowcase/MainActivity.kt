package com.example.apiconnectionshowcase

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.rememberNavController
import com.example.apiconnectionshowcase.navigation.AppNavigation
import com.example.apiconnectionshowcase.navigation.Screens
import com.example.apiconnectionshowcase.ui.theme.APIConnectionShowcaseTheme
import com.example.apiconnectionshowcase.viewmodel.CategoryViewModel
import com.example.apiconnectionshowcase.viewmodel.ProductViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            APIConnectionShowcaseTheme {
                val navController = rememberNavController()
                val productViewModel: ProductViewModel = koinViewModel()
                val drawerState = rememberDrawerState(DrawerValue.Closed)
                val scope = rememberCoroutineScope()

                val categoryViewModel: CategoryViewModel = koinViewModel()
                val categoryUIState by categoryViewModel.categoryUIState.collectAsStateWithLifecycle()

                ModalNavigationDrawer(
                    drawerState = drawerState,
                    drawerContent = {
                        ModalDrawerSheet {
                            Text("Categories", modifier = Modifier.padding(16.dp))
                            HorizontalDivider()
                            LazyColumn {
                                items(categoryUIState.categories) { category ->
                                    NavigationDrawerItem(
                                        label = { Text(text = category.toUpperCase()) },
                                        selected = false,
                                        onClick = {
                                            navController.navigate(Screens.ProductsScreen.route)
                                            productViewModel.getProducts(category)
                                            scope.launch {
                                                drawerState.close()
                                            }
                                        }
                                    )
                                }
                            }
                        }
                    }
                ) {
                    Scaffold(
                        modifier = Modifier.fillMaxSize(),
                        topBar = {
                            TopAppBar(
                                title = {
                                    Text(
                                        text = "ShopTy",
                                        modifier = Modifier.clickable {
                                            productViewModel.getProducts()
                                            navController.navigate(Screens.ProductsScreen.route)
                                        }
                                    )
                                },
                                navigationIcon = {
                                    IconButton(onClick = {
                                        scope.launch {
                                            drawerState.open()
                                        }
                                    }) {
                                        Icon(Icons.Default.Menu, contentDescription = "Burger Menu")
                                    }
                                },
                                actions = {
                                    IconButton(onClick = {
                                        navController.navigate(Screens.CartScreen.route)
                                    }) {
                                        Icon(Icons.Default.ShoppingCart, contentDescription = "Cart")
                                    }
                                    IconButton(onClick = {
                                        navController.navigate(Screens.FavoriteScreen.route)
                                    }) {
                                        Icon(Icons.Default.Favorite, contentDescription = "Favorite")
                                    }
                                },
                                colors = TopAppBarDefaults.topAppBarColors(
                                    containerColor = MaterialTheme.colorScheme.primary,
                                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                                )
                            )
                        }
                    ) { innerPadding ->
                        AppNavigation(
                            productViewModel = productViewModel,
                            modifier = Modifier.padding(innerPadding),
                            navHostController = navController
                        )
                    }
                }
            }
        }
    }
}