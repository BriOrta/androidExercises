package com.example.project1

// Processes all of the incoming info (from API -- retrofit or from DB)
class RecipeRepository(private val recipeDao: RecipeDao) {
    // Room executes all queries on a separate thread.
    // Observed Flow will notify the observer when the data has changed.
    //val allRecipes: List<Recipe> = recipeDao.getRecipes()

    // By default Room runs suspend queries off the main thread, therefore, we don't need to
    // implement anything else to ensure we're not doing long running database work
    // off the main thread.
    fun insert(recipe: Recipe) {
        recipeDao.insert(recipe)
    }

    fun insertAll(recipes: List<Recipe>) {
        recipeDao.insertAll(recipes)
    }
}