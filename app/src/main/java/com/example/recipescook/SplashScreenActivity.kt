package com.example.recipescook

import android.content.Intent
import android.widget.ProgressBar

import android.os.Bundle

import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.room.RoomDatabase
import com.example.recipescook.Database.AppDatabase
import com.example.recipescook.Entity.Recipe


class SplashScreenActivity : AppCompatActivity() {
    lateinit var database: RoomDatabase
    lateinit var tempList: List<Recipe>
    private var mProgress: ProgressBar? = null
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_screen_layout)
        mProgress = findViewById<View>(R.id.splash_screen_progress_bar) as ProgressBar

        Thread {
            doWork()
            startApp()
            finish()
        }.start()
    }

    private fun doWork() {
        var progress = 0
        database = AppDatabase.getDatabase(this)
        tempList = (database as AppDatabase).recipeDao().getAll()
        while (progress < 20) {
            try {
                Thread.sleep(1000)
            } catch (e: Exception) {
                e.printStackTrace()
            }
            progress += 10
        }
    }

    private fun startApp() {
        val intent = Intent(this@SplashScreenActivity, MainActivity::class.java)
        startActivity(intent)
    }
}