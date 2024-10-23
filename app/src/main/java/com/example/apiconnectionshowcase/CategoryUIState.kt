package com.example.apiconnectionshowcase

import com.example.apiconnectionshowcase.data.Category

data class CategoryUIState(
    val isLoading: Boolean = false,
    val categories: List<String> = emptyList(),
    val error: String? = null
)