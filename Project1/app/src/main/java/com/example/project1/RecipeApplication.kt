package com.example.project1

import android.app.Application
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class RecipeApplication : Application() {
    private val aplicationScope = CoroutineScope(SupervisorJob())

    private val database by lazy { RecipesDatabase.createInstance(this, aplicationScope) }
    val repository by lazy { RecipeRepository(database.recipeDao()) }
}