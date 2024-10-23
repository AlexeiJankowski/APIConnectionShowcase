package com.example.apiconnectionshowcase

import com.example.apiconnectionshowcase.data.Product

data class ProductUIState(
    val isLoading: Boolean = false,
    val category: String? = null,
    val selectedProduct: Product? = null,
    val error: String? = null
)