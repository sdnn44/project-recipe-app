package com.example.recipescook.DAO

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.recipescook.Entity.Recipe

@Dao
interface RecipeDao {
    @Query("select * from recipe")
    fun getAll(): List<Recipe>

    //to be determined!
    @Query("select * from recipe where id = :recipeId")
    fun getRecipeById(recipeId: Int): Recipe

    @Query("delete from recipe where id = :recipeId")
    fun deleteRecipeById(recipeId: Int)

    @Insert
    fun insert(recipe: Recipe)

    @Insert
    fun insertRecipes(vararg recipes: Recipe)

    @Delete
    fun delete(recipe: Recipe)
}