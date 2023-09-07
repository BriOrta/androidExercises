package com.example.project1

import android.app.Application

class RecipeApplication : Application() {
    // lazy - don't initialize until needed by any part of the app
    //val repository by lazy { RecipeRepository(database.recipeDao()) }

    override fun onCreate() {
        super.onCreate()
        RecipesDatabase.createInstance(this)
    }
}