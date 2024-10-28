package com.example.apiconnectionshowcase.viewmodel

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apiconnectionshowcase.data.Cart
import com.example.apiconnectionshowcase.data.UserRepository
import kotlinx.coroutines.launch

class UserViewModel(
    private val userRepository: UserRepository
): ViewModel() {
    private val _loginResult = mutableStateOf<Boolean?>(null)
    val loginResult: State<Boolean?> = _loginResult

    private val _userId = mutableStateOf<Int?>(null)
    val userId: State<Int?> = _userId

    private val _cart = mutableStateOf<Cart?>(null)
    val cart: State<Cart?> = _cart

    fun login(username: String, password: String) {
        viewModelScope.launch {
            val userId = userRepository.login(username, password)
            _userId.value = userId
            if (_userId.value != null) {
                _loginResult.value = true
            }
        }
    }

    fun resetLoginResult() {
        _loginResult.value = null
    }

    fun getCart() {
        viewModelScope.launch {
            if (_userId.value != null) {
                Log.d("GREAT", "1")
                val cart = userRepository.getCartByUserId(_userId.value!!)
                if (cart != null) {
                    _cart.value = cart
                    Log.d("GREAT", cart.id.toString())
                } else {
                    Log.d("ERROR", "Cart is null for userId: ${_userId.value}")
                }
            }
        }
    }
}