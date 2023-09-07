package com.example.project1

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// Link backend with UI -- (get info from repo to show to the view)
class MainViewModel : ViewModel() {

    val liveDataRecipes = MutableLiveData<List<Recipe>>()
    lateinit var recipes: List<Recipe>

    private val repository = RecipeRepository(RecipesDatabase.getInstance()!!.recipeDao())

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
        CoroutineScope(Dispatchers.IO).launch {
            recipes = retrofitInterface.getRecipes()
            liveDataRecipes.postValue(recipes)
            repository.insertAll(recipes)
            // Checking the results
            /*for (recipe in recipes) {
                Log.d("Recipe: ", recipe.toString())
            }*/
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