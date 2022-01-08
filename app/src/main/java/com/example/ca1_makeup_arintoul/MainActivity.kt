package com.example.ca1_makeup_arintoul

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.ca1_makeup_arintoul.databinding.ActivityMainBinding
import com.google.android.gms.analytics.ecommerce.Product

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
//        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
//
//
//        binding.button.setOnClickListener {
//            viewModel.getProducts()
//        }
    }
}