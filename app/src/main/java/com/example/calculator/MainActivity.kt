package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

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

    fun onClear (view: View){
        tvInput.text = ""
        isDecimal = false
        isNumeric = false
    }

    fun onDecimalPoint (view: View) {
        if (isNumeric && !isDecimal) {
            tvInput.append((view as Button).text)
            isDecimal = true
        }
    }
}