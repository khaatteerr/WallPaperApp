package com.example.retromvvm.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.retromvvm.R

class SearchActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_acivity)

        supportActionBar?.hide()

    }
}