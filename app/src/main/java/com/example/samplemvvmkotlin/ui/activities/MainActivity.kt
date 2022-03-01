package com.example.samplemvvmkotlin.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.samplemvvmkotlin.databinding.ActivityMainBinding
import com.example.samplemvvmkotlin.ui.fragments.DashboardFragment

class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity"
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val fragment = DashboardFragment()
        showFragment(fragment)

    }

    public fun showFragment(fragment: Fragment) {
        val fram = supportFragmentManager.beginTransaction()
        fram.replace(binding.fragmentContainer.id, fragment)
        fram.commit()
    }

    override fun onBackPressed() {

    }

}