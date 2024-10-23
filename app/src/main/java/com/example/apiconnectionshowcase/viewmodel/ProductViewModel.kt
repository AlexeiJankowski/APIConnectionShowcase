package com.example.apiconnectionshowcase.viewmodel

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apiconnectionshowcase.ProductUIState
import com.example.apiconnectionshowcase.ProductsUIState
import com.example.apiconnectionshowcase.data.NetworkResult
import com.example.apiconnectionshowcase.data.Product
import com.example.apiconnectionshowcase.data.ProductRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ProductViewModel(
    private val productRepository: ProductRepository
): ViewModel() {
    val productsUIState = MutableStateFlow(ProductsUIState())
    val productUIState = MutableStateFlow(ProductUIState())

    var cartItems = MutableStateFlow<List<Product>>(emptyList())
    var favoriteItems = MutableStateFlow<List<Product>>(emptyList())

    init {
        getProducts()
    }

    fun getProducts(category: String = "") {
        productsUIState.value = ProductsUIState(isLoading = true)
        viewModelScope.launch {
            when (val result = productRepository.getProducts(category)) {
                is NetworkResult.Success -> {
                    productsUIState.update {
                        it.copy(isLoading = false, products = result.data)
                    }
                }
                is NetworkResult.Error -> {
                    productsUIState.update {
                        it.copy(isLoading = false, error = result.error)
                    }
                }
            }
        }
    }

    fun getFavoriteProducts() {
        productsUIState.update {
            it.copy(isLoading = false, products = favoriteItems.value)
        }
    }

    fun addFavoriteProduct(product: Product) {
        favoriteItems.value += product
    }

    fun getCart() {
        productsUIState.update {
            it.copy(isLoading = false, products = cartItems.value)
        }
    }

    fun addToCart(product: Product) {
        cartItems.value += product
    }

    fun getNumOfProducts(number: Int = 1) {
        productsUIState.value = ProductsUIState(isLoading = true)
        viewModelScope.launch {
            when (val result = productRepository.getLimitedProducts(number)) {
                is NetworkResult.Success -> {
                    productsUIState.update {
                        it.copy(isLoading = false, products = result.data)
                    }
                }
                is NetworkResult.Error -> {
                    productsUIState.update {
                        it.copy(isLoading = false, error = result.error)
                    }
                }
            }
        }
    }

    fun getProduct(id: Int) {
        productUIState.value = ProductUIState(isLoading = true)
        viewModelScope.launch {
            when (val result = productRepository.getProduct(id)) {
                is NetworkResult.Success -> {
                    Log.d("selected product", result.data.description)
                    productUIState.update {
                        it.copy(isLoading = false, selectedProduct = result.data)
                    }
                }
                is NetworkResult.Error -> {
                    productUIState.update {
                        it.copy(isLoading = false, error = result.error)
                    }
                }
            }
        }
    }

    fun onQuantityChange(productId: Int, quantity: Int) {
        productsUIState.update { currentState ->
            val updatedProducts = currentState.products.map { product ->
                if (product.id == productId) {
                    Product(
                        id = product.id,
                        title = product.title,
                        price = product.price,
                        description = product.description,
                        category = product.category,
                        image = product.image,
                        rating = product.rating,
                        quantity = quantity
                    )
                } else {
                    product
                }
            }
            currentState.copy(products = updatedProducts)
        }
    }
}