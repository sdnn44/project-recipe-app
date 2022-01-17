package com.example.recipescook

import android.content.ClipDescription
import android.content.Context
import android.content.Intent
import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.room.Room
import com.example.recipescook.Database.AppDatabase
import com.example.recipescook.Entity.Recipe
import org.w3c.dom.Text

class AddRecipeActivity : AppCompatActivity() {
    private lateinit var database: AppDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_recipe_layout)
        this.supportActionBar?.hide()

        val backToPreviousActivityButton = findViewById<ImageButton>(R.id.imgToolbarBtnBack)
        backToPreviousActivityButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        val addRecipeButton = findViewById<ImageButton>(R.id.imgToolbarBtnAdd)
        addRecipeButton.setOnClickListener {
            insertDataToDatabase()
        }
        database = AppDatabase.getDatabase(this)
    }

    private fun insertDataToDatabase() {
        val recipeName = findViewById<EditText>(R.id.control_recipe_name)
        val recipeDescription = findViewById<EditText>(R.id.control_recipe_description)
        val recipeIngredients = findViewById<EditText>(R.id.control_recipe_ingredients)
        val recipeMethod = findViewById<EditText>(R.id.control_recipe_method)
        val recipeCookingTime = findViewById<EditText>(R.id.control_recipe_cooking_time)
        val recipeServings = findViewById<EditText>(R.id.control_recipe_no_servings)
        val recipeCalories = findViewById<EditText>(R.id.control_recipe_calories)
        val recipe_name = recipeName.text.toString()
        val recipe_description = recipeDescription.text.toString()
        val recipe_ingredients = recipeIngredients.text.toString()
        val recipe_method = recipeMethod.text.toString()
        val recipe_cooking_time = recipeCookingTime.text
        val recipe_servings = recipeServings.text
        val recipe_calories = recipeCalories.text

        if (inputCheck(
                recipe_name,
                recipe_description,
                recipe_ingredients,
                recipe_method,
                recipe_cooking_time,
                recipe_servings,
                recipe_calories
            )
        ) {
            val recipe = Recipe(
                recipe_name,
                recipe_description,
                recipe_ingredients,
                recipe_method,
                Integer.parseInt(recipe_cooking_time.toString()),
                Integer.parseInt(recipe_servings.toString()),
                Integer.parseInt(recipe_calories.toString())
            )

            database.recipeDao().insert(recipe)

            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("recipe_name", recipe_name)
            intent.putExtra("recipe_description", recipe_description)
            intent.putExtra("recipe_ingredients", recipe_ingredients)
            intent.putExtra("recipe_method", recipe_method)
            intent.putExtra("recipe_cooking_time", recipe_cooking_time.toString())
            intent.putExtra("recipe_servings", recipe_servings.toString())
            intent.putExtra("recipe_calories", recipe_calories.toString())
            startActivity(intent)

            recipeName.text.clear()
            recipeDescription.text.clear()
            recipeIngredients.text.clear()
            recipeMethod.text.clear()
            recipeCookingTime.text.clear()
            recipeServings.text.clear()
            recipeCalories.text.clear()
            recipeName.requestFocus()

        } else {
            Toast.makeText(
                this,
                resources.getString(R.string.toast_for_add_recipe),
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun inputCheck(
        recipeName: String,
        recipeDescription: String,
        recipeIngredients: String,
        recipeMethod: String,
        recipeCookingTime: Editable,
        recipeServings: Editable,
        recipeCalories: Editable
    ): Boolean {
        return !(TextUtils.isEmpty(recipeName) || TextUtils.isEmpty(recipeDescription) || TextUtils.isEmpty(
            recipeIngredients
        ) || TextUtils.isEmpty(recipeMethod) || recipeCookingTime.isEmpty() || recipeServings.isEmpty() || recipeCalories.isEmpty())
    }

//    fun clearInputs(
//        recipe_name: String,
//        recipeDescription: String,
//        recipeIngredients: String,
//        recipeMethod: String,
//        recipeCookingTime: Editable,
//        recipeServings: Editable,
//        recipeCalories: Editable
//    ): Void? {
//        return null
//    }
}