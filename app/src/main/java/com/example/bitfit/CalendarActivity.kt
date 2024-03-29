package com.example.bitfit

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch



class CalendarActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calendar)

        var rv_calendar = findViewById<RecyclerView>(R.id.rv_calendar)
        val adapter = CalendarAdapter(this, runs)
        val deleteButton = findViewById<Button>(R.id.btn_delete)


        lifecycleScope.launch {
            (application as BitFitApplication).db.bitFitDao().getAll().collect { databaseList ->
                databaseList.map { entity ->
                    RunningData(
                        entity.date,
                        entity.distance,
                        entity.speed
                    )
                }.also { mappedList ->
                    runs.clear()
                    runs.addAll(mappedList)
                    adapter.notifyDataSetChanged()
                }
            }
        }
        rv_calendar.adapter = adapter
        rv_calendar.layoutManager = LinearLayoutManager(this)

        lifecycleScope.launch(Dispatchers.IO) {
            (application as BitFitApplication).db.bitFitDao().deleteAll()
            (application as BitFitApplication).db.bitFitDao().insertAll(runs.map {
                RunningEntity(
                    date = it.date,
                    distance = it.distance,
                    speed = it.speed
                )
            })
        }

        deleteButton.setOnClickListener(){
            lifecycleScope.launch(Dispatchers.IO) {
                (application as BitFitApplication).db.bitFitDao().deleteAll()
            }
            runs.clear()
            adapter.notifyDataSetChanged()
        }
    }
}