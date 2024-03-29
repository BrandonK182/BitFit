package com.example.bitfit

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.Date

class AddFragment : Fragment() {
    private lateinit var et_distance : EditText
    private lateinit var et_speed : EditText
    private lateinit var btn_submit : Button

    var distance : Float = 0F
    var speed : Int = 0
    private lateinit var currentRun : RunningData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Change this statement to store the view in a variable instead of a return statement
        val view = inflater.inflate(R.layout.fragment_add, container, false)
        val et_distance : EditText = view.findViewById(R.id.etDistance)
        val et_speed : EditText = view.findViewById(R.id.etSpeed)
        val btn_submit : Button = view.findViewById(R.id.btnSubmit)

        var d = Date()
        var today = d.date.toString()+"-"+d.month.toString()+ "-" + d.year.toString()

        btn_submit.setOnClickListener(){
            distance = et_distance.text.toString().toFloat()
            speed = et_speed.text.toString().toInt()
            currentRun = RunningData(today,distance,speed)
            et_distance.text.clear()
            et_speed.text.clear()
            runs.add(currentRun)
            val toast = Toast.makeText(view.context, "SAVED RUN", Toast.LENGTH_SHORT)
            toast.show()
        }

        // Update the return statement to return the inflated view from above
        return view
    }

    companion object {
        fun newInstance(): CalendarFragment {
            return CalendarFragment()
        }
    }
}