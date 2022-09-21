package com.example.retromvvm.ui.activities

import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.drawable.toDrawable
import com.bumptech.glide.Glide
import com.example.retromvvm.R
import com.example.retromvvm.databinding.ActivityDownloadBinding
import com.example.retromvvm.ui.fragments.BottomSheetFragment
import com.example.retromvvm.utils.BlurHashDecoder
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


        val wallUrl = intent.extras?.getString(Constants.DOWNLOAD_WALL)
        val blurHash = intent.extras?.getString(Constants.IMAGE_NAME)
       val blurHashAsDrawable = BlurHashDecoder.decode(blurHash)
        Glide.with(this)
            .asBitmap()
            .load(wallUrl)
            .centerCrop()
            .placeholder( blurHashAsDrawable?.toDrawable(this.resources) )
            .error(R.drawable.bluredimage)
            .into(binding.downloadImageView)

        binding.constraintDownload.background = BitmapDrawable(this.resources, blurHashAsDrawable)

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
