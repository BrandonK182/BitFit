package com.example.bitfit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.lifecycleScope
import com.example.bitfit.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.launch
import java.util.Date

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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
                }
            }
        }

        val fragmentManager: FragmentManager = supportFragmentManager

        // define your fragments here
        val calendarFragment: Fragment = CalendarFragment()
        val addFragment: Fragment = AddFragment()

        // Call helper method to swap the FrameLayout with the fragment
        replaceFragment(AddFragment())

        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottom_navigation)

        bottomNavigationView.setOnItemSelectedListener { item ->
            lateinit var fragment: Fragment
            when (item.itemId) {
                R.id.action_add ->
                    fragment = addFragment
                R.id.action_calendar ->
                    fragment = calendarFragment
            }
            replaceFragment(fragment)
            true
        }
        bottomNavigationView.selectedItemId = R.id.action_add
    }

    private fun replaceFragment(aFragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout, aFragment)
        fragmentTransaction.commit()
    }
}