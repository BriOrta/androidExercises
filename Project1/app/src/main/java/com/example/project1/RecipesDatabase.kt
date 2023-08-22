package com.example.project1

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

private var INSTANCE: RecipesDatabase? = null

@Database(
    version = 1,
    entities = [Recipe::class]
)
abstract class RecipesDatabase : RoomDatabase() {
    companion object {
        private const val DB_NAME = "RecipesDatabase"

        /***
         * Initializes the database and returns its instance.
         *
         * @param context The application context used to initialized internal components.
         * @return Instance of [RecipesDatabase].
         */
        fun createInstance(context: Context, scope: CoroutineScope): RecipesDatabase {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context,
                    RecipesDatabase::class.java,
                    DB_NAME
                )
                    .fallbackToDestructiveMigration().addCallback(RecipeCallback(scope)).build().also { INSTANCE = it }
            }
        }
    }

    abstract fun recipeDao(): RecipeDao

}

private class RecipeCallback(private val scope: CoroutineScope) : RoomDatabase.Callback() {

    override fun onCreate(db: SupportSQLiteDatabase) {
        super.onCreate(db)
        INSTANCE?.let { database ->
            scope.launch {
                populateDatabase(database.recipeDao())
            }
        }
    }

    suspend fun populateDatabase(recipeDao: RecipeDao) {
        // Delete all content here.
        recipeDao.deleteAll()

        recipeDao.insert(Recipe("FakeRecipe","FakeDescription","FakeCarbos","FakeProteins","FakeCalories"))
    }
}
