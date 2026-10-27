package com.example.expensecalculatorapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Find all views by ID
        val etAmount = findViewById<EditText>(R.id.etAmount)
        val etCategory = findViewById<EditText>(R.id.etCategory)
        val btnAdd = findViewById<Button>(R.id.btnAdd)
        val tvTotal = findViewById<TextView>(R.id.tvTotal)
        val llExpenseList = findViewById<LinearLayout>(R.id.llExpenseList)

        // Declare a variable var totalExpense = 0.0
        var totalExpense = 0.0

        // Set OnClickListener on btnAdd
        btnAdd.setOnClickListener {
            val amountText = etAmount.text.toString().trim()
            val category = etCategory.text.toString().trim()

            val amount = amountText.toDoubleOrNull()
            if (amount == null || category.isEmpty()) {
                Toast.makeText(this, "Please enter valid details", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            totalExpense += amount
            tvTotal.text = "Total Expense: ₹${String.format("%.2f", totalExpense)}"

            val expenseTextView = TextView(this)
            expenseTextView.text = "$category - ₹${String.format("%.2f", amount)}"
            expenseTextView.textSize = 16f
            val padding = (8 * resources.displayMetrics.density).toInt().coerceAtLeast(8)
            expenseTextView.setPadding(padding, padding, padding, padding)

            llExpenseList.addView(expenseTextView)

            etAmount.text.clear()
            etCategory.text.clear()
        }
    }
}