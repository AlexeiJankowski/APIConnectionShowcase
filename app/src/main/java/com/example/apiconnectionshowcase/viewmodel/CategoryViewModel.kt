package com.example.apiconnectionshowcase.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apiconnectionshowcase.CategoryUIState
import com.example.apiconnectionshowcase.data.CategoryRepository
import com.example.apiconnectionshowcase.data.NetworkResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CategoryViewModel(
    private val categoryRepository: CategoryRepository
): ViewModel() {
    val categoryUIState = MutableStateFlow(CategoryUIState())

    init {
        getCategories()
    }

    private fun getCategories() {
        categoryUIState.value = CategoryUIState(isLoading = true)
        viewModelScope.launch {
            when (val result = categoryRepository.getCategories()) {
                is NetworkResult.Success -> {
                    categoryUIState.update {
                        it.copy(isLoading = false, categories = result.data)
                    }
                }
                is NetworkResult.Error -> {
                    categoryUIState.update {
                        it.copy(isLoading = false, error = result.error)
                    }
                }
            }
        }
    }
}