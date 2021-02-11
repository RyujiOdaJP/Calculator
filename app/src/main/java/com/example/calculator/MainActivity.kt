package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.ArithmeticException
import java.util.function.BinaryOperator

class MainActivity : AppCompatActivity() {

    var isDecimal = false
    var isNumeric = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onDigit(view: View) {
        tvInput.append((view as Button).text)
        isNumeric = true
    }

    fun onClear(view: View) {
        tvInput.text = ""
        isDecimal = false
        isNumeric = false
    }

    fun onDecimalPoint(view: View) {
        if (isNumeric && !isDecimal) {
            tvInput.append((view as Button).text)
            isDecimal = true
        }
    }

    fun onOperator(view: View) {
        if (isNumeric && !isOperatorAdded(tvInput.text.toString())) {
            tvInput.append((view as Button).text)
            isNumeric = false
            isDecimal = false
        }
    }

    fun onEqual(view: View) {
        if (isNumeric) {
            var tvValue = tvInput.text.toString()
            var prefix = ""
            try {
                if (tvValue.startsWith("-")) {
                    prefix = "-"

                    // tvValue convert to strings without minus operator -21 -> 21
                    tvValue = tvValue.substring(1)
                }
                if (tvValue.contains("-")) { //中身に何があるか
                    val splitValue = tvValue.split("-")

                    var baseValue = splitValue[0]
                    var comparingValue = splitValue[1]

                    if (prefix.isNotEmpty()) {
                        baseValue = prefix + baseValue
                    }

                    tvInput.text = (baseValue.toDouble() - comparingValue.toDouble()).toString()
                }
            } catch (e: ArithmeticException) {
                e.printStackTrace()
            }
        }
    }

    private fun isOperatorAdded(value: String): Boolean {
        return if (value.startsWith("-")) false
        else {
            value.contains("/") || value.contains("*")
                    || value.contains("+") || value.contains("-")
        }
    }
}