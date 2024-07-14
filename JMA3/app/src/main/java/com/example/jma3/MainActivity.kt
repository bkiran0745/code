package com.example.jma3

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.lang.String
import kotlin.synchronized

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private var lblCounter: TextView? = null
    private var btnStart: Button? = null
    private var btnStop: Button? = null
    private var counter = 0
    private var running = false
    private var handler: Handler? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lblCounter = findViewById(R.id.lbl_text)
        btnStart = findViewById(R.id.btn_start)
        btnStop = findViewById(R.id.btn_stop)

        if (btnStart != null) {
            btnStart!!.setOnClickListener(this)
        }
        if (btnStop != null) {
            btnStop!!.setOnClickListener(this)
        }

        handler = Handler(Looper.getMainLooper()) { msg ->
            if (lblCounter != null) {
                this.lblCounter!!.setText(String.valueOf(msg.what))
            }
            true
        }
    }

    override fun onClick(v: View) {
        if (v != null) {
            val id = v.id
            if (id == R.id.btn_start) {
                startCounter()
            } else if (id == R.id.btn_stop) {
                stopCounter()
            }
        }
    }

    private fun startCounter() {
        counter = 0
        running = true
        Thread(MyCounter()).start()
    }

    private fun stopCounter() {
        running = false
    }

    private inner class MyCounter : Runnable {
        override fun run() {
            while (running) {
                synchronized(this) {
                    counter++
                }
                handler!!.sendEmptyMessage(counter)
                try {
                    Thread.sleep(1000)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
            }
        }
    }
}