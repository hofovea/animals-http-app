package com.example.animalhttpapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    private lateinit var animalsListFragment: AnimalsListFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        animalsListFragment = AnimalsListFragment()
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.animals_fragment_container, animalsListFragment)
            commit()
        }
    }
}