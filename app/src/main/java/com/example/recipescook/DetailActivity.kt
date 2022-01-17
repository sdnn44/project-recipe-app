package com.example.recipescook

import android.content.Intent
import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import com.example.recipescook.Database.AppDatabase
import org.w3c.dom.Text

class DetailActivity : AppCompatActivity() {

    lateinit var database: AppDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.recipe_detail)
        this.supportActionBar?.hide()

        val backToPreviousActivityButton = findViewById<ImageButton>(R.id.imgToolbarBtnBack)
        backToPreviousActivityButton.setOnClickListener {
            finish()
        }

        val recipeName = findViewById<TextView>(R.id.recipe_name)
        val recipeIngredients = findViewById<TextView>(R.id.text_view_ingredients)
        val recipeMethod = findViewById<TextView>(R.id.recipe_method)
        val cookingTime = findViewById<TextView>(R.id.recipe_cooking_time)
        val recipeServings = findViewById<TextView>(R.id.recipe_no_servings)
        val recipeCalories = findViewById<TextView>(R.id.recipe_calories)
        val deleteButton = findViewById<ImageButton>(R.id.imgToolbarBtnFav)

        database = AppDatabase.getDatabase(this)

        val intent = intent
        if (intent.hasExtra("id")) {
            val recipePosition = intent.getIntExtra("position",-1)
            val recipeById = database.recipeDao().getRecipeById(intent.getIntExtra("id", 1))
            val recipe_name = recipeById.recipeName
            val recipe_ingredients = recipeById.ingredients
            val recipe_method = recipeById.method
            val cooking_time = recipeById.cookingTime
            val recipe_servings = recipeById.numberOfServings
            val recipe_calories = recipeById.calories
            recipeName.text = recipe_name
            recipeIngredients.text = recipe_ingredients
            recipeMethod.text = recipe_method
            cookingTime.text = cooking_time.toString().plus(" min")
            recipeServings.text = recipe_servings.toString()
            recipeCalories.text = recipe_calories.toString().plus(" kcal")

            deleteButton.setOnClickListener {
                val intent = Intent()
                intent.putExtra("id", recipeById.id)
                intent.putExtra("name", recipe_name)
                intent.putExtra("position",recipePosition)
                setResult(RESULT_OK,intent)
                finish()
            }
        } else {
            val recipe_name = intent.getStringExtra("recipe_name")
            val recipe_ingredients = intent.getStringExtra("recipe_ingredients")
            val recipe_method = intent.getStringExtra("recipe_method")
            val cooking_time = intent.getStringExtra("recipe_cooking_time")
            val recipe_servings = intent.getStringExtra("recipe_servings")
            val recipe_calories = intent.getStringExtra("recipe_calories")
            recipeName.text = recipe_name
            recipeIngredients.text = recipe_ingredients
            recipeMethod.text = recipe_method
            cookingTime.text = cooking_time.plus(" min")
            recipeServings.text = recipe_servings
            recipeCalories.text = recipe_calories.plus(" kcal")
            deleteButton.visibility = View.GONE

            Toast.makeText(this, resources.getString(R.string.toast_for_added_recipe), Toast.LENGTH_SHORT).show()
        }

    }

}