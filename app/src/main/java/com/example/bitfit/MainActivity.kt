package com.example.bitfit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import java.util.Date


const val item_name = "TODAY_RUN"

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val et_distance : EditText = findViewById(R.id.etDistance)
        val et_speed : EditText = findViewById(R.id.etSpeed)
        val btn_submit : Button = findViewById(R.id.btnSubmit)
        val btn_check : Button = findViewById(R.id.btnCheck)

        var distance : Float = 0F
        var speed : Int = 0

        var d = Date()
        var today = d.date.toString()+"-"+d.month.toString()+ "-" + d.year.toString()

        btn_submit.setOnClickListener(){
            distance = et_distance.text.toString().toFloat()
            speed = et_speed.text.toString().toInt()
            val currentRun = RunningData(today,distance,speed)
            et_distance.text.clear()
            et_speed.text.clear()
            val intent = Intent(this, CalendarActivity::class.java)
            intent.putExtra(item_name,currentRun)
            //save the current item into the database
            startActivity(intent)
        }

        //button to check the listing without adding a new one in
        btn_check.setOnClickListener(){
            val intent = Intent(this, CalendarActivityNoAdd::class.java)
            startActivity(intent)
        }
    }
}