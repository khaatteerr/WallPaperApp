package com.example.retromvvm.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.retromvvm.R
import com.example.retromvvm.databinding.ActivityDownloadBinding
import com.example.retromvvm.ui.fragments.BottomSheetFragment
import com.example.retromvvm.utils.Constants



class DownloadActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDownloadBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDownloadBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.hide()

        loadWall()
        addCallBacks()
        bottomSheet()


    }

    private fun loadWall() {
        val wallUrl = intent.extras?.getString(Constants.DOWNLOADWALL)
        Glide.with(this)
            .load(wallUrl)
            .centerCrop()
            .error(R.drawable.cat)
            .into(binding.downloadImageView)

    }
    private fun addCallBacks(){
        binding.backButton.setOnClickListener {
            finish()
        }
    }

    private fun bottomSheet(){

        val bottomSheetFragment = BottomSheetFragment()
        binding.downloadButton.setOnClickListener {
            bottomSheetFragment.show(supportFragmentManager,"bottomSheetDialog")
        }

    }

}
