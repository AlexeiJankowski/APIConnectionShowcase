package com.example.apiconnectionshowcase.data

class CartRepositoryImpl(
    private val api: ShopAPI
): CartRepository {
    override fun insertCartItem(cartItem: Cart) {
        TODO("Not yet implemented")
    }

    override fun getAllCartItems(): List<Cart> {
        TODO("Not yet implemented")
    }

    override fun updateQuantity(productId: Long, quantity: Int) {
        TODO("Not yet implemented")
    }

    override fun deleteCartItem(productId: Long) {
        TODO("Not yet implemented")
    }

    override fun clearCart() {
        TODO("Not yet implemented")
    }

    override suspend fun getCartByUserId(userId: Int): Cart? {
        val response = api.getAllCarts()
        if (response.isSuccessful) {
            return response.body()?.find { it.userId == userId }
        }
        return null
    }

    override fun updateCart(userId: Int, cartId: Int) {
        TODO("Not yet implemented")
    }

}