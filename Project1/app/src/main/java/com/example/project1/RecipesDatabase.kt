package com.example.project1

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    version = 1,
    entities = [Recipe::class]
)
abstract class RecipesDatabase : RoomDatabase() {
    companion object {
        private const val DB_NAME = "RecipesDatabase"
        private var INSTANCE: RecipesDatabase? = null

        /***
         * Initializes the database and returns its instance.
         *
         * @param context The application context used to initialized internal components.
         * @return Instance of [RecipesDatabase].
         */
        fun createInstance(context: Context): RecipesDatabase {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context,
                    RecipesDatabase::class.java,
                    DB_NAME
                )
                    .fallbackToDestructiveMigration().build().also { INSTANCE = it }
            }
        }

        fun getInstance() = INSTANCE
    }

    abstract fun recipeDao(): RecipeDao

    /*
    private class RecipeCallback(private val scope: CoroutineScope) : RoomDatabase.Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            db as RecipesDatabase
            scope.launch {
                populateDatabase(db.recipeDao())
            }
        }

        fun populateDatabase(recipeDao: RecipeDao) {
            // Delete all content here.
            recipeDao.deleteAll()

            recipeDao.insert(Recipe("FakeRecipe","FakeDescription","FakeCarbos","FakeProteins","FakeCalories"))
        }
    }*/

}
