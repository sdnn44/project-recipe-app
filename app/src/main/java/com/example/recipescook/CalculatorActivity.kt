package com.example.recipescook

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View.*
import android.widget.*
import kotlin.math.pow

class CalculatorActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.calculator_layout)

        val actionbar = supportActionBar
        actionbar?.setDisplayHomeAsUpEnabled(true)

        // init

        val weight = findViewById<EditText>(R.id.weight_value)
        val height = findViewById<EditText>(R.id.height_value)

        val calculateButton = findViewById<Button>(R.id.calculate_button)

        val bmiValue = findViewById<TextView>(R.id.bmi_value)
        val bmiStatus = findViewById<TextView>(R.id.bmi_status)
        val bmiResultView = findViewById<LinearLayout>(R.id.bmi_result_view)

        val calculateAgainButton = findViewById<TextView>(R.id.calculate_again)

        calculateButton.setOnClickListener {

//            if (weight.text.toString().isEmpty() || height.text.toString().isEmpty()) {
//                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT)
//                    .show()
//            }

            var weightValue = 0.0
            var heightValue = 0.0

            if (weight.text.toString().isNotEmpty()) {
                weightValue = weight.text.toString().toDouble()
            }
            if (height.text.toString().isNotEmpty()) {
                heightValue = height.text.toString().toDouble() / 100
            }
            if (weightValue > 0.0 && heightValue > 0.0 && weightValue < 200.0 && heightValue < 260.0) {
                val result = weightValue / heightValue.pow(2)
                val bmi = String.format("%.2f", result)
                bmiValue.text = bmi
                bmiStatus.text = calculateValue(result)
                bmiResultView.visibility = VISIBLE
                calculateButton.visibility = GONE
            } else {
                Toast.makeText(this, R.string.toast_for_calculator, Toast.LENGTH_SHORT)
                    .show()
            }
        }
        calculateAgainButton.setOnClickListener {
            bmiResultView.visibility = GONE
            calculateButton.visibility = VISIBLE
            weight.text.clear()
            height.text.clear()
            weight.requestFocus()
        }
    }

    private fun calculateValue(bmi: Double): String {

        return when {
            bmi < 16 -> resources.getString(R.string.calculated_result_underweight_I)
            bmi in 16.0..16.99 -> resources.getString(R.string.calculated_result_underweight_II)
            bmi in 17.0..18.49 -> resources.getString(R.string.calculated_result_underweight_III)
            bmi in 18.5..24.99 -> resources.getString(R.string.calculated_result_normal_range)
            bmi in 25.0..29.99 -> resources.getString(R.string.calculated_result_overweight)
            bmi in 30.0..34.99 -> resources.getString(R.string.calculated_result_obese_I)
            bmi in 35.0..39.99 -> resources.getString(R.string.calculated_result_obese_II)
            else -> resources.getString(R.string.calculated_result_obese_III)
        } // kolory + wyśrodkować status!
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}