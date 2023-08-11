package com.example.project1

import retrofit2.http.GET

interface RetrofitInterface {
    @GET("recipes.json")
    suspend fun getRecipes(): List<Recipe>
}