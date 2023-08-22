package com.example.project1

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        /*val viewModel: MainViewModel by viewModels {
            MainViewModelFactory((application as RecipeApplication).repository)
        }*/

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        val recipeAdapter = Adapter()
        recyclerView.adapter = recipeAdapter
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        viewModel.liveDataRecipes.observe(this) {
            recipeAdapter.submitList(it)
        }

    }
}