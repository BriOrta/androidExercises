package com.example.project1

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainViewModel() : ViewModel() {

    val liveDataRecipes = MutableLiveData<List<Recipe>>()
    lateinit var recipes: List<Recipe>

    object RetrofitHelper {
        private const val baseUrl =
            "https://hf-android-app.s3-eu-west-1.amazonaws.com/android-test/"

        fun getInstance(): Retrofit {
            return Retrofit.Builder().baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                // we need to add converter factory to
                // convert JSON object to Java object
                .build()
        }
    }

    init {
        val retrofitInterface = RetrofitHelper.getInstance().create(RetrofitInterface::class.java)

        // launching a new coroutine
        viewModelScope.launch {
            recipes = retrofitInterface.getRecipes()
            liveDataRecipes.postValue(recipes)
            // Checking the results
            for (recipe in recipes) {
                //repository.insert(recipe)
                Log.d("Recipe: ", recipe.toString())
            }
        }
    }
}

/*
class MainViewModelFactory(private val repository: RecipeRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MainViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}*/
