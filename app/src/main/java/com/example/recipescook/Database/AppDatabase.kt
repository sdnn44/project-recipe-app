package com.example.recipescook.Database

import android.content.Context
import android.content.res.Resources
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.recipescook.DAO.RecipeDao
import com.example.recipescook.Entity.Recipe
import com.example.recipescook.R
import java.util.concurrent.Executors

@Database(entities = arrayOf(Recipe::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun recipeDao(): RecipeDao

    companion object {
        @Volatile
        private var instance: AppDatabase? = null
        fun getDatabase(context: Context): AppDatabase = instance ?: synchronized(this) {
            instance ?: buildDB(context).also { instance = it }
        }

        private fun buildDB(context: Context): AppDatabase {
            return Room.databaseBuilder(
                context,
                AppDatabase::class.java,
                "Recipe"
            ).addCallback(object : RoomDatabase.Callback() { // prepopulate database with static content
                override fun onCreate(db: SupportSQLiteDatabase) {
                    Executors.newSingleThreadExecutor().execute {
                        instance?.let {
                            it.recipeDao().insertRecipes(
                                recipes =
                                arrayOf(
                                    Recipe(
                                        context.resources.getString(R.string.prepopulate_recipe_name_2),
                                        context.resources.getString(R.string.prepopulate_recipe_description_2),
                                        context.resources.getString(R.string.prepopulate_recipe_ingredients_2),
                                        context.resources.getString(R.string.prepopulate_recipe_method_2),
                                        5,
                                        40,
                                        400
                                    ),
                                    Recipe(
                                        context.resources.getString(R.string.prepopulate_recipe_name_1),
                                        context.resources.getString(R.string.prepopulate_recipe_description_1),
                                        context.resources.getString(R.string.prepopulate_recipe_ingredients_1),
                                        context.resources.getString(R.string.prepopulate_recipe_method_1),
                                        5,
                                        40,
                                        400
                                    ),
                                    Recipe(
                                        context.resources.getString(R.string.prepopulate_recipe_name_3),
                                        context.resources.getString(R.string.prepopulate_recipe_description_3),
                                        context.resources.getString(R.string.prepopulate_recipe_ingredients_3),
                                        context.resources.getString(R.string.prepopulate_recipe_method_3),
                                        5,
                                        40,
                                        400
                                    )
                                )
                            )
                        }
                    }
                }
            })
                .allowMainThreadQueries().build()
        }
    }
}