package com.example.recipescook.Entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "Recipe")
data class Recipe(
    @ColumnInfo(name = "recipe_name")
    var recipeName: String?,
    @ColumnInfo(name = "recipe_description")
    var recipeDescription: String?,
    @ColumnInfo(name = "ingredients")
    var ingredients: String?,
    @ColumnInfo(name = "method")
    var method: String?,
    @ColumnInfo(name = "number_of_servings")
    var numberOfServings: Int?,
    @ColumnInfo(name = "cooking_time")
    var cookingTime: Int?,
    @ColumnInfo(name = "calories")
    var calories: Int?
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}

