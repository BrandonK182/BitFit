package com.example.bitfit

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

private const val TAG = "CalendarFragment"
public var runs = mutableListOf<RunningData>()
class CalendarFragment : Fragment() {
    private lateinit var rv_calendar: RecyclerView
    private lateinit var adapter: CalendarAdapter
    private lateinit var deleteButton: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Change this statement to store the view in a variable instead of a return statement
        val view = inflater.inflate(R.layout.activity_calendar, container, false)

        // Add these configurations for the recyclerView and to configure the adapter
        val layoutManager = LinearLayoutManager(context)
        rv_calendar = view.findViewById<RecyclerView>(R.id.rv_calendar)
        rv_calendar.layoutManager = layoutManager
        rv_calendar.setHasFixedSize(true)
        adapter = CalendarAdapter(view.context, runs)
        rv_calendar.adapter = adapter
        deleteButton = view.findViewById<Button>(R.id.btn_delete)

        deleteButton.setOnClickListener(){
            lifecycleScope.launch(Dispatchers.IO) {
                (activity?.application as BitFitApplication).db.bitFitDao().deleteAll()
            }
            runs.clear()
            adapter.notifyDataSetChanged()
            val toast = Toast.makeText(view.context,"DELETED RUNS",Toast.LENGTH_SHORT)
            toast.show()
        }

        lifecycleScope.launch(Dispatchers.IO) {
            (activity?.application as BitFitApplication).db.bitFitDao().deleteAll()
            (activity?.application as BitFitApplication).db.bitFitDao().insertAll(runs.map {
                RunningEntity(
                    date = it.date,
                    distance = it.distance,
                    speed = it.speed
                )
            })
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