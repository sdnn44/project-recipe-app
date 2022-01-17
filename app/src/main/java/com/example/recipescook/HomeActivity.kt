package com.example.recipescook

import android.content.Intent
import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recipescook.Adapter.CustomAdapter
import com.example.recipescook.Database.AppDatabase
import com.example.recipescook.Entity.Recipe

class HomeActivity : AppCompatActivity() {
    lateinit var recipeList: ArrayList<Recipe>
    lateinit var database: AppDatabase
    lateinit var recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_layout)

        val actionbar = supportActionBar
        actionbar?.setDisplayHomeAsUpEnabled(true)

        database = AppDatabase.getDatabase(this)
        recipeList = database.recipeDao().getAll() as ArrayList<Recipe>

        recyclerView = findViewById(R.id.recyclerView)
        val adapter = CustomAdapter(recipeList, this) { position -> onListItemClick(position) }

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

    }

    private fun onListItemClick(position: Int) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("id", recipeList[position].id)
        intent.putExtra("position", position)
//        Toast.makeText(this, position.toString(), Toast.LENGTH_SHORT).show()

        startActivityForResult(intent, 1)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                Toast.makeText(
                    this,
                    resources.getString(R.string.toast_for_delete_recipe) + data!!.getStringExtra("name"),
                    Toast.LENGTH_SHORT
                ).show()
                database.recipeDao().deleteRecipeById(data.getIntExtra("id", -1))
                val position = data.getIntExtra("position", -1)
                recipeList.removeAt(position)
                recyclerView.adapter?.notifyItemRemoved(position)
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}