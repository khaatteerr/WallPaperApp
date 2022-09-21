package com.example.retromvvm.ui.fragments

import android.app.DownloadManager
import android.app.WallpaperManager
import android.content.Context
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.retromvvm.R
import com.example.retromvvm.databinding.BottomSheetBinding
import com.example.retromvvm.utils.Constants
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.imageview.ShapeableImageView
import java.io.File
import java.io.IOException


class BottomSheetFragment : BottomSheetDialogFragment() {

    private lateinit var binding: BottomSheetBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = BottomSheetBinding.inflate(inflater, container, false)

        initButtons()
        return binding.root
    }

    private fun initButtons() {

        val wallUrl = activity?.intent?.extras?.getString(Constants.DOWNLOAD_WALL)
        val imageName = activity?.intent?.extras?.getString(Constants.IMAGE_NAME)

        binding.downLoadFromNet.setOnClickListener {
            downloadImageFromWeb(wallUrl.toString(), imageName?.split("-").toString())
        }
        binding.setAsBackground.setOnClickListener {
            setAsBackground(Constants.BackGroundState.backGround)
        }
        binding.setAsLockscreen.setOnClickListener {
            setAsBackground(Constants.BackGroundState.lockScreen)
        }
    }


    private fun downloadImageFromWeb(url: String, filename: String) {
        try {
            val downloadManager =
                context?.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager

            val downloadUri = Uri.parse(url)

            val request = DownloadManager.Request(downloadUri).apply {
                setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI or DownloadManager.Request.NETWORK_MOBILE)
                    .setMimeType("images/jpeg")
                    .setAllowedOverRoaming(false)
                    .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                    .setTitle(filename)
                    .setDestinationInExternalPublicDir(
                        Environment.DIRECTORY_PICTURES,
                        File.separator + filename + ".jpg",
                    )

            }
            downloadManager.enqueue(request)
            Toast.makeText(context, "Downloading...", Toast.LENGTH_LONG).show()

        } catch (e: java.lang.Exception) {
            Toast.makeText(context, "Image Download Failed ${e.message}", Toast.LENGTH_LONG).show()
        }

    }


    private fun setAsBackground(LockOrBackGround: Int) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            try {
                val wallpaperManager = WallpaperManager.getInstance(context)
                val image = activity?.findViewById<ShapeableImageView>(R.id.downloadImageView)
                if (image?.drawable == null) {
                    Toast.makeText(context, "Wait to loading", Toast.LENGTH_LONG).show()
                } else {
                    val bitmap = (image.drawable as BitmapDrawable).bitmap
                    wallpaperManager.setBitmap(bitmap, null, true, LockOrBackGround)
                    Toast.makeText(context, "DONE", Toast.LENGTH_LONG).show()
                }

            } catch (e: IOException) {
                Toast.makeText(context, e.message, Toast.LENGTH_LONG).show()
            }
        }
    }

}

