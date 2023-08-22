package com.example.project1

import androidx.annotation.WorkerThread

class RecipeRepository(private val recipeDao: RecipeDao){
    // Room executes all queries on a separate thread.
    // Observed Flow will notify the observer when the data has changed.
    val allRecipes: List<Recipe> = recipeDao.getRecipes()

    // By default Room runs suspend queries off the main thread, therefore, we don't need to
    // implement anything else to ensure we're not doing long running database work
    // off the main thread.
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(recipe : Recipe) {
        recipeDao.insert(recipe)
    }
}