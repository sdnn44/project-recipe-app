package com.example.recipescook

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.room.RoomDatabase
import com.example.recipescook.Database.AppDatabase
import com.example.recipescook.Entity.Recipe

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        this.supportActionBar?.hide()
        initListeners()

    }

    private fun initListeners() {
        val buttonRecipes = findViewById<Button>(R.id.btnRecipes)
        val buttonAddRecipe = findViewById<Button>(R.id.btnAddRecipes)
        val buttonCalculator = findViewById<Button>(R.id.btnCalculator)
        val buttonAbout = findViewById<Button>(R.id.btnAbout)

        buttonRecipes.setOnClickListener(buttonRecipesListener)
        buttonAddRecipe.setOnClickListener(buttonAddRecipeListener)
        buttonCalculator.setOnClickListener(buttonCalculatorListener)
        buttonAbout.setOnClickListener(buttonAboutListener)
    }

    private val buttonRecipesListener = View.OnClickListener { callRecipes() }
    private val buttonAddRecipeListener = View.OnClickListener { callAddRecipe() }
    private val buttonCalculatorListener = View.OnClickListener { callCalculator() }
    private val buttonAboutListener = View.OnClickListener { callAbout() }

    private fun callRecipes() {
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
//        finish()
    }

    private fun callAddRecipe() {
        val intent = Intent(this, AddRecipeActivity::class.java)
        startActivity(intent)
//        finish()
    }

    private fun callCalculator() {
        val intent = Intent(this, CalculatorActivity::class.java)
        startActivity(intent)
//        finish()
    }
    private fun callAbout() {
        val intent = Intent(this, AboutActivity::class.java)
        startActivity(intent)
//        finish()
    }
}