package com.khater.retromvvm.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.khater.retromvvm.R
import com.khater.retromvvm.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_RetroMVVM_NoActionbar)
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }


}