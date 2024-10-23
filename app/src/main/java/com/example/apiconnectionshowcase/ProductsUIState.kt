package com.example.apiconnectionshowcase

import com.example.apiconnectionshowcase.data.Product

data class ProductsUIState(
    val isLoading: Boolean = false,
    val category: String? = null,
    val products: List<Product> = emptyList(),
    val error: String? = null
)