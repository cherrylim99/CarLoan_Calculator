package com.example.carloan_calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import java.math.RoundingMode

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val calculateButton: Button = findViewById(R.id.buttonCalculate)
        calculateButton.setOnClickListener{calculateLoan()}
        val resetButton: Button = findViewById(R.id.buttonReset)
        resetButton.setOnClickListener{reset()}
    }

    private fun calculateLoan(){
        val carPrice = findViewById<EditText>(R.id.editTextCarPrice)
        val downPayment = findViewById<EditText>(R.id.editTextDownPayment)
        val loanPeriod = findViewById<EditText>(R.id.editTextLoanPeriod)
        val interestRate = findViewById<EditText>(R.id.editTextInterestRate)

        val carLoan: TextView = findViewById(R.id.textViewLoan)
        val interest: TextView = findViewById(R.id.textViewInterest)
        val monthlyPayment: TextView = findViewById(R.id.textViewMonthlyRepayment)

        if(carPrice.text.isNotEmpty() || downPayment.text.isNotEmpty() || loanPeriod.text.isNotEmpty() || interestRate.text.isNotEmpty()){
            carLoan.text = (carPrice.text.toString().toDouble() - downPayment.text.toString().toDouble()).toBigDecimal().setScale(2, RoundingMode.UP).toDouble().toString()
            interest.text = (carLoan.text.toString().toDouble() * (interestRate.text.toString().toDouble() / 100) * loanPeriod.text.toString().toInt()).toBigDecimal().setScale(2, RoundingMode.UP).toDouble().toString()
            monthlyPayment.text = ((carLoan.text.toString().toDouble() + interest.text.toString().toDouble()) / loanPeriod.text.toString().toInt() / 12).toBigDecimal().setScale(2, RoundingMode.UP).toDouble().toString()

            carLoan.visibility = View.VISIBLE
            interest.visibility = View.VISIBLE
            monthlyPayment.visibility = View.VISIBLE
        }
    }

    private fun reset(){
        val carPrice = findViewById<EditText>(R.id.editTextCarPrice)
        val downPayment = findViewById<EditText>(R.id.editTextDownPayment)
        val loanPeriod = findViewById<EditText>(R.id.editTextLoanPeriod)
        val interestRate = findViewById<EditText>(R.id.editTextInterestRate)

        val carLoan: TextView = findViewById(R.id.textViewLoan)
        val interest: TextView = findViewById(R.id.textViewInterest)
        val monthlyPayment: TextView = findViewById(R.id.textViewMonthlyRepayment)

        carPrice.text.clear()
        downPayment.text.clear()
        loanPeriod.text.clear()
        interestRate.text.clear()
        carLoan.text = "Loan : "
        interest.text = "Interest : "
        monthlyPayment.text = "Monthly Repayment : "
    }
}
