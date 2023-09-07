package com.example.project1

import androidx.room.*

@Dao
// Data Access Object
interface RecipeDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(recipe: Recipe)

    @Update(onConflict = OnConflictStrategy.IGNORE)
    fun update(recipe: Recipe)

    @Query(
        "DELETE FROM recipe"
    )
    fun deleteAll()

    /***
     * Retrieves a list of [Recipe] entries.
     *
     * @return The list of [Recipe] entries.
     */
    @Query(
        "SELECT * FROM recipe " +
                "ORDER BY iddd ASC"
    )
    fun getRecipes(): List<Recipe>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(recipes: List<Recipe>)
}
