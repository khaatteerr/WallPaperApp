package com.khater.retromvvm.ui.fragments

import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.graphics.drawable.toDrawable
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.khater.retromvvm.databinding.FragmentDownloadBinding
import com.khater.retromvvm.utils.BlurHashDecoder


class DownloadFragment : Fragment() {



    private lateinit var binding : FragmentDownloadBinding
    private val args : DownloadFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View  {
        binding = FragmentDownloadBinding.inflate(inflater, container, false)

        loadWall( args.imageData[0] )
        bottomSheet()
        addCallBacks()
        return binding.root
    }

    private fun loadWall(url: String) {
        val blurHashAsDrawable = BlurHashDecoder.decode(args.imageData[1])
        Glide.with(this)
            .load(url)
            .centerCrop()
            .placeholder(blurHashAsDrawable?.toDrawable(this.resources))
            .error(blurHashAsDrawable)
            .into(binding.downloadImageView)

        binding.constraintDownload.background = BitmapDrawable(this.resources, blurHashAsDrawable)
    }
    private fun addCallBacks() {
        binding.backButton.setOnClickListener {v->
           findNavController(v).popBackStack()
        }

    }

    private fun bottomSheet() {
        val bottomSheetFragment = BottomSheetFragment(args.imageData[0])
        binding.downloadButton.setOnClickListener {
            bottomSheetFragment.show(requireActivity().supportFragmentManager, "bottomSheetDialog")
        }

    }
}