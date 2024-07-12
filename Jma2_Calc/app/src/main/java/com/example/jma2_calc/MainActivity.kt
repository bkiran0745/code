package com.example.jma2_calc

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(), View.OnClickListener {
    var but_Add: Button? = null
    var but_Sub: Button? = null
    var but_Mul: Button? = null
    var but_Div: Button? = null
    var txt_Num1: EditText? = null
    var txt_Num2: EditText? = null
    var txt_Res: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        but_Add = findViewById<View>(R.id.butAdd) as Button
        but_Sub = findViewById<View>(R.id.butSub) as Button
        but_Mul = findViewById<View>(R.id.butMul) as Button
        but_Div = findViewById<View>(R.id.butDiv) as Button
        txt_Num1 = findViewById<View>(R.id.txtNum1) as EditText
        txt_Num2 = findViewById<View>(R.id.txtNum2) as EditText
        txt_Res = findViewById<View>(R.id.txtResult1) as TextView
        but_Add!!.setOnClickListener(this)
        but_Sub!!.setOnClickListener(this)
        but_Mul!!.setOnClickListener(this)
        but_Div!!.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        val num1Str = txt_Num1!!.text.toString()
        val num2Str = txt_Num2!!.text.toString()

        if (num1Str.isEmpty() || num2Str.isEmpty()) {
            txt_Res!!.text = "Please enter both numbers"
            return
        }

        val op1 = num1Str.toDouble()
        val op2 = num2Str.toDouble()
        val Res: Double

        when (v.id) {
            R.id.butAdd -> Res = op1 + op2
            R.id.butSub -> Res = op1 - op2
            R.id.butMul -> Res = op1 * op2
            R.id.butDiv -> {
                if (op2 == 0.0) {
                    txt_Res!!.text = "Cannot divide by zero"
                    return
                }
                Res = op1 / op2
            }

            else -> Res = 0.0
        }
        txt_Res!!.text = Res.toString()
    }
}