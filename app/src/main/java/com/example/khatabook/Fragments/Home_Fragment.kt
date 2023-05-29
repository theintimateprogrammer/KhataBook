package com.example.khatabook.Fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.khatabook.R
import com.example.khatabook.databinding.FragmentHomeBinding
import com.example.khatabook.income


class Home_Fragment : Fragment() {

  lateinit var binding: FragmentHomeBinding




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentHomeBinding.inflate(layoutInflater)
        binding.income.setOnClickListener{

            val intent = Intent (context,income ::class.java)
            startActivity(intent)

        }
        return  binding.root

    }

    fun newintance (param1 : String,param2:String)=Home_Fragment().apply {

    }

    }
