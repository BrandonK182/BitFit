package com.example.bitfit

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CalendarAdapter(private val runs: List<RunningData>) : RecyclerView.Adapter<CalendarAdapter.RunViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RunViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.day_layout, parent, false)
        return RunViewHolder(view)
    }

    override fun getItemCount(): Int {
        return runs.size
    }

    override fun onBindViewHolder(holder: RunViewHolder, position: Int) {
        val run = runs[position]

        holder.mItem = run
        holder.tv_date.text = "DATE: " + run.date
        holder.tv_distance.text = "DISTANCE RAN: " + run.distance
        holder.tv_speed.text = "AVERAGE SPEED: " + run.speed
    }

    inner class RunViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        var mItem: RunningData? = null
        var tv_date = mView.findViewById<TextView>(R.id.tv_date)
        var tv_distance = mView.findViewById<TextView>(R.id.tv_distance)
        var tv_speed = mView.findViewById<TextView>(R.id.tv_speed)
    }
}