package com.example.jetpack1.ui.viewmodel

import com.data.model.Meal

sealed interface RecipeViewState {
    object Loading: RecipeViewState
    data class Success(val recipes: List<Meal>): RecipeViewState
    data class Error(val message: String): RecipeViewState

}