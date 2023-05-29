package com.example.khatabook

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.khatabook.Fragments.Budget_Fragment
import com.example.khatabook.Fragments.Home_Fragment
import com.example.khatabook.Fragments.More_Fragment
import com.example.khatabook.Fragments.Stats_Fragment
import com.example.khatabook.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationItemView



class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding  =ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        var Types = arrayOf("home","budget","stats","more")
        var Fragment = arrayOf(Home_Fragment(),Budget_Fragment(),Stats_Fragment(),More_Fragment())

        binding.bnview.setOnNavigationItemReselectedListener { object : BottomNavigationItemView. }    }
}
