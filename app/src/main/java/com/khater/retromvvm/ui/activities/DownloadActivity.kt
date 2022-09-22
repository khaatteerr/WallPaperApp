package com.khater.retromvvm.ui.activities

import android.app.Activity
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.drawable.toDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.khater.retromvvm.R
import com.khater.retromvvm.databinding.ActivityDownloadBinding

import com.khater.retromvvm.ui.fragments.BottomSheetFragment
import com.khater.retromvvm.utils.BlurHashDecoder
import com.khater.retromvvm.utils.Constants


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
            .placeholder(blurHashAsDrawable?.toDrawable(this.resources))
            .error(blurHashAsDrawable)
            .into(binding.downloadImageView)

        binding.constraintDownload.background = BitmapDrawable(this.resources, blurHashAsDrawable)

    }

    private fun addCallBacks() {
        binding.backButton.setOnClickListener {
            finish()
        }
    }

    private fun bottomSheet() {

        val bottomSheetFragment = BottomSheetFragment()
        binding.downloadButton.setOnClickListener {
            bottomSheetFragment.show(supportFragmentManager, "bottomSheetDialog")
        }

    }

}
