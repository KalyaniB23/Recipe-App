package com.example.jetpack1.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import com.example.jetpack1.ui.components.ErrorComponent
import com.example.jetpack1.ui.components.LoadingComponent
import com.example.jetpack1.ui.components.SuccessComponent
import com.example.jetpack1.ui.viewmodel.RecipeViewIntent
import com.example.jetpack1.ui.viewmodel.RecipeViewModel
import com.example.jetpack1.ui.viewmodel.RecipeViewState

@Composable
fun HomeScreen(recipeViewModel: RecipeViewModel){
    val state by recipeViewModel.state

    when(state){
        is RecipeViewState.Loading -> LoadingComponent()
        is RecipeViewState.Success -> {
            val recipes = (state as RecipeViewState.Success).recipes
            SuccessComponent(recipes = recipes, onSearchClicked = {query ->
                recipeViewModel.processIntent((RecipeViewIntent.SearchRecipes(query)))
            })
        }
        is RecipeViewState.Error -> {
            val message = (state as RecipeViewState.Error).message
            ErrorComponent(message = message, onRefreshedClicked = {
                recipeViewModel.processIntent((RecipeViewIntent.LoadRandomRecipe))
            })
        }
    }
    LaunchedEffect(Unit) {
        recipeViewModel.processIntent(RecipeViewIntent.LoadRandomRecipe)
    }

}