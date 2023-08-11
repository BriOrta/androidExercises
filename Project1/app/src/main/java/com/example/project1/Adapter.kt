package com.example.project1

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class RecipeViewHolder(private val rootView : View) : RecyclerView.ViewHolder(rootView) {
    // Find views in the layout and assign them the values of each object
    fun bind(recipe : Recipe){
        rootView.findViewById<TextView>(R.id.title).text = recipe.title
        rootView.findViewById<TextView>(R.id.description).text = recipe.description
        rootView.findViewById<TextView>(R.id.carbs).text = recipe.carbs.toString()
        rootView.findViewById<TextView>(R.id.calories).text = recipe.calories.toString()
        rootView.findViewById<TextView>(R.id.proteins).text = recipe.proteins.toString()
    }
}

class Adapter : ListAdapter<Recipe, RecipeViewHolder>(DIFF_RECIPE) {

    // Companion object (static final class) - initialized when app runs. Member of Adapter.
    // We use this to send this parameter to the adapter. If this wasn't a companion object, Adapter would
    // have to be initialized first and we couldn't use it as a parameter.
    companion object{
        // Used to update the values of the list (if contents/items are the same)
        val DIFF_RECIPE = object : DiffUtil.ItemCallback<Recipe>(){
            override fun areItemsTheSame(oldItem: Recipe, newItem: Recipe): Boolean {
                return true
            }

            override fun areContentsTheSame(oldItem: Recipe, newItem: Recipe): Boolean {
                return true
            }
        }
    }

    // View Holder is an item, parent is the recyclerView
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        return RecipeViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_recipe,parent)
        )
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}