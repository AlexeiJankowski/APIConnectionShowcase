package com.example.apiconnectionshowcase.data

interface CartRepository {
     fun insertCartItem(cartItem: Cart)
     fun getAllCartItems(): List<Cart>
     fun updateQuantity(productId: Long, quantity: Int)
     fun deleteCartItem(productId: Long)
     fun clearCart()

     suspend fun getCartByUserId(userId: Int): Cart?
     fun updateCart(userId: Int, cartId: Int)
}